package com.fashion.member.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 會員實體類
 */
@Entity
@Table(name = "members")
public class Member {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "帳號不能為空")
    @Size(min = 3, max = 20, message = "帳號長度必須在3-20字元之間")
    @Column(unique = true, nullable = false)
    private String account;
    
    @NotBlank(message = "電子郵件不能為空")
    @Email(message = "請輸入有效的電子郵件地址")
    @Column(unique = true, nullable = false)
    private String email;
    
    @NotBlank(message = "姓名不能為空")
    @Size(min = 2, max = 50, message = "姓名長度必須在2-50字元之間")
    @Column(nullable = false)
    private String name;
    
    @NotBlank(message = "密碼不能為空")
    @Size(min = 6, message = "密碼至少需要6個字元")
    @Column(nullable = false)
    private String password;
    
    @Column(name = "member_level", nullable = false)
    private String memberLevel = "一般會員";
    
    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;
    
    @Column(name = "email_verified", nullable = false)
    private Boolean emailVerified = false;
    
    @Column(name = "marketing_consent")
    private Boolean marketingConsent = false;
    
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Address> addresses = new ArrayList<>();
    
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PasswordResetToken> passwordResetTokens = new ArrayList<>();
    
    // 建構子
    public Member() {}
    
    public Member(String account, String email, String name, String password) {
        this.account = account;
        this.email = email;
        this.name = name;
        this.password = password;
    }
    
    // Getter 和 Setter
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getAccount() {
        return account;
    }
    
    public void setAccount(String account) {
        this.account = account;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getMemberLevel() {
        return memberLevel;
    }
    
    public void setMemberLevel(String memberLevel) {
        this.memberLevel = memberLevel;
    }
    
    public Boolean getIsActive() {
        return isActive;
    }
    
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
    
    public Boolean getEmailVerified() {
        return emailVerified;
    }
    
    public void setEmailVerified(Boolean emailVerified) {
        this.emailVerified = emailVerified;
    }
    
    public Boolean getMarketingConsent() {
        return marketingConsent;
    }
    
    public void setMarketingConsent(Boolean marketingConsent) {
        this.marketingConsent = marketingConsent;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    public List<Address> getAddresses() {
        return addresses;
    }
    
    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }
    
    public List<PasswordResetToken> getPasswordResetTokens() {
        return passwordResetTokens;
    }
    
    public void setPasswordResetTokens(List<PasswordResetToken> passwordResetTokens) {
        this.passwordResetTokens = passwordResetTokens;
    }
}
