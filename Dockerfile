# ---- Build stage ----
FROM maven:3.9.5-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# ---- Run stage ----
FROM openjdk:21-jdk-slim
WORKDIR /app

# Copy the built JAR from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose default Spring Boot port (for local dev); Railway will override
EXPOSE 8080

# Run the JAR, binding to Railway's assigned $PORT
ENTRYPOINT ["sh", "-c", "java -jar app.jar --server.port=$PORT --server.address=0.0.0.0"]
