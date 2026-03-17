package com.example.Kanaeru_Back.repository;

import com.example.Kanaeru_Back.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    
    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByUserIdAndDelFlg(String userId, String delFlg);
    
    Optional<UserEntity> findByEmailAndPasswordHash(String email, String passwordHash);
    
    Optional<UserEntity> findByResetToken(String resetToken);
    
    List<UserEntity> findByRoleAndDelFlg(String role, String delFlg);
    
    List<UserEntity> findByRoleInAndDelFlg(List<String> roles, String delFlg);

}

