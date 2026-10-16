# BKSTG

A social photo sharing app where users can publish posts, discover other people, and manage their own profiles.

## Features

- **User discovery**: browse a randomized selection of user profiles on the home screen
- **Search**: find users by name or username with live results
- **Posts**: create posts by uploading an image from your device
- **Profiles**: view any user's profile with avatar, name, username, bio, and their post grid
- **Profile editing**: update your name, username (when available), bio, and profile picture, with a preview before saving
- **Post viewer**: open a post in an enlarged view, with the rest of the interface dimmed

## Tech Stack

| Layer | Technology |
|-------|-----------|
| Frontend | React, TypeScript |
| Backend | Java, Spring Boot, Spring Data JPA, Spring Security , JWT |
| Database | PostgreSQL |

## Architecture

The backend exposes a REST API that handles users, posts, search, and profile management.

## Getting Started

### Backend

```bash
cd backend
./mvnw spring-boot:run
```

### Frontend

```bash
cd frontend
npm install
npm run dev
```

### Environment Variables

Create the required configuration file for the backend (`application.properties`) with your database connection:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/[database_name]
spring.datasource.username=[username]
spring.datasource.password=[password]
```

## Project Structure

```
backend/     # Spring Boot REST API
frontend/    # Web client
```

## Status

BKSTG is currently under development. Features and architecture are still evolving, and the list above represents the project's intended direction rather than a finished feature set.

More details, screenshots, and setup instructions will be added as development progresses.


---

*Built by [rafael-vincensi](https://github.com/rafael-vincensi)*
