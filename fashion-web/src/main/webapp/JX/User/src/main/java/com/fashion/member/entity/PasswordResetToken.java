package com.fashion.member.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

/**
 * 密碼重設令牌實體類
 */
@Entity
@Table(name = "password_reset_tokens")
public class PasswordResetToken {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;
    
    @NotBlank(message = "令牌不能為空")
    @Column(unique = true, nullable = false)
    private String token;
    
    @Column(name = "expires_at", nullable = false)
    private LocalDateTime expiresAt;
    
    @Column(name = "used", nullable = false)
    private Boolean used = false;
    
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    // 建構子
    public PasswordResetToken() {}
    
    public PasswordResetToken(Member member, String token, LocalDateTime expiresAt) {
        this.member = member;
        this.token = token;
        this.expiresAt = expiresAt;
        this.createdAt = LocalDateTime.now();
    }
    
    // 檢查令牌是否過期
    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expiresAt);
    }
    
    // 檢查令牌是否有效（未使用且未過期）
    public boolean isValid() {
        return !used && !isExpired();
    }
    
    // Getter 和 Setter
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Member getMember() {
        return member;
    }
    
    public void setMember(Member member) {
        this.member = member;
    }
    
    public String getToken() {
        return token;
    }
    
    public void setToken(String token) {
        this.token = token;
    }
    
    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }
    
    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }
    
    public Boolean getUsed() {
        return used;
    }
    
    public void setUsed(Boolean used) {
        this.used = used;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
