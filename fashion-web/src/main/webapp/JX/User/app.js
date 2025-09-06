// 簡易 Tab 切換
const tabButtons = Array.from(document.querySelectorAll('.tab-button'));
const panels = Array.from(document.querySelectorAll('.panel'));

tabButtons.forEach((btn) => {
  btn.addEventListener('click', () => {
    // 如果是註冊按鈕，跳轉到註冊頁面
    if (btn.getAttribute('data-target') === '#register') {
      return; // 由 onclick 處理跳轉
    }
    
    const target = btn.getAttribute('data-target');
    tabButtons.forEach(b => b.classList.remove('active'));
    btn.classList.add('active');
    panels.forEach(p => p.classList.remove('active'));
    const panel = document.querySelector(target);
    if (panel) panel.classList.add('active');
  });
});

// 跳轉到註冊頁面
function goToRegister() {
  window.location.href = 'register.html';
}

// 表單驗證與提交占位
function showError(input, message) {
  const id = input.getAttribute('id');
  const errorEl = document.querySelector(`[data-error-for="${id}"]`);
  if (errorEl) errorEl.textContent = message || '';
}

function clearErrors(form) {
  Array.from(form.querySelectorAll('.error')).forEach(el => el.textContent = '');
}

function toast(message) {
  const t = document.getElementById('toast-template');
  if (!t) return;
  const el = t.content.firstElementChild.cloneNode(true);
  el.textContent = message;
  document.body.appendChild(el);
  setTimeout(() => el.remove(), 2200);
}

function isValidEmail(email) {
  return /.+@.+\..+/.test(email);
}

// 登入
document.getElementById('login')?.addEventListener('submit', (e) => {
  e.preventDefault();
  const form = e.currentTarget;
  clearErrors(form);
  const email = document.getElementById('login-email');
  const password = document.getElementById('login-password');
  let ok = true;
  if (!email.value || !isValidEmail(email.value)) { showError(email, '請輸入有效的電子郵件'); ok = false; }
  if (!password.value || password.value.length < 6) { showError(password, '密碼至少 6 碼'); ok = false; }
  if (!ok) return;
  
  // 發送登入請求到後端
  loginUser(email.value, password.value);
});

// 登入用戶
async function loginUser(email, password) {
  try {
    const response = await fetch('http://localhost:8080/api/auth/login', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ email, password })
    });
    
    const result = await response.json();
    
    if (result.success) {
      // 儲存會員資料和令牌
      localStorage.setItem('memberData', JSON.stringify(result.data.member));
      localStorage.setItem('authToken', result.data.token);
      
      toast('登入成功！正在跳轉到會員中心...');
      setTimeout(() => {
        window.location.href = 'member-details.html';
      }, 1000);
    } else {
      toast(result.message || '登入失敗');
    }
  } catch (error) {
    console.error('登入錯誤:', error);
    toast('登入失敗，請稍後再試');
  }
}

// 註冊
document.getElementById('register')?.addEventListener('submit', (e) => {
  e.preventDefault();
  const form = e.currentTarget;
  clearErrors(form);
  const name = document.getElementById('reg-name');
  const email = document.getElementById('reg-email');
  const pwd = document.getElementById('reg-password');
  const pwd2 = document.getElementById('reg-password2');
  let ok = true;
  if (!name.value) { showError(name, '請輸入姓名'); ok = false; }
  if (!email.value || !isValidEmail(email.value)) { showError(email, '請輸入有效的電子郵件'); ok = false; }
  if (!pwd.value || pwd.value.length < 6) { showError(pwd, '密碼至少 6 碼'); ok = false; }
  if (pwd2.value !== pwd.value) { showError(pwd2, '兩次密碼不一致'); ok = false; }
  if (!ok) return;
  
  // 發送註冊請求到後端
  registerUser(name.value, email.value, pwd.value);
});

// 註冊用戶
async function registerUser(name, email, password) {
  try {
    const response = await fetch('http://localhost:8080/api/auth/register', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        account: email.split('@')[0] + Math.floor(Math.random() * 1000),
        email: email,
        name: name,
        password: password,
        confirmPassword: password,
        recipientName: name,
        recipientPhone: '0912-345-678',
        city: '台北市',
        district: '信義區',
        addressDetail: '信義路五段7號',
        agreeTerms: true,
        agreeMarketing: false
      })
    });
    
    const result = await response.json();
    
    if (result.success) {
      // 儲存會員資料和令牌
      localStorage.setItem('memberData', JSON.stringify(result.data.member));
      localStorage.setItem('authToken', result.data.token);
      
      toast('註冊成功！正在跳轉到會員中心...');
      setTimeout(() => {
        window.location.href = 'member-details.html';
      }, 1500);
    } else {
      toast(result.message || '註冊失敗');
    }
  } catch (error) {
    console.error('註冊錯誤:', error);
    toast('註冊失敗，請稍後再試');
  }
}

// 忘記密碼
document.getElementById('forgot')?.addEventListener('submit', (e) => {
  e.preventDefault();
  const form = e.currentTarget;
  clearErrors(form);
  const email = document.getElementById('forgot-email');
  if (!email.value || !isValidEmail(email.value)) { showError(email, '請輸入有效的電子郵件'); return; }
  
  // 發送忘記密碼請求到後端
  forgotPassword(email.value);
});

// 忘記密碼
async function forgotPassword(email) {
  try {
    const response = await fetch('http://localhost:8080/api/auth/forgot-password', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ email })
    });
    
    const result = await response.json();
    toast(result.message || '已寄送重設連結');
  } catch (error) {
    console.error('忘記密碼錯誤:', error);
    toast('發送失敗，請稍後再試');
  }
}


