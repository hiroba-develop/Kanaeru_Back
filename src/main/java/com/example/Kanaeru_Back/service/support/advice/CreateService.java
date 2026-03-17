package com.example.Kanaeru_Back.service.support.advice;

import com.example.Kanaeru_Back.entity.AdviceEntity;
import com.example.Kanaeru_Back.entity.UserEntity;
import com.example.Kanaeru_Back.model.ApiSupportAdviceCreatePost200Response;
import com.example.Kanaeru_Back.model.ApiSupportAdviceCreatePostRequest;
import com.example.Kanaeru_Back.repository.AdviceRepository;
import com.example.Kanaeru_Back.repository.UserRepository;
import com.example.Kanaeru_Back.service.email.EmailService;
import com.example.Kanaeru_Back.templates.EmailTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class CreateService {

    private static final Logger logger = LoggerFactory.getLogger(CreateService.class);
    private static final ZoneId JAPAN_ZONE = ZoneId.of("Asia/Tokyo");

    @Autowired
    private AdviceRepository adviceRepository;

    // ★ 追加
    @Autowired
    private UserRepository userRepository;

    // ★ 追加
    @Autowired
    private EmailService emailService;

    // ★ 追加
    @Value("${app.frontend.url}")
    private String appUrl;

    @Value("${app.mail.from}")
    private String supportEmail;

    @Transactional
    public ApiSupportAdviceCreatePost200Response createAdvice(
            ApiSupportAdviceCreatePostRequest request, String adminId) {

        ApiSupportAdviceCreatePost200Response response = new ApiSupportAdviceCreatePost200Response();

        try {
            if (request.getUserId() == null || request.getUserId().isBlank()) {
                logger.warn("createAdvice: userId が未指定のためスキップ");
                response.setResponseStatus(0);
                return response;
            }
            if (request.getAdviceContent() == null || request.getAdviceContent().isBlank()) {
                logger.warn("createAdvice: adviceContent が未指定のためスキップ");
                response.setResponseStatus(0);
                return response;
            }

            LocalDateTime now = LocalDateTime.now(JAPAN_ZONE);
            String adviceId = UUID.randomUUID().toString();

            AdviceEntity advice = new AdviceEntity();
            advice.setAdviceId(adviceId);
            advice.setUserId(request.getUserId());
            advice.setAdminId(adminId);
            advice.setContent(request.getAdviceContent());
            advice.setCreatedAt(now);
            advice.setUpdatedAt(now);
            advice.setDelFlg("0");

            adviceRepository.save(advice);

            // ★ アドバイス通知メール送信
            try {
                Optional<UserEntity> recipient = userRepository
                    .findByUserIdAndDelFlg(request.getUserId(), "0");
                Optional<UserEntity> admin = userRepository
                    .findByUserIdAndDelFlg(adminId, "0");

                if (recipient.isPresent() && recipient.get().getEmail() != null) {
                    emailService.sendTemplatedEmail(
                        recipient.get().getEmail(),
                        EmailTemplate.ADVICE_NOTIFICATION,
                        Map.of(
                            "name", recipient.get().getName() != null ? recipient.get().getName() : "",
                            "senderName", admin.map(UserEntity::getName).orElse("メンター"),
                            "appUrl", appUrl,
                            "supportEmail", supportEmail
                        )
                    );
                }
            } catch (Exception emailError) {
                logger.warn("アドバイス通知メール送信でエラー発生（処理は継続）: {}", emailError.getMessage());
            }

            response.setAdviceId(adviceId);
            response.setResponseStatus(1);

        } catch (Exception e) {
            logger.error("createAdvice() でエラーが発生: {}", e.getMessage(), e);
            response.setResponseStatus(0);
        }

        return response;
    }
}