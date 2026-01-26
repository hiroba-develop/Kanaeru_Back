package com.example.Kanaeru_Back.service.screenDisplay;

import com.example.Kanaeru_Back.entity.GrossProfitEntity;
import com.example.Kanaeru_Back.entity.LargeGoalEntity;
import com.example.Kanaeru_Back.entity.MainGoalEntity;
import com.example.Kanaeru_Back.entity.MandalaChartEntity;
import com.example.Kanaeru_Back.entity.MiddleGoalEntity;
import com.example.Kanaeru_Back.entity.OperatingProfitEntity;
import com.example.Kanaeru_Back.entity.SalesEntity;
import com.example.Kanaeru_Back.entity.SettingEntity;
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
import com.example.Kanaeru_Back.repository.SettingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class YearlyBudgetActualService {

    @Autowired
    private SettingRepository settingRepository;

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
     * 予実管理(年次)画面の初期表示データを取得
     * 事業年度開始年月から10年分のデータを取得
     * 
     * @param userId ユーザーID
     * @return レスポンス
     */
    public ApiYearlyBudgetActualGet200Response getYearlyBudgetActual(String userId) {
        ApiYearlyBudgetActualGet200Response response = new ApiYearlyBudgetActualGet200Response();

        try {
            // SETTINGSテーブルから事業年度開始年月を取得
            Optional<SettingEntity> settingOpt = settingRepository.findByUserId(userId);
            if (settingOpt.isEmpty() || settingOpt.get().getFiscalYearStartYear() == null 
                || settingOpt.get().getFiscalYearStartMonth() == null) {
                response.setResponseStatus(0);
                return response;
            }

            SettingEntity setting = settingOpt.get();
            Integer fiscalYearStartYear = setting.getFiscalYearStartYear();
            Integer fiscalYearStartMonth = setting.getFiscalYearStartMonth();

            // 10年後の年を計算（年次管理のため、事業年度開始月の10年後まで）
            // 例：2025年8月が事業年度開始年月の場合、2025年8月～2034年8月の10レコードを取得
            int endYear = fiscalYearStartYear + 9; // 開始年から9年後（合計10年分）

            // 各テーブルから事業年度開始月のみの10年分のデータを取得
            List<SalesEntity> salesEntities = salesRepository.findByUserIdAndYearRangeAndMonth(
                userId, fiscalYearStartYear, endYear, fiscalYearStartMonth);
            List<GrossProfitEntity> grossProfitEntities = grossProfitRepository.findByUserIdAndYearRangeAndMonth(
                userId, fiscalYearStartYear, endYear, fiscalYearStartMonth);
            List<OperatingProfitEntity> operatingProfitEntities = operatingProfitRepository.findByUserIdAndYearRangeAndMonth(
                userId, fiscalYearStartYear, endYear, fiscalYearStartMonth);

            // EntityをSchemaに変換
            List<SaleSchema> saleSchemas = convertToSaleSchemas(salesEntities);
            List<GrossProfitSchema> grossProfitSchemas = convertToGrossProfitSchemas(grossProfitEntities);
            List<OperatingProfitSchema> operatingProfitSchemas = convertToOperatingProfitSchemas(operatingProfitEntities);

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
     * SalesEntityのリストをSaleSchemaのリストに変換
     */
    private List<SaleSchema> convertToSaleSchemas(List<SalesEntity> entities) {
        List<SaleSchema> schemas = new ArrayList<>();
        for (SalesEntity entity : entities) {
            SaleSchema schema = new SaleSchema();
            schema.setUserId(entity.getUserId());
            schema.setYear(entity.getYear());
            schema.setMonth(entity.getMonth());
            schema.setSaleTarget(entity.getSaleTarget() != null ? BigDecimal.valueOf(entity.getSaleTarget()) : null);
            schema.setSaleResult(entity.getSaleResult() != null ? BigDecimal.valueOf(entity.getSaleResult()) : null);
            schemas.add(schema);
        }
        return schemas;
    }

    /**
     * GrossProfitEntityのリストをGrossProfitSchemaのリストに変換
     */
    private List<GrossProfitSchema> convertToGrossProfitSchemas(List<GrossProfitEntity> entities) {
        List<GrossProfitSchema> schemas = new ArrayList<>();
        for (GrossProfitEntity entity : entities) {
            GrossProfitSchema schema = new GrossProfitSchema();
            schema.setUserId(entity.getUserId());
            schema.setYear(entity.getYear());
            schema.setMonth(entity.getMonth());
            schema.setGrossProfitTarget(entity.getGrossProfitTarget() != null ? BigDecimal.valueOf(entity.getGrossProfitTarget()) : null);
            schema.setGrossProfitResult(entity.getGrossProfitResult() != null ? BigDecimal.valueOf(entity.getGrossProfitResult()) : null);
            schemas.add(schema);
        }
        return schemas;
    }

    /**
     * OperatingProfitEntityのリストをOperatingProfitSchemaのリストに変換
     */
    private List<OperatingProfitSchema> convertToOperatingProfitSchemas(List<OperatingProfitEntity> entities) {
        List<OperatingProfitSchema> schemas = new ArrayList<>();
        for (OperatingProfitEntity entity : entities) {
            OperatingProfitSchema schema = new OperatingProfitSchema();
            schema.setUserId(entity.getUserId());
            schema.setYear(entity.getYear());
            schema.setMonth(entity.getMonth());
            schema.setOperatingProfitTarget(entity.getOperatingProfitTarget() != null ? BigDecimal.valueOf(entity.getOperatingProfitTarget()) : null);
            schema.setOperatingProfitResult(entity.getOperatingProfitResult() != null ? BigDecimal.valueOf(entity.getOperatingProfitResult()) : null);
            schemas.add(schema);
        }
        return schemas;
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
                        // target_yearはそのまま使用（絶対年として）
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
                            // target_yearはそのまま使用（絶対年として）
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
