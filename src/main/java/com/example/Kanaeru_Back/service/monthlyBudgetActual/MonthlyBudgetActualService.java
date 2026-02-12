package com.example.Kanaeru_Back.service.monthlyBudgetActual;

import com.example.Kanaeru_Back.entity.GrossProfitEntity;
import com.example.Kanaeru_Back.entity.LargeGoalEntity;
import com.example.Kanaeru_Back.entity.MainGoalEntity;
import com.example.Kanaeru_Back.entity.MandalaChartEntity;
import com.example.Kanaeru_Back.entity.MiddleGoalEntity;
import com.example.Kanaeru_Back.entity.OperatingProfitEntity;
import com.example.Kanaeru_Back.entity.SalesEntity;
import com.example.Kanaeru_Back.model.ApiYearlyBudgetActualGet200Response;
import com.example.Kanaeru_Back.model.GrossProfitSchema;
import com.example.Kanaeru_Back.model.LargePLLinkedItemSchema;
import com.example.Kanaeru_Back.model.MiddlePLLinkedItemSchema;
import com.example.Kanaeru_Back.model.OperatingProfitSchema;
import com.example.Kanaeru_Back.model.SaleSchema;
import com.example.Kanaeru_Back.repository.GrossProfitRepository;
import com.example.Kanaeru_Back.repository.LargeGoalRepository;
import com.example.Kanaeru_Back.repository.MandalaChartRepository;
import com.example.Kanaeru_Back.repository.MiddleGoalRepository;
import com.example.Kanaeru_Back.repository.MainGoalRepository;
import com.example.Kanaeru_Back.repository.OperatingProfitRepository;
import com.example.Kanaeru_Back.repository.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MonthlyBudgetActualService {

    @Autowired
    private SalesRepository salesRepository;

    @Autowired
    private GrossProfitRepository grossProfitRepository;

    @Autowired
    private OperatingProfitRepository operatingProfitRepository;

    @Autowired
    private MandalaChartRepository mandalaChartRepository;

    @Autowired
    private MainGoalRepository mainGoalRepository;

    @Autowired
    private LargeGoalRepository largeGoalRepository;

    @Autowired
    private MiddleGoalRepository middleGoalRepository;

    /**
     * 予実管理(月次)画面の初期表示データを取得
     * 事業開始月から12か月分（1年分）のデータを取得
     * 
     * @param userId ユーザーID
     * @param year 年
     * @param startMonth 事業開始月
     * @return レスポンス
     */
    public ApiYearlyBudgetActualGet200Response getMonthlyBudgetActual(String userId, Integer year, Integer startMonth) {
        ApiYearlyBudgetActualGet200Response response = new ApiYearlyBudgetActualGet200Response();

        try {
            // 終了年月を計算（開始月から12か月後）
            Integer endYear = year;
            Integer endMonth = startMonth + 11;
            
            // 月が12を超える場合、年を繰り上げ
            if (endMonth > 12) {
                endYear = year + 1;
                endMonth = endMonth - 12;
            }

            // 各テーブルから事業開始月から12か月分のデータを取得
            List<SalesEntity> salesEntities = salesRepository.findByUserIdAndYearMonthRange(
                userId, year, startMonth, endYear, endMonth);
            List<GrossProfitEntity> grossProfitEntities = grossProfitRepository.findByUserIdAndYearMonthRange(
                userId, year, startMonth, endYear, endMonth);
            List<OperatingProfitEntity> operatingProfitEntities = operatingProfitRepository.findByUserIdAndYearMonthRange(
                userId, year, startMonth, endYear, endMonth);

            // EntityをSchemaに変換（リスト形式）
            List<SaleSchema> saleSchemas = new ArrayList<>();
            for (SalesEntity salesEntity : salesEntities) {
                SaleSchema schema = new SaleSchema();
                schema.setUserId(salesEntity.getUserId());
                schema.setYear(salesEntity.getYear());
                schema.setMonth(salesEntity.getMonth());
                schema.setSaleTarget(salesEntity.getSaleTarget() != null ? BigDecimal.valueOf(salesEntity.getSaleTarget()) : null);
                schema.setSaleResult(salesEntity.getSaleResult() != null ? BigDecimal.valueOf(salesEntity.getSaleResult()) : null);
                saleSchemas.add(schema);
            }

            List<GrossProfitSchema> grossProfitSchemas = new ArrayList<>();
            for (GrossProfitEntity grossProfitEntity : grossProfitEntities) {
                GrossProfitSchema schema = new GrossProfitSchema();
                schema.setUserId(grossProfitEntity.getUserId());
                schema.setYear(grossProfitEntity.getYear());
                schema.setMonth(grossProfitEntity.getMonth());
                schema.setGrossProfitTarget(grossProfitEntity.getGrossProfitTarget() != null ? BigDecimal.valueOf(grossProfitEntity.getGrossProfitTarget()) : null);
                schema.setGrossProfitResult(grossProfitEntity.getGrossProfitResult() != null ? BigDecimal.valueOf(grossProfitEntity.getGrossProfitResult()) : null);
                grossProfitSchemas.add(schema);
            }

            List<OperatingProfitSchema> operatingProfitSchemas = new ArrayList<>();
            for (OperatingProfitEntity operatingProfitEntity : operatingProfitEntities) {
                OperatingProfitSchema schema = new OperatingProfitSchema();
                schema.setUserId(operatingProfitEntity.getUserId());
                schema.setYear(operatingProfitEntity.getYear());
                schema.setMonth(operatingProfitEntity.getMonth());
                schema.setOperatingProfitTarget(operatingProfitEntity.getOperatingProfitTarget() != null ? BigDecimal.valueOf(operatingProfitEntity.getOperatingProfitTarget()) : null);
                schema.setOperatingProfitResult(operatingProfitEntity.getOperatingProfitResult() != null ? BigDecimal.valueOf(operatingProfitEntity.getOperatingProfitResult()) : null);
                operatingProfitSchemas.add(schema);
            }

            // PL連動項目を取得（大目標と中目標でGOAL_TYPEが2~4の項目）
            List<LargePLLinkedItemSchema> largePLLinkedItemSchemas = getLargePLLinkedItems(userId);
            List<MiddlePLLinkedItemSchema> middlePLLinkedItemSchemas = getMiddlePLLinkedItems(userId);

            response.setResponseStatus(1);
            response.setSaleSchema(saleSchemas);
            response.setGrossProfitSchema(grossProfitSchemas);
            response.setOperatingProfitSchema(operatingProfitSchemas);
            response.setLargePLLinkedItemSchema(largePLLinkedItemSchemas);
            response.setMiddlePLLinkedItemSchema(middlePLLinkedItemSchemas);

        } catch (Exception e) {
            response.setResponseStatus(0);
        }

        return response;
    }

    /**
     * 大目標のPL連動項目を取得（GOAL_TYPEが2~4）
     * 
     * @param userId ユーザーID
     * @return 大目標PL連動項目のリスト
     */
    private List<LargePLLinkedItemSchema> getLargePLLinkedItems(String userId) {
        List<LargePLLinkedItemSchema> schemas = new ArrayList<>();

        try {
            // ユーザーIDからアクティブなマンダラチャートを取得
            Optional<MandalaChartEntity> mandalaChartOpt = 
                mandalaChartRepository.findFirstByUserIdAndIsActiveAndDelFlgOrderByCreatedAtDesc(userId, "1", "0");

            if (mandalaChartOpt.isPresent()) {
                MandalaChartEntity mandalaChart = mandalaChartOpt.get();
                
                // マンダラチャートIDからメイン目標を取得
                Optional<MainGoalEntity> mainGoalOpt = 
                    mainGoalRepository.findByChartIdAndDelFlg(mandalaChart.getChartId(), "0");

                if (mainGoalOpt.isPresent()) {
                    MainGoalEntity mainGoal = mainGoalOpt.get();
                    
                    // メイン目標IDからGOAL_TYPEが2~4の大目標を取得
                    List<LargeGoalEntity> largeGoalEntities = 
                        largeGoalRepository.findByMainGoalIdAndDelFlgAndGoalTypeInOrderByPosition(
                            mainGoal.getMainGoalId(), "0");

                    // EntityをSchemaに変換
                    for (LargeGoalEntity entity : largeGoalEntities) {
                        LargePLLinkedItemSchema schema = new LargePLLinkedItemSchema();
                        schema.setLargeGoalId(entity.getLargeGoalId());
                        schema.setGoalType(entity.getGoalType());
                        schema.setTargetYear(entity.getTargetYear());
                        schema.setTargetAmount(entity.getTargetAmount());
                        schemas.add(schema);
                    }
                }
            }
        } catch (Exception e) {
            // エラーが発生した場合は空のリストを返す
        }

        return schemas;
    }

    /**
     * 中目標のPL連動項目を取得（GOAL_TYPEが2~4）
     * 
     * @param userId ユーザーID
     * @return 中目標PL連動項目のリスト
     */
    private List<MiddlePLLinkedItemSchema> getMiddlePLLinkedItems(String userId) {
        List<MiddlePLLinkedItemSchema> schemas = new ArrayList<>();

        try {
            // ユーザーIDからアクティブなマンダラチャートを取得
            Optional<MandalaChartEntity> mandalaChartOpt = 
                mandalaChartRepository.findFirstByUserIdAndIsActiveAndDelFlgOrderByCreatedAtDesc(userId, "1", "0");

            if (mandalaChartOpt.isPresent()) {
                MandalaChartEntity mandalaChart = mandalaChartOpt.get();
                
                // マンダラチャートIDからメイン目標を取得
                Optional<MainGoalEntity> mainGoalOpt = 
                    mainGoalRepository.findByChartIdAndDelFlg(mandalaChart.getChartId(), "0");

                if (mainGoalOpt.isPresent()) {
                    MainGoalEntity mainGoal = mainGoalOpt.get();
                    
                    // メイン目標IDから大目標を取得
                    List<LargeGoalEntity> largeGoalEntities = 
                        largeGoalRepository.findByMainGoalIdAndDelFlgOrderByPositionAsc(mainGoal.getMainGoalId(), "0");

                    // 各大目標に紐づく中目標でGOAL_TYPEが2~4のものを取得
                    for (LargeGoalEntity largeGoal : largeGoalEntities) {
                        List<MiddleGoalEntity> middleGoalEntities = 
                            middleGoalRepository.findByLargeGoalIdAndDelFlgAndGoalTypeInOrderByPosition(
                                largeGoal.getLargeGoalId(), "0");

                        // EntityをSchemaに変換
                        for (MiddleGoalEntity entity : middleGoalEntities) {
                            MiddlePLLinkedItemSchema schema = new MiddlePLLinkedItemSchema();
                            schema.setMiddleGoalId(entity.getMiddleGoalId());
                            schema.setGoalType(entity.getGoalType());
                            schema.setTargetYear(entity.getTargetYear());
                            schema.setTargetAmount(entity.getTargetAmount());
                            schemas.add(schema);
                        }
                    }
                }
            }
        } catch (Exception e) {
            // エラーが発生した場合は空のリストを返す
        }

        return schemas;
    }
}
