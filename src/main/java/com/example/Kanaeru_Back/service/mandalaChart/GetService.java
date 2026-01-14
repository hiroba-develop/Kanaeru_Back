package com.example.Kanaeru_Back.service.mandalaChart;

import com.example.Kanaeru_Back.entity.LargeGoalEntity;
import com.example.Kanaeru_Back.entity.MainGoalEntity;
import com.example.Kanaeru_Back.entity.MandalaChartEntity;
import com.example.Kanaeru_Back.entity.MiddleGoalEntity;
import com.example.Kanaeru_Back.model.ApiMandalaChartsGet200Response;
import com.example.Kanaeru_Back.model.ApiMandalaChartsGet200ResponseChartsInner;
import com.example.Kanaeru_Back.model.ApiMandalaChartsGet200ResponseChartsInnerMainGoal;
import com.example.Kanaeru_Back.model.LargeGoalSchema;
import com.example.Kanaeru_Back.model.LargeGoalSchemaMiddleGoalsProgressInner;
import com.example.Kanaeru_Back.repository.LargeGoalRepository;
import com.example.Kanaeru_Back.repository.MainGoalRepository;
import com.example.Kanaeru_Back.repository.MandalaChartRepository;
import com.example.Kanaeru_Back.repository.MiddleGoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("mandalaChartGetService")
public class GetService {

    @Autowired
    private MandalaChartRepository mandalaChartRepository;

    @Autowired
    private MainGoalRepository mainGoalRepository;

    @Autowired
    private LargeGoalRepository largeGoalRepository;

    @Autowired
    private MiddleGoalRepository middleGoalRepository;

    @Transactional(readOnly = true)
    public ApiMandalaChartsGet200Response getMandalaCharts(String userId) {
        ApiMandalaChartsGet200Response response = new ApiMandalaChartsGet200Response();

        try {
            // IS_ACTIVE='1'かつDEL_FLG='0'のマンダラチャートを1件取得
            Optional<MandalaChartEntity> mandalaChartOptional = 
                    mandalaChartRepository.findFirstByUserIdAndIsActiveAndDelFlgOrderByCreatedAtDesc(userId, "1", "0");

            List<ApiMandalaChartsGet200ResponseChartsInner> charts = new ArrayList<>();

            if (mandalaChartOptional.isPresent()) {
                MandalaChartEntity mandalaChart = mandalaChartOptional.get();

                // メイン目標を取得
                Optional<MainGoalEntity> mainGoalOptional = 
                        mainGoalRepository.findByChartIdAndDelFlg(mandalaChart.getChartId(), "0");

                ApiMandalaChartsGet200ResponseChartsInner chartInner = 
                        new ApiMandalaChartsGet200ResponseChartsInner();
                chartInner.setChartId(mandalaChart.getChartId());
                chartInner.setStartYearMonth(mandalaChart.getStartYearMonth() != null ? 
                        String.valueOf(mandalaChart.getStartYearMonth()) : null);
                chartInner.setEndYearMonth(mandalaChart.getEndYearMonth() != null ? 
                        String.valueOf(mandalaChart.getEndYearMonth()) : null);
                chartInner.setIsActive("1".equals(mandalaChart.getIsActive()));
                chartInner.setCreatedAt(mandalaChart.getCreatedAt());

                // メイン目標情報を設定
                if (mainGoalOptional.isPresent()) {
                    MainGoalEntity mainGoal = mainGoalOptional.get();
                    ApiMandalaChartsGet200ResponseChartsInnerMainGoal mainGoalResponse = 
                            new ApiMandalaChartsGet200ResponseChartsInnerMainGoal();
                    mainGoalResponse.setMainGoalId(mainGoal.getMainGoalId());
                    mainGoalResponse.setGoalTitle(mainGoal.getGoalTitle());
                    chartInner.setMainGoal(mainGoalResponse);

                    // 大目標情報を取得（position順にソート）
                    List<LargeGoalEntity> largeGoalEntities = 
                            largeGoalRepository.findByMainGoalIdAndDelFlgOrderByPositionAsc(
                                    mainGoal.getMainGoalId(), "0");

                    // LargeGoalEntityからLargeGoalSchemaへのマッピング
                    List<LargeGoalSchema> largeGoalSchemas = largeGoalEntities.stream()
                            .map(this::convertToLargeGoalSchema)
                            .collect(Collectors.toList());

                    chartInner.setLargeGoals(largeGoalSchemas);
                }

                charts.add(chartInner);
            }

            response.setCharts(charts);
            response.setResponseStatus(1);
        } catch (Exception e) {
            response.setResponseStatus(0);
            response.setCharts(new ArrayList<>());
        }

        return response;
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
