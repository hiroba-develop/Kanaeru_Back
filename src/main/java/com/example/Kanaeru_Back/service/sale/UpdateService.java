package com.example.Kanaeru_Back.service.sale;

import com.example.Kanaeru_Back.entity.SalesEntity;
import com.example.Kanaeru_Back.model.ApiSaleUpdatePut200Response;
import com.example.Kanaeru_Back.model.SaleSchema;
import com.example.Kanaeru_Back.repository.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service("saleUpdateService")
public class UpdateService {

    @Autowired
    private SalesRepository salesRepository;

    @Transactional
    public ApiSaleUpdatePut200Response updateSale(SaleSchema saleSchema) {
        ApiSaleUpdatePut200Response response = new ApiSaleUpdatePut200Response();

        try {
            if (saleSchema == null || saleSchema.getUserId() == null 
                || saleSchema.getYear() == null || saleSchema.getMonth() == null) {
                response.setResponseStatus(0);
                return response;
            }

            // 複合主キーで既存レコードを取得
            SalesEntity.SalesId salesId = new SalesEntity.SalesId();
            salesId.setUserId(saleSchema.getUserId());
            salesId.setYear(saleSchema.getYear());
            salesId.setMonth(saleSchema.getMonth());

            Optional<SalesEntity> salesOptional = salesRepository.findById(salesId);

            if (salesOptional.isPresent()) {
                SalesEntity salesEntity = salesOptional.get();

                // 売上データを更新
                if (saleSchema.getSaleTarget() != null) {
                    salesEntity.setSaleTarget(saleSchema.getSaleTarget().longValue());
                }
                if (saleSchema.getSaleResult() != null) {
                    salesEntity.setSaleResult(saleSchema.getSaleResult().longValue());
                }
                salesEntity.setUpdatedAt(LocalDateTime.now());

                salesRepository.save(salesEntity);

                // レスポンスに更新後のデータを設定
                SaleSchema updatedSchema = new SaleSchema();
                updatedSchema.setUserId(salesEntity.getUserId());
                updatedSchema.setYear(salesEntity.getYear());
                updatedSchema.setMonth(salesEntity.getMonth());
                updatedSchema.setSaleTarget(salesEntity.getSaleTarget() != null ? 
                    java.math.BigDecimal.valueOf(salesEntity.getSaleTarget()) : null);
                updatedSchema.setSaleResult(salesEntity.getSaleResult() != null ? 
                    java.math.BigDecimal.valueOf(salesEntity.getSaleResult()) : null);

                response.setResponseStatus(1);
                response.setSaleSchema(updatedSchema);
            } else {
                // レコードが見つからない場合は新規作成
                SalesEntity newSalesEntity = new SalesEntity();
                newSalesEntity.setUserId(saleSchema.getUserId());
                newSalesEntity.setYear(saleSchema.getYear());
                newSalesEntity.setMonth(saleSchema.getMonth());
                newSalesEntity.setSaleTarget(saleSchema.getSaleTarget() != null ? 
                    saleSchema.getSaleTarget().longValue() : null);
                newSalesEntity.setSaleResult(saleSchema.getSaleResult() != null ? 
                    saleSchema.getSaleResult().longValue() : null);
                newSalesEntity.setCreatedAt(LocalDateTime.now());
                newSalesEntity.setUpdatedAt(LocalDateTime.now());

                salesRepository.save(newSalesEntity);

                // レスポンスに作成後のデータを設定
                SaleSchema createdSchema = new SaleSchema();
                createdSchema.setUserId(newSalesEntity.getUserId());
                createdSchema.setYear(newSalesEntity.getYear());
                createdSchema.setMonth(newSalesEntity.getMonth());
                createdSchema.setSaleTarget(newSalesEntity.getSaleTarget() != null ? 
                    java.math.BigDecimal.valueOf(newSalesEntity.getSaleTarget()) : null);
                createdSchema.setSaleResult(newSalesEntity.getSaleResult() != null ? 
                    java.math.BigDecimal.valueOf(newSalesEntity.getSaleResult()) : null);

                response.setResponseStatus(1);
                response.setSaleSchema(createdSchema);
            }
        } catch (Exception e) {
            response.setResponseStatus(0);
        }

        return response;
    }
}
