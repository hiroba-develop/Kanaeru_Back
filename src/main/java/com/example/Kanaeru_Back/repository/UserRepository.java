package com.example.Kanaeru_Back.repository;

import com.example.Kanaeru_Back.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    
    Optional<UserEntity> findByEmail(String email);
    
    Optional<UserEntity> findByEmailAndPasswordHash(String email, String passwordHash);
}

