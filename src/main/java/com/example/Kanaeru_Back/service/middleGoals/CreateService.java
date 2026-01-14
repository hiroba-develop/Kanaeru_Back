package com.example.Kanaeru_Back.service.middleGoals;

import com.example.Kanaeru_Back.entity.MiddleGoalEntity;
import com.example.Kanaeru_Back.model.ApiMiddleGoalsLargeGoalIdCreatePost200Response;
import com.example.Kanaeru_Back.model.ApiMiddleGoalsLargeGoalIdCreatePostRequest;
import com.example.Kanaeru_Back.repository.MiddleGoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service("middleGoalCreateService")
public class CreateService {

    @Autowired
    private MiddleGoalRepository middleGoalRepository;

    @Transactional
    public ApiMiddleGoalsLargeGoalIdCreatePost200Response createMiddleGoal(
            String largeGoalId, ApiMiddleGoalsLargeGoalIdCreatePostRequest request) {
        ApiMiddleGoalsLargeGoalIdCreatePost200Response response = new ApiMiddleGoalsLargeGoalIdCreatePost200Response();

        try {
            // MIDDLE_GOAL_IDをUUIDで生成
            String newMiddleGoalId = UUID.randomUUID().toString();

            // MIDDLE_GOALSテーブルにレコードを登録
            MiddleGoalEntity middleGoalEntity = new MiddleGoalEntity();
            middleGoalEntity.setMiddleGoalId(newMiddleGoalId);
            middleGoalEntity.setLargeGoalId(largeGoalId);
            middleGoalEntity.setPosition(request.getPosition());
            middleGoalEntity.setGoalTitle(request.getGoalTitle());
            middleGoalEntity.setGoalDescription(request.getGoalDescription());
            middleGoalEntity.setGoalType(request.getGoalType());
            middleGoalEntity.setTargetYear(request.getTargetYear());
            middleGoalEntity.setTargetAmount(request.getTargetAmount());
            middleGoalEntity.setProgress(0);
            middleGoalEntity.setDelFlg("0");
            middleGoalEntity.setCreatedAt(LocalDateTime.now());
            middleGoalEntity.setUpdatedAt(LocalDateTime.now());
            middleGoalRepository.save(middleGoalEntity);

            response.setResponseStatus(1);
            response.setMiddleGoalId(newMiddleGoalId);
        } catch (Exception e) {
            response.setResponseStatus(0);
        }

        return response;
    }
}
