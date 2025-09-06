package com.fashion.member.scheduler;

import com.fashion.member.service.PasswordResetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 令牌清理排程器
 */
@Component
public class TokenCleanupScheduler {
    
    @Autowired
    private PasswordResetService passwordResetService;
    
    /**
     * 每小時清理過期的密碼重設令牌
     */
    @Scheduled(fixedRate = 3600000) // 3600000毫秒 = 1小時
    public void cleanupExpiredTokens() {
        passwordResetService.cleanupExpiredTokens();
    }
}
