package com.fashion.member.service;

import com.fashion.member.entity.Member;
import com.fashion.member.entity.PasswordResetToken;
import com.fashion.member.repository.MemberRepository;
import com.fashion.member.repository.PasswordResetTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

/**
 * 密碼重設服務類
 */
@Service
@Transactional
public class PasswordResetService {
    
    @Autowired
    private MemberRepository memberRepository;
    
    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;
    
    @Autowired
    private JavaMailSender mailSender;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Value("${app.reset-password-url}")
    private String resetPasswordUrl;
    
    /**
     * 發送密碼重設郵件
     */
    public boolean sendPasswordResetEmail(String email) {
        Optional<Member> memberOpt = memberRepository.findByEmail(email);
        if (!memberOpt.isPresent()) {
            return false; // 不透露會員是否存在
        }
        
        Member member = memberOpt.get();
        
        // 刪除該會員的舊令牌
        passwordResetTokenRepository.deleteByMemberId(member.getId());
        
        // 生成新令牌
        String token = UUID.randomUUID().toString();
        LocalDateTime expiresAt = LocalDateTime.now().plusHours(24); // 24小時後過期
        
        PasswordResetToken resetToken = new PasswordResetToken(member, token, expiresAt);
        passwordResetTokenRepository.save(resetToken);
        
        // 發送郵件
        try {
            sendResetEmail(member.getEmail(), member.getName(), token);
            return true;
        } catch (Exception e) {
            // 記錄錯誤日誌
            System.err.println("發送密碼重設郵件失敗: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * 驗證重設令牌
     */
    public boolean validateResetToken(String token) {
        Optional<PasswordResetToken> tokenOpt = passwordResetTokenRepository.findValidToken(
                token, LocalDateTime.now());
        return tokenOpt.isPresent();
    }
    
    /**
     * 重設密碼
     */
    public boolean resetPassword(String token, String newPassword) {
        Optional<PasswordResetToken> tokenOpt = passwordResetTokenRepository.findValidToken(
                token, LocalDateTime.now());
        
        if (!tokenOpt.isPresent()) {
            return false;
        }
        
        PasswordResetToken resetToken = tokenOpt.get();
        Member member = resetToken.getMember();
        
        // 更新密碼
        member.setPassword(passwordEncoder.encode(newPassword));
        memberRepository.save(member);
        
        // 標記令牌為已使用
        resetToken.setUsed(true);
        passwordResetTokenRepository.save(resetToken);
        
        return true;
    }
    
    /**
     * 發送重設郵件
     */
    private void sendResetEmail(String email, String name, String token) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("密碼重設通知 - 時尚網站");
        
        String resetUrl = resetPasswordUrl + "?token=" + token;
        String content = String.format(
                "親愛的 %s，\n\n" +
                "您申請了密碼重設服務。請點擊以下連結重設您的密碼：\n\n" +
                "%s\n\n" +
                "此連結將在24小時後失效。\n\n" +
                "如果您沒有申請此服務，請忽略此郵件。\n\n" +
                "謝謝！\n" +
                "時尚網站團隊",
                name, resetUrl
        );
        
        message.setText(content);
        mailSender.send(message);
    }
    
    /**
     * 清理過期令牌
     */
    @Transactional
    public void cleanupExpiredTokens() {
        passwordResetTokenRepository.deleteExpiredTokens(LocalDateTime.now());
    }
}
