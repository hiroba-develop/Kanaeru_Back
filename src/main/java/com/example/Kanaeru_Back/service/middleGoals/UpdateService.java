package com.example.Kanaeru_Back.service.middleGoals;

import com.example.Kanaeru_Back.entity.MiddleGoalEntity;
import com.example.Kanaeru_Back.model.ApiAuthLogoutPost200Response;
import com.example.Kanaeru_Back.model.ApiMiddleGoalsMiddleGoalIdUpdatePutRequest;
import com.example.Kanaeru_Back.repository.MiddleGoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service("middleGoalUpdateService")
public class UpdateService {

    @Autowired
    private MiddleGoalRepository middleGoalRepository;

    @Transactional
    public ApiAuthLogoutPost200Response updateMiddleGoal(
            String middleGoalId, ApiMiddleGoalsMiddleGoalIdUpdatePutRequest request) {
        ApiAuthLogoutPost200Response response = new ApiAuthLogoutPost200Response();

        try {
            // middleGoalIdで中目標を取得（既存レコードを取得）
            Optional<MiddleGoalEntity> middleGoalOptional = 
                    middleGoalRepository.findById(middleGoalId);

            if (middleGoalOptional.isPresent()) {
                MiddleGoalEntity middleGoal = middleGoalOptional.get();
                
                // DEL_FLGが'0'でない場合はエラー
                if (!"0".equals(middleGoal.getDelFlg())) {
                    response.setResponseStatus(0);
                    return response;
                }

                // 中目標を更新（既存のエンティティのフィールドを更新）
                if (request.getPosition() != null) {
                    middleGoal.setPosition(request.getPosition());
                }
                if (request.getGoalTitle() != null) {
                    middleGoal.setGoalTitle(request.getGoalTitle());
                }
                if (request.getGoalDescription() != null) {
                    middleGoal.setGoalDescription(request.getGoalDescription());
                }
                if (request.getGoalType() != null) {
                    middleGoal.setGoalType(request.getGoalType());
                }
                if (request.getTargetYear() != null) {
                    middleGoal.setTargetYear(request.getTargetYear());
                }
                if (request.getTargetAmount() != null) {
                    middleGoal.setTargetAmount(request.getTargetAmount());
                }
                middleGoal.setUpdatedAt(LocalDateTime.now());

                // 既存のエンティティを更新（IDが設定されているので更新として扱われる）
                middleGoalRepository.save(middleGoal);

                response.setResponseStatus(1);
            } else {
                // 中目標が見つからない場合
                response.setResponseStatus(0);
            }
        } catch (Exception e) {
            response.setResponseStatus(0);
        }

        return response;
    }
}
