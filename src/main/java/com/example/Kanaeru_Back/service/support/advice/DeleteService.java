package com.example.Kanaeru_Back.service.support.advice;

import com.example.Kanaeru_Back.model.ApiAuthLogoutPost200Response;
import com.example.Kanaeru_Back.repository.AdviceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * /api/support/advice/delete: アドバイス論理削除サービス
 */
@Service
public class DeleteService {

    private static final Logger logger = LoggerFactory.getLogger(DeleteService.class);

    private static final ZoneId JAPAN_ZONE = ZoneId.of("Asia/Tokyo");

    @Autowired
    private AdviceRepository adviceRepository;

    /**
     * 指定したアドバイスを論理削除する（DEL_FLG = '1' に更新）
     *
     * @param adviceId 削除対象のアドバイスID
     * @return 処理結果レスポンス（responseStatus: 1=成功, 0=失敗）
     */
    @Transactional
    public ApiAuthLogoutPost200Response deleteAdvice(String adviceId) {
        ApiAuthLogoutPost200Response response = new ApiAuthLogoutPost200Response();

        try {
            if (adviceId == null || adviceId.isBlank()) {
                logger.warn("deleteAdvice: adviceId が未指定のためスキップ");
                response.setResponseStatus(0);
                return response;
            }

            LocalDateTime now = LocalDateTime.now(JAPAN_ZONE);
            int updatedCount = adviceRepository.logicalDelete(adviceId, now);

            response.setResponseStatus(updatedCount > 0 ? 1 : 0);
        } catch (Exception e) {
            logger.error("deleteAdvice() でエラーが発生: {}", e.getMessage(), e);
            response.setResponseStatus(0);
        }

        return response;
    }
}
