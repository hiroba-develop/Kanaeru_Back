package com.example.Kanaeru_Back.service.largeGoals;

import com.example.Kanaeru_Back.entity.LargeGoalEntity;
import com.example.Kanaeru_Back.entity.MainGoalEntity;
import com.example.Kanaeru_Back.model.ApiLargeGoalsChartIdCreatePost200Response;
import com.example.Kanaeru_Back.model.ApiLargeGoalsChartIdCreatePostRequest;
import com.example.Kanaeru_Back.repository.LargeGoalRepository;
import com.example.Kanaeru_Back.repository.MainGoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service("largeGoalCreateService")
public class CreateService {

    @Autowired
    private LargeGoalRepository largeGoalRepository;

    @Autowired
    private MainGoalRepository mainGoalRepository;

    @Transactional
    public ApiLargeGoalsChartIdCreatePost200Response createLargeGoal(
            String chartId, ApiLargeGoalsChartIdCreatePostRequest request) {
        ApiLargeGoalsChartIdCreatePost200Response response = new ApiLargeGoalsChartIdCreatePost200Response();

        try {
            // chartIdからmainGoalIdを取得（パスパラメータのchartIdを使用）
            Optional<MainGoalEntity> mainGoalOptional = 
                    mainGoalRepository.findByChartIdAndDelFlg(chartId, "0");

            if (!mainGoalOptional.isPresent()) {
                response.setResponseStatus(0);
                return response;
            }

            MainGoalEntity mainGoal = mainGoalOptional.get();
            String mainGoalId = mainGoal.getMainGoalId();

            // LARGE_GOAL_IDをUUIDで生成
            String newLargeGoalId = UUID.randomUUID().toString();

            // LARGE_GOALSテーブルにレコードを登録
            LargeGoalEntity largeGoalEntity = new LargeGoalEntity();
            largeGoalEntity.setLargeGoalId(newLargeGoalId);
            largeGoalEntity.setMainGoalId(mainGoalId);
            largeGoalEntity.setPosition(request.getPosition());
            largeGoalEntity.setGoalTitle(request.getGoalTitle());
            largeGoalEntity.setGoalDescription(request.getGoalDescription());
            largeGoalEntity.setGoalType(request.getGoalType());
            largeGoalEntity.setTargetYear(request.getTargetYear());
            largeGoalEntity.setTargetAmount(request.getTargetAmount());
            largeGoalEntity.setProgress(0);
            largeGoalEntity.setDelFlg("0");
            largeGoalEntity.setCreatedAt(LocalDateTime.now());
            largeGoalEntity.setUpdatedAt(LocalDateTime.now());
            largeGoalRepository.save(largeGoalEntity);

            response.setResponseStatus(1);
            response.setLargeGoalId(newLargeGoalId);
        } catch (Exception e) {
            response.setResponseStatus(0);
        }

        return response;
    }
}
