package com.example.Kanaeru_Back.service.largeGoals;

import com.example.Kanaeru_Back.entity.MiddleGoalEntity;
import com.example.Kanaeru_Back.model.ApiMiddleGoalsLargeGoalIdGet200Response;
import com.example.Kanaeru_Back.model.ApiMiddleGoalsLargeGoalIdGet200ResponseMiddleGoalsInner;
import com.example.Kanaeru_Back.repository.MiddleGoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetMiddleGoalsService {

    @Autowired
    private MiddleGoalRepository middleGoalRepository;

    @Transactional(readOnly = true)
    public ApiMiddleGoalsLargeGoalIdGet200Response getMiddleGoals(String largeGoalId) {
        ApiMiddleGoalsLargeGoalIdGet200Response response = new ApiMiddleGoalsLargeGoalIdGet200Response();

        try {
            // largeGoalIdで中目標を取得（DEL_FLG='0'でフィルタ、position順にソート）
            List<MiddleGoalEntity> middleGoalEntities = 
                    middleGoalRepository.findByLargeGoalIdAndDelFlgOrderByPositionAsc(largeGoalId, "0");

            // MiddleGoalEntityからレスポンスモデルに変換
            List<ApiMiddleGoalsLargeGoalIdGet200ResponseMiddleGoalsInner> middleGoals = 
                    middleGoalEntities.stream()
                            .map(this::convertToResponseModel)
                            .collect(Collectors.toList());

            response.setMiddleGoals(middleGoals);
            response.setResponseStatus(1);
        } catch (Exception e) {
            response.setResponseStatus(0);
            response.setMiddleGoals(new ArrayList<>());
        }

        return response;
    }

    /**
     * MiddleGoalEntityをレスポンスモデルに変換
     */
    private ApiMiddleGoalsLargeGoalIdGet200ResponseMiddleGoalsInner convertToResponseModel(
            MiddleGoalEntity entity) {
        ApiMiddleGoalsLargeGoalIdGet200ResponseMiddleGoalsInner responseModel = 
                new ApiMiddleGoalsLargeGoalIdGet200ResponseMiddleGoalsInner();
        
        responseModel.setMiddleGoalId(entity.getMiddleGoalId());
        responseModel.setPosition(entity.getPosition());
        responseModel.setGoalTitle(entity.getGoalTitle());
        responseModel.setGoalDescription(entity.getGoalDescription());
        responseModel.setGoalType(entity.getGoalType());
        responseModel.setTargetYear(entity.getTargetYear());
        responseModel.setTargetAmount(entity.getTargetAmount());
        
        // progressはIntegerからBigDecimalに変換
        if (entity.getProgress() != null) {
            responseModel.setProgress(BigDecimal.valueOf(entity.getProgress()));
        }
        
        return responseModel;
    }
}
