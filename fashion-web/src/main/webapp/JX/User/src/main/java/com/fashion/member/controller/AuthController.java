package com.fashion.member.controller;

import com.fashion.member.dto.ApiResponse;
import com.fashion.member.dto.LoginRequest;
import com.fashion.member.dto.MemberInfo;
import com.fashion.member.dto.RegisterRequest;
import com.fashion.member.entity.Member;
import com.fashion.member.service.JwtService;
import com.fashion.member.service.MemberService;
import com.fashion.member.service.PasswordResetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 認證控制器
 */
@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    
    @Autowired
    private MemberService memberService;
    
    @Autowired
    private PasswordResetService passwordResetService;
    
    @Autowired
    private JwtService jwtService;
    
    /**
     * 會員註冊
     */
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Map<String, Object>>> register(@Valid @RequestBody RegisterRequest request) {
        try {
            Member member = memberService.registerMember(request);
            MemberInfo memberInfo = memberService.convertToMemberInfo(member);
            String token = jwtService.generateToken(member.getId(), member.getEmail());
            
            Map<String, Object> data = new HashMap<>();
            data.put("member", memberInfo);
            data.put("token", token);
            
            return ResponseEntity.ok(ApiResponse.success("註冊成功", data));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error("註冊失敗: " + e.getMessage()));
        }
    }
    
    /**
     * 會員登入
     */
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<Map<String, Object>>> login(@Valid @RequestBody LoginRequest request) {
        try {
            Optional<Member> memberOpt = memberService.validateLogin(request.getEmail(), request.getPassword());
            
            if (memberOpt.isPresent()) {
                Member member = memberOpt.get();
                MemberInfo memberInfo = memberService.convertToMemberInfo(member);
                String token = jwtService.generateToken(member.getId(), member.getEmail());
                
                Map<String, Object> data = new HashMap<>();
                data.put("member", memberInfo);
                data.put("token", token);
                
                return ResponseEntity.ok(ApiResponse.success("登入成功", data));
            } else {
                return ResponseEntity.badRequest()
                        .body(ApiResponse.error("電子郵件或密碼錯誤"));
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error("登入失敗: " + e.getMessage()));
        }
    }
    
    /**
     * 忘記密碼
     */
    @PostMapping("/forgot-password")
    public ResponseEntity<ApiResponse<String>> forgotPassword(@RequestParam String email) {
        try {
            boolean success = passwordResetService.sendPasswordResetEmail(email);
            
            if (success) {
                return ResponseEntity.ok(ApiResponse.success("密碼重設連結已發送到您的電子郵件"));
            } else {
                return ResponseEntity.badRequest()
                        .body(ApiResponse.error("發送失敗，請稍後再試"));
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error("發送失敗: " + e.getMessage()));
        }
    }
    
    /**
     * 重設密碼
     */
    @PostMapping("/reset-password")
    public ResponseEntity<ApiResponse<String>> resetPassword(
            @RequestParam String token,
            @RequestParam String newPassword) {
        try {
            if (newPassword.length() < 6) {
                return ResponseEntity.badRequest()
                        .body(ApiResponse.error("密碼至少需要6個字元"));
            }
            
            boolean success = passwordResetService.resetPassword(token, newPassword);
            
            if (success) {
                return ResponseEntity.ok(ApiResponse.success("密碼重設成功"));
            } else {
                return ResponseEntity.badRequest()
                        .body(ApiResponse.error("重設失敗，請檢查令牌是否有效"));
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error("重設失敗: " + e.getMessage()));
        }
    }
    
    /**
     * 驗證令牌
     */
    @GetMapping("/validate-token")
    public ResponseEntity<ApiResponse<Boolean>> validateToken(@RequestParam String token) {
        try {
            boolean isValid = passwordResetService.validateResetToken(token);
            return ResponseEntity.ok(ApiResponse.success("驗證完成", isValid));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error("驗證失敗: " + e.getMessage()));
        }
    }
}
