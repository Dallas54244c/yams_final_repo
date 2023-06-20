# Use a base image
FROM maven:3.8.3-openjdk-11-slim AS build

# Set the working directory
WORKDIR /app

# Copy the pom.xml
COPY pom.xml .

# Copy the source code
COPY src ./src

# Build the application
RUN mvn clean install

# Run the integration tests
RUN mvn failsafe:integration-test -Dit.test.includes="**/IntegrationTest.java"

# Second stage for the actual container
FROM openjdk:11-jre-slim

# Set the working directory
WORKDIR /app

# Copy the built JAR file
COPY --from=build /app/target/Yams_final-0.0.1-SNAPSHOT.jar  app.jar

# Set the command to run the application
CMD ["java", "-jar", "app.jar"]
