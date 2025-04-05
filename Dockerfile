# Build stage
FROM eclipse-temurin:21-jdk-jammy as builder

WORKDIR /app

# Copy Gradle files
COPY gradlew .
COPY gradle ./gradle
COPY build.gradle .
COPY settings.gradle .
COPY LICENSE .

# Copy source code
COPY src ./src

# Build the application
RUN chmod +x ./gradlew && \
    ./gradlew bootJar --no-daemon

# Run stage
FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

# Copy the built JAR and license from the builder stage
COPY --from=builder /app/build/libs/*.jar app.jar
COPY --from=builder /app/LICENSE .

# Set environment variables
ENV JAVA_OPTS="-Xmx512m -Xms256m"

# Expose the port the app runs on
EXPOSE 8080

# Start the application
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"] 