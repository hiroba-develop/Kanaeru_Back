package com.example.Kanaeru_Back.service.slack;

import com.example.Kanaeru_Back.entity.SlackUserMappingEntity;
import com.example.Kanaeru_Back.model.GetSlackUserMapping200Response;
import com.example.Kanaeru_Back.model.SlackUserMappingRequest;
import com.example.Kanaeru_Back.repository.SlackUserMappingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

@Service
public class SlackUserMappingService {

    private static final Logger logger = LoggerFactory.getLogger(SlackUserMappingService.class);

    @Autowired
    private SlackUserMappingRepository slackUserMappingRepository;

    public GetSlackUserMapping200Response getMapping(String userId) {
        GetSlackUserMapping200Response response = new GetSlackUserMapping200Response();
        Optional<SlackUserMappingEntity> mapping =
                slackUserMappingRepository.findByUserIdAndDelFlg(userId, "0");
        if (mapping.isPresent()) {
            response.setResponseStatus(1);
            response.setSlackUserId(mapping.get().getSlackUserId());
        } else {
            response.setResponseStatus(1);
            response.setSlackUserId(null);
        }
        return response;
    }

    // 戻り値: 1=成功, -1=slackUserId重複, 0=その他エラー
    public int upsertMapping(SlackUserMappingRequest request) {
        Optional<SlackUserMappingEntity> existingSlack =
                slackUserMappingRepository.findBySlackUserIdAndDelFlg(request.getSlackUserId(), "0");
        if (existingSlack.isPresent() && !existingSlack.get().getUserId().equals(request.getUserId())) {
            return -1;
        }

        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Tokyo"));
        Optional<SlackUserMappingEntity> existing =
                slackUserMappingRepository.findByUserIdAndDelFlg(request.getUserId(), "0");

        if (existing.isPresent()) {
            SlackUserMappingEntity entity = existing.get();
            entity.setSlackUserId(request.getSlackUserId());
            entity.setUpdatedAt(now);
            slackUserMappingRepository.save(entity);
        } else {
            SlackUserMappingEntity entity = new SlackUserMappingEntity();
            entity.setMappingId(UUID.randomUUID().toString());
            entity.setUserId(request.getUserId());
            entity.setSlackUserId(request.getSlackUserId());
            entity.setDelFlg("0");
            entity.setCreatedAt(now);
            entity.setUpdatedAt(now);
            slackUserMappingRepository.save(entity);
        }
        return 1;
    }
}
