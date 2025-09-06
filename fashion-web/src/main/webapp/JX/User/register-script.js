// 註冊頁面 JavaScript

// 表單驗證與提交功能
function showError(input, message) {
  const id = input.getAttribute('id');
  const errorEl = document.querySelector(`[data-error-for="${id}"]`);
  if (errorEl) {
    errorEl.textContent = message || '';
    errorEl.style.display = message ? 'block' : 'none';
  }
  
  // 添加錯誤樣式
  if (message) {
    input.classList.add('error');
    input.closest('.field').classList.add('error');
  } else {
    input.classList.remove('error');
    input.closest('.field').classList.remove('error');
  }
}

function clearErrors(form) {
  Array.from(form.querySelectorAll('.error')).forEach(el => {
    el.textContent = '';
    el.style.display = 'none';
  });
  
  // 清除錯誤樣式
  Array.from(form.querySelectorAll('input, select, textarea')).forEach(input => {
    input.classList.remove('error');
    input.closest('.field').classList.remove('error');
  });
}

function toast(message) {
  const t = document.getElementById('toast-template');
  if (!t) return;
  const el = t.content.firstElementChild.cloneNode(true);
  el.textContent = message;
  document.body.appendChild(el);
  setTimeout(() => el.remove(), 3000);
}

// 驗證函數
function isValidEmail(email) {
  return /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email);
}

function isValidPhone(phone) {
  return /^09\d{2}-?\d{3}-?\d{3}$/.test(phone.replace(/\s/g, ''));
}

function isValidAccount(account) {
  return /^[a-zA-Z0-9_]{3,20}$/.test(account);
}

// 電話號碼格式化
function formatPhoneNumber(input) {
  let value = input.value.replace(/\D/g, '');
  if (value.length >= 4) {
    value = value.substring(0, 4) + '-' + value.substring(4);
  }
  if (value.length >= 8) {
    value = value.substring(0, 8) + '-' + value.substring(8, 11);
  }
  input.value = value;
}

// 帳號可用性檢查
function checkAccountAvailability(account) {
  // 模擬檢查已存在的帳號
  const existingAccounts = ['admin', 'user', 'test', 'demo'];
  return !existingAccounts.includes(account.toLowerCase());
}

// 表單驗證
function validateForm(formData) {
  const errors = {};
  
  // 驗證會員帳號
  if (!formData.account) {
    errors.account = '請輸入會員帳號';
  } else if (!isValidAccount(formData.account)) {
    errors.account = '帳號只能包含英文字母、數字和底線，長度3-20字元';
  } else if (!checkAccountAvailability(formData.account)) {
    errors.account = '此帳號已被使用，請選擇其他帳號';
  }
  
  // 驗證電子郵件
  if (!formData.email) {
    errors.email = '請輸入電子郵件';
  } else if (!isValidEmail(formData.email)) {
    errors.email = '請輸入有效的電子郵件地址';
  }
  
  // 驗證姓名
  if (!formData.name) {
    errors.name = '請輸入姓名';
  } else if (formData.name.length < 2) {
    errors.name = '姓名至少需要2個字元';
  }
  
  // 驗證密碼
  if (!formData.password) {
    errors.password = '請輸入密碼';
  } else if (formData.password.length < 6) {
    errors.password = '密碼至少需要6個字元';
  }
  
  // 驗證確認密碼
  if (!formData.confirmPassword) {
    errors.confirmPassword = '請確認密碼';
  } else if (formData.password !== formData.confirmPassword) {
    errors.confirmPassword = '兩次輸入的密碼不一致';
  }
  
  // 驗證收件人姓名
  if (!formData.recipientName) {
    errors.recipientName = '請輸入收件人姓名';
  }
  
  // 驗證聯絡電話
  if (!formData.recipientPhone) {
    errors.recipientPhone = '請輸入聯絡電話';
  } else if (!isValidPhone(formData.recipientPhone)) {
    errors.recipientPhone = '請輸入有效的手機號碼格式';
  }
  
  // 驗證地址
  if (!formData.city) {
    errors.city = '請選擇縣市';
  }
  
  if (!formData.district) {
    errors.district = '請輸入區域';
  }
  
  if (!formData.addressDetail) {
    errors.addressDetail = '請輸入詳細地址';
  }
  
  // 驗證服務條款
  if (!formData.agreeTerms) {
    errors.agreeTerms = '請同意服務條款和隱私政策';
  }
  
  return errors;
}

// 表單提交處理
document.getElementById('register-form').addEventListener('submit', (e) => {
  e.preventDefault();
  
  const form = e.currentTarget;
  clearErrors(form);
  
  // 收集表單資料
  const formData = {
    account: document.getElementById('member-account').value.trim(),
    email: document.getElementById('member-email').value.trim(),
    name: document.getElementById('member-name').value.trim(),
    password: document.getElementById('member-password').value,
    confirmPassword: document.getElementById('confirm-password').value,
    recipientName: document.getElementById('recipient-name').value.trim(),
    recipientPhone: document.getElementById('recipient-phone').value.trim(),
    city: document.getElementById('address-city').value,
    district: document.getElementById('address-district').value.trim(),
    addressDetail: document.getElementById('address-detail').value.trim(),
    agreeTerms: document.getElementById('agree-terms').checked,
    agreeMarketing: document.getElementById('agree-marketing').checked
  };
  
  // 驗證表單
  const errors = validateForm(formData);
  
  // 顯示錯誤
  let hasErrors = false;
  Object.keys(errors).forEach(fieldName => {
    const input = document.getElementById(fieldName === 'agreeTerms' ? 'agree-terms' : fieldName);
    if (input) {
      showError(input, errors[fieldName]);
      hasErrors = true;
    }
  });
  
  if (hasErrors) {
    toast('請修正表單中的錯誤');
    return;
  }
  
  // 提交表單
  submitRegistration(formData);
});

// 提交註冊
async function submitRegistration(formData) {
  const submitBtn = document.querySelector('.primary-btn');
  const originalText = submitBtn.textContent;
  
  // 顯示載入狀態
  submitBtn.disabled = true;
  submitBtn.textContent = '註冊中...';
  
  try {
    const response = await fetch('http://localhost:8080/api/auth/register', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(formData)
    });
    
    const result = await response.json();
    
    if (result.success) {
      // 儲存會員資料和令牌
      localStorage.setItem('memberData', JSON.stringify(result.data.member));
      localStorage.setItem('authToken', result.data.token);
      
      toast('註冊成功！正在跳轉到會員中心...');
      
      // 跳轉到會員詳細資料頁面
      setTimeout(() => {
        window.location.href = 'member-details.html';
      }, 1500);
    } else {
      toast(result.message || '註冊失敗');
    }
  } catch (error) {
    console.error('註冊失敗:', error);
    toast('註冊失敗，請稍後再試');
  } finally {
    // 恢復按鈕狀態
    submitBtn.disabled = false;
    submitBtn.textContent = originalText;
  }
}

// 返回登入頁面
function goBack() {
  window.location.href = 'index.html';
}

// 顯示服務條款
function showTerms() {
  alert('服務條款\n\n1. 會員權利與義務\n2. 商品購買與退換貨\n3. 隱私保護\n4. 免責聲明\n\n詳細內容請參考完整條款。');
}

// 顯示隱私政策
function showPrivacy() {
  alert('隱私政策\n\n1. 個人資料收集\n2. 資料使用目的\n3. 資料保護措施\n4. 資料分享政策\n\n我們重視您的隱私權。');
}

// 即時驗證
document.addEventListener('DOMContentLoaded', () => {
  // 電話號碼格式化
  const phoneInput = document.getElementById('recipient-phone');
  phoneInput.addEventListener('input', () => {
    formatPhoneNumber(phoneInput);
  });
  
  // 帳號即時驗證
  const accountInput = document.getElementById('member-account');
  let accountTimeout;
  accountInput.addEventListener('input', () => {
    clearTimeout(accountTimeout);
    accountTimeout = setTimeout(() => {
      const account = accountInput.value.trim();
      if (account && isValidAccount(account)) {
        if (!checkAccountAvailability(account)) {
          showError(accountInput, '此帳號已被使用');
        } else {
          showError(accountInput, '');
          accountInput.closest('.field').classList.add('success');
        }
      }
    }, 500);
  });
  
  // 密碼確認即時驗證
  const passwordInput = document.getElementById('member-password');
  const confirmPasswordInput = document.getElementById('confirm-password');
  
  confirmPasswordInput.addEventListener('input', () => {
    const password = passwordInput.value;
    const confirmPassword = confirmPasswordInput.value;
    
    if (confirmPassword && password !== confirmPassword) {
      showError(confirmPasswordInput, '密碼不一致');
    } else if (confirmPassword && password === confirmPassword) {
      showError(confirmPasswordInput, '');
      confirmPasswordInput.closest('.field').classList.add('success');
    }
  });
  
  // 電子郵件即時驗證
  const emailInput = document.getElementById('member-email');
  emailInput.addEventListener('blur', () => {
    const email = emailInput.value.trim();
    if (email && !isValidEmail(email)) {
      showError(emailInput, '請輸入有效的電子郵件地址');
    } else if (email && isValidEmail(email)) {
      showError(emailInput, '');
      emailInput.closest('.field').classList.add('success');
    }
  });
});
