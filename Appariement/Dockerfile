# Use a base image with Java and Maven installed
FROM maven:3.8.4-openjdk-11 AS build

# Set the working directory in the container
WORKDIR /app

# Copy the Maven project file
COPY pom.xml .

# Download the project dependencies
RUN mvn dependency:go-offline -B

# Copy the source code
COPY src ./src

# Build the project
RUN mvn package

# Run the tests using the test runner (JUnit)
RUN mvn test

# Create a new stage for the final image
FROM openjdk:11-jre-slim

# Set the working directory in the container
WORKDIR /app

# Copy the built JAR file from the previous stage
COPY --from=build /app/target/Appariement-0.0.1-SNAPSHOT.jar appariement.jar

# Set the command to run the application
CMD ["java", "-jar", "appariement.jar"]
