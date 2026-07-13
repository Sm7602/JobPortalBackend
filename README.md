# 🚀 Job Portal Backend API (with JWT Authentication)

A backend REST API for a **Job Portal / Recruitment platform**, built with **Java 21 + Spring Boot 3**. Companies can post jobs, candidates can apply, and admins manage the platform — all secured with **Spring Security + JWT (stateless authentication)**.

---

## 🧩 Tech Stack

| Layer | Technology |
|------------|--------------------------------------------|
| Language | Java 21 |
| Framework | Spring Boot 3.5.3 |
| Security | Spring Security, JWT (jjwt 0.13.0), BCrypt |
| Persistence | Spring Data JPA, Hibernate |
| Database | MySQL |
| Validation | Jakarta Bean Validation |
| Build Tool | Maven |
| Utilities | Lombok, Spring DevTools |
| API Testing | Postman |

---

## 🏗️ Architecture

A classic layered architecture with a dedicated security package:

```
com.jpb.api
│
├── controller      → REST endpoints (Auth, Candidate, Company, Job, Application, Admin)
├── service         → Business logic
├── dao             → Spring Data JPA repositories
├── dto             → Request / Response / Update objects (per module)
├── entity          → JPA entities (User, Candidate, Company, Job, Application, Admin, Role)
├── security        → JwtService, JwtAuthenticationFilter, SecurityConfiguration, ApplicationConfig
└── JobPortalBackendApplication.java
```

**Request → Controller → Service → Repository → Database**, with DTOs used at the boundary so entities are never exposed directly.

---

## 🔐 Authentication & Security

- **JWT-based stateless authentication** (`SessionCreationPolicy.STATELESS`).
- Passwords hashed with **BCrypt**.
- A single `User` entity implements `UserDetails` and is linked (one-to-one) to a `Candidate`, `Company`, or `Admin` profile.
- A `JwtAuthenticationFilter` validates the `Authorization: Bearer <token>` header on each request.
- Token signed with **HS256**, valid for **24 hours**.

### Roles

```
ADMIN | CANDIDATE | COMPANY
```

### Auth Endpoints

```http
POST /api/auth/registerCandidate     # Register a candidate + get a token
POST /api/auth/registerCompany       # Register a company   + get a token
POST /api/auth/registerAdmin         # Register an admin     + get a token
POST /api/auth/authenticate          # Login → returns a JWT
```

**Sample login response**

```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9...",
  "tokenType": "Bearer",
  "userId": 1,
  "email": "user@example.com",
  "role": "CANDIDATE",
  "message": "Login successful"
}
```

Use the token on protected routes:

```http
Authorization: Bearer <your-token>
```

---

## 📡 API Endpoints

### Candidates — `/api/candidates`
```http
POST   /api/candidates
GET    /api/candidates
GET    /api/candidates/{id}
PUT    /api/candidates/{id}
DELETE /api/candidates/{id}      # soft delete (active = false)
```

### Companies — `/api/companies`
```http
POST   /api/companies
GET    /api/companies
GET    /api/companies/{id}
PUT    /api/companies/{id}
DELETE /api/companies/{id}
```

### Jobs — `/api/jobs`
```http
POST   /api/jobs?companyId={companyId}
GET    /api/jobs
GET    /api/jobs/{id}
PUT    /api/jobs/{id}
DELETE /api/jobs/{id}
GET    /api/jobs/search?keyword={keyword}
GET    /api/jobs/location/{city}
GET    /api/jobs/company/{companyId}
```

### Applications — `/api/applications`
```http
POST   /api/applications
GET    /api/applications
GET    /api/applications/{id}
GET    /api/applications/job/{jobId}
GET    /api/applications/candidate/{candidateId}
PUT    /api/applications/{id}         # update status
DELETE /api/applications/{id}         # soft delete
```

### Admins — `/api/admins`
```http
POST   /api/admins
GET    /api/admins
GET    /api/admins/{id}
PUT    /api/admins/{id}
DELETE /api/admins/{id}
```

---

## 🗂️ Data Model & Relationships

```
User (1) ──── (1) Candidate ──< Application >── Job >── (1) Company
User (1) ──── (1) Company
User (1) ──── (1) Admin
```

- **Company** `1 ── *` **Job**
- **Job** `1 ── *` **Application**
- **Candidate** `1 ── *` **Application**
- **User** `1 ── 1` **Candidate / Company / Admin**

Common auditing fields on domain entities: `createdAt`, `updatedAt`, `active` (used for soft deletes).

---

## ⚙️ Getting Started

### 1. Prerequisites
- JDK 21
- Maven 3.9+
- MySQL 8+

### 2. Clone
```bash
git clone <your-repo-url>
cd JobPortalBackend
```

### 3. Create the database
```sql
CREATE DATABASE jobportalbackend;
```

### 4. Configure `src/main/resources/application.properties`

> ⚠️ Do not commit real credentials. Prefer environment variables.

```properties
server.port=8080

spring.datasource.url=jdbc:mysql://localhost:3306/jobportalbackend
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# JWT secret (base64) — keep this out of source control
jwt.secret=${JWT_SECRET}
```

### 5. Run
```bash
mvn spring-boot:run
```

App starts on **http://localhost:8080**

---

## 🧪 Testing

APIs tested with **Postman**. Typical flow:

1. `POST /api/auth/registerCandidate` → copy the returned `token`.
2. Send the token as `Authorization: Bearer <token>` on subsequent calls.
3. `POST /api/applications` → candidate applies to a job.

---

## 🛣️ Roadmap / Known Improvements

These are the next steps to make the project production-ready:

- [ ] **Enforce authorization** — endpoints are currently open (`/api/**` permitAll); enable role-based rules so only `/api/auth/**` is public.
- [ ] **Externalize secrets** — move the JWT secret and DB password to environment variables.
- [ ] **Global exception handling** — add `@RestControllerAdvice` to return proper 404/409/400 responses instead of 500.
- [ ] **Replace `System.out.println` with SLF4J logging** (and stop logging the raw token).
- [ ] **Add `@Transactional`** to multi-step service methods (e.g. registration).
- [ ] **Pagination** on list endpoints.
- [ ] **Fix role enum typo** (`CAMPANY` → `COMPANY`).
- [ ] Swagger / OpenAPI documentation.
- [ ] Refresh tokens, email notifications, resume upload, Docker deployment.

---

## 👨‍💻 Author

**Souvik Maity** — Java / Spring Boot Backend Developer

[![GitHub](https://img.shields.io/badge/GitHub-sm7602-181717?style=for-the-badge&logo=github)](https://github.com/sm7602)
[![LinkedIn](https://img.shields.io/badge/LinkedIn-Connect-0077B5?style=for-the-badge&logo=linkedin)](https://linkedin.com/in/souvik-maity-2a6759333)
[![Email](https://img.shields.io/badge/Email-Contact-D14836?style=for-the-badge&logo=gmail)](mailto:sm2496444l@gmail.com)

