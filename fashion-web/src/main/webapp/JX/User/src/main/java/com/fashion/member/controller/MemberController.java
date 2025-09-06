package com.fashion.member.controller;

import com.fashion.member.dto.ApiResponse;
import com.fashion.member.dto.MemberInfo;
import com.fashion.member.entity.Member;
import com.fashion.member.service.JwtService;
import com.fashion.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * 會員管理控制器
 */
@RestController
@RequestMapping("/member")
@CrossOrigin(origins = "*")
public class MemberController {
    
    @Autowired
    private MemberService memberService;
    
    @Autowired
    private JwtService jwtService;
    
    /**
     * 獲取會員資料
     */
    @GetMapping("/profile")
    public ResponseEntity<ApiResponse<MemberInfo>> getProfile(HttpServletRequest request) {
        try {
            Long memberId = getMemberIdFromToken(request);
            Optional<Member> memberOpt = memberService.findById(memberId);
            
            if (memberOpt.isPresent()) {
                MemberInfo memberInfo = memberService.convertToMemberInfo(memberOpt.get());
                return ResponseEntity.ok(ApiResponse.success("獲取成功", memberInfo));
            } else {
                return ResponseEntity.badRequest()
                        .body(ApiResponse.error("會員不存在"));
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error("獲取失敗: " + e.getMessage()));
        }
    }
    
    /**
     * 更新會員資料
     */
    @PutMapping("/profile")
    public ResponseEntity<ApiResponse<MemberInfo>> updateProfile(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Boolean marketingConsent,
            HttpServletRequest request) {
        try {
            Long memberId = getMemberIdFromToken(request);
            Member member = memberService.updateMember(memberId, name, marketingConsent);
            MemberInfo memberInfo = memberService.convertToMemberInfo(member);
            
            return ResponseEntity.ok(ApiResponse.success("更新成功", memberInfo));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error("更新失敗: " + e.getMessage()));
        }
    }
    
    /**
     * 修改密碼
     */
    @PutMapping("/password")
    public ResponseEntity<ApiResponse<String>> changePassword(
            @RequestParam String currentPassword,
            @RequestParam String newPassword,
            HttpServletRequest request) {
        try {
            if (newPassword.length() < 6) {
                return ResponseEntity.badRequest()
                        .body(ApiResponse.error("新密碼至少需要6個字元"));
            }
            
            Long memberId = getMemberIdFromToken(request);
            boolean success = memberService.updatePassword(memberId, currentPassword, newPassword);
            
            if (success) {
                return ResponseEntity.ok(ApiResponse.success("密碼修改成功"));
            } else {
                return ResponseEntity.badRequest()
                        .body(ApiResponse.error("目前密碼錯誤"));
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error("修改失敗: " + e.getMessage()));
        }
    }
    
    /**
     * 從請求中提取會員ID
     */
    private Long getMemberIdFromToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            return jwtService.extractMemberId(token);
        }
        throw new RuntimeException("無效的認證令牌");
    }
}
