FROM eclipse-temurin:17-jre

LABEL authors="alzaidy"

# Set working directory
WORKDIR /home/app


# Copy built jar file from local build folder to the container
COPY build/libs/jenkins-notes-api.jar notes-api.jar

EXPOSE 8082

# Command to run your app
ENTRYPOINT ["java", "-jar", "notes-api.jar"]