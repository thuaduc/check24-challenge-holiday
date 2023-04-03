# Use openjdk as base image
FROM openjdk:latest

# Set working directory
WORKDIR /app

# Copy the packaged jar file into the container
COPY target/holiday-backend-0.0.1-SNAPSHOT.jar app.jar

# Expose port
EXPOSE 8080

# Set entry point for the container
ENTRYPOINT ["java", "-jar", "app.jar"]
