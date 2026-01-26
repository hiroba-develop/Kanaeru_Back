package com.example.Kanaeru_Back.service.screenDisplay;

import com.example.Kanaeru_Back.entity.LargeGoalEntity;
import com.example.Kanaeru_Back.entity.MainGoalEntity;
import com.example.Kanaeru_Back.entity.MandalaChartEntity;
import com.example.Kanaeru_Back.entity.MiddleGoalEntity;
import com.example.Kanaeru_Back.entity.SettingEntity;
import com.example.Kanaeru_Back.model.ApiHomeGet200Response;
import com.example.Kanaeru_Back.model.GrossProfitSchema;
import com.example.Kanaeru_Back.model.LargeGoalSchema;
import com.example.Kanaeru_Back.model.LargeGoalSchemaMiddleGoalsProgressInner;
import com.example.Kanaeru_Back.model.MainGoalSchema;
import com.example.Kanaeru_Back.model.OperatingProfitSchema;
import com.example.Kanaeru_Back.model.SaleSchema;
import com.example.Kanaeru_Back.repository.GrossProfitRepository;
import com.example.Kanaeru_Back.repository.LargeGoalRepository;
import com.example.Kanaeru_Back.repository.MainGoalRepository;
import com.example.Kanaeru_Back.repository.MandalaChartRepository;
import com.example.Kanaeru_Back.repository.MiddleGoalRepository;
import com.example.Kanaeru_Back.repository.OperatingProfitRepository;
import com.example.Kanaeru_Back.repository.SalesRepository;
import com.example.Kanaeru_Back.repository.SettingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HomeService {

    private static final Logger logger = LoggerFactory.getLogger(HomeService.class);

    @Autowired
    private MandalaChartRepository mandalaChartRepository;

    @Autowired
    private MainGoalRepository mainGoalRepository;

    @Autowired
    private LargeGoalRepository largeGoalRepository;

    @Autowired
    private MiddleGoalRepository middleGoalRepository;

    @Autowired
    private SalesRepository salesRepository;

    @Autowired
    private GrossProfitRepository grossProfitRepository;

    @Autowired
    private OperatingProfitRepository operatingProfitRepository;

    @Autowired
    private SettingRepository settingRepository;

    /**
     * ホーム画面の初期表示データを取得
     * 
     * @param targetUserId 対象ユーザーID（role:0の場合は自分のユーザーID、role:1または2の場合は選択されているユーザーID）
     * @return レスポンス
     */
    @Transactional(readOnly = true)
    public ApiHomeGet200Response getHomeData(String targetUserId) {
        ApiHomeGet200Response response = new ApiHomeGet200Response();

        try {
            logger.debug("getHomeData called with targetUserId: {}", targetUserId);
            
            if (targetUserId == null || targetUserId.isEmpty()) {
                logger.warn("targetUserId is null or empty");
                response.setResponseStatus(0);
                return response;
            }

            // アクティブなマンダラチャートを取得
            Optional<MandalaChartEntity> mandalaChartOpt = 
                mandalaChartRepository.findFirstByUserIdAndIsActiveAndDelFlgOrderByCreatedAtDesc(targetUserId, "1", "0");

            MainGoalSchema mainGoalSchema = null;
            List<LargeGoalSchema> largeGoalSchemas = new ArrayList<>();

            if (mandalaChartOpt.isPresent()) {
                MandalaChartEntity mandalaChart = mandalaChartOpt.get();

                // メイン目標を取得
                Optional<MainGoalEntity> mainGoalOpt = 
                    mainGoalRepository.findByChartIdAndDelFlg(mandalaChart.getChartId(), "0");

                if (mainGoalOpt.isPresent()) {
                    MainGoalEntity mainGoalEntity = mainGoalOpt.get();

                    // MainGoalSchemaに変換
                    mainGoalSchema = convertToMainGoalSchema(mainGoalEntity);

                    // 全ての大目標を取得（position順）
                    List<LargeGoalEntity> largeGoalEntities = 
                        largeGoalRepository.findByMainGoalIdAndDelFlgOrderByPositionAsc(mainGoalEntity.getMainGoalId(), "0");

                    largeGoalSchemas = largeGoalEntities.stream()
                        .map(this::convertToLargeGoalSchema)
                        .collect(Collectors.toList());
                }
            }

            // SETTINGSテーブルから事業年度開始年月を取得
            Optional<SettingEntity> settingOpt = settingRepository.findByUserId(targetUserId);
            SaleSchema saleSchema = null;
            GrossProfitSchema grossProfitSchema = null;
            OperatingProfitSchema operatingProfitSchema = null;

            if (settingOpt.isPresent()) {
                SettingEntity setting = settingOpt.get();
                Integer fiscalYearStartYear = setting.getFiscalYearStartYear();
                Integer fiscalYearStartMonth = setting.getFiscalYearStartMonth();
                
                if (fiscalYearStartYear != null && fiscalYearStartMonth != null) {
                    logger.debug("Fiscal year start: year={}, month={}", fiscalYearStartYear, fiscalYearStartMonth);

                    // 現在の日付から事業年度を計算
                    LocalDate now = LocalDate.now();
                    int currentYear = now.getYear();
                    int currentMonth = now.getMonthValue();
                    
                    // 事業年度の今年度を計算
                    // 例：事業年度開始月が8月の場合、8月～7月が1年度
                    int fiscalYear;
                    if (currentMonth >= fiscalYearStartMonth) {
                        // 事業年度開始月以降の場合、現在の年が事業年度の開始年
                        fiscalYear = currentYear;
                    } else {
                        // 事業年度開始月より前の場合、前年が事業年度の開始年
                        fiscalYear = currentYear - 1;
                    }

                    // 事業年度の今年度分の売上、粗利益、営業利益の目標・実績を合計して取得
                    // 事業年度開始月から12ヶ月分のデータを取得
                    Long saleTargetSum = 0L;
                    Long saleResultSum = 0L;
                    Long grossProfitTargetSum = 0L;
                    Long grossProfitResultSum = 0L;
                    Long operatingProfitTargetSum = 0L;
                    Long operatingProfitResultSum = 0L;

                    for (int month = 0; month < 12; month++) {
                        int targetYear = fiscalYear;
                        int targetMonth = fiscalYearStartMonth + month;
                        
                        // 月が12を超える場合、年を繰り上げ
                        if (targetMonth > 12) {
                            targetYear++;
                            targetMonth -= 12;
                        }

                        // 各月の目標・実績を合計
                        List<com.example.Kanaeru_Back.entity.SalesEntity> salesEntities = 
                            salesRepository.findByUserIdAndYearRangeAndMonth(targetUserId, targetYear, targetYear, targetMonth);
                        for (com.example.Kanaeru_Back.entity.SalesEntity salesEntity : salesEntities) {
                            if (salesEntity.getSaleTarget() != null) {
                                saleTargetSum += salesEntity.getSaleTarget();
                            }
                            if (salesEntity.getSaleResult() != null) {
                                saleResultSum += salesEntity.getSaleResult();
                            }
                        }

                        List<com.example.Kanaeru_Back.entity.GrossProfitEntity> grossProfitEntities = 
                            grossProfitRepository.findByUserIdAndYearRangeAndMonth(targetUserId, targetYear, targetYear, targetMonth);
                        for (com.example.Kanaeru_Back.entity.GrossProfitEntity grossProfitEntity : grossProfitEntities) {
                            if (grossProfitEntity.getGrossProfitTarget() != null) {
                                grossProfitTargetSum += grossProfitEntity.getGrossProfitTarget();
                            }
                            if (grossProfitEntity.getGrossProfitResult() != null) {
                                grossProfitResultSum += grossProfitEntity.getGrossProfitResult();
                            }
                        }

                        List<com.example.Kanaeru_Back.entity.OperatingProfitEntity> operatingProfitEntities = 
                            operatingProfitRepository.findByUserIdAndYearRangeAndMonth(targetUserId, targetYear, targetYear, targetMonth);
                        for (com.example.Kanaeru_Back.entity.OperatingProfitEntity operatingProfitEntity : operatingProfitEntities) {
                            if (operatingProfitEntity.getOperatingProfitTarget() != null) {
                                operatingProfitTargetSum += operatingProfitEntity.getOperatingProfitTarget();
                            }
                            if (operatingProfitEntity.getOperatingProfitResult() != null) {
                                operatingProfitResultSum += operatingProfitEntity.getOperatingProfitResult();
                            }
                        }
                    }

                    // Schemaに変換
                    saleSchema = new SaleSchema();
                    saleSchema.setUserId(targetUserId);
                    saleSchema.setYear(fiscalYear);
                    saleSchema.setMonth(fiscalYearStartMonth);
                    saleSchema.setSaleTarget(BigDecimal.valueOf(saleTargetSum));
                    saleSchema.setSaleResult(BigDecimal.valueOf(saleResultSum));

                    grossProfitSchema = new GrossProfitSchema();
                    grossProfitSchema.setUserId(targetUserId);
                    grossProfitSchema.setYear(fiscalYear);
                    grossProfitSchema.setMonth(fiscalYearStartMonth);
                    grossProfitSchema.setGrossProfitTarget(BigDecimal.valueOf(grossProfitTargetSum));
                    grossProfitSchema.setGrossProfitResult(BigDecimal.valueOf(grossProfitResultSum));

                    operatingProfitSchema = new OperatingProfitSchema();
                    operatingProfitSchema.setUserId(targetUserId);
                    operatingProfitSchema.setYear(fiscalYear);
                    operatingProfitSchema.setMonth(fiscalYearStartMonth);
                    operatingProfitSchema.setOperatingProfitTarget(BigDecimal.valueOf(operatingProfitTargetSum));
                    operatingProfitSchema.setOperatingProfitResult(BigDecimal.valueOf(operatingProfitResultSum));
                }
            }

            // レスポンスに値をセット
            response.setResponseStatus(1);
            response.setMainGoalSchema(mainGoalSchema);
            response.setLargeGoalSchema(largeGoalSchemas != null && !largeGoalSchemas.isEmpty() ? largeGoalSchemas : new ArrayList<>());
            response.setSaleSchema(saleSchema);
            response.setGrossProfitSchema(grossProfitSchema);
            response.setOperatingProfitSchema(operatingProfitSchema);
            
            logger.info("Successfully retrieved home data for userId: {}, mainGoalSchema: {}, largeGoals count: {}, saleSchema: {}, grossProfitSchema: {}, operatingProfitSchema: {}", 
                targetUserId, 
                mainGoalSchema != null ? "present" : "null",
                largeGoalSchemas != null ? largeGoalSchemas.size() : 0,
                saleSchema != null ? "present" : "null",
                grossProfitSchema != null ? "present" : "null",
                operatingProfitSchema != null ? "present" : "null");

        } catch (Exception e) {
            logger.error("Error in getHomeData for userId: " + targetUserId, e);
            e.printStackTrace();
            response.setResponseStatus(0);
        }

        return response;
    }

    /**
     * MainGoalEntityをMainGoalSchemaに変換
     */
    private MainGoalSchema convertToMainGoalSchema(MainGoalEntity entity) {
        MainGoalSchema schema = new MainGoalSchema();
        schema.setMainGoalId(entity.getMainGoalId());
        schema.setChartId(entity.getChartId());
        schema.setGoalTitle(entity.getGoalTitle());
        return schema;
    }

    /**
     * LargeGoalEntityをLargeGoalSchemaに変換
     */
    private LargeGoalSchema convertToLargeGoalSchema(LargeGoalEntity entity) {
        LargeGoalSchema schema = new LargeGoalSchema();
        schema.setLargeGoalId(entity.getLargeGoalId());
        schema.setPosition(entity.getPosition());
        schema.setGoalTitle(entity.getGoalTitle());
        schema.setGoalDescription(entity.getGoalDescription());
        schema.setGoalType(entity.getGoalType());
        schema.setTargetYear(entity.getTargetYear());
        schema.setTargetAmount(entity.getTargetAmount());
        
        // progressはIntegerからBigDecimalに変換
        if (entity.getProgress() != null) {
            schema.setProgress(BigDecimal.valueOf(entity.getProgress()));
        }

        // 大目標に紐づく中目標のprogressをposition順に取得
        List<MiddleGoalEntity> middleGoalEntities = 
            middleGoalRepository.findByLargeGoalIdAndDelFlgOrderByPositionAsc(
                entity.getLargeGoalId(), "0");
        
        List<LargeGoalSchemaMiddleGoalsProgressInner> middleGoalsProgress = 
            middleGoalEntities.stream()
                .map(this::convertToMiddleGoalsProgressInner)
                .collect(Collectors.toList());
        
        schema.setMiddleGoalsProgress(middleGoalsProgress);

        return schema;
    }

    /**
     * MiddleGoalEntityをLargeGoalSchemaMiddleGoalsProgressInnerに変換
     */
    private LargeGoalSchemaMiddleGoalsProgressInner convertToMiddleGoalsProgressInner(
            MiddleGoalEntity entity) {
        LargeGoalSchemaMiddleGoalsProgressInner inner = 
            new LargeGoalSchemaMiddleGoalsProgressInner();
        
        // positionを設定
        inner.setPosition(entity.getPosition());
        
        // progressはIntegerからBigDecimalに変換
        if (entity.getProgress() != null) {
            inner.setProgress(BigDecimal.valueOf(entity.getProgress()));
        } else {
            inner.setProgress(BigDecimal.ZERO);
        }
        
        return inner;
    }
}
