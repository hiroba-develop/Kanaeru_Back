package com.example.Kanaeru_Back.service.smallGoals;

import com.example.Kanaeru_Back.entity.SmallGoalEntity;
import com.example.Kanaeru_Back.model.ApiSmallGoalsMiddleGoalIdCreatePost200Response;
import com.example.Kanaeru_Back.model.ApiSmallGoalsMiddleGoalIdCreatePostRequest;
import com.example.Kanaeru_Back.repository.SmallGoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service("smallGoalCreateService")
public class CreateService {

    @Autowired
    private SmallGoalRepository smallGoalRepository;

    @Transactional
    public ApiSmallGoalsMiddleGoalIdCreatePost200Response createSmallGoal(
            String middleGoalId, ApiSmallGoalsMiddleGoalIdCreatePostRequest request) {
        ApiSmallGoalsMiddleGoalIdCreatePost200Response response = new ApiSmallGoalsMiddleGoalIdCreatePost200Response();

        try {
            // SMALL_GOAL_IDをUUIDで生成
            String newSmallGoalId = UUID.randomUUID().toString();

            // SMALL_GOALSテーブルにレコードを登録
            SmallGoalEntity smallGoalEntity = new SmallGoalEntity();
            smallGoalEntity.setSmallGoalId(newSmallGoalId);
            smallGoalEntity.setMiddleGoalId(middleGoalId);
            smallGoalEntity.setPosition(request.getPosition());
            smallGoalEntity.setGoalTitle(request.getGoalTitle());
            smallGoalEntity.setGoalDescription(request.getGoalDescription());
            smallGoalEntity.setIsCompleted("0");
            smallGoalEntity.setCompletedAt(null);
            smallGoalEntity.setDelFlg("0");
            smallGoalEntity.setCreatedAt(LocalDateTime.now());
            smallGoalEntity.setUpdatedAt(LocalDateTime.now());
            smallGoalRepository.save(smallGoalEntity);

            response.setResponseStatus(1);
            response.setSmallGoalId(newSmallGoalId);
        } catch (Exception e) {
            response.setResponseStatus(0);
        }

        return response;
    }
}
