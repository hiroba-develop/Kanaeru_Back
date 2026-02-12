package com.example.Kanaeru_Back.service.mandalaChart.largeGoals;

import com.example.Kanaeru_Back.entity.LargeGoalEntity;
import com.example.Kanaeru_Back.model.ApiAuthLogoutPost200Response;
import com.example.Kanaeru_Back.model.ApiLargeGoalsChartIdCreatePostRequest;
import com.example.Kanaeru_Back.repository.LargeGoalRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service("largeGoalUpdateService")
public class UpdateService {

    private static final Logger logger = LoggerFactory.getLogger(UpdateService.class);

    @Autowired
    private LargeGoalRepository largeGoalRepository;

    @Transactional
    public ApiAuthLogoutPost200Response updateLargeGoal(
            String largeGoalId, ApiLargeGoalsChartIdCreatePostRequest request) {
        logger.info("大目標更新開始 - largeGoalId: {}", largeGoalId);
        logger.debug("リクエスト内容: {}", request);
        
        ApiAuthLogoutPost200Response response = new ApiAuthLogoutPost200Response();

        try {
            // largeGoalIdで大目標を取得（既存レコードを取得）
            Optional<LargeGoalEntity> largeGoalOptional = 
                    largeGoalRepository.findById(largeGoalId);

            if (largeGoalOptional.isPresent()) {
                LargeGoalEntity largeGoal = largeGoalOptional.get();
                logger.debug("大目標が見つかりました - ID: {}, DEL_FLG: {}", largeGoalId, largeGoal.getDelFlg());
                
                // DEL_FLGが'0'でない場合はエラー
                if (!"0".equals(largeGoal.getDelFlg())) {
                    logger.warn("削除済みの大目標です - ID: {}, DEL_FLG: {}", largeGoalId, largeGoal.getDelFlg());
                    response.setResponseStatus(0);
                    return response;
                }

                // 大目標を更新（既存のエンティティのフィールドを更新）
                if (request.getPosition() != null) {
                    logger.debug("position更新: {} -> {}", largeGoal.getPosition(), request.getPosition());
                    largeGoal.setPosition(request.getPosition());
                }
                if (request.getGoalTitle() != null) {
                    logger.debug("goalTitle更新: {} -> {}", largeGoal.getGoalTitle(), request.getGoalTitle());
                    largeGoal.setGoalTitle(request.getGoalTitle());
                }
                if (request.getGoalDescription() != null) {
                    logger.debug("goalDescription更新");
                    largeGoal.setGoalDescription(request.getGoalDescription());
                }
                if (request.getGoalType() != null) {
                    logger.debug("goalType更新: {} -> {}", largeGoal.getGoalType(), request.getGoalType());
                    largeGoal.setGoalType(request.getGoalType());
                }
                if (request.getTargetYear() != null) {
                    logger.debug("targetYear更新: {} -> {}", largeGoal.getTargetYear(), request.getTargetYear());
                    largeGoal.setTargetYear(request.getTargetYear());
                }
                if (request.getTargetAmount() != null) {
                    logger.debug("targetAmount更新: {} -> {}", largeGoal.getTargetAmount(), request.getTargetAmount());
                    largeGoal.setTargetAmount(request.getTargetAmount());
                }
                largeGoal.setUpdatedAt(LocalDateTime.now());

                // 既存のエンティティを更新（IDが設定されているので更新として扱われる）
                logger.debug("大目標を保存します");
                largeGoalRepository.save(largeGoal);
                logger.info("大目標の更新に成功しました - ID: {}", largeGoalId);

                response.setResponseStatus(1);
            } else {
                // 大目標が見つからない場合
                logger.warn("大目標が見つかりません - ID: {}", largeGoalId);
                response.setResponseStatus(0);
            }
        } catch (Exception e) {
            logger.error("大目標の更新中にエラーが発生しました - ID: {}, エラー: {}", largeGoalId, e.getMessage(), e);
            response.setResponseStatus(0);
        }

        return response;
    }
}
