package com.example.Kanaeru_Back.repository;

import com.example.Kanaeru_Back.entity.SlackMessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SlackMessageRepository extends JpaRepository<SlackMessageEntity, String> {

    boolean existsByWorkspaceIdAndSlackTs(String workspaceId, String slackTs);
}
