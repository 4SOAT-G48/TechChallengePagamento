# Use an official Debian runtime as a parent image
FROM debian:bullseye-slim

# Install OpenJDK 17 and necessary tools
RUN apt-get update && apt-get install -y openjdk-17-jdk iputils-ping netcat

# Set the working directory
WORKDIR /app

# Copy the current directory contents into the container at /app
COPY . /app

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "target/TechChallengePagamento-1.0.0.jar"]