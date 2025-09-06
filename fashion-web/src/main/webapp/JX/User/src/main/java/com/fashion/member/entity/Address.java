package com.fashion.member.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * 收件地址實體類
 */
@Entity
@Table(name = "addresses")
public class Address {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;
    
    @NotBlank(message = "收件人姓名不能為空")
    @Size(max = 50, message = "收件人姓名不能超過50字元")
    @Column(name = "recipient_name", nullable = false)
    private String recipientName;
    
    @NotBlank(message = "聯絡電話不能為空")
    @Size(max = 20, message = "聯絡電話不能超過20字元")
    @Column(name = "recipient_phone", nullable = false)
    private String recipientPhone;
    
    @NotBlank(message = "縣市不能為空")
    @Size(max = 20, message = "縣市不能超過20字元")
    @Column(nullable = false)
    private String city;
    
    @NotBlank(message = "區域不能為空")
    @Size(max = 20, message = "區域不能超過20字元")
    @Column(nullable = false)
    private String district;
    
    @NotBlank(message = "詳細地址不能為空")
    @Size(max = 200, message = "詳細地址不能超過200字元")
    @Column(name = "address_detail", nullable = false)
    private String addressDetail;
    
    @Column(name = "is_default")
    private Boolean isDefault = false;
    
    // 建構子
    public Address() {}
    
    public Address(Member member, String recipientName, String recipientPhone, 
                   String city, String district, String addressDetail) {
        this.member = member;
        this.recipientName = recipientName;
        this.recipientPhone = recipientPhone;
        this.city = city;
        this.district = district;
        this.addressDetail = addressDetail;
    }
    
    // Getter 和 Setter
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Member getMember() {
        return member;
    }
    
    public void setMember(Member member) {
        this.member = member;
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
