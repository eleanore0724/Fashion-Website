// 會員詳細資料頁面 JavaScript

// 載入會員資料
async function loadMemberData() {
  const token = localStorage.getItem('authToken');
  if (!token) {
    // 沒有令牌，跳轉到登入頁面
    window.location.href = 'index.html';
    return;
  }
  
  try {
    const response = await fetch('http://localhost:8080/api/member/profile', {
      method: 'GET',
      headers: {
        'Authorization': `Bearer ${token}`,
        'Content-Type': 'application/json',
      }
    });
    
    const result = await response.json();
    
    if (result.success) {
      const data = result.data;
      document.getElementById('member-account').textContent = data.account;
      document.getElementById('member-email').textContent = data.email;
      document.getElementById('member-reg-date').textContent = data.createdAt.split('T')[0];
      document.getElementById('member-level').textContent = data.memberLevel;
      
      // 更新localStorage中的會員資料
      localStorage.setItem('memberData', JSON.stringify(data));
    } else {
      // 令牌無效，清除並跳轉到登入頁面
      localStorage.removeItem('authToken');
      localStorage.removeItem('memberData');
      window.location.href = 'index.html';
    }
  } catch (error) {
    console.error('載入會員資料失敗:', error);
    // 發生錯誤，跳轉到登入頁面
    localStorage.removeItem('authToken');
    localStorage.removeItem('memberData');
    window.location.href = 'index.html';
  }
}

// Tab 切換功能
const navButtons = Array.from(document.querySelectorAll('.nav-button'));
const memberPanels = Array.from(document.querySelectorAll('.member-panel'));

navButtons.forEach((btn) => {
  btn.addEventListener('click', () => {
    const target = btn.getAttribute('data-target');
    navButtons.forEach(b => b.classList.remove('active'));
    btn.classList.add('active');
    memberPanels.forEach(p => p.classList.remove('active'));
    const panel = document.querySelector(target);
    if (panel) panel.classList.add('active');
  });
});

// 表單驗證與提交功能
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

// 密碼修改表單
document.getElementById('password-form')?.addEventListener('submit', (e) => {
  e.preventDefault();
  const form = e.currentTarget;
  clearErrors(form);
  
  const currentPassword = document.getElementById('current-password');
  const newPassword = document.getElementById('new-password');
  const confirmPassword = document.getElementById('confirm-password');
  
  let ok = true;
  
  if (!currentPassword.value) {
    showError(currentPassword, '請輸入目前密碼');
    ok = false;
  }
  
  if (!newPassword.value || newPassword.value.length < 6) {
    showError(newPassword, '新密碼至少 6 碼');
    ok = false;
  }
  
  if (confirmPassword.value !== newPassword.value) {
    showError(confirmPassword, '兩次密碼不一致');
    ok = false;
  }
  
  if (!ok) return;
  
  // 發送密碼修改請求到後端
  changePassword(currentPassword.value, newPassword.value);
});

// 修改密碼
async function changePassword(currentPassword, newPassword) {
  const token = localStorage.getItem('authToken');
  if (!token) {
    window.location.href = 'index.html';
    return;
  }
  
  try {
    const response = await fetch('http://localhost:8080/api/member/password', {
      method: 'PUT',
      headers: {
        'Authorization': `Bearer ${token}`,
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        currentPassword: currentPassword,
        newPassword: newPassword
      })
    });
    
    const result = await response.json();
    
    if (result.success) {
      toast('密碼修改成功！');
      document.getElementById('password-form').reset();
    } else {
      toast(result.message || '密碼修改失敗');
    }
  } catch (error) {
    console.error('密碼修改錯誤:', error);
    toast('密碼修改失敗，請稍後再試');
  }
}

// 編輯帳號資訊
async function editAccountInfo() {
  const name = prompt('請輸入姓名：');
  if (name) {
    const token = localStorage.getItem('authToken');
    if (!token) {
      window.location.href = 'index.html';
      return;
    }
    
    try {
      const response = await fetch('http://localhost:8080/api/member/profile', {
        method: 'PUT',
        headers: {
          'Authorization': `Bearer ${token}`,
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          name: name
        })
      });
      
      const result = await response.json();
      
      if (result.success) {
        // 更新頁面顯示
        document.getElementById('member-account').textContent = result.data.account;
        document.getElementById('member-email').textContent = result.data.email;
        document.getElementById('member-reg-date').textContent = result.data.createdAt.split('T')[0];
        document.getElementById('member-level').textContent = result.data.memberLevel;
        
        // 更新localStorage
        localStorage.setItem('memberData', JSON.stringify(result.data));
        toast('資料更新成功！');
      } else {
        toast(result.message || '資料更新失敗');
      }
    } catch (error) {
      console.error('資料更新錯誤:', error);
      toast('資料更新失敗，請稍後再試');
    }
  }
}

// 地址管理功能
function addAddress() {
  const name = prompt('請輸入收件人姓名：');
  if (!name) return;
  
  const phone = prompt('請輸入手機號碼：');
  if (!phone) return;
  
  const address = prompt('請輸入地址：');
  if (!address) return;
  
  const addressList = document.getElementById('address-list');
  const addressId = Date.now();
  
  const addressItem = document.createElement('div');
  addressItem.className = 'address-item';
  addressItem.innerHTML = `
    <div class="address-info">
      <h3>${name}</h3>
      <p>${phone}</p>
      <p>${address}</p>
    </div>
    <div class="address-actions">
      <button class="edit-btn small" onclick="editAddress(${addressId})">編輯</button>
      <button class="delete-btn small" onclick="deleteAddress(${addressId})">刪除</button>
    </div>
  `;
  
  addressList.appendChild(addressItem);
  toast('地址新增成功！');
}

function editAddress(id) {
  const addressItem = event.target.closest('.address-item');
  const name = addressItem.querySelector('h3').textContent;
  const phone = addressItem.querySelectorAll('p')[0].textContent;
  const address = addressItem.querySelectorAll('p')[1].textContent;
  
  const newName = prompt('請輸入收件人姓名：', name);
  if (!newName) return;
  
  const newPhone = prompt('請輸入手機號碼：', phone);
  if (!newPhone) return;
  
  const newAddress = prompt('請輸入地址：', address);
  if (!newAddress) return;
  
  addressItem.querySelector('h3').textContent = newName;
  addressItem.querySelectorAll('p')[0].textContent = newPhone;
  addressItem.querySelectorAll('p')[1].textContent = newAddress;
  
  toast('地址修改成功！');
}

function deleteAddress(id) {
  if (confirm('確定要刪除此地址嗎？')) {
    const addressItem = event.target.closest('.address-item');
    addressItem.remove();
    toast('地址刪除成功！');
  }
}

// 訂單查詢功能
document.getElementById('order-query-form')?.addEventListener('submit', (e) => {
  e.preventDefault();
  const form = e.currentTarget;
  clearErrors(form);
  
  const orderNumber = document.getElementById('order-number');
  const queryResult = document.getElementById('query-result');
  const orderInfo = document.getElementById('order-info');
  
  if (!orderNumber.value) {
    showError(orderNumber, '請輸入訂單編號');
    return;
  }
  
  // 模擬查詢結果
  const mockOrderData = {
    'ORD-2024-001': {
      status: '處理中',
      date: '2024-01-15',
      items: '時尚T恤 x2, 牛仔褲 x1',
      total: 'NT$ 2,580'
    },
    'ORD-2024-002': {
      status: '已完成',
      date: '2024-01-10',
      items: '運動鞋 x1',
      total: 'NT$ 1,890'
    }
  };
  
  const orderData = mockOrderData[orderNumber.value];
  if (orderData) {
    orderInfo.innerHTML = `
      <p><strong>訂單狀態：</strong>${orderData.status}</p>
      <p><strong>訂單日期：</strong>${orderData.date}</p>
      <p><strong>商品：</strong>${orderData.items}</p>
      <p><strong>總金額：</strong>${orderData.total}</p>
    `;
    queryResult.style.display = 'block';
    toast('查詢成功！');
  } else {
    orderInfo.innerHTML = '<p>找不到此訂單編號，請確認後重新輸入。</p>';
    queryResult.style.display = 'block';
    toast('查詢失敗，請確認訂單編號是否正確。');
  }
});

// 訂單相關功能
function viewOrder(orderId) {
  toast(`查看訂單 ${orderId} 詳情（示意功能）`);
}

function trackOrder(orderId) {
  toast(`追蹤訂單 ${orderId} 物流（示意功能）`);
}

function reviewOrder(orderId) {
  toast(`評價訂單 ${orderId} 商品（示意功能）`);
}

// 登出功能
function logout() {
  if (confirm('確定要登出嗎？')) {
    localStorage.removeItem('memberData');
    localStorage.removeItem('authToken');
    toast('已登出，正在跳轉到登入頁面...');
    setTimeout(() => {
      window.location.href = 'index.html';
    }, 1000);
  }
}

// 頁面載入時初始化
document.addEventListener('DOMContentLoaded', () => {
  loadMemberData();
});
