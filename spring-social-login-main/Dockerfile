FROM openjdk:17

ARG JAR_LOCATION=target/*.jar

COPY ${JAR_LOCATION} social-login.jar

ENTRYPOINT ["java", "-jar", "/social-login.jar"]

EXPOSE 9090