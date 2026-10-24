# BKSTG

> BKSTG is a photo-sharing social backend where users can create accounts, publish posts, look up other profiles, and manage their own profile. Images are stored in Amazon S3, and the API is secured with JWT.

## Tech Stack

<div align="center">

[![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-brightgreen?style=for-the-badge&logo=spring&logoColor=white)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-4169E1?style=for-the-badge&logo=postgresql&logoColor=white)](https://www.postgresql.org/)
[![AWS S3](https://img.shields.io/badge/AWS_S3-FF9900?style=for-the-badge&logo=amazonaws&logoColor=white)](https://aws.amazon.com/s3/)
[![JWT](https://img.shields.io/badge/JWT-000000?style=for-the-badge&logo=jsonwebtokens&logoColor=white)](https://jwt.io/)

</div>

## Features

- User registration and profile management
- JWT-based authentication
- Look up users by ID or username
- Create, list, and delete posts with image uploads
- Post images stored in Amazon S3

## Architecture

The application is a REST API organized into controllers, services, repositories, and DTOs. Post images are uploaded to an S3 bucket, and the database stores only the post metadata and the image reference.

## API Endpoints

### Authentication

| Method | Route | Description |
|--------|-------|-------------|
| `POST` | `/auth/login` | Authenticates a user and returns a JWT |

### Users (`/users`)

| Method | Route | Description |
|--------|-------|-------------|
| `POST` | `/users` | Create a user |
| `GET` | `/users/{id}` | Get a user by ID |
| `GET` | `/users/username/{username}` | Get a user by username |
| `PATCH` | `/users/{id}` | Update profile (name, username, bio, profile picture) |
| `DELETE` | `/users/{id}` | Delete a user |

### Posts (`/post`)

| Method | Route | Description |
|--------|-------|-------------|
| `POST` | `/post` | Create a post from an uploaded image |
| `GET` | `/post/user/{id}` | List all posts from a user |
| `DELETE` | `/post/{id}` | Delete a post |

## Authentication

Log in through `POST /auth/login` to receive a JWT. Send it in the `Authorization` header on protected requests:

```http
Authorization: Bearer <token>
```

Tokens expire after 1 hour by default (`jwt.expiration=3600000`).

## Getting Started

### Prerequisites

- Java 21
- Maven (or the included `./mvnw` wrapper)
- PostgreSQL
- An AWS account with an S3 bucket and access credentials

### Environment Variables

The application reads its configuration from environment variables:

| Variable | Description |
|----------|-------------|
| `DATABASE_URL` | JDBC URL, e.g. `jdbc:postgresql://localhost:5432/bkstg` |
| `DATABASE_USERNAME` | Database username |
| `DATABASE_PASSWORD` | Database password |
| `TOKEN_KEY` | Secret used to sign JWTs |
| `AWS_ACCESS_KEY_ID` | AWS access key |
| `AWS_SECRET_KEY_ID` | AWS secret key |
| `S3_BUCKET_NAME` | Name of the S3 bucket for post images and profile pictures |

### Configuration

`application.properties`:

```properties
spring.application.name=backstage

spring.datasource.url=${DATABASE_URL}
spring.datasource.username=${DATABASE_USERNAME}
spring.datasource.password=${DATABASE_PASSWORD}

api.security.token.secret=${TOKEN_KEY}
jwt.expiration=3600000

spring.cloud.aws.credentials.access-key=${AWS_ACCESS_KEY_ID}
spring.cloud.aws.credentials.secret-key=${AWS_SECRET_KEY_ID}
spring.cloud.aws.region.static=us-east-2
spring.cloud.aws.s3.bucket=${S3_BUCKET_NAME}
```

### Run the Application

```bash
cd backend
./mvnw spring-boot:run
```

---

<div align="center">

Built by [rafael-vincensi](https://github.com/rafael-vincensi)

</div>
