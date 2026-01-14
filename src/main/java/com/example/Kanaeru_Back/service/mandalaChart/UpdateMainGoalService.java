package com.example.Kanaeru_Back.service.mandalaChart;

import com.example.Kanaeru_Back.entity.MainGoalEntity;
import com.example.Kanaeru_Back.entity.MandalaChartEntity;
import com.example.Kanaeru_Back.model.ApiAuthLogoutPost200Response;
import com.example.Kanaeru_Back.model.ApiMandalaChartsCreatePostRequestMainGoal;
import com.example.Kanaeru_Back.repository.MainGoalRepository;
import com.example.Kanaeru_Back.repository.MandalaChartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UpdateMainGoalService {

    @Autowired
    private MainGoalRepository mainGoalRepository;

    @Autowired
    private MandalaChartRepository mandalaChartRepository;

    @Transactional
    public ApiAuthLogoutPost200Response updateMainGoal(
            String chartId, ApiMandalaChartsCreatePostRequestMainGoal request) {
        ApiAuthLogoutPost200Response response = new ApiAuthLogoutPost200Response();

        try {
            // チャートIDでマンダラチャートを取得
            Optional<MandalaChartEntity> mandalaChartOptional = 
                    mandalaChartRepository.findById(chartId);

            if (!mandalaChartOptional.isPresent() || !"0".equals(mandalaChartOptional.get().getDelFlg())) {
                response.setResponseStatus(0);
                return response;
            }

            MandalaChartEntity mandalaChart = mandalaChartOptional.get();

            // start_year_monthを更新
            if (request.getStartYearMonth() != null) {
                try {
                    Integer startYearMonth = Integer.parseInt(request.getStartYearMonth());
                    mandalaChart.setStartYearMonth(startYearMonth);
                    mandalaChart.setUpdatedAt(LocalDateTime.now());
                    mandalaChartRepository.save(mandalaChart);
                } catch (NumberFormatException e) {
                    response.setResponseStatus(0);
                    return response;
                }
            }

            // チャートIDでメイン目標を取得（既存レコードを取得）
            Optional<MainGoalEntity> mainGoalOptional = 
                    mainGoalRepository.findByChartIdAndDelFlg(chartId, "0");

            if (mainGoalOptional.isPresent()) {
                MainGoalEntity mainGoal = mainGoalOptional.get();
                
                // エンティティのIDが設定されていることを確認（更新として扱われるため）
                if (mainGoal.getMainGoalId() == null || mainGoal.getMainGoalId().isEmpty()) {
                    response.setResponseStatus(0);
                    return response;
                }

                // メイン目標を更新（既存のエンティティのフィールドを更新）
                if (request.getGoalTitle() != null) {
                    mainGoal.setGoalTitle(request.getGoalTitle());
                }
                mainGoal.setUpdatedAt(LocalDateTime.now());

                // 既存のエンティティを更新（IDが設定されているので更新として扱われる）
                mainGoalRepository.save(mainGoal);
            }

            response.setResponseStatus(1);
        } catch (Exception e) {
            response.setResponseStatus(0);
        }

        return response;
    }
}
