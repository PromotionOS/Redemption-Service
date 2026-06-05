FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -q
COPY src ./src
RUN mvn clean package -DskipTests -q

FROM eclipse-temurin:17-jre
WORKDIR /app
ENV SPRING_PROFILES_ACTIVE=railway \
    MANAGEMENT_HEALTH_REDIS_ENABLED=false \
    MANAGEMENT_HEALTH_DB_ENABLED=false
COPY --from=build /app/target/*.jar app.jar
CMD ["sh", "-c", "echo \"Starting redemption-service with SPRING_PROFILES_ACTIVE=${SPRING_PROFILES_ACTIVE}\" && java -jar /app/app.jar"]
