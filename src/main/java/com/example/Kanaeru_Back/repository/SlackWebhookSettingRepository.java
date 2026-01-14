package com.example.Kanaeru_Back.repository;

import com.example.Kanaeru_Back.entity.SlackWebhookSettingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SlackWebhookSettingRepository extends JpaRepository<SlackWebhookSettingEntity, String> {
    
    Optional<SlackWebhookSettingEntity> findByUserId(String userId);
}
