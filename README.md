# 🚀 Job Portal Backend API

A production-style Job Portal Backend built using Java Spring Boot, Spring Data JPA, Hibernate, and MySQL.

This project simulates a real-world recruitment platform where companies can post jobs, candidates can apply for jobs, and administrators can manage the platform.

---

## 📌 Project Overview

The Job Portal Backend provides RESTful APIs for:

* Candidate Management
* Company Management
* Job Management
* Application Tracking
* Admin Management

The system follows a layered architecture using:

* Controller Layer
* Service Layer
* Repository Layer
* Entity Layer

---

## 🛠 Tech Stack

### Backend

* Java 21
* Spring Boot
* Spring Data JPA
* Hibernate ORM

### Database

* MySQL

### Build Tool

* Maven

### Utilities

* Lombok
* DevTools

### API Testing

* Postman

---

# 📂 Project Structure

```text
JobPortalBackend
│
├── controller
│   ├── AdminController
│   ├── CandidateController
│   ├── CompanyController
│   ├── JobController
│   └── ApplicationController
│
├── service
│   ├── AdminService
│   ├── CandidateService
│   ├── CompanyService
│   ├── JobService
│   └── ApplicationService
│
├── dao
│   ├── AdminRepository
│   ├── CandidateRepository
│   ├── CompanyRepository
│   ├── JobRepository
│   └── ApplicationRepository
│
├── entity
│   ├── Admin
│   ├── Candidate
│   ├── Company
│   ├── Job
│   └── Application
│
└── JobPortalBackendApplication
```

---

# 🎯 Features

## Candidate Module

### APIs

```http
POST   /api/candidates
GET    /api/candidates
GET    /api/candidates/{id}
PUT    /api/candidates/{id}
DELETE /api/candidates/{id}
```

### Candidate Information

* Personal Details
* Skills
* Experience
* Qualification
* Resume URL
* LinkedIn URL
* GitHub URL

---

## Company Module

### APIs

```http
POST   /api/companies
GET    /api/companies
GET    /api/companies/{id}
PUT    /api/companies/{id}
DELETE /api/companies/{id}
```

### Company Information

* Company Name
* Industry
* Website
* Location
* Logo
* Description

---

## Job Module

### APIs

```http
POST   /api/jobs
GET    /api/jobs
GET    /api/jobs/{id}
PUT    /api/jobs/{id}
DELETE /api/jobs/{id}

GET    /api/jobs/search
GET    /api/jobs/location/{city}
GET    /api/jobs/company/{companyId}
```

### Job Information

* Title
* Description
* Salary
* Location
* Vacancies
* Experience Required
* Skills Required
* Job Type
* Application Deadline

---

## Application Module

### APIs

```http
POST   /api/applications

GET    /api/applications

GET    /api/applications/{id}

GET    /api/applications/job/{jobId}

GET    /api/applications/candidate/{candidateId}

PUT    /api/applications/{id}

DELETE /api/applications/{id}
```

### Application Features

* Apply For Jobs
* Track Application Status
* View Candidate Applications
* View Job Applications
* Update Application Status
* Soft Delete Support

---

## Admin Module

### APIs

```http
POST   /api/admins
GET    /api/admins
GET    /api/admins/{id}
PUT    /api/admins/{id}
DELETE /api/admins/{id}
```

---

# 🔗 Entity Relationships

## Company ↔ Job

```text
One Company
     |
     |
     ▼
Many Jobs
```

---

## Candidate ↔ Application

```text
One Candidate
      |
      |
      ▼
Many Applications
```

---

## Job ↔ Application

```text
One Job
   |
   |
   ▼
Many Applications
```

---

# 📊 Database Design

```text
Company
   |
   └──< Job
            |
            └──< Application >── Candidate

Admin
```

---

# 🚀 How To Run

## Clone Repository

```bash
git clone https://github.com/yourusername/job-portal-backend.git
```

## Open Project

```bash
cd job-portal-backend
```

## Configure MySQL

application.properties

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/jobportal
spring.datasource.username=root
spring.datasource.password=yourpassword

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

## Run Application

```bash
mvn spring-boot:run
```

Application starts on:

```http
http://localhost:8080
```

---

# 📮 API Testing

All APIs were tested using Postman.

Example:

```http
POST /api/applications
```

Candidate applies for a job.

```http
GET /api/jobs/location/Kolkata
```

Fetch jobs available in Kolkata.

---

# 💡 Key Learnings

* Spring Boot REST APIs
* Layered Architecture
* Hibernate Relationships
* JPA Repositories
* MySQL Integration
* Entity Mapping
* Exception Handling
* Backend System Design

---

# 🔮 Future Enhancements

* Spring Security + JWT Authentication
* Role Based Authorization
* Swagger Documentation
* Docker Deployment
* Email Notifications
* Resume Upload
* Job Recommendation System
* Admin Dashboard Analytics

---

# 👨‍💻 Author

Souvik Maity

Java Backend Developer

GitHub: https://github.com/sm7602

LinkedIn: https://www.linkedin.com/in/souvik-maity-2a6759333
# JobPortalBackend

