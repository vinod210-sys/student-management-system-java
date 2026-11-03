# Student Management System

## Project Overview
A Student Management System developed using Java and Spring Boot to manage student records through RESTful APIs. It supports CRUD operations, validation, searching, sorting, pagination, and duplicate email prevention.

## Technologies Used
- Java 17
- Spring Boot 4.1.2
- Spring Data JPA
- Hibernate
- MySQL
- Maven
- Swagger / OpenAPI
- Postman

## Features
- Add a new student
- Retrieve all students
- Retrieve a student by ID
- Update student details
- Delete a student
- Search students by department
- Search by exact and partial name
- Pagination and sorting
- DTO implementation
- Input validation
- Custom exception handling
- Duplicate email prevention
- Swagger API documentation

## Database Configuration

Create a MySQL database:

```sql
CREATE DATABASE student_management;
```

Configure your local `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/student_management
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
server.port=8080
```

## Running the Project

1. Clone the repository.
2. Configure your MySQL database.
3. Configure `application.properties`.
4. Run the application using:

```bash
mvn spring-boot:run
```

## API Documentation

After starting the application, open:

http://localhost:8080/swagger-ui/index.html

## Developer

Vinod Kumar