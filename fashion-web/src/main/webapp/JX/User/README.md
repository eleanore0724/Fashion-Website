# 時尚網站會員系統

這是一個完整的會員系統，包含前端HTML頁面和Java Spring Boot後端API。

## 功能特色

### 前端功能
- 會員登入/註冊頁面
- 會員詳細資料管理
- 密碼修改
- 忘記密碼功能
- 響應式設計

### 後端功能
- 會員註冊API
- 會員登入API（JWT認證）
- 忘記密碼API（郵件發送）
- 密碼重設API
- 會員資料管理API
- 密碼修改API

## 技術棧

### 前端
- HTML5
- CSS3
- JavaScript (ES6+)
- 響應式設計

### 後端
- Java 17
- Spring Boot 3.2.0
- Spring Security
- Spring Data JPA
- H2 Database (開發環境)
- MySQL (生產環境)
- JWT Token認證
- Spring Mail

## 安裝與運行

### 前置要求
- Java 17 或更高版本
- Maven 3.6 或更高版本

### 1. 克隆專案
```bash
git clone <repository-url>
cd fashion-member-system
```

### 2. 配置資料庫
預設使用H2內存資料庫，無需額外配置。如需使用MySQL，請修改 `src/main/resources/application.yml`：

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/fashion_member
    username: your_username
    password: your_password
  jpa:
    hibernate:
      dialect: org.hibernate.dialect.MySQL8Dialect
```

### 3. 配置郵件服務（可選）
如需使用忘記密碼功能，請配置郵件服務：

```yaml
spring:
  mail:
    host: smtp.gmail.com
    port: 587
    username: your-email@gmail.com
    password: your-app-password
```

### 4. 運行後端
```bash
mvn spring-boot:run
```

後端服務將在 `http://localhost:8080` 啟動

### 5. 運行前端
使用任何HTTP服務器運行前端文件，例如：

```bash
# 使用Python
python -m http.server 8000

# 或使用Node.js
npx http-server -p 8000
```

前端將在 `http://localhost:8000` 啟動

## API 文檔

### 認證相關 API

#### 會員註冊
```
POST /api/auth/register
Content-Type: application/json

{
  "account": "user123",
  "email": "user@example.com",
  "name": "王小明",
  "password": "password123",
  "confirmPassword": "password123",
  "recipientName": "王小明",
  "recipientPhone": "0912-345-678",
  "city": "台北市",
  "district": "信義區",
  "addressDetail": "信義路五段7號",
  "agreeTerms": true,
  "agreeMarketing": false
}
```

#### 會員登入
```
POST /api/auth/login
Content-Type: application/json

{
  "email": "user@example.com",
  "password": "password123"
}
```

#### 忘記密碼
```
POST /api/auth/forgot-password
Content-Type: application/json

{
  "email": "user@example.com"
}
```

#### 重設密碼
```
POST /api/auth/reset-password
Content-Type: application/json

{
  "token": "reset-token",
  "newPassword": "newpassword123"
}
```

### 會員管理 API

#### 獲取會員資料
```
GET /api/member/profile
Authorization: Bearer <jwt-token>
```

#### 更新會員資料
```
PUT /api/member/profile
Authorization: Bearer <jwt-token>
Content-Type: application/json

{
  "name": "新姓名",
  "marketingConsent": true
}
```

#### 修改密碼
```
PUT /api/member/password
Authorization: Bearer <jwt-token>
Content-Type: application/json

{
  "currentPassword": "oldpassword",
  "newPassword": "newpassword123"
}
```

## 資料庫結構

### Members 表
- id: 主鍵
- account: 會員帳號（唯一）
- email: 電子郵件（唯一）
- name: 姓名
- password: 密碼（加密）
- member_level: 會員等級
- is_active: 是否啟用
- email_verified: 郵件是否驗證
- marketing_consent: 是否同意行銷
- created_at: 創建時間
- updated_at: 更新時間

### Addresses 表
- id: 主鍵
- member_id: 會員ID（外鍵）
- recipient_name: 收件人姓名
- recipient_phone: 聯絡電話
- city: 縣市
- district: 區域
- address_detail: 詳細地址
- is_default: 是否為預設地址

### Password_Reset_Tokens 表
- id: 主鍵
- member_id: 會員ID（外鍵）
- token: 重設令牌
- expires_at: 過期時間
- used: 是否已使用
- created_at: 創建時間

## 安全特性

- 密碼使用BCrypt加密
- JWT Token認證
- CORS跨域支援
- 輸入驗證和錯誤處理
- 密碼重設令牌過期機制

## 開發說明

### 專案結構
```
src/main/java/com/fashion/member/
├── controller/          # 控制器層
├── service/            # 服務層
├── repository/         # 資料存取層
├── entity/            # 實體類
├── dto/               # 資料傳輸物件
├── config/            # 配置類
└── scheduler/         # 排程任務
```

### 前端文件
- `index.html` - 登入頁面
- `register.html` - 註冊頁面
- `member-details.html` - 會員詳細資料頁面
- `reset-password.html` - 密碼重設頁面
- `app.js` - 登入頁面JavaScript
- `register-script.js` - 註冊頁面JavaScript
- `member-script.js` - 會員詳細資料頁面JavaScript

## 注意事項

1. 預設使用H2內存資料庫，重啟後資料會清空
2. 郵件功能需要配置SMTP服務
3. JWT密鑰請在生產環境中修改
4. 建議在生產環境中使用HTTPS

## 授權

此專案僅供學習和開發參考使用。
