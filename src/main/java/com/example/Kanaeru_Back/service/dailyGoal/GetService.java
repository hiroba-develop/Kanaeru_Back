package com.example.Kanaeru_Back.service.dailyGoal;

import com.example.Kanaeru_Back.entity.DailyGoalEntity;
import com.example.Kanaeru_Back.model.ApiDailyGoalsGet200Response;
import com.example.Kanaeru_Back.model.ApiDailyGoalsGet200ResponseDayInner;
import com.example.Kanaeru_Back.model.DailyGoalSchema;
import com.example.Kanaeru_Back.repository.DailyGoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service("dailyGoalGetService")
public class GetService {

    @Autowired
    private DailyGoalRepository dailyGoalRepository;

    public ApiDailyGoalsGet200Response getDailyGoals(String userId, LocalDate startDate, LocalDate endDate) {
        ApiDailyGoalsGet200Response response = new ApiDailyGoalsGet200Response();

        try {
            List<DailyGoalEntity> entities = dailyGoalRepository
                    .findByUserIdAndGoalDateBetweenAndDelFlgOrderBySortOrderAsc(userId, startDate, endDate, "0");

            List<ApiDailyGoalsGet200ResponseDayInner> days = new ArrayList<>();
            LocalDate current = startDate;
            while (!current.isAfter(endDate)) {
                final LocalDate date = current;
                List<DailyGoalSchema> goals = entities.stream()
                        .filter(e -> e.getGoalDate().equals(date))
                        .map(this::toSchema)
                        .collect(Collectors.toList());

                ApiDailyGoalsGet200ResponseDayInner day = new ApiDailyGoalsGet200ResponseDayInner();
                day.setDate(date);
                day.setGoals(goals);
                days.add(day);

                current = current.plusDays(1);
            }

            response.setResponseStatus(1);
            response.setDays(days);
        } catch (Exception e) {
            response.setResponseStatus(0);
        }

        return response;
    }

    private DailyGoalSchema toSchema(DailyGoalEntity entity) {
        DailyGoalSchema schema = new DailyGoalSchema();
        schema.setDailyGoalId(entity.getDailyGoalId());
        schema.setUserId(entity.getUserId());
        schema.setGoalDate(entity.getGoalDate());
        schema.setTitle(entity.getTitle());
        schema.setIsCompleted(entity.getIsCompleted());
        schema.setCompletedAt(entity.getCompletedAt());
        schema.setSource(entity.getSource());
        schema.setMemo(entity.getMemo());
        schema.setDueDate(entity.getDueDate());
        schema.setCategoryGoalId(entity.getCategoryGoalId());
        schema.setPlannedMin(entity.getPlannedMin());
        schema.setActualMin(entity.getActualMin());
        schema.setSortOrder(entity.getSortOrder());
        schema.setCarriedFrom(entity.getCarriedFrom());
        return schema;
    }
}
