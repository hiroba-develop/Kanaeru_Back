package com.example.Kanaeru_Back.service.smallGoals;

import com.example.Kanaeru_Back.entity.SmallGoalEntity;
import com.example.Kanaeru_Back.model.ApiAuthLogoutPost200Response;
import com.example.Kanaeru_Back.model.ApiSmallGoalsMiddleGoalIdCreatePostRequest;
import com.example.Kanaeru_Back.repository.SmallGoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service("smallGoalUpdateService")
public class UpdateService {

    @Autowired
    private SmallGoalRepository smallGoalRepository;

    @Transactional
    public ApiAuthLogoutPost200Response updateSmallGoal(
            String smallGoalId, ApiSmallGoalsMiddleGoalIdCreatePostRequest request) {
        ApiAuthLogoutPost200Response response = new ApiAuthLogoutPost200Response();

        try {
            // smallGoalIdで小目標を取得（既存レコードを取得）
            Optional<SmallGoalEntity> smallGoalOptional = 
                    smallGoalRepository.findById(smallGoalId);

            if (smallGoalOptional.isPresent()) {
                SmallGoalEntity smallGoal = smallGoalOptional.get();
                
                // DEL_FLGが'0'でない場合はエラー
                if (!"0".equals(smallGoal.getDelFlg())) {
                    response.setResponseStatus(0);
                    return response;
                }

                // 小目標を更新（既存のエンティティのフィールドを更新）
                if (request.getPosition() != null) {
                    smallGoal.setPosition(request.getPosition());
                }
                if (request.getGoalTitle() != null) {
                    smallGoal.setGoalTitle(request.getGoalTitle());
                }
                if (request.getGoalDescription() != null) {
                    smallGoal.setGoalDescription(request.getGoalDescription());
                }
                smallGoal.setUpdatedAt(LocalDateTime.now());

                // 既存のエンティティを更新（IDが設定されているので更新として扱われる）
                smallGoalRepository.save(smallGoal);

                response.setResponseStatus(1);
            } else {
                // 小目標が見つからない場合
                response.setResponseStatus(0);
            }
        } catch (Exception e) {
            response.setResponseStatus(0);
        }

        return response;
    }
}
