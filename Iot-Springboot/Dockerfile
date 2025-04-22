FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

# Kopiér det færdige JAR-build fra target-mappen
COPY ./target/*.jar app.jar

# Eksponer porten
EXPOSE 8080

# Start applikationen
ENTRYPOINT ["java", "-jar", "app.jar"]