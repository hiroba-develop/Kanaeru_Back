package com.example.Kanaeru_Back.service.support.advice;

import com.example.Kanaeru_Back.model.ApiAuthTermsAgreePost200Response;
import com.example.Kanaeru_Back.model.ApiSupportAdviceUpdatePutRequest;
import com.example.Kanaeru_Back.repository.AdviceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * /api/support/advice/update: アドバイス更新サービス
 */
@Service
public class UpdateService {

    private static final Logger logger = LoggerFactory.getLogger(UpdateService.class);

    private static final ZoneId JAPAN_ZONE = ZoneId.of("Asia/Tokyo");

    @Autowired
    private AdviceRepository adviceRepository;

    /**
     * 指定したアドバイスの内容を更新する
     *
     * @param request adviceId と更新後の adviceContent を含むリクエスト
     * @return 処理結果レスポンス（responseStatus: 1=成功, 0=失敗）
     */
    @Transactional
    public ApiAuthTermsAgreePost200Response updateAdvice(ApiSupportAdviceUpdatePutRequest request) {
        ApiAuthTermsAgreePost200Response response = new ApiAuthTermsAgreePost200Response();

        try {
            if (request.getAdviceId() == null) {
                logger.warn("updateAdvice: adviceId が未指定のためスキップ");
                response.setResponseStatus(0);
                return response;
            }
            if (request.getAdviceContent() == null || request.getAdviceContent().isBlank()) {
                logger.warn("updateAdvice: adviceContent が未指定のためスキップ");
                response.setResponseStatus(0);
                return response;
            }

            LocalDateTime now = LocalDateTime.now(JAPAN_ZONE);
            int updatedCount = adviceRepository.updateContent(
                    request.getAdviceId().toString(),
                    request.getAdviceContent(),
                    now);

            response.setResponseStatus(updatedCount > 0 ? 1 : 0);
        } catch (Exception e) {
            logger.error("updateAdvice() でエラーが発生: {}", e.getMessage(), e);
            response.setResponseStatus(0);
        }

        return response;
    }
}
