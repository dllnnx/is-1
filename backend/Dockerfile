FROM maven:3.9.11-amazoncorretto-21-alpine
WORKDIR /app

COPY pom.xml .
COPY dragons-service/pom.xml dragons-service
COPY openapi-gen/pom.xml openapi-gen
RUN mvn dependency:go-offline -B

COPY . .
RUN mvn package -DskipTests
ENTRYPOINT java -jar ./backend/dragons-service/target/dragons_service.jar