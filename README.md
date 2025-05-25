# Loan-Management-System
A secure and role-based Loan Management System built with Spring Boot, Spring Security, and JWT. It allows customers to apply for loans, admins to approve/reject them, and automatically calculates EMIs and repayment schedules.


🚀 Features

✅ Authentication

User registration & login

JWT-based stateless authentication

Role-based access control (CUSTOMER, ADMIN)

🧾 Loan Application (Customer)

Submit loan application with type, duration, income, etc.

✔️ Loan Approval (Admin)

Approve or reject pending loans with remarks

Generate loan start date and EMI

Automatically create a repayment schedule

💸 Repayment Tracking

Repayment entities generated on approval

Future support for marking payments as paid

🛠 Tech Stack

Backend:	Spring Boot, Spring Security

Authentication:	JWT

ORM:	Spring Data JPA

Database:	PostgreSQL

Build Tool:	Maven

 Setup Instructions
 
1. Clone the Repository
<pre> git clone https://github.com/yourusername/loan-management-system.git 
  cd loan 
</pre>

2. Configure PostgreSQL
   <pre>
   spring.datasource.url=jdbc:postgresql://localhost:5432/loan_db
   
   spring.datasource.username=your_postgres_username
   
   spring.datasource.password=your_postgres_password

   spring.jpa.hibernate.ddl-auto=update
   
   spring.jpa.show-sql=true
   spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
   </pre>
 3. Build & Run
   <pre>
    mvn clean install

    mvn spring-boot:run
   </pre>
