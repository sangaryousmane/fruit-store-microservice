# Fruit Store Microservice

This repository contains a simple Fruit Store Microservice built with Spring Boot that allows users to manage fruits, place orders, and handle customer operations.

### Technologies Used

- Java
- Spring Boot
- Spring Data JPA
- H2 Database for Fruit and order service (for demo purposes, can be changed to other databases)
- MYSQL for Customers service
- Maven
- API Gateway & Eureka Service Registry
- Kubernetes and Docker 
- Intellij Idea for development, Postman for testing
- Git and GitHub 
- Sonarqube, Jenkins & Argo CD 
- Deploy to AWS

### Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com/sangaryousmane/fruit-store-microservice.git

2. **Navigate to project directory**
   ```bash
   cd fruits-microservice

3. **Build each service in the root directory**
  ```bash
  mvn clean install
  docker build -t projectname:tag

## Endpoint
GET /api/v1/fruits: Retrieve all fruits.
GET /api/v1/fruits/{id}: Retrieve a specific fruit by ID.
POST /api/v1/fruits/saveFruit: Add a new fruit.
