FROM maven:3.9.5-eclipse-temurin-17 AS builder
WORKDIR /workspace

COPY pom.xml ./
COPY src ./src

RUN mvn -B package

FROM eclipse-temurin:17-jre-jammy as runtime
WORKDIR /app

ARG JAR_FILE=/workspace/target/snake-ladder-1.0.0.jar
COPY --from=builder ${JAR_FILE} ./app.jar

EXPOSE 0

ENTRYPOINT ["java", "-jar", "/app/app.jar"]

