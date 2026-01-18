# Multi-stage Dockerfile
# Builder: use an official Maven image with JDK 17 to compile the app
FROM maven:3.9.5-eclipse-temurin-17 AS builder
WORKDIR /workspace

# Copy only what is needed for a reproducible build
COPY pom.xml ./
COPY src ./src

# Build the application (skip tests are disabled in CI by default, here we run them)
RUN mvn -B package

# Runtime image: minimal JRE for smaller image size
FROM eclipse-temurin:17-jre-jammy as runtime
WORKDIR /app

# Copy the packaged jar from the builder stage
ARG JAR_FILE=/workspace/target/snake-ladder-1.0.0.jar
COPY --from=builder ${JAR_FILE} ./app.jar

# CLI application; no network ports required but keep an exposed port placeholder
EXPOSE 0

# Run the jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
