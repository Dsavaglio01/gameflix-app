FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY target/*.jar app.jar
LABEL authors="dosav"

ENTRYPOINT ["java", "-jar", "app.jar"]

