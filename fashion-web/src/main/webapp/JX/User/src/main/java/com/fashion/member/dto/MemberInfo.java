package com.fashion.member.dto;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 會員資訊DTO
 */
public class MemberInfo {
    
    private Long id;
    private String account;
    private String email;
    private String name;
    private String memberLevel;
    private Boolean isActive;
    private Boolean emailVerified;
    private Boolean marketingConsent;
    private LocalDateTime createdAt;
    private List<AddressInfo> addresses;
    
    // 建構子
    public MemberInfo() {}
    
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
    
    public List<AddressInfo> getAddresses() {
        return addresses;
    }
    
    public void setAddresses(List<AddressInfo> addresses) {
        this.addresses = addresses;
    }
    
    /**
     * 地址資訊內部類
     */
    public static class AddressInfo {
        private Long id;
        private String recipientName;
        private String recipientPhone;
        private String city;
        private String district;
        private String addressDetail;
        private Boolean isDefault;
        
        // 建構子
        public AddressInfo() {}
        
        // Getter 和 Setter
        public Long getId() {
            return id;
        }
        
        public void setId(Long id) {
            this.id = id;
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
        
        public Boolean getIsDefault() {
            return isDefault;
        }
        
        public void setIsDefault(Boolean isDefault) {
            this.isDefault = isDefault;
        }
    }
}
