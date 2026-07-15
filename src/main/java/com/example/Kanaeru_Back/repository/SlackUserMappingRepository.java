package com.example.Kanaeru_Back.repository;

import com.example.Kanaeru_Back.entity.SlackUserMappingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SlackUserMappingRepository extends JpaRepository<SlackUserMappingEntity, String> {

    // 旧・手動Slack Member ID入力機能（OAuthに一本化予定のため今回は非改修）が使用中のため残置
    Optional<SlackUserMappingEntity> findBySlackUserIdAndDelFlg(String slackUserId, String delFlg);

    Optional<SlackUserMappingEntity> findByUserIdAndDelFlg(String userId, String delFlg);

    Optional<SlackUserMappingEntity> findByWorkspaceIdAndSlackUserIdAndDelFlg(
            String workspaceId, String slackUserId, String delFlg);
}
