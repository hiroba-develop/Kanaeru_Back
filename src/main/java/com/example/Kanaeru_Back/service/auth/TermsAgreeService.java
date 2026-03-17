package com.example.Kanaeru_Back.service.auth;

import com.example.Kanaeru_Back.entity.UserEntity;
import com.example.Kanaeru_Back.model.ApiAuthTermsAgreePost200Response;
import com.example.Kanaeru_Back.model.ApiAuthTermsAgreePostRequest;
import com.example.Kanaeru_Back.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/**
 * /api/auth/termsAgree: 利用規約同意日時更新サービス
 */
@Service
public class TermsAgreeService {

    private static final Logger logger = LoggerFactory.getLogger(TermsAgreeService.class);

    @Autowired
    private UserRepository userRepository;

    /**
     * 対象ユーザーの TERMS_AGREED_AT を更新する
     *
     * @param request userId と termsAgreedAt を含むリクエスト
     * @return 処理結果レスポンス（responseStatus: 1=成功, 0=失敗）
     */
    @Transactional
    public ApiAuthTermsAgreePost200Response agreeToTerms(ApiAuthTermsAgreePostRequest request) {
        ApiAuthTermsAgreePost200Response response = new ApiAuthTermsAgreePost200Response();

        try {
            if (request.getUserId() == null || request.getUserId().isBlank()) {
                logger.warn("agreeToTerms: userId が未指定のためスキップ");
                response.setResponseStatus(0);
                return response;
            }

            Optional<UserEntity> userOpt = userRepository.findByUserIdAndDelFlg(request.getUserId(), "0");
            if (userOpt.isEmpty()) {
                logger.warn("agreeToTerms: 対象ユーザーが見つかりません userId={}", request.getUserId());
                response.setResponseStatus(0);
                return response;
            }

            LocalDateTime agreedAt = parseTermsAgreedAt(request.getTermsAgreedAt());

            UserEntity user = userOpt.get();
            user.setTermsAgreedAt(agreedAt);
            user.setUpdatedAt(LocalDateTime.now());
            userRepository.save(user);

            logger.info("agreeToTerms: 利用規約同意日時を更新しました userId={}", request.getUserId());
            response.setResponseStatus(1);

        } catch (Exception e) {
            logger.error("agreeToTerms() でエラーが発生: {}", e.getMessage(), e);
            response.setResponseStatus(0);
        }

        return response;
    }

    /**
     * 文字列の日時を LocalDateTime に変換する
     * null または空文字の場合は現在日時を返す
     */
    private LocalDateTime parseTermsAgreedAt(String termsAgreedAt) {
        if (termsAgreedAt == null || termsAgreedAt.isBlank()) {
            return LocalDateTime.now();
        }
        try {
            return LocalDateTime.parse(termsAgreedAt, DateTimeFormatter.ISO_DATE_TIME);
        } catch (Exception e) {
            logger.warn("termsAgreedAt のパースに失敗しました。現在日時を使用します: {}", termsAgreedAt);
            return LocalDateTime.now();
        }
    }
}
