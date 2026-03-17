package com.example.Kanaeru_Back.service.support;

import org.springframework.beans.factory.annotation.Value;
import com.example.Kanaeru_Back.entity.AdviceEntity;
import com.example.Kanaeru_Back.entity.DmMessageEntity;
import com.example.Kanaeru_Back.entity.SettingEntity;
import com.example.Kanaeru_Back.entity.UserEntity;
import com.example.Kanaeru_Back.model.AdviceSchema;
import com.example.Kanaeru_Back.model.ApiSupportGet200Response;
import com.example.Kanaeru_Back.model.ApiSupportSendPost200Response;
import com.example.Kanaeru_Back.model.ApiSupportSendPostRequest;
import com.example.Kanaeru_Back.model.DmMessagesSchema;
import com.example.Kanaeru_Back.repository.AdviceRepository;
import com.example.Kanaeru_Back.repository.DmMessageRepository;
import com.example.Kanaeru_Back.repository.SettingRepository;
import com.example.Kanaeru_Back.repository.UserRepository;
import com.example.Kanaeru_Back.service.email.EmailService;
import com.example.Kanaeru_Back.templates.EmailTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.Map;

/**
 * サポート機能（DM機能）に関するビジネスロジックを提供するサービスクラス。
 */
@Service
public class SupportService {

    private static final Logger logger = LoggerFactory.getLogger(SupportService.class);

    @Autowired
    private DmMessageRepository dmMessageRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SettingRepository settingRepository;

    @Autowired
    private AdviceRepository adviceRepository;

    @Autowired
    private SseConnectionManager sseConnectionManager;

    @Autowired
    private SlackNotificationService slackNotificationService;

    @Autowired
    private EmailService emailService;

    @Value("${app.frontend.url}")
    private String appUrl;

    @Value("${app.mail.from}")
    private String supportEmail;

    private static final ZoneId JAPAN_ZONE = ZoneId.of("Asia/Tokyo");

    private static final long READ_EVENT_DELAY_MS = 500;

    /**
     * サポート画面の初期表示データを取得
     *
     * @param userId     ログインユーザーID
     * @param selectedId 選択されたユーザーID
     * @return ApiSupportGet200Response
     */
    @Transactional(readOnly = true)
    public ApiSupportGet200Response getSupportData(String userId, String selectedId) {
        try {
            List<DmMessageEntity> messages;
            Long latestMessageSeq;

            Optional<UserEntity> currentUser = userRepository.findByUserIdAndDelFlg(userId, "0");
            Optional<UserEntity> selectedUser = userRepository.findByUserIdAndDelFlg(selectedId, "0");

            boolean isAdminViewingIndividual = false;
            if (currentUser.isPresent() && selectedUser.isPresent()) {
                String userRole = currentUser.get().getRole();
                String selectedRole = selectedUser.get().getRole();
                boolean currentIsAdmin = "1".equals(userRole) || "2".equals(userRole);
                boolean selectedIsNonAdmin = !("1".equals(selectedRole) || "2".equals(selectedRole));

                if (currentIsAdmin && selectedIsNonAdmin) {
                    isAdminViewingIndividual = true;
                }
            }

            if (isAdminViewingIndividual) {
                messages = dmMessageRepository.findMessagesByUserId(selectedId);
                latestMessageSeq = dmMessageRepository.findLatestMessageSeqByUserId(selectedId);
            } else if (userId != null && userId.equals(selectedId)) {
                messages = dmMessageRepository.findMessagesByUserId(userId);
                latestMessageSeq = dmMessageRepository.findLatestMessageSeqByUserId(userId);
            } else {
                messages = dmMessageRepository.findMessagesBetweenUsers(userId, selectedId);
                latestMessageSeq = dmMessageRepository.findLatestMessageSeqBetweenUsers(userId, selectedId);
            }

            List<DmMessagesSchema> dmMessagesSchemaList = new ArrayList<>();
            for (DmMessageEntity message : messages) {
                dmMessagesSchemaList.add(convertToDmMessagesSchema(message));
            }

            String adviceTargetUserId = null;
            if (isAdminViewingIndividual) {
                adviceTargetUserId = selectedId;
            } else if (userId != null && userId.equals(selectedId) && currentUser.isPresent()) {
                String userRole = currentUser.get().getRole();
                boolean currentIsNonAdmin = !("1".equals(userRole) || "2".equals(userRole));
                if (currentIsNonAdmin) {
                    adviceTargetUserId = userId;
                }
            }

            List<AdviceSchema> adviceSchemaList = new ArrayList<>();
            if (adviceTargetUserId != null) {
                List<AdviceEntity> advices = adviceRepository
                    .findByUserId(adviceTargetUserId);
                for (AdviceEntity advice : advices) {
                    adviceSchemaList.add(convertToAdviceSchema(advice));
                }
            }

            ApiSupportGet200Response response = new ApiSupportGet200Response();
            response.setResponseStatus(1);
            response.setLastMessageSeq(latestMessageSeq != null ? latestMessageSeq.intValue() : null);
            response.setDmMessagesSchemaList(dmMessagesSchemaList);
            response.setAdviceSchema(adviceSchemaList);
            return response;

        } catch (Exception e) {
            logger.error("getSupportData() でエラーが発生: {}", e.getMessage(), e);
            ApiSupportGet200Response response = new ApiSupportGet200Response();
            response.setResponseStatus(0);
            response.setLastMessageSeq(null);
            response.setDmMessagesSchemaList(new ArrayList<>());
            return response;
        }
    }

    /**
     * メッセージ送信処理
     *
     * @param request メッセージ送信リクエスト
     * @return ApiSupportSendPost200Response
     */
    @Transactional
    public ApiSupportSendPost200Response sendMessage(ApiSupportSendPostRequest request) {
        try {
            String senderId = request.getSenderId();
            String recipientId = request.getRecipientId();
            boolean isIndividualUserFirstMessage = false;

            if (senderId != null && (senderId.equals(recipientId) ||
                    "0".equals(recipientId) || recipientId == null || recipientId.trim().isEmpty())) {

                Optional<UserEntity> senderUser = userRepository.findByUserIdAndDelFlg(senderId, "0");
                if (senderUser.isPresent()) {
                    String senderRole = senderUser.get().getRole();

                    if ("3".equals(senderRole) || "4".equals(senderRole) || "0".equals(senderRole)) {
                        // 一般ユーザーはRECIPIENT_IDをnullにセット（管理者全員が見られる）
                        recipientId = null;
                        isIndividualUserFirstMessage = true;
                    }
                } else {
                    ApiSupportSendPost200Response response = new ApiSupportSendPost200Response();
                    response.setResponseStatus(0);
                    response.setLastMessageSeq(null);
                    return response;
                }
            }

            DmMessageEntity newMessage = new DmMessageEntity();
            newMessage.setSenderId(senderId);
            newMessage.setRecipientId(recipientId);
            newMessage.setContent(request.getContent());

            LocalDateTime now = LocalDateTime.now(JAPAN_ZONE);
            newMessage.setCreatedAt(now);
            newMessage.setUpdatedAt(now);

            dmMessageRepository.save(newMessage);

            DmMessagesSchema responseSchema = convertToDmMessagesSchema(newMessage);

            Long latestMessageSeq = dmMessageRepository.findLatestMessageSeqBetweenUsers(senderId, recipientId);

            ApiSupportSendPost200Response response = new ApiSupportSendPost200Response();
            response.setResponseStatus(1);
            response.setLastMessageSeq(latestMessageSeq != null ? latestMessageSeq.intValue() : null);
            response.setDmMessageSchema(responseSchema);

            sseConnectionManager.sendMessageToUser(senderId, responseSchema);

            try {
                Optional<UserEntity> senderUser = userRepository.findByUserIdAndDelFlg(senderId, "0");
                if (senderUser.isPresent()) {
                    String senderRole = senderUser.get().getRole();
                    boolean isSenderNonAdmin = !("1".equals(senderRole) || "2".equals(senderRole));
            
                    if (isSenderNonAdmin) {
                        String senderName = getSenderName(senderId);
                        slackNotificationService.sendMessageNotificationToAllAdmins(senderId, senderName, request.getContent());
                    }
                }
            } catch (Exception slackError) {
                logger.warn("Slack通知送信でエラー発生（処理は継続）: {}", slackError.getMessage());
            }
            try {
                Optional<UserEntity> senderUser2 = userRepository.findByUserIdAndDelFlg(senderId, "0");
                if (senderUser2.isPresent()) {
                    String senderRole = senderUser2.get().getRole();
                    boolean isSenderAdmin = "1".equals(senderRole) || "2".equals(senderRole);
            
                    if (isSenderAdmin && recipientId != null) {
                        int unreadCount = dmMessageRepository
                            .countByRecipientIdAndSenderIdAndReadAtIsNull(recipientId, senderId);
            
                        if (unreadCount == 1) {
                            Optional<UserEntity> recipientUser = userRepository
                                .findByUserIdAndDelFlg(recipientId, "0");
                            if (recipientUser.isPresent() && recipientUser.get().getEmail() != null) {
                                emailService.sendTemplatedEmail(
                                    recipientUser.get().getEmail(),
                                    EmailTemplate.CHAT_NOTIFICATION,
                                    Map.of(
                                        "name", recipientUser.get().getName() != null ? recipientUser.get().getName() : "",
                                        "senderName", getSenderName(senderId),
                                        "appUrl", appUrl,
                                        "supportEmail", supportEmail
                                    )
                                );
                            }
                        }
                    }
                }
            } catch (Exception emailError) {
                logger.warn("チャット通知メール送信でエラー発生（処理は継続）: {}", emailError.getMessage());
            }

            return response;

        } catch (Exception e) {
            logger.error("sendMessage() でエラーが発生: {}", e.getMessage(), e);
            ApiSupportSendPost200Response response = new ApiSupportSendPost200Response();
            response.setResponseStatus(0);
            response.setLastMessageSeq(null);
            return response;
        }
    }

    /**
     * メッセージ既読処理
     * 指定されたmessageSeq以前の未読メッセージに既読日時を設定する
     *
     * @param readByUserId    既読を付けるユーザーID（受信者）
     * @param messageSenderId メッセージの送信者ID
     * @param messageSeqStr   既読対象のメッセージシーケンス番号（文字列）
     * @return 処理結果（1: 成功、0: 失敗）
     */
    @Transactional
    public Integer markMessagesAsRead(String readByUserId, String messageSenderId, String messageSeqStr) {
        try {
            if (messageSeqStr == null || readByUserId == null) {
                return 0;
            }

            Long messageSeq = Long.parseLong(messageSeqStr);
            LocalDateTime readAt = LocalDateTime.now(JAPAN_ZONE);

            int updatedCount = dmMessageRepository.markMessagesAsReadBeforeSeq(
                    readByUserId, messageSenderId, messageSeq, readAt);

            if (updatedCount > 0) {
                handleReadAtUpdate(readByUserId, messageSenderId, messageSeq, readAt, updatedCount);
            }

            return 1;

        } catch (NumberFormatException e) {
            return 0;
        } catch (Exception e) {
            logger.error("markMessagesAsRead() でエラーが発生: {}", e.getMessage(), e);
            return 0;
        }
    }

    /**
     * readAt更新時の処理（SSE既読通知を非同期送信）
     */
    private void handleReadAtUpdate(String userId1, String userId2, Long messageSeq, LocalDateTime readAt,
            int updatedCount) {
        try {
            CompletableFuture.delayedExecutor(READ_EVENT_DELAY_MS, TimeUnit.MILLISECONDS)
                    .execute(() -> sendReadEventsAsync(userId1, userId2, messageSeq, readAt, updatedCount));
        } catch (Exception e) {
            logger.error("handleReadAtUpdate() でエラーが発生: {}", e.getMessage(), e);
        }
    }

    /**
     * 既読イベントを非同期で送信する
     */
    @Transactional(readOnly = true)
    private void sendReadEventsAsync(String userId1, String userId2, Long messageSeq, LocalDateTime readAt,
            int updatedCount) {
        try {
            List<DmMessageEntity> readMessages = dmMessageRepository.findReadMessagesBySeq(userId1, userId2, messageSeq);

            Set<String> notifiedSenders = new HashSet<>();
            for (DmMessageEntity message : readMessages) {
                String senderId = message.getSenderId();
                String actualReadAt = message.getReadAt() != null
                        ? message.getReadAt().toString()
                        : readAt.toString();

                sseConnectionManager.sendDetailedReadEventToUser(
                        senderId,
                        message.getMessageSeq().toString(),
                        message.getSenderId(),
                        message.getRecipientId(),
                        actualReadAt);

                notifiedSenders.add(senderId);
            }

            if (!notifiedSenders.contains(userId1)) {
                sseConnectionManager.sendReadEventToUser(userId1, messageSeq.toString());
            }

            if (userId1.equals(userId2)) {
                Optional<SettingEntity> setting = settingRepository.findByUserId(userId1);
                if (setting.isPresent()) {
                    String adminId = setting.get().getAdminId();
                    if (adminId != null && !adminId.equals(userId1) && !notifiedSenders.contains(adminId)) {
                        for (DmMessageEntity message : readMessages) {
                            String actualReadAt = message.getReadAt() != null
                                    ? message.getReadAt().toString()
                                    : readAt.toString();
                            sseConnectionManager.sendDetailedReadEventToUser(
                                    adminId,
                                    message.getMessageSeq().toString(),
                                    message.getSenderId(),
                                    message.getRecipientId(),
                                    actualReadAt);
                        }
                    }
                }
            }

        } catch (Exception e) {
            logger.error("sendReadEventsAsync() でエラーが発生: {}", e.getMessage(), e);
        }
    }

    /**
     * DmMessageEntityをDmMessagesSchemaに変換する
     */
    private DmMessagesSchema convertToDmMessagesSchema(DmMessageEntity message) {
        DmMessagesSchema schema = new DmMessagesSchema();

        if (message.getMessageSeq() != null) {
            schema.setMessageSeq(message.getMessageSeq().intValue());
        }
        schema.setSenderId(message.getSenderId());
        schema.setRecipientId(message.getRecipientId());
        schema.setContent(message.getContent());

        Optional<UserEntity> sender = userRepository.findByUserIdAndDelFlg(message.getSenderId(), "0");
        schema.setSenderName(sender.map(UserEntity::getName).orElse("不明なユーザー"));

        Optional<UserEntity> recipient = userRepository.findByUserIdAndDelFlg(message.getRecipientId(), "0");
        schema.setRecipientName(recipient.map(UserEntity::getName).orElse("不明なユーザー"));

        schema.setReadAt(message.getReadAt());
        schema.setCreatedAt(message.getCreatedAt());
        schema.setUpdatedAt(message.getUpdatedAt());

        return schema;
    }

    /**
     * AdviceEntityをAdviceSchemaに変換する
     */
    private AdviceSchema convertToAdviceSchema(AdviceEntity advice) {
        AdviceSchema schema = new AdviceSchema();
        schema.setAdviceId(advice.getAdviceId());
        schema.setUserId(advice.getUserId());
        schema.setAdminId(advice.getAdminId());
        Optional<UserEntity> admin = userRepository.findByUserIdAndDelFlg(advice.getAdminId(), "0");
        schema.setAdminName(admin.map(UserEntity::getName).orElse("不明な管理者"));
        schema.setAdviceContent(advice.getContent());
        schema.setCreatedAt(advice.getCreatedAt());
        schema.setUpdatedAt(advice.getUpdatedAt());
        return schema;
    }

    /**
     * ユーザーIDから送信者名を取得する
     */
    private String getSenderName(String userId) {
        try {
            if (userId == null) {
                return "Unknown User";
            }
            return userRepository.findById(userId)
                    .map(user -> user.getName() != null ? user.getName() : "Unknown User")
                    .orElse("Unknown User");
        } catch (Exception e) {
            logger.error("送信者名取得エラー for userId: {}", userId, e);
            return "Unknown User";
        }
    }
}
