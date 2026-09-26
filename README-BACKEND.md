# Email Template Management System - Spring Boot Backend

## 📋 Tổng Quan

Đây là một hệ thống quản lý email template được xây dựng với **Spring Boot 3.1.5**, **Hibernate/JPA**, **MySQL**, và tuân theo tiêu chuẩn DAO Pattern.

## 🚀 Công Nghệ Sử Dụng

- **Java 17+**
- **Spring Boot 3.1.5**
- **Spring Data JPA + Hibernate**
- **MySQL 8.0+**
- **Maven**
- **Lombok** (giảm boilerplate code)
- **REST API**

## 📁 Cấu Trúc Dự Án

```
src/main/
├── java/com/nova/
│   ├── EmailTemplateApplication.java          # Main Application
│   ├── entity/
│   │   └── EmailTemplate.java                  # JPA Entity
│   ├── repository/
│   │   └── EmailTemplateRepository.java        # Spring Data JPA Repository
│   ├── dao/
│   │   ├── EmailTemplateDao.java              # DAO Interface
│   │   └── impl/
│   │       └── EmailTemplateDaoImpl.java       # DAO Implementation
│   ├── service/
│   │   ├── EmailTemplateService.java          # Business Logic Interface
│   │   └── impl/
│   │       └── EmailTemplateServiceImpl.java   # Service Implementation
│   ├── controller/
│   │   └── EmailTemplateController.java       # REST Controller
│   ├── dto/
│   │   └── EmailTemplateDto.java              # Data Transfer Object
│   └── exception/
│       └── GlobalExceptionHandler.java        # Global Exception Handler
└── resources/
    └── application.yml                         # Configuration
```

## 🔧 Cài Đặt và Chạy

### 1. Yêu Cầu Tiên Quyết

- Java 17 trở lên
- Maven 3.6+
- MySQL 8.0+

### 2. Cấu Hình Database

```sql
CREATE DATABASE email_template_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

### 3. Cấu Hình Application

Chỉnh sửa `src/main/resources/application.yml`:

```yml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/email_template_db
    username: root
    password: your_password_here
```

### 4. Chạy Ứng Dụng

```bash
# Chạy bằng Maven
mvn spring-boot:run

# Hoặc build JAR rồi chạy
mvn clean package
java -jar target/email-template-1.0.0.jar
```

Ứng dụng sẽ chạy tại: `http://localhost:8080/api`

## 📚 API Endpoints

### Create Template
```http
POST /api/templates
Content-Type: application/json

{
  "name": "Welcome Email",
  "description": "Welcome campaign email",
  "htmlContent": "<html>...</html>",
  "cssContent": "body { ... }",
  "category": "Welcome",
  "isActive": true
}
```

### Get All Templates
```http
GET /api/templates
```

### Get Active Templates
```http
GET /api/templates/active
```

### Get Template by ID
```http
GET /api/templates/{id}
```

### Get Template by Name
```http
GET /api/templates/by-name/{name}
```

### Get Templates by Category
```http
GET /api/templates/category/{category}
```

### Search Templates
```http
GET /api/templates/search?query=welcome
```

### Update Template
```http
PUT /api/templates/{id}
Content-Type: application/json

{
  "name": "Updated Name",
  "description": "Updated description",
  "htmlContent": "<html>...</html>",
  "cssContent": "body { ... }",
  "category": "Newsletter",
  "isActive": true
}
```

### Delete Template
```http
DELETE /api/templates/{id}
```

## 🏗️ Kiến Trúc Tầng

### 1. Entity Layer (JPA)
- Định nghĩa các entity tương ứng với table trong database
- Sử dụng annotations: `@Entity`, `@Table`, `@Column`
- Tự động quản lý `createdAt`, `updatedAt`

### 2. Repository Layer (Spring Data JPA)
- Extends `JpaRepository` để thực hiện CRUD
- Các custom query methods với `@Query`
- Không cần viết SQL thủ công

### 3. DAO Layer
- Interface `EmailTemplateDao` định nghĩa các operation
- Implementation `EmailTemplateDaoImpl` sử dụng Repository
- Tách biệt business logic khỏi database access

### 4. Service Layer
- Interface `EmailTemplateService` định nghĩa business logic
- Implementation `EmailTemplateServiceImpl` sử dụng DAO
- Xử lý transactions với `@Transactional`
- Logging và error handling

### 5. Controller Layer
- REST endpoints với `@RestController`
- HTTP methods: GET, POST, PUT, DELETE
- Response Entity handling
- Cross-Origin support

## 💾 Database Schema

Table `email_template` được tạo tự động với các cột:

```sql
CREATE TABLE email_template (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL UNIQUE,
  description VARCHAR(500),
  html_content LONGTEXT NOT NULL,
  css_content LONGTEXT,
  category VARCHAR(100),
  is_active BOOLEAN DEFAULT TRUE,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
```

## 🔍 Hibernate Configuration

```yml
spring:
  jpa:
    hibernate:
      ddl-auto: update  # Tự động tạo/update table
    show-sql: true
    properties:
      hibernate:
        dialect: org.hibernate.dialect.MySQL8Dialect
        format_sql: true
```

**Giải thích:**
- `ddl-auto: update` - Tự động tạo/update table nếu cần
- `show-sql: true` - Log SQL queries
- `format_sql: true` - Format SQL đẹp

## 🧪 Testing

Ví dụ tạo template mới:

```bash
curl -X POST http://localhost:8080/api/templates \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Newsletter",
    "description": "Weekly newsletter",
    "htmlContent": "<html><body><h1>Newsletter</h1></body></html>",
    "category": "Newsletter",
    "isActive": true
  }'
```

## 📝 Ghi Chú Quan Trọng

1. **Connection Pool**: Mặc định sử dụng HikariCP
2. **Transaction Management**: Tự động với `@Transactional`
3. **Lazy Loading**: Cấu hình mặc định để tránh N+1 queries
4. **Validation**: Sử dụng Jakarta Validation annotations
5. **Logging**: Cấu hình bằng application.yml

## 🚀 Next Steps

- Thêm Spring Security cho authentication
- Implement pagination và sorting
- Thêm file upload cho templates
- Integrate email sending service
- Thêm unit tests
- Docker containerization

## 📞 Support

Nếu gặp vấn đề, kiểm tra:
1. MySQL server đang chạy?
2. Database `email_template_db` đã được tạo?
3. Credentials trong `application.yml` đúng?
4. Port 8080 không bị occupied?
