package com.example.Kanaeru_Back.service.dailyGoal;

import com.example.Kanaeru_Back.entity.DailyGoalEntity;
import com.example.Kanaeru_Back.model.ApiDailyGoalsCompleteRequest;
import com.example.Kanaeru_Back.repository.DailyGoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service("dailyGoalCompleteService")
public class CompleteService {

    @Autowired
    private DailyGoalRepository dailyGoalRepository;

    @Transactional
    public int completeDailyGoal(String dailyGoalId, ApiDailyGoalsCompleteRequest request) {
        try {
            Optional<DailyGoalEntity> optional = dailyGoalRepository.findById(dailyGoalId);
            if (optional.isEmpty() || !"0".equals(optional.get().getDelFlg())) {
                return 0;
            }

            DailyGoalEntity entity = optional.get();
            String isCompleted = request.getIsCompleted();
            entity.setIsCompleted(isCompleted);

            if ("1".equals(isCompleted)) {
                entity.setCompletedAt(LocalDateTime.now());
                entity.setActualMin(request.getActualMin());
            } else {
                entity.setCompletedAt(null);
                entity.setActualMin(null);
            }

            entity.setUpdatedAt(LocalDateTime.now());
            dailyGoalRepository.save(entity);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }
}
