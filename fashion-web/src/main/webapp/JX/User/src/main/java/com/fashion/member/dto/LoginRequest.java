package com.fashion.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * 會員登入請求DTO
 */
public class LoginRequest {
    
    @NotBlank(message = "電子郵件不能為空")
    @Email(message = "請輸入有效的電子郵件地址")
    private String email;
    
    @NotBlank(message = "密碼不能為空")
    private String password;
    
    // 建構子
    public LoginRequest() {}
    
    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
    
    // Getter 和 Setter
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
}
