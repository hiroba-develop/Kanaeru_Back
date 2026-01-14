package com.example.Kanaeru_Back.service.mandalaChart;

import com.example.Kanaeru_Back.entity.MainGoalEntity;
import com.example.Kanaeru_Back.entity.MandalaChartEntity;
import com.example.Kanaeru_Back.model.ApiAuthRegistrationUserPost200Response;
import com.example.Kanaeru_Back.model.ApiMandalaChartsCreatePostRequest;
import com.example.Kanaeru_Back.repository.MainGoalRepository;
import com.example.Kanaeru_Back.repository.MandalaChartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service("mandalaChartCreateService")
public class CreateService {

    @Autowired
    private MandalaChartRepository mandalaChartRepository;

    @Autowired
    private MainGoalRepository mainGoalRepository;

    @Transactional
    public ApiAuthRegistrationUserPost200Response createMandalaChart(
            ApiMandalaChartsCreatePostRequest request) {
        ApiAuthRegistrationUserPost200Response response = new ApiAuthRegistrationUserPost200Response();

        try {
            // CHART_IDをUUIDで生成
            String chartId = UUID.randomUUID().toString();

            // MAIN_GOAL_IDをUUIDで生成
            String mainGoalId = UUID.randomUUID().toString();

            // start_year_monthをStringからIntegerに変換
            if (request.getMainGoal() == null || request.getMainGoal().getStartYearMonth() == null) {
                response.setResponseStatus(0);
                return response;
            }

            Integer startYearMonth;
            try {
                startYearMonth = Integer.parseInt(request.getMainGoal().getStartYearMonth());
            } catch (NumberFormatException e) {
                response.setResponseStatus(0);
                return response;
            }

            // MANDALA_CHARTSテーブルにレコードを登録
            MandalaChartEntity mandalaChartEntity = new MandalaChartEntity();
            mandalaChartEntity.setChartId(chartId);
            mandalaChartEntity.setUserId(request.getUserId());
            mandalaChartEntity.setStartYearMonth(startYearMonth);
            mandalaChartEntity.setEndYearMonth(null);
            mandalaChartEntity.setIsActive("1");
            mandalaChartEntity.setDelFlg("0");
            mandalaChartEntity.setCreatedAt(LocalDateTime.now());
            mandalaChartEntity.setUpdatedAt(LocalDateTime.now());
            mandalaChartRepository.save(mandalaChartEntity);

            // MAIN_GOALSテーブルにレコードを登録
            MainGoalEntity mainGoalEntity = new MainGoalEntity();
            mainGoalEntity.setMainGoalId(mainGoalId);
            mainGoalEntity.setChartId(chartId);
            mainGoalEntity.setGoalTitle(request.getMainGoal().getGoalTitle());
            mainGoalEntity.setGoalDescription(null);
            mainGoalEntity.setDelFlg("0");
            mainGoalEntity.setCreatedAt(LocalDateTime.now());
            mainGoalEntity.setUpdatedAt(LocalDateTime.now());
            mainGoalRepository.save(mainGoalEntity);

            response.setResponseStatus(1);
        } catch (Exception e) {
            response.setResponseStatus(0);
        }

        return response;
    }
}
