package com.example.Kanaeru_Back.controller;

import com.example.Kanaeru_Back.model.ApiAuthTermsAgreePost200Response;
import com.example.Kanaeru_Back.model.ApiDailyGoalsCompleteRequest;
import com.example.Kanaeru_Back.model.ApiDailyGoalsCreatePost200Response;
import com.example.Kanaeru_Back.model.ApiDailyGoalsCreatePostRequest;
import com.example.Kanaeru_Back.model.ApiDailyGoalsGet200Response;
import com.example.Kanaeru_Back.model.ApiDailyGoalsReorderRequest;
import com.example.Kanaeru_Back.model.ApiDailyGoalsUpdatePutRequest;
import com.example.Kanaeru_Back.service.dailyGoal.CompleteService;
import com.example.Kanaeru_Back.service.dailyGoal.CreateService;
import com.example.Kanaeru_Back.service.dailyGoal.DeleteService;
import com.example.Kanaeru_Back.service.dailyGoal.GetService;
import com.example.Kanaeru_Back.service.dailyGoal.ReorderService;
import com.example.Kanaeru_Back.service.dailyGoal.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class DailyGoalController implements DailyGoalApi {

    @Autowired
    @Qualifier("dailyGoalGetService")
    private GetService getService;

    @Autowired
    @Qualifier("dailyGoalCreateService")
    private CreateService createService;

    @Autowired
    @Qualifier("dailyGoalUpdateService")
    private UpdateService updateService;

    @Autowired
    @Qualifier("dailyGoalDeleteService")
    private DeleteService deleteService;

    @Autowired
    @Qualifier("dailyGoalCompleteService")
    private CompleteService completeService;

    @Autowired
    @Qualifier("dailyGoalReorderService")
    private ReorderService reorderService;

    @Override
    public ResponseEntity<ApiDailyGoalsGet200Response> apiDailyGoalsGet(
            String userId, LocalDate startDate, LocalDate endDate) {
        return ResponseEntity.ok(getService.getDailyGoals(userId, startDate, endDate));
    }

    @Override
    public ResponseEntity<ApiDailyGoalsCreatePost200Response> apiDailyGoalsCreatePost(
            ApiDailyGoalsCreatePostRequest request) {
        return ResponseEntity.ok(createService.createDailyGoal(request));
    }

    @Override
    public ResponseEntity<ApiAuthTermsAgreePost200Response> apiDailyGoalsDailyGoalIdUpdatePut(
            String dailyGoalId, ApiDailyGoalsUpdatePutRequest request) {
        ApiAuthTermsAgreePost200Response response = new ApiAuthTermsAgreePost200Response();
        response.setResponseStatus(updateService.updateDailyGoal(dailyGoalId, request));
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiAuthTermsAgreePost200Response> apiDailyGoalsDailyGoalIdDeleteDelete(
            String dailyGoalId) {
        ApiAuthTermsAgreePost200Response response = new ApiAuthTermsAgreePost200Response();
        response.setResponseStatus(deleteService.deleteDailyGoal(dailyGoalId));
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiAuthTermsAgreePost200Response> apiDailyGoalsDailyGoalIdCompletePut(
            String dailyGoalId, ApiDailyGoalsCompleteRequest request) {
        ApiAuthTermsAgreePost200Response response = new ApiAuthTermsAgreePost200Response();
        response.setResponseStatus(completeService.completeDailyGoal(dailyGoalId, request));
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ApiAuthTermsAgreePost200Response> apiDailyGoalsDailyGoalIdReorderPost(
            String dailyGoalId, ApiDailyGoalsReorderRequest request) {
        ApiAuthTermsAgreePost200Response response = new ApiAuthTermsAgreePost200Response();
        response.setResponseStatus(reorderService.reorderDailyGoal(dailyGoalId, request));
        return ResponseEntity.ok(response);
    }
}
