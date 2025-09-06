package com.fashion.member.service;

import com.fashion.member.dto.MemberInfo;
import com.fashion.member.dto.RegisterRequest;
import com.fashion.member.entity.Address;
import com.fashion.member.entity.Member;
import com.fashion.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 會員服務類
 */
@Service
@Transactional
public class MemberService {
    
    @Autowired
    private MemberRepository memberRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    /**
     * 註冊新會員
     */
    public Member registerMember(RegisterRequest request) {
        // 檢查帳號是否已存在
        if (memberRepository.existsByAccount(request.getAccount())) {
            throw new RuntimeException("此帳號已被使用，請選擇其他帳號");
        }
        
        // 檢查電子郵件是否已存在
        if (memberRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("此電子郵件已被註冊，請使用其他電子郵件");
        }
        
        // 驗證密碼確認
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new RuntimeException("兩次輸入的密碼不一致");
        }
        
        // 驗證服務條款同意
        if (!request.getAgreeTerms()) {
            throw new RuntimeException("請同意服務條款和隱私政策");
        }
        
        // 創建會員實體
        Member member = new Member();
        member.setAccount(request.getAccount());
        member.setEmail(request.getEmail());
        member.setName(request.getName());
        member.setPassword(passwordEncoder.encode(request.getPassword()));
        member.setMarketingConsent(request.getAgreeMarketing());
        
        // 保存會員
        member = memberRepository.save(member);
        
        // 創建預設地址
        Address address = new Address();
        address.setMember(member);
        address.setRecipientName(request.getRecipientName());
        address.setRecipientPhone(request.getRecipientPhone());
        address.setCity(request.getCity());
        address.setDistrict(request.getDistrict());
        address.setAddressDetail(request.getAddressDetail());
        address.setIsDefault(true);
        
        member.getAddresses().add(address);
        
        return memberRepository.save(member);
    }
    
    /**
     * 根據電子郵件查找會員
     */
    public Optional<Member> findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }
    
    /**
     * 根據ID查找會員
     */
    public Optional<Member> findById(Long id) {
        return memberRepository.findById(id);
    }
    
    /**
     * 驗證會員登入
     */
    public Optional<Member> validateLogin(String email, String password) {
        Optional<Member> memberOpt = memberRepository.findByEmailAndIsActive(email, true);
        if (memberOpt.isPresent()) {
            Member member = memberOpt.get();
            if (passwordEncoder.matches(password, member.getPassword())) {
                return Optional.of(member);
            }
        }
        return Optional.empty();
    }
    
    /**
     * 更新會員資料
     */
    public Member updateMember(Long id, String name, Boolean marketingConsent) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("會員不存在"));
        
        if (name != null) {
            member.setName(name);
        }
        if (marketingConsent != null) {
            member.setMarketingConsent(marketingConsent);
        }
        
        return memberRepository.save(member);
    }
    
    /**
     * 更新會員密碼
     */
    public boolean updatePassword(Long id, String currentPassword, String newPassword) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("會員不存在"));
        
        // 驗證目前密碼
        if (!passwordEncoder.matches(currentPassword, member.getPassword())) {
            return false;
        }
        
        // 更新密碼
        member.setPassword(passwordEncoder.encode(newPassword));
        memberRepository.save(member);
        return true;
    }
    
    /**
     * 將會員實體轉換為MemberInfo DTO
     */
    public MemberInfo convertToMemberInfo(Member member) {
        MemberInfo memberInfo = new MemberInfo();
        memberInfo.setId(member.getId());
        memberInfo.setAccount(member.getAccount());
        memberInfo.setEmail(member.getEmail());
        memberInfo.setName(member.getName());
        memberInfo.setMemberLevel(member.getMemberLevel());
        memberInfo.setIsActive(member.getIsActive());
        memberInfo.setEmailVerified(member.getEmailVerified());
        memberInfo.setMarketingConsent(member.getMarketingConsent());
        memberInfo.setCreatedAt(member.getCreatedAt());
        
        // 轉換地址資訊
        List<MemberInfo.AddressInfo> addressInfos = member.getAddresses().stream()
                .map(this::convertToAddressInfo)
                .collect(Collectors.toList());
        memberInfo.setAddresses(addressInfos);
        
        return memberInfo;
    }
    
    /**
     * 將地址實體轉換為AddressInfo DTO
     */
    private MemberInfo.AddressInfo convertToAddressInfo(Address address) {
        MemberInfo.AddressInfo addressInfo = new MemberInfo.AddressInfo();
        addressInfo.setId(address.getId());
        addressInfo.setRecipientName(address.getRecipientName());
        addressInfo.setRecipientPhone(address.getRecipientPhone());
        addressInfo.setCity(address.getCity());
        addressInfo.setDistrict(address.getDistrict());
        addressInfo.setAddressDetail(address.getAddressDetail());
        addressInfo.setIsDefault(address.getIsDefault());
        return addressInfo;
    }
}
