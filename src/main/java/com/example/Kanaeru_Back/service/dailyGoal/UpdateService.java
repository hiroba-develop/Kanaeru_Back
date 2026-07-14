package com.example.Kanaeru_Back.service.dailyGoal;

import com.example.Kanaeru_Back.entity.DailyGoalEntity;
import com.example.Kanaeru_Back.model.ApiDailyGoalsUpdatePutRequest;
import com.example.Kanaeru_Back.repository.DailyGoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service("dailyGoalUpdateService")
public class UpdateService {

    @Autowired
    private DailyGoalRepository dailyGoalRepository;

    @Transactional
    public int updateDailyGoal(String dailyGoalId, ApiDailyGoalsUpdatePutRequest request) {
        try {
            Optional<DailyGoalEntity> optional = dailyGoalRepository.findById(dailyGoalId);
            if (optional.isEmpty() || !"0".equals(optional.get().getDelFlg())) {
                return 0;
            }

            DailyGoalEntity entity = optional.get();
            if (request.getTitle() != null) entity.setTitle(request.getTitle());
            if (request.getGoalDate() != null) entity.setGoalDate(request.getGoalDate());
            entity.setMemo(request.getMemo());
            entity.setDueDate(request.getDueDate());
            entity.setCategoryGoalId(request.getCategoryGoalId());
            entity.setPlannedMin(request.getPlannedMin());
            if (request.getSortOrder() != null) entity.setSortOrder(request.getSortOrder());
            entity.setUpdatedAt(LocalDateTime.now());

            dailyGoalRepository.save(entity);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }
}
