package com.example.Kanaeru_Back.repository;

import com.example.Kanaeru_Back.entity.SettingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SettingRepository extends JpaRepository<SettingEntity, String> {
    
    Optional<SettingEntity> findByUserId(String userId);
}

