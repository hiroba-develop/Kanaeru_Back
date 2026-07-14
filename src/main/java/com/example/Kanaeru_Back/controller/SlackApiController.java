package com.example.Kanaeru_Back.controller;

import com.example.Kanaeru_Back.model.ApiAuthTermsAgreePost200Response;
import com.example.Kanaeru_Back.model.GetSlackUserMapping200Response;
import com.example.Kanaeru_Back.model.SlackUserMappingRequest;
import com.example.Kanaeru_Back.service.slack.SlackUserMappingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SlackApiController implements SlackApi {

    private static final Logger logger = LoggerFactory.getLogger(SlackApiController.class);

    @Autowired
    private SlackUserMappingService slackUserMappingService;

    @Override
    public ResponseEntity<GetSlackUserMapping200Response> getSlackUserMapping(String userId) {
        try {
            return ResponseEntity.ok(slackUserMappingService.getMapping(userId));
        } catch (Exception e) {
            logger.error("SlackユーザーIDマッピング取得エラー userId={}", userId, e);
            GetSlackUserMapping200Response response = new GetSlackUserMapping200Response();
            response.setResponseStatus(0);
            return ResponseEntity.ok(response);
        }
    }

    @Override
    public ResponseEntity<ApiAuthTermsAgreePost200Response> updateSlackUserMapping(
            SlackUserMappingRequest slackUserMappingRequest) {
        ApiAuthTermsAgreePost200Response response = new ApiAuthTermsAgreePost200Response();
        try {
            int result = slackUserMappingService.upsertMapping(slackUserMappingRequest);
            if (result == -1) {
                response.setResponseStatus(0);
                return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
            }
            response.setResponseStatus(result);
        } catch (Exception e) {
            logger.error("SlackユーザーIDマッピング更新エラー userId={}", slackUserMappingRequest.getUserId(), e);
            response.setResponseStatus(0);
        }
        return ResponseEntity.ok(response);
    }
}
