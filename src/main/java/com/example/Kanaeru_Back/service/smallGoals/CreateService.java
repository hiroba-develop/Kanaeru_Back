package com.example.Kanaeru_Back.service.smallGoals;

import com.example.Kanaeru_Back.entity.LargeGoalEntity;
import com.example.Kanaeru_Back.entity.MiddleGoalEntity;
import com.example.Kanaeru_Back.entity.SmallGoalEntity;
import com.example.Kanaeru_Back.model.ApiSmallGoalsMiddleGoalIdCreatePost200Response;
import com.example.Kanaeru_Back.model.ApiSmallGoalsMiddleGoalIdCreatePostRequest;
import com.example.Kanaeru_Back.repository.LargeGoalRepository;
import com.example.Kanaeru_Back.repository.MiddleGoalRepository;
import com.example.Kanaeru_Back.repository.SmallGoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service("smallGoalCreateService")
public class CreateService {

    @Autowired
    private SmallGoalRepository smallGoalRepository;

    @Autowired
    private MiddleGoalRepository middleGoalRepository;

    @Autowired
    private LargeGoalRepository largeGoalRepository;

    @Transactional
    public ApiSmallGoalsMiddleGoalIdCreatePost200Response createSmallGoal(
            String middleGoalId, ApiSmallGoalsMiddleGoalIdCreatePostRequest request) {
        ApiSmallGoalsMiddleGoalIdCreatePost200Response response = new ApiSmallGoalsMiddleGoalIdCreatePost200Response();

        try {
            // SMALL_GOAL_IDをUUIDで生成
            String newSmallGoalId = UUID.randomUUID().toString();

            // SMALL_GOALSテーブルにレコードを登録
            SmallGoalEntity smallGoalEntity = new SmallGoalEntity();
            smallGoalEntity.setSmallGoalId(newSmallGoalId);
            smallGoalEntity.setMiddleGoalId(middleGoalId);
            smallGoalEntity.setPosition(request.getPosition());
            smallGoalEntity.setGoalTitle(request.getGoalTitle());
            smallGoalEntity.setGoalDescription(request.getGoalDescription());
            smallGoalEntity.setIsCompleted("0");
            smallGoalEntity.setCompletedAt(null);
            smallGoalEntity.setDelFlg("0");
            smallGoalEntity.setCreatedAt(LocalDateTime.now());
            smallGoalEntity.setUpdatedAt(LocalDateTime.now());
            smallGoalRepository.save(smallGoalEntity);

            // 中目標のPROGRESSを再計算
            updateMiddleGoalProgress(middleGoalId);

            response.setResponseStatus(1);
            response.setSmallGoalId(newSmallGoalId);
        } catch (Exception e) {
            response.setResponseStatus(0);
        }

        return response;
    }

    /**
     * 中目標の進捗を計算して更新し、その後大目標の進捗も更新
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

                // 同じ中目標に紐づく小目標を取得(DEL_FLG='0')
                List<SmallGoalEntity> smallGoals = 
                        smallGoalRepository.findByMiddleGoalIdAndDelFlgOrderByPositionAsc(middleGoalId, "0");

                // 設定済みの小目標の数
                int totalCount = smallGoals.size();

                if (totalCount == 0) {
                    // 小目標が0件の場合はprogressを0に設定
                    middleGoal.setProgress(0);
                } else {
                    // 達成している小目標の数(IS_COMPLETED='1')
                    long completedCount = smallGoals.stream()
                            .filter(sg -> "1".equals(sg.getIsCompleted()))
                            .count();

                    // progress = (達成している数 / 設定済みの数) * 100 を計算(整数に変換)
                    int progress = (int) Math.round((completedCount * 100.0) / totalCount);
                    middleGoal.setProgress(progress);
                }

                middleGoal.setUpdatedAt(LocalDateTime.now());
                middleGoalRepository.save(middleGoal);

                // 大目標のPROGRESSも再計算
                updateLargeGoalProgress(middleGoal.getLargeGoalId());
            }
        } catch (Exception e) {
            // 進捗更新のエラーはログに記録するが、小目標の作成処理は成功とする
            // エラーハンドリングは必要に応じて追加
        }
    }

    /**
     * 大目標の進捗を計算して更新
     * 
     * @param largeGoalId 大目標ID
     */
    private void updateLargeGoalProgress(String largeGoalId) {
        try {
            // 大目標を取得
            Optional<LargeGoalEntity> largeGoalOptional = 
                    largeGoalRepository.findById(largeGoalId);

            if (largeGoalOptional.isPresent()) {
                LargeGoalEntity largeGoal = largeGoalOptional.get();

                // DEL_FLGが'0'でない場合はスキップ
                if (!"0".equals(largeGoal.getDelFlg())) {
                    return;
                }

                // 同じ大目標に紐づく中目標を取得(DEL_FLG='0')
                List<MiddleGoalEntity> middleGoals = 
                        middleGoalRepository.findByLargeGoalIdAndDelFlgOrderByPositionAsc(largeGoalId, "0");

                // 設定済みの中目標の数
                int totalCount = middleGoals.size();

                if (totalCount == 0) {
                    // 中目標が0件の場合はprogressを0に設定
                    largeGoal.setProgress(0);
                } else {
                    // 各中目標のprogressの合計を計算
                    int totalProgress = middleGoals.stream()
                            .mapToInt(MiddleGoalEntity::getProgress)
                            .sum();

                    // progress = (中目標のprogress合計 / 中目標の数) を計算(整数に変換)
                    int progress = (int) Math.round((double) totalProgress / totalCount);
                    largeGoal.setProgress(progress);
                }

                largeGoal.setUpdatedAt(LocalDateTime.now());
                largeGoalRepository.save(largeGoal);
            }
        } catch (Exception e) {
            // 進捗更新のエラーはログに記録するが、処理は成功とする
            // エラーハンドリングは必要に応じて追加
        }
    }
}
