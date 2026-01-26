package com.example.Kanaeru_Back.service.grossProfit;

import com.example.Kanaeru_Back.entity.GrossProfitEntity;
import com.example.Kanaeru_Back.model.ApiGrossProfitUpdatePut200Response;
import com.example.Kanaeru_Back.model.GrossProfitSchema;
import com.example.Kanaeru_Back.repository.GrossProfitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service("grossProfitUpdateService")
public class UpdateService {

    @Autowired
    private GrossProfitRepository grossProfitRepository;

    @Transactional
    public ApiGrossProfitUpdatePut200Response updateGrossProfit(GrossProfitSchema grossProfitSchema) {
        ApiGrossProfitUpdatePut200Response response = new ApiGrossProfitUpdatePut200Response();

        try {
            if (grossProfitSchema == null || grossProfitSchema.getUserId() == null 
                || grossProfitSchema.getYear() == null || grossProfitSchema.getMonth() == null) {
                response.setResponseStatus(0);
                return response;
            }

            // 複合主キーで既存レコードを取得
            GrossProfitEntity.GrossProfitId grossProfitId = new GrossProfitEntity.GrossProfitId();
            grossProfitId.setUserId(grossProfitSchema.getUserId());
            grossProfitId.setYear(grossProfitSchema.getYear());
            grossProfitId.setMonth(grossProfitSchema.getMonth());

            Optional<GrossProfitEntity> grossProfitOptional = grossProfitRepository.findById(grossProfitId);

            if (grossProfitOptional.isPresent()) {
                GrossProfitEntity grossProfitEntity = grossProfitOptional.get();

                // 粗利益データを更新
                if (grossProfitSchema.getGrossProfitTarget() != null) {
                    grossProfitEntity.setGrossProfitTarget(grossProfitSchema.getGrossProfitTarget().longValue());
                }
                if (grossProfitSchema.getGrossProfitResult() != null) {
                    grossProfitEntity.setGrossProfitResult(grossProfitSchema.getGrossProfitResult().longValue());
                }
                grossProfitEntity.setUpdatedAt(LocalDateTime.now());

                grossProfitRepository.save(grossProfitEntity);

                // レスポンスに更新後のデータを設定
                GrossProfitSchema updatedSchema = new GrossProfitSchema();
                updatedSchema.setUserId(grossProfitEntity.getUserId());
                updatedSchema.setYear(grossProfitEntity.getYear());
                updatedSchema.setMonth(grossProfitEntity.getMonth());
                updatedSchema.setGrossProfitTarget(grossProfitEntity.getGrossProfitTarget() != null ? 
                    java.math.BigDecimal.valueOf(grossProfitEntity.getGrossProfitTarget()) : null);
                updatedSchema.setGrossProfitResult(grossProfitEntity.getGrossProfitResult() != null ? 
                    java.math.BigDecimal.valueOf(grossProfitEntity.getGrossProfitResult()) : null);

                response.setResponseStatus(1);
                response.setGrossProfitSchema(updatedSchema);
            } else {
                // レコードが見つからない場合は新規作成
                GrossProfitEntity newGrossProfitEntity = new GrossProfitEntity();
                newGrossProfitEntity.setUserId(grossProfitSchema.getUserId());
                newGrossProfitEntity.setYear(grossProfitSchema.getYear());
                newGrossProfitEntity.setMonth(grossProfitSchema.getMonth());
                newGrossProfitEntity.setGrossProfitTarget(grossProfitSchema.getGrossProfitTarget() != null ? 
                    grossProfitSchema.getGrossProfitTarget().longValue() : null);
                newGrossProfitEntity.setGrossProfitResult(grossProfitSchema.getGrossProfitResult() != null ? 
                    grossProfitSchema.getGrossProfitResult().longValue() : null);
                newGrossProfitEntity.setCreatedAt(LocalDateTime.now());
                newGrossProfitEntity.setUpdatedAt(LocalDateTime.now());

                grossProfitRepository.save(newGrossProfitEntity);

                // レスポンスに作成後のデータを設定
                GrossProfitSchema createdSchema = new GrossProfitSchema();
                createdSchema.setUserId(newGrossProfitEntity.getUserId());
                createdSchema.setYear(newGrossProfitEntity.getYear());
                createdSchema.setMonth(newGrossProfitEntity.getMonth());
                createdSchema.setGrossProfitTarget(newGrossProfitEntity.getGrossProfitTarget() != null ? 
                    java.math.BigDecimal.valueOf(newGrossProfitEntity.getGrossProfitTarget()) : null);
                createdSchema.setGrossProfitResult(newGrossProfitEntity.getGrossProfitResult() != null ? 
                    java.math.BigDecimal.valueOf(newGrossProfitEntity.getGrossProfitResult()) : null);

                response.setResponseStatus(1);
                response.setGrossProfitSchema(createdSchema);
            }
        } catch (Exception e) {
            response.setResponseStatus(0);
        }

        return response;
    }
}
