package com.example.Kanaeru_Back.service.dailyGoal;

import com.example.Kanaeru_Back.entity.DailyGoalEntity;
import com.example.Kanaeru_Back.model.ApiDailyGoalsCreatePost200Response;
import com.example.Kanaeru_Back.model.ApiDailyGoalsCreatePostRequest;
import com.example.Kanaeru_Back.repository.DailyGoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service("dailyGoalCreateService")
public class CreateService {

    @Autowired
    private DailyGoalRepository dailyGoalRepository;

    @Transactional
    public ApiDailyGoalsCreatePost200Response createDailyGoal(ApiDailyGoalsCreatePostRequest request) {
        ApiDailyGoalsCreatePost200Response response = new ApiDailyGoalsCreatePost200Response();

        try {
            String newId = UUID.randomUUID().toString();

            DailyGoalEntity entity = new DailyGoalEntity();
            entity.setDailyGoalId(newId);
            entity.setUserId(request.getUserId());
            entity.setGoalDate(request.getGoalDate());
            entity.setTitle(request.getTitle());
            entity.setIsCompleted("0");
            entity.setCompletedAt(null);
            entity.setSource(request.getSource() != null ? request.getSource() : "1");
            entity.setMemo(request.getMemo());
            entity.setDueDate(request.getDueDate());
            entity.setCategoryGoalId(request.getCategoryGoalId());
            entity.setPlannedMin(request.getPlannedMin());
            entity.setActualMin(null);
            entity.setSortOrder(request.getSortOrder() != null ? request.getSortOrder() : 1);
            entity.setCarriedFrom(null);
            entity.setDelFlg("0");
            entity.setCreatedAt(LocalDateTime.now());
            entity.setUpdatedAt(LocalDateTime.now());

            dailyGoalRepository.save(entity);

            response.setResponseStatus(1);
            response.setDailyGoalId(newId);
        } catch (Exception e) {
            response.setResponseStatus(0);
        }

        return response;
    }
}
