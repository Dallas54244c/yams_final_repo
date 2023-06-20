# Étape 1 : Construction de l'application
FROM maven:3.8.3-openjdk-11-slim AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn package

# Étape 2 : Exécution des tests d'intégration
FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
COPY src/test ./src/test
CMD ["java", "-jar", "app.jar", "test"]
