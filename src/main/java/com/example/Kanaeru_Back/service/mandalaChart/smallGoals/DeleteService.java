package com.example.Kanaeru_Back.service.mandalaChart.smallGoals;

import com.example.Kanaeru_Back.entity.MiddleGoalEntity;
import com.example.Kanaeru_Back.entity.SmallGoalEntity;
import com.example.Kanaeru_Back.model.ApiAuthLogoutPost200Response;
import com.example.Kanaeru_Back.repository.MiddleGoalRepository;
import com.example.Kanaeru_Back.repository.SmallGoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service("smallGoalDeleteService")
public class DeleteService {

    @Autowired
    private SmallGoalRepository smallGoalRepository;

    @Autowired
    private MiddleGoalRepository middleGoalRepository;

    @Transactional
    public ApiAuthLogoutPost200Response deleteSmallGoal(String smallGoalId) {
        ApiAuthLogoutPost200Response response = new ApiAuthLogoutPost200Response();

        try {
            Optional<SmallGoalEntity> smallGoalOptional = smallGoalRepository.findById(smallGoalId);

            if (smallGoalOptional.isEmpty()) {
                response.setResponseStatus(0);
                return response;
            }

            SmallGoalEntity smallGoal = smallGoalOptional.get();

            // 既に論理削除済みの場合はエラー
            if ("1".equals(smallGoal.getDelFlg())) {
                response.setResponseStatus(0);
                return response;
            }

            // 論理削除
            smallGoal.setDelFlg("1");
            smallGoal.setUpdatedAt(LocalDateTime.now());
            smallGoalRepository.save(smallGoal);

            // 中目標の進捗を再計算して更新
            updateMiddleGoalProgress(smallGoal.getMiddleGoalId());

            response.setResponseStatus(1);
        } catch (Exception e) {
            response.setResponseStatus(0);
        }

        return response;
    }

    private void updateMiddleGoalProgress(String middleGoalId) {
        try {
            Optional<MiddleGoalEntity> middleGoalOptional = middleGoalRepository.findById(middleGoalId);

            if (middleGoalOptional.isEmpty()) {
                return;
            }

            MiddleGoalEntity middleGoal = middleGoalOptional.get();

            if (!"0".equals(middleGoal.getDelFlg())) {
                return;
            }

            // 削除されていない小目標のみを取得して進捗を再計算
            List<SmallGoalEntity> activeSmallGoals =
                    smallGoalRepository.findByMiddleGoalIdAndDelFlgOrderByPositionAsc(middleGoalId, "0");

            int totalCount = activeSmallGoals.size();
            if (totalCount == 0) {
                middleGoal.setProgress(0);
            } else {
                long completedCount = activeSmallGoals.stream()
                        .filter(sg -> "1".equals(sg.getIsCompleted()))
                        .count();
                int progress = (int) Math.round((completedCount * 100.0) / totalCount);
                middleGoal.setProgress(progress);
            }

            middleGoal.setUpdatedAt(LocalDateTime.now());
            middleGoalRepository.save(middleGoal);
        } catch (Exception e) {
            // 進捗更新エラーは小目標削除処理の成否に影響させない
        }
    }
}
