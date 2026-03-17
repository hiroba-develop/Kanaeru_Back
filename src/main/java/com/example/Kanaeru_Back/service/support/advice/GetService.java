package com.example.Kanaeru_Back.service.support.advice;

import com.example.Kanaeru_Back.entity.AdviceEntity;
import com.example.Kanaeru_Back.entity.UserEntity;
import com.example.Kanaeru_Back.model.AdviceSchema;
import com.example.Kanaeru_Back.model.ApiSupportAdviceGet200Response;
import com.example.Kanaeru_Back.repository.AdviceRepository;
import com.example.Kanaeru_Back.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * /api/support/advice: アドバイス取得サービス
 */
@Service
public class GetService {

    private static final Logger logger = LoggerFactory.getLogger(GetService.class);

    @Autowired
    private AdviceRepository adviceRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * 指定ユーザーの指定年月のアドバイスを取得する（履歴切り替え用）
     * 対象月にデータがない場合は空リストを返す
     *
     * @param userId 対象ユーザーID
     * @param year   対象年
     * @param month  対象月
     * @return アドバイスリストを含むレスポンス
     */
    @Transactional(readOnly = true)
    public ApiSupportAdviceGet200Response getAdvice(String userId, Integer year, Integer month) {
        ApiSupportAdviceGet200Response response = new ApiSupportAdviceGet200Response();

        try {
            List<AdviceEntity> advices = adviceRepository.findByUserIdAndYearMonth(userId, year, month);

            List<AdviceSchema> schemaList = new ArrayList<>();
            for (AdviceEntity advice : advices) {
                schemaList.add(convertToAdviceSchema(advice));
            }

            response.setResponseStatus(1);
            response.setAdviceSchema(schemaList);
        } catch (Exception e) {
            logger.error("getAdvice() でエラーが発生: {}", e.getMessage(), e);
            response.setResponseStatus(0);
            response.setAdviceSchema(new ArrayList<>());
        }

        return response;
    }
    /**
     * 指定ユーザーのアドバイスを取得
     *
     * @param userId 対象ユーザーID
     * @return アドバイスリストを含むレスポンス
     */
    @Transactional(readOnly = true)
    public ApiSupportAdviceGet200Response getAllAdvice(String userId) {
        ApiSupportAdviceGet200Response response = new ApiSupportAdviceGet200Response();

        try {
            List<AdviceEntity> advices = adviceRepository.findByUserId(userId);

            List<AdviceSchema> schemaList = new ArrayList<>();
            for (AdviceEntity advice : advices) {
                schemaList.add(convertToAdviceSchema(advice));
            }

            response.setResponseStatus(1);
            response.setAdviceSchema(schemaList);
        } catch (Exception e) {
            logger.error("getAllAdvice() でエラーが発生: {}", e.getMessage(), e);
            response.setResponseStatus(0);
            response.setAdviceSchema(new ArrayList<>());
        }

        return response;
    }

    private AdviceSchema convertToAdviceSchema(AdviceEntity advice) {
        AdviceSchema schema = new AdviceSchema();
        schema.setAdviceId(advice.getAdviceId());
        schema.setUserId(advice.getUserId());
        schema.setAdminId(advice.getAdminId());
        Optional<UserEntity> admin = userRepository.findByUserIdAndDelFlg(advice.getAdminId(), "0");
        schema.setAdminName(admin.map(UserEntity::getName).orElse("不明な管理者"));
        schema.setAdviceContent(advice.getContent());
        schema.setCreatedAt(advice.getCreatedAt());
        schema.setUpdatedAt(advice.getUpdatedAt());
        return schema;
    }
}
