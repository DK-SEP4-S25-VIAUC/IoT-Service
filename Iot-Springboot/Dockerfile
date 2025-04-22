# Use a lightweight JDK base image
FROM eclipse-temurin:21-jdk-alpine

# Create app directory
WORKDIR /app

# Copy the fat JAR built by Spring Boot
COPY target/*.jar app.jar

# Expose the port your app runs on
EXPOSE 8080

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]