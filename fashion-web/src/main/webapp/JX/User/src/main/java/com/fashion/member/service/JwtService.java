package com.fashion.member.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * JWT服務類
 */
@Service
public class JwtService {
    
    @Value("${jwt.secret}")
    private String secret;
    
    @Value("${jwt.expiration}")
    private Long expiration;
    
    /**
     * 生成JWT令牌
     */
    public String generateToken(Long memberId, String email) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("memberId", memberId);
        claims.put("email", email);
        return createToken(claims, email);
    }
    
    /**
     * 創建令牌
     */
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    
    /**
     * 從令牌中提取會員ID
     */
    public Long extractMemberId(String token) {
        return extractClaim(token, claims -> claims.get("memberId", Long.class));
    }
    
    /**
     * 從令牌中提取電子郵件
     */
    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    
    /**
     * 從令牌中提取過期時間
     */
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
    
    /**
     * 提取特定聲明
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    
    /**
     * 提取所有聲明
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    
    /**
     * 檢查令牌是否過期
     */
    public Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
    
    /**
     * 驗證令牌
     */
    public Boolean validateToken(String token, String email) {
        final String tokenEmail = extractEmail(token);
        return (tokenEmail.equals(email) && !isTokenExpired(token));
    }
    
    /**
     * 獲取簽名密鑰
     */
    private SecretKey getSignKey() {
        byte[] keyBytes = secret.getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
