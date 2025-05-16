# Spring Boot User Management Demo

## Overview
A simple Spring Boot application demonstrating MVC architecture, dependency injection, and unit testing. It manages users with an in-memory repository.

## Technologies
- Java 17
- Spring Boot 3.3.4
- JUnit 5
- Gradle

## How to Run
1. Clone the repository: `git clone https://github.com/Stephan-23/spring-boot-user-demo.git`
2. Navigate to the project directory: `cd spring-boot-user-demo`
3. Run the application: `./gradlew bootRun`
4. The console will display user interaction messages.

## How to Test
Run: `./gradlew test`

## Project Structure
- **Model**: `User.java` - Represents a user with ID, name, and surname.
- **Repository**: `FakeRepo.java` - In-memory user storage.
- **Service**: `UserServiceImpl.java` - Business logic for user operations.
- **Tests**: `UserServiceTests.java` - Unit tests for service methods.

## Git Workflow
- Uses Gitflow with `main`, `develop`, and `feature/*` branches.
- Follows commit message standards (e.g., `feat: add User model`).