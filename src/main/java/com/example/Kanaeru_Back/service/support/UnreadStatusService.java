package com.example.Kanaeru_Back.service.support;

import com.example.Kanaeru_Back.entity.UserEntity;
import com.example.Kanaeru_Back.model.ApiSupportUnreadStatusGet200Response;
import com.example.Kanaeru_Back.repository.DmMessageRepository;
import com.example.Kanaeru_Back.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * /api/support/unread-status: チャット未読状態取得サービス
 * 一般ユーザーは自分宛の未読有無（hasUnread）、管理者は未読送信者IDリスト（unreadUserIds）を返す
 */
@Service
public class UnreadStatusService {

    private static final Logger logger = LoggerFactory.getLogger(UnreadStatusService.class);

    @Autowired
    private DmMessageRepository dmMessageRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * 未読状態を取得する
     *
     * @param userId リクエストユーザーID
     * @return 未読状態レスポンス
     */
    @Transactional(readOnly = true)
    public ApiSupportUnreadStatusGet200Response getUnreadStatus(String userId) {
        ApiSupportUnreadStatusGet200Response response = new ApiSupportUnreadStatusGet200Response();

        try {
            Optional<UserEntity> userOpt = userRepository.findByUserIdAndDelFlg(userId, "0");
            if (userOpt.isEmpty()) {
                logger.warn("getUnreadStatus: userId={} のユーザーが見つからないためスキップ", userId);
                response.setResponseStatus(0);
                return response;
            }

            String role = userOpt.get().getRole();
            boolean isAdmin = "1".equals(role) || "2".equals(role);

            if (isAdmin) {
                List<String> unreadSenderIds = dmMessageRepository.findUnreadSenderIdsByRecipientId(userId);
                response.setUnreadUserIds(unreadSenderIds);
            } else {
                long unreadCount = dmMessageRepository.countUnreadByRecipientId(userId);
                response.setHasUnread(unreadCount > 0);
            }

            response.setResponseStatus(1);
        } catch (Exception e) {
            logger.error("getUnreadStatus() でエラーが発生: {}", e.getMessage(), e);
            response.setResponseStatus(0);
        }

        return response;
    }
}
