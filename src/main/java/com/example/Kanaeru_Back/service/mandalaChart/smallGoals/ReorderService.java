package com.example.Kanaeru_Back.service.mandalaChart.smallGoals;

import com.example.Kanaeru_Back.entity.SmallGoalEntity;
import com.example.Kanaeru_Back.model.ApiAuthLogoutPost200Response;
import com.example.Kanaeru_Back.model.ApiSmallGoalsSmallGoalIdReorderPostRequest;
import com.example.Kanaeru_Back.repository.SmallGoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service("smallGoalReorderService")
public class ReorderService {

    @Autowired
    private SmallGoalRepository smallGoalRepository;

    @Transactional
    public ApiAuthLogoutPost200Response reorderSmallGoal(
            String smallGoalId, ApiSmallGoalsSmallGoalIdReorderPostRequest request) {
        ApiAuthLogoutPost200Response response = new ApiAuthLogoutPost200Response();

        try {
            Optional<SmallGoalEntity> targetOptional = smallGoalRepository.findById(smallGoalId);
            if (targetOptional.isEmpty()) {
                response.setResponseStatus(0);
                return response;
            }

            SmallGoalEntity target = targetOptional.get();
            if (!"0".equals(target.getDelFlg())) {
                response.setResponseStatus(0);
                return response;
            }

            Integer newPosition = request.getPosition();
            if (newPosition == null) {
                response.setResponseStatus(0);
                return response;
            }

            target.setPosition(newPosition);
            target.setUpdatedAt(LocalDateTime.now());
            smallGoalRepository.save(target);

            response.setResponseStatus(1);
        } catch (Exception e) {
            response.setResponseStatus(0);
        }

        return response;
    }
}
