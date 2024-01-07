# Spring Boot 3.0 Security with JWT Implementation
This project demonstrates the implementation of security using Spring Boot 3.0 and JSON Web Tokens (JWT). It includes the following features:

<img src="https://github.com/sangaryousmane/spring-social-login/blob/main/img.png" />
    
  **image courtesy**: Aliboucoding.com

## Features
* User registration and login with JWT authentication
* Password encryption using BCrypt
* Role-based authorization with Spring Security
<!-- * Customized access denied handling -->
<!-- * Logout mechanism -->
<!-- * Refresh token
 -->
 
## Technologies
* Spring Boot 3.0
* Spring Security
* JSON Web Tokens (JWT)
* BCrypt
* Maven
* PostgreSQL 13+
* GitHub CI/CD workflows
* Docker
* Kubernetes - create a manifest file for postgreSQL and a deployment for the application
 
## Getting Started
To get started with this project, you will need to have the following installed on your local machine:

* JDK 17+
* Maven 3+


To build and run the project, follow these steps:

* Clone the repository: `git clone https://github.com/ali-bouali/spring-boot-3-jwt-security.git`
* Navigate to the project directory: cd spring-boot-security-jwt
* Add database "playground" to postgres 
* Build the project: mvn clean install
* Run the project: mvn spring-boot:run 
* Please note that there is CI/CD support that build, test and deploy your docker image to docker hub for you
-> The application will be available at http://localhost:8080.
* All manifest file relating to k8s can be foundin the /k8s directory. Navigate there and get some insight of the configuration
