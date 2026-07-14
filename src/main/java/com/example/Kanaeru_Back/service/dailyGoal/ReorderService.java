package com.example.Kanaeru_Back.service.dailyGoal;

import com.example.Kanaeru_Back.entity.DailyGoalEntity;
import com.example.Kanaeru_Back.model.ApiDailyGoalsReorderRequest;
import com.example.Kanaeru_Back.repository.DailyGoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service("dailyGoalReorderService")
public class ReorderService {

    @Autowired
    private DailyGoalRepository dailyGoalRepository;

    @Transactional
    public int reorderDailyGoal(String dailyGoalId, ApiDailyGoalsReorderRequest request) {
        try {
            Optional<DailyGoalEntity> optional = dailyGoalRepository.findById(dailyGoalId);
            if (optional.isEmpty() || !"0".equals(optional.get().getDelFlg())) {
                return 0;
            }

            DailyGoalEntity entity = optional.get();
            if (request.getSortOrder() == null) return 0;

            entity.setSortOrder(request.getSortOrder());
            entity.setUpdatedAt(LocalDateTime.now());
            dailyGoalRepository.save(entity);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }
}
