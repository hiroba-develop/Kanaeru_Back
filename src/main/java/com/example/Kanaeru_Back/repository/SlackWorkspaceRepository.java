package com.example.Kanaeru_Back.repository;

import com.example.Kanaeru_Back.entity.SlackWorkspaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface SlackWorkspaceRepository extends JpaRepository<SlackWorkspaceEntity, String> {
    Optional<SlackWorkspaceEntity> findByTeamIdAndDelFlg(String teamId, String delFlg);
    Optional<SlackWorkspaceEntity> findByWorkspaceIdAndDelFlg(String workspaceId, String delFlg);

    // TEAM_IDはDEL_FLGに関わらずテーブル全体でUNIQUEのため、DEL_FLG不問でOAuth再インストール時のUPSERT判定に使う
    Optional<SlackWorkspaceEntity> findByTeamId(String teamId);
}
