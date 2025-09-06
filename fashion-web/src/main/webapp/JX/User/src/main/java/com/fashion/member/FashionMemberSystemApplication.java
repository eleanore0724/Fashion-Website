package com.fashion.member;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 時尚會員系統主應用程式
 */
@SpringBootApplication
@EnableScheduling
public class FashionMemberSystemApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(FashionMemberSystemApplication.class, args);
    }
}
