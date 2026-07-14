package com.example.Kanaeru_Back.repository;

import com.example.Kanaeru_Back.entity.SlackUserMappingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SlackUserMappingRepository extends JpaRepository<SlackUserMappingEntity, String> {

    Optional<SlackUserMappingEntity> findBySlackUserIdAndDelFlg(String slackUserId, String delFlg);

    Optional<SlackUserMappingEntity> findByUserIdAndDelFlg(String userId, String delFlg);
}
