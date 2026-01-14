package com.example.Kanaeru_Back.service.largeGoals;

import com.example.Kanaeru_Back.entity.LargeGoalEntity;
import com.example.Kanaeru_Back.model.ApiAuthLogoutPost200Response;
import com.example.Kanaeru_Back.model.ApiLargeGoalsChartIdCreatePostRequest;
import com.example.Kanaeru_Back.repository.LargeGoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service("largeGoalUpdateService")
public class UpdateService {

    @Autowired
    private LargeGoalRepository largeGoalRepository;

    @Transactional
    public ApiAuthLogoutPost200Response updateLargeGoal(
            String largeGoalId, ApiLargeGoalsChartIdCreatePostRequest request) {
        ApiAuthLogoutPost200Response response = new ApiAuthLogoutPost200Response();

        try {
            // largeGoalIdで大目標を取得（既存レコードを取得）
            Optional<LargeGoalEntity> largeGoalOptional = 
                    largeGoalRepository.findById(largeGoalId);

            if (largeGoalOptional.isPresent()) {
                LargeGoalEntity largeGoal = largeGoalOptional.get();
                
                // DEL_FLGが'0'でない場合はエラー
                if (!"0".equals(largeGoal.getDelFlg())) {
                    response.setResponseStatus(0);
                    return response;
                }

                // 大目標を更新（既存のエンティティのフィールドを更新）
                if (request.getPosition() != null) {
                    largeGoal.setPosition(request.getPosition());
                }
                if (request.getGoalTitle() != null) {
                    largeGoal.setGoalTitle(request.getGoalTitle());
                }
                if (request.getGoalDescription() != null) {
                    largeGoal.setGoalDescription(request.getGoalDescription());
                }
                if (request.getGoalType() != null) {
                    largeGoal.setGoalType(request.getGoalType());
                }
                if (request.getTargetYear() != null) {
                    largeGoal.setTargetYear(request.getTargetYear());
                }
                if (request.getTargetAmount() != null) {
                    largeGoal.setTargetAmount(request.getTargetAmount());
                }
                largeGoal.setUpdatedAt(LocalDateTime.now());

                // 既存のエンティティを更新（IDが設定されているので更新として扱われる）
                largeGoalRepository.save(largeGoal);

                response.setResponseStatus(1);
            } else {
                // 大目標が見つからない場合
                response.setResponseStatus(0);
            }
        } catch (Exception e) {
            response.setResponseStatus(0);
        }

        return response;
    }
}
