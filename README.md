# BKSTG

A photo-sharing social network where users can publish images, discover people, and manage their profiles.

## Features

- **Authentication:** secure authentication and authorization using JWT.
- **User discovery:** discover people through a randomized selection of user profiles.
- **Search:** find users by name or username.
- **Posts:** create and manage image-based posts.
- **Cloud storage:** store uploaded post images and profile pictures using Amazon S3.
- **User profiles:** view profiles with avatars, names, usernames, bios, and published posts.
- **Profile editing:** update names, usernames, bios, and profile pictures.
- **Username validation:** check username availability when updating profile information.

## Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 21 |
| Framework | Spring Boot |
| Security | Spring Security, JWT |
| Persistence | Spring Data JPA, Hibernate |
| Database | PostgreSQL |
| Object Storage | Amazon S3 |
| Build Tool | Maven |

## Architecture

BKSTG uses a RESTful architecture built with Spring Boot, separating application logic, security, persistence, and file storage.

Spring Security and JWT handle authentication and access control. Spring Data JPA and Hibernate manage relational persistence with PostgreSQL, while Amazon S3 stores uploaded images independently of the database.

This separation keeps structured application data and media storage independent, providing a foundation for a scalable photo-sharing platform.

## Getting Started

### Prerequisites

- Java 21
- PostgreSQL
- An AWS account with an S3 bucket configured for image storage

### Running the Application

```bash
./mvnw spring-boot:run
```

### Configuration

Configure the database connection in `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/[database_name]
spring.datasource.username=[username]
spring.datasource.password=[password]
```

Configure the AWS region, S3 bucket, and credentials according to the application's configuration. Use environment variables, an AWS credentials provider, or IAM roles where appropriate, rather than hardcoding secrets.

---

Built by [rafael-vincensi](https://github.com/rafael-vincensi)
