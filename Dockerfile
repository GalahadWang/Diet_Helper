# Use an official Maven runtime as a parent image
FROM maven:3.6.3-jdk-8 as builder

# Set the working directory in the container
WORKDIR /app

# Copy the pom.xml file and install dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the project source
COPY src src

# Package the application
RUN mvn package -DskipTests

# Use an official Java runtime as a base image
FROM openjdk:8-jdk-alpine

# Copy the jar file from the builder stage
COPY --from=builder /app/target/diet_helper-0.0.1-SNAPSHOT.jar /app/diet_helper.jar

# Run the jar file
ENTRYPOINT ["java","-jar","/app/diet_helper.jar"]
