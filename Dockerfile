# Use a JDK 21 base image
FROM openjdk:21-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the Gradle-built JAR
COPY build/libs/*.jar app.jar

# Expose the port
EXPOSE 8080

# Define the command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
