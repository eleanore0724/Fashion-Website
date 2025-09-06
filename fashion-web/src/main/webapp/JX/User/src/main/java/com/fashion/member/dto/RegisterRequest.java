package com.fashion.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * 會員註冊請求DTO
 */
public class RegisterRequest {
    
    @NotBlank(message = "帳號不能為空")
    @Size(min = 3, max = 20, message = "帳號長度必須在3-20字元之間")
    private String account;
    
    @NotBlank(message = "電子郵件不能為空")
    @Email(message = "請輸入有效的電子郵件地址")
    private String email;
    
    @NotBlank(message = "姓名不能為空")
    @Size(min = 2, max = 50, message = "姓名長度必須在2-50字元之間")
    private String name;
    
    @NotBlank(message = "密碼不能為空")
    @Size(min = 6, message = "密碼至少需要6個字元")
    private String password;
    
    @NotBlank(message = "確認密碼不能為空")
    private String confirmPassword;
    
    // 收件地址資訊
    @NotBlank(message = "收件人姓名不能為空")
    @Size(max = 50, message = "收件人姓名不能超過50字元")
    private String recipientName;
    
    @NotBlank(message = "聯絡電話不能為空")
    @Size(max = 20, message = "聯絡電話不能超過20字元")
    private String recipientPhone;
    
    @NotBlank(message = "縣市不能為空")
    @Size(max = 20, message = "縣市不能超過20字元")
    private String city;
    
    @NotBlank(message = "區域不能為空")
    @Size(max = 20, message = "區域不能超過20字元")
    private String district;
    
    @NotBlank(message = "詳細地址不能為空")
    @Size(max = 200, message = "詳細地址不能超過200字元")
    private String addressDetail;
    
    // 服務條款同意
    private Boolean agreeTerms = false;
    private Boolean agreeMarketing = false;
    
    // 建構子
    public RegisterRequest() {}
    
    // Getter 和 Setter
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
    
    public String getConfirmPassword() {
        return confirmPassword;
    }
    
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
    
    public String getRecipientName() {
        return recipientName;
    }
    
    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }
    
    public String getRecipientPhone() {
        return recipientPhone;
    }
    
    public void setRecipientPhone(String recipientPhone) {
        this.recipientPhone = recipientPhone;
    }
    
    public String getCity() {
        return city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    public String getDistrict() {
        return district;
    }
    
    public void setDistrict(String district) {
        this.district = district;
    }
    
    public String getAddressDetail() {
        return addressDetail;
    }
    
    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }
    
    public Boolean getAgreeTerms() {
        return agreeTerms;
    }
    
    public void setAgreeTerms(Boolean agreeTerms) {
        this.agreeTerms = agreeTerms;
    }
    
    public Boolean getAgreeMarketing() {
        return agreeMarketing;
    }
    
    public void setAgreeMarketing(Boolean agreeMarketing) {
        this.agreeMarketing = agreeMarketing;
    }
}
