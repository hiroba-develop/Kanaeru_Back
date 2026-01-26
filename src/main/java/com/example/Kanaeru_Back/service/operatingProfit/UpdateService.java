package com.example.Kanaeru_Back.service.operatingProfit;

import com.example.Kanaeru_Back.entity.OperatingProfitEntity;
import com.example.Kanaeru_Back.model.ApiOperatingProfitUpdatePut200Response;
import com.example.Kanaeru_Back.model.OperatingProfitSchema;
import com.example.Kanaeru_Back.repository.OperatingProfitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service("operatingProfitUpdateService")
public class UpdateService {

    @Autowired
    private OperatingProfitRepository operatingProfitRepository;

    @Transactional
    public ApiOperatingProfitUpdatePut200Response updateOperatingProfit(OperatingProfitSchema operatingProfitSchema) {
        ApiOperatingProfitUpdatePut200Response response = new ApiOperatingProfitUpdatePut200Response();

        try {
            if (operatingProfitSchema == null || operatingProfitSchema.getUserId() == null 
                || operatingProfitSchema.getYear() == null || operatingProfitSchema.getMonth() == null) {
                response.setResponseStatus(0);
                return response;
            }

            // 複合主キーで既存レコードを取得
            OperatingProfitEntity.OperatingProfitId operatingProfitId = new OperatingProfitEntity.OperatingProfitId();
            operatingProfitId.setUserId(operatingProfitSchema.getUserId());
            operatingProfitId.setYear(operatingProfitSchema.getYear());
            operatingProfitId.setMonth(operatingProfitSchema.getMonth());

            Optional<OperatingProfitEntity> operatingProfitOptional = operatingProfitRepository.findById(operatingProfitId);

            if (operatingProfitOptional.isPresent()) {
                OperatingProfitEntity operatingProfitEntity = operatingProfitOptional.get();

                // 営業利益データを更新
                if (operatingProfitSchema.getOperatingProfitTarget() != null) {
                    operatingProfitEntity.setOperatingProfitTarget(operatingProfitSchema.getOperatingProfitTarget().longValue());
                }
                if (operatingProfitSchema.getOperatingProfitResult() != null) {
                    operatingProfitEntity.setOperatingProfitResult(operatingProfitSchema.getOperatingProfitResult().longValue());
                }
                operatingProfitEntity.setUpdatedAt(LocalDateTime.now());

                operatingProfitRepository.save(operatingProfitEntity);

                // レスポンスに更新後のデータを設定
                OperatingProfitSchema updatedSchema = new OperatingProfitSchema();
                updatedSchema.setUserId(operatingProfitEntity.getUserId());
                updatedSchema.setYear(operatingProfitEntity.getYear());
                updatedSchema.setMonth(operatingProfitEntity.getMonth());
                updatedSchema.setOperatingProfitTarget(operatingProfitEntity.getOperatingProfitTarget() != null ? 
                    java.math.BigDecimal.valueOf(operatingProfitEntity.getOperatingProfitTarget()) : null);
                updatedSchema.setOperatingProfitResult(operatingProfitEntity.getOperatingProfitResult() != null ? 
                    java.math.BigDecimal.valueOf(operatingProfitEntity.getOperatingProfitResult()) : null);

                response.setResponseStatus(1);
                response.setOperatingProfitSchema(updatedSchema);
            } else {
                // レコードが見つからない場合は新規作成
                OperatingProfitEntity newOperatingProfitEntity = new OperatingProfitEntity();
                newOperatingProfitEntity.setUserId(operatingProfitSchema.getUserId());
                newOperatingProfitEntity.setYear(operatingProfitSchema.getYear());
                newOperatingProfitEntity.setMonth(operatingProfitSchema.getMonth());
                newOperatingProfitEntity.setOperatingProfitTarget(operatingProfitSchema.getOperatingProfitTarget() != null ? 
                    operatingProfitSchema.getOperatingProfitTarget().longValue() : null);
                newOperatingProfitEntity.setOperatingProfitResult(operatingProfitSchema.getOperatingProfitResult() != null ? 
                    operatingProfitSchema.getOperatingProfitResult().longValue() : null);
                newOperatingProfitEntity.setCreatedAt(LocalDateTime.now());
                newOperatingProfitEntity.setUpdatedAt(LocalDateTime.now());

                operatingProfitRepository.save(newOperatingProfitEntity);

                // レスポンスに作成後のデータを設定
                OperatingProfitSchema createdSchema = new OperatingProfitSchema();
                createdSchema.setUserId(newOperatingProfitEntity.getUserId());
                createdSchema.setYear(newOperatingProfitEntity.getYear());
                createdSchema.setMonth(newOperatingProfitEntity.getMonth());
                createdSchema.setOperatingProfitTarget(newOperatingProfitEntity.getOperatingProfitTarget() != null ? 
                    java.math.BigDecimal.valueOf(newOperatingProfitEntity.getOperatingProfitTarget()) : null);
                createdSchema.setOperatingProfitResult(newOperatingProfitEntity.getOperatingProfitResult() != null ? 
                    java.math.BigDecimal.valueOf(newOperatingProfitEntity.getOperatingProfitResult()) : null);

                response.setResponseStatus(1);
                response.setOperatingProfitSchema(createdSchema);
            }
        } catch (Exception e) {
            response.setResponseStatus(0);
        }

        return response;
    }
}
