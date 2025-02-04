# Diet Helper Project

## Overview
The **Diet Helper Project** is a **Spring Boot-based health and diet management system** that leverages AI to generate **personalized meal plans, cooking instructions, and nutrition-related quiz questions**. The system integrates **OpenAI's GPT API** to provide intelligent responses and recommendations for a healthy lifestyle.

## Features
- **User Management**: Handles user-related operations.
- **Diet Plan Management**: Creates, updates, and retrieves diet plans.
- **AI-powered Cooking Instructions**: Generates meal preparation steps using GPT.
- **Nutrition Quiz**: Creates multiple-choice questions about healthy eating.
- **Cross-Origin Support**: Configured CORS filter for frontend integration.

## Project Structure
```
diet_helper_project
│-- .idea/
│-- .mvn/
│-- src/
│   ├── main/java/com/example/diet_helper/
│   │   ├── common/             # Common utilities
│   │   ├── config/             # Application configurations (GPT, OpenAI)
│   │   ├── controller/         # API controllers (User, Diet Plan, Cooking, Exam)
│   │   ├── filter/             # CORS filter
│   │   ├── mapper/             # Database mappers (MyBatis-Plus)
│   │   ├── pojo/               # Data objects (DTOs, Entities)
│   │   │   ├── dto/            # Data Transfer Objects
│   │   │   ├── vo/             # View Objects (Requests, Responses)
│   │   ├── service/            # Business logic layer
│   │   │   ├── impl/           # Service implementations
│   │   ├── utils/              # Utility classes
│   │   ├── DietHelperApplication.java # Main Spring Boot application
│-- resources/                  # Configuration and static resources
│-- test/                        # Unit and integration tests
│-- target/                      # Compiled output
│-- Dockerfile                   # Docker containerization
│-- pom.xml                       # Maven dependencies
```

## API Endpoints
### User Management
- `POST /user/register` - Register a new user
- `POST /user/login` - User authentication
- `GET /user/profile/{id}` - Get user profile details

### Diet Plan
- `POST /diet-plan/create` - Create a new diet plan
- `GET /diet-plan/{id}` - Retrieve a diet plan
- `PUT /diet-plan/update/{id}` - Update an existing plan

### AI-powered Cooking Instructions
- `POST /cooking/generate` - Generate cooking instructions
- `GET /cooking/instructions/{planId}` - Retrieve cooking instructions

### Nutrition Quiz
- `POST /exam-question/generate` - Generate AI-powered quiz questions
- `GET /exam-question/{id}` - Retrieve a specific quiz question

## Setup & Installation
### Prerequisites
- Java 11+
- Maven 3+
- Docker (optional for containerization)
- OpenAI API Key (for AI features)

### Steps
1. **Clone the repository**
   ```sh
   git clone https://github.com/your-repo/diet_helper_project.git
   cd diet_helper_project
   ```
2. **Configure the application**
    - Update `application.properties` with database and API keys.
3. **Build and Run**
   ```sh
   mvn clean install
   mvn spring-boot:run
   ```
4. **Access the API**
    - The application runs on `http://localhost:8080`
    - Use **Postman** or **Swagger** to test the endpoints.

### Running with Docker
```sh
docker build -t diet_helper .
docker run -p 8080:8080 diet_helper
```

## Technologies Used
- **Spring Boot** (Backend Framework)
- **MyBatis-Plus** (ORM for database operations)
- **OpenAI GPT API** (AI integration)
- **Maven** (Build management)
- **Docker** (Deployment & Containerization)

## License
This project is licensed under the MIT License.

