package com.fashion.member.repository;

import com.fashion.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 會員資料Repository
 */
@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    
    /**
     * 根據電子郵件查找會員
     */
    Optional<Member> findByEmail(String email);
    
    /**
     * 根據帳號查找會員
     */
    Optional<Member> findByAccount(String account);
    
    /**
     * 檢查電子郵件是否已存在
     */
    boolean existsByEmail(String email);
    
    /**
     * 檢查帳號是否已存在
     */
    boolean existsByAccount(String account);
    
    /**
     * 根據電子郵件和密碼查找會員
     */
    Optional<Member> findByEmailAndPassword(String email, String password);
    
    /**
     * 根據電子郵件和啟用狀態查找會員
     */
    Optional<Member> findByEmailAndIsActive(String email, Boolean isActive);
}
