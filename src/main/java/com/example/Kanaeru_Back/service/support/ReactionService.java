package com.example.Kanaeru_Back.service.support;

import com.example.Kanaeru_Back.model.ApiAuthLogoutPost200Response;
import com.example.Kanaeru_Back.model.ApiAuthTermsAgreePost200Response;
import com.example.Kanaeru_Back.model.ApiSupportReactionCreatePutRequest;
import com.example.Kanaeru_Back.repository.DmMessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * /api/support/reaction: チャットメッセージリアクション登録・削除サービス
 */
@Service
public class ReactionService {

    private static final Logger logger = LoggerFactory.getLogger(ReactionService.class);

    private static final ZoneId JAPAN_ZONE = ZoneId.of("Asia/Tokyo");

    @Autowired
    private DmMessageRepository dmMessageRepository;

    /**
     * 指定メッセージのリアクションフラグを'1'に更新する
     *
     * @param request messageSeq と reactionFlag を含むリクエスト
     * @return 処理結果レスポンス
     */
    @Transactional
    public ApiAuthTermsAgreePost200Response createReaction(ApiSupportReactionCreatePutRequest request) {
        ApiAuthTermsAgreePost200Response response = new ApiAuthTermsAgreePost200Response();
        try {
            if (request.getMessageSeq() == null) {
                response.setResponseStatus(0);
                return response;
            }

            LocalDateTime now = LocalDateTime.now(JAPAN_ZONE);
            int updated = dmMessageRepository.updateReactionFlag(
                    request.getMessageSeq().longValue(),
                    "1",
                    now);

            response.setResponseStatus(updated > 0 ? 1 : 0);
        } catch (Exception e) {
            logger.error("createReaction() でエラーが発生: {}", e.getMessage(), e);
            response.setResponseStatus(0);
        }
        return response;
    }

    /**
     * 指定メッセージのリアクションフラグを'0'に更新する（リアクション削除）
     *
     * @param messageSeq 対象メッセージシーケンス番号
     * @return 処理結果レスポンス
     */
    @Transactional
    public ApiAuthLogoutPost200Response deleteReaction(Integer messageSeq) {
        ApiAuthLogoutPost200Response response = new ApiAuthLogoutPost200Response();
        try {
            if (messageSeq == null) {
                response.setResponseStatus(0);
                return response;
            }

            LocalDateTime now = LocalDateTime.now(JAPAN_ZONE);
            int updated = dmMessageRepository.updateReactionFlag(
                    messageSeq.longValue(),
                    "0",
                    now);

            response.setResponseStatus(updated > 0 ? 1 : 0);
        } catch (Exception e) {
            logger.error("deleteReaction() でエラーが発生: {}", e.getMessage(), e);
            response.setResponseStatus(0);
        }
        return response;
    }
}
