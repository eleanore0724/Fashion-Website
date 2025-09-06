package com.fashion.member.repository;

import com.fashion.member.entity.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * 密碼重設令牌Repository
 */
@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    
    /**
     * 根據令牌查找密碼重設令牌
     */
    Optional<PasswordResetToken> findByToken(String token);
    
    /**
     * 根據會員ID查找所有密碼重設令牌
     */
    List<PasswordResetToken> findByMemberId(Long memberId);
    
    /**
     * 查找有效的密碼重設令牌
     */
    @Query("SELECT prt FROM PasswordResetToken prt WHERE prt.token = :token AND prt.used = false AND prt.expiresAt > :now")
    Optional<PasswordResetToken> findValidToken(@Param("token") String token, @Param("now") LocalDateTime now);
    
    /**
     * 刪除過期的令牌
     */
    @Query("DELETE FROM PasswordResetToken prt WHERE prt.expiresAt < :now")
    void deleteExpiredTokens(@Param("now") LocalDateTime now);
    
    /**
     * 根據會員ID刪除所有令牌
     */
    void deleteByMemberId(Long memberId);
}
