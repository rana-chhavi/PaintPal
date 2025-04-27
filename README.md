# PaintPal

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Technologies Used](#technologies-used)
    - [Backend (paintPal-api)](#paintPal-api)
    - [Frontend (paintPal-ui)](#paintPal-ui)
- [Learning Objectives](#learning-objectives)
- [Getting Started](#getting-started)

## Overview

PaintPal is a full-stack application that enables users to manage their painting collections and engage with a community of painting enthusiasts. It offers features such as user registration, secure email validation, painting management (including creation, updating, sharing, and archiving), painting borrowing with availability checks, painting return functionality, and approval of painting returns. The application ensures security using JWT tokens and follows best practices in REST API design. The backend is built with Spring Boot 3 and Spring Security 6, while the frontend is developed using Angular with Bootstrap for styling.

## Features

- User Registration: Users can register for a new account.
- Email Validation: Accounts are activated using secure email validation codes.
- User Authentication: Existing users can log in to their accounts securely.
- Painting Management: Users can create, update, share, and archive their paintings.
- Painting Borrowing: Implements necessary checks to determine if a painting is borrowable.
- Painting Returning: Users can return borrowed paintings.
- Painting Return Approval: Functionality to approve painting returns.

#### Class diagram
![image](https://github.com/user-attachments/assets/bbe3d766-f80e-4a39-b6aa-6707dfc34c90)



#### Spring security diagram
![image](https://github.com/user-attachments/assets/fa1dbcf6-e029-4a76-ac27-894d5804c2bf)



## Technologies Used

### Backend (paintPal-api)

- Spring Boot 3
- Spring Security 6
- JWT Token Authentication
- Spring Data JPA
- JSR-303 and Spring Validation
- OpenAPI and Swagger UI Documentation
- Docker
- GitHub Actions
- Keycloak

### Frontend (paintPal-ui)

- Angular
- Component-Based Architecture
- Lazy Loading
- Authentication Guard
- OpenAPI Generator for Angular
- Bootstrap

## Learning Objectives

By following this project, students will learn:

- Designing a class diagram from business requirements
- Implementing a mono repo approach
- Securing an application using JWT tokens with Spring Security
- Registering users and validating accounts via email
- Utilizing inheritance with Spring Data JPA
- Implementing the service layer and handling application exceptions
- Object validation using JSR-303 and Spring Validation
- Handling custom exceptions
- Implementing pagination and REST API best practices
- Using Spring Profiles for environment-specific configurations
- Documenting APIs using OpenAPI and Swagger UI
- Implementing business requirements and handling business exceptions
- Dockerizing the infrastructure
- CI/CD pipeline & deployment


## Getting Started

To get started with the PaintPal project, follow the setup instructions in the respective directories:

- [Backend Setup Instructions](/paintPal-api/README.md)
- [Frontend Setup Instructions](paintPal-ui/README.md)

