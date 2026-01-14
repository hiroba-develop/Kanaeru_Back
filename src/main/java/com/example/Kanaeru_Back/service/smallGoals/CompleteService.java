package com.example.Kanaeru_Back.service.smallGoals;

import com.example.Kanaeru_Back.entity.MiddleGoalEntity;
import com.example.Kanaeru_Back.entity.SmallGoalEntity;
import com.example.Kanaeru_Back.model.ApiSmallGoalsSmallGoalIdCompletePut200Response;
import com.example.Kanaeru_Back.repository.MiddleGoalRepository;
import com.example.Kanaeru_Back.repository.SmallGoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service("smallGoalCompleteService")
public class CompleteService {

    @Autowired
    private SmallGoalRepository smallGoalRepository;

    @Autowired
    private MiddleGoalRepository middleGoalRepository;

    @Transactional
    public ApiSmallGoalsSmallGoalIdCompletePut200Response completeSmallGoal(String smallGoalId) {
        ApiSmallGoalsSmallGoalIdCompletePut200Response response = new ApiSmallGoalsSmallGoalIdCompletePut200Response();

        try {
            // smallGoalIdで小目標を取得（既存レコードを取得）
            Optional<SmallGoalEntity> smallGoalOptional = 
                    smallGoalRepository.findById(smallGoalId);

            if (smallGoalOptional.isPresent()) {
                SmallGoalEntity smallGoal = smallGoalOptional.get();
                
                // DEL_FLGが'0'でない場合はエラー
                if (!"0".equals(smallGoal.getDelFlg())) {
                    response.setResponseStatus(0);
                    return response;
                }

                // 完了/未完了を切り替え
                String currentIsCompleted = smallGoal.getIsCompleted();
                if ("0".equals(currentIsCompleted)) {
                    // 未完了 → 完了
                    smallGoal.setIsCompleted("1");
                    smallGoal.setCompletedAt(LocalDateTime.now());
                    response.setIsCompleted(true);
                    response.setCompletedAt(smallGoal.getCompletedAt());
                } else {
                    // 完了 → 未完了
                    smallGoal.setIsCompleted("0");
                    smallGoal.setCompletedAt(null);
                    response.setIsCompleted(false);
                    response.setCompletedAt(null);
                }
                
                smallGoal.setUpdatedAt(LocalDateTime.now());

                // 既存のエンティティを更新（IDが設定されているので更新として扱われる）
                smallGoalRepository.save(smallGoal);

                // 同じ中目標に紐づく小目標の進捗を計算して中目標のprogressを更新
                updateMiddleGoalProgress(smallGoal.getMiddleGoalId());

                response.setResponseStatus(1);
            } else {
                // 小目標が見つからない場合
                response.setResponseStatus(0);
            }
        } catch (Exception e) {
            response.setResponseStatus(0);
        }

        return response;
    }

    /**
     * 中目標の進捗を計算して更新
     * 
     * @param middleGoalId 中目標ID
     */
    private void updateMiddleGoalProgress(String middleGoalId) {
        try {
            // 中目標を取得
            Optional<MiddleGoalEntity> middleGoalOptional = 
                    middleGoalRepository.findById(middleGoalId);

            if (middleGoalOptional.isPresent()) {
                MiddleGoalEntity middleGoal = middleGoalOptional.get();

                // DEL_FLGが'0'でない場合はスキップ
                if (!"0".equals(middleGoal.getDelFlg())) {
                    return;
                }

                // 同じ中目標に紐づく小目標を取得（DEL_FLG='0'）
                List<SmallGoalEntity> smallGoals = 
                        smallGoalRepository.findByMiddleGoalIdAndDelFlgOrderByPositionAsc(middleGoalId, "0");

                // 設定済みの小目標の数
                int totalCount = smallGoals.size();

                if (totalCount == 0) {
                    // 小目標が0件の場合はprogressを0に設定
                    middleGoal.setProgress(0);
                } else {
                    // 達成している小目標の数（IS_COMPLETED='1'）
                    long completedCount = smallGoals.stream()
                            .filter(sg -> "1".equals(sg.getIsCompleted()))
                            .count();

                    // progress = (達成している数 / 設定済みの数) * 100 を計算（整数に変換）
                    int progress = (int) Math.round((completedCount * 100.0) / totalCount);
                    middleGoal.setProgress(progress);
                }

                middleGoal.setUpdatedAt(LocalDateTime.now());
                middleGoalRepository.save(middleGoal);
            }
        } catch (Exception e) {
            // 進捗更新のエラーはログに記録するが、小目標の完了処理は成功とする
            // エラーハンドリングは必要に応じて追加
        }
    }
}
