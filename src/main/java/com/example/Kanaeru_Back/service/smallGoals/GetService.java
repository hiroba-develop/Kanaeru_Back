package com.example.Kanaeru_Back.service.smallGoals;

import com.example.Kanaeru_Back.entity.SmallGoalEntity;
import com.example.Kanaeru_Back.model.ApiSmallGoalsMiddleGoalIdGet200Response;
import com.example.Kanaeru_Back.model.ApiSmallGoalsMiddleGoalIdGet200ResponseSmallGoalsInner;
import com.example.Kanaeru_Back.repository.SmallGoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service("smallGoalGetService")
public class GetService {

    @Autowired
    private SmallGoalRepository smallGoalRepository;

    @Transactional(readOnly = true)
    public ApiSmallGoalsMiddleGoalIdGet200Response getSmallGoals(String middleGoalId) {
        ApiSmallGoalsMiddleGoalIdGet200Response response = new ApiSmallGoalsMiddleGoalIdGet200Response();

        try {
            // middleGoalIdで小目標を取得（DEL_FLG='0'でフィルタ、position順にソート）
            List<SmallGoalEntity> smallGoalEntities = 
                    smallGoalRepository.findByMiddleGoalIdAndDelFlgOrderByPositionAsc(middleGoalId, "0");

            // SmallGoalEntityからレスポンスモデルに変換
            List<ApiSmallGoalsMiddleGoalIdGet200ResponseSmallGoalsInner> smallGoals = 
                    smallGoalEntities.stream()
                            .map(this::convertToResponseModel)
                            .collect(Collectors.toList());

            response.setSmallGoals(smallGoals);
            response.setResponseStatus(1);
        } catch (Exception e) {
            response.setResponseStatus(0);
            response.setSmallGoals(new ArrayList<>());
        }

        return response;
    }

    /**
     * SmallGoalEntityをレスポンスモデルに変換
     */
    private ApiSmallGoalsMiddleGoalIdGet200ResponseSmallGoalsInner convertToResponseModel(
            SmallGoalEntity entity) {
        ApiSmallGoalsMiddleGoalIdGet200ResponseSmallGoalsInner responseModel = 
                new ApiSmallGoalsMiddleGoalIdGet200ResponseSmallGoalsInner();
        
        responseModel.setSmallGoalId(entity.getSmallGoalId());
        responseModel.setPosition(entity.getPosition());
        responseModel.setGoalTitle(entity.getGoalTitle());
        responseModel.setGoalDescription(entity.getGoalDescription());
        
        // isCompletedはString型（"0"または"1"）をBoolean型に変換
        responseModel.setIsCompleted("1".equals(entity.getIsCompleted()));
        
        responseModel.setCompletedAt(entity.getCompletedAt());
        
        return responseModel;
    }
}
