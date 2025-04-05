# Build stage
FROM eclipse-temurin:21-jdk-jammy as builder

WORKDIR /app

# Copy Gradle files
COPY gradlew .
COPY gradle ./gradle
COPY build.gradle .
COPY LICENSE .

# Copy source code
COPY src ./src

# Build the application
RUN chmod +x ./gradlew && \
    ./gradlew shadowJar --no-daemon

# Run stage
FROM eclipse-temurin:21-jre-jammy

# Install supervisor
RUN apt-get update && \
    apt-get install -y supervisor && \
    rm -rf /var/lib/apt/lists/*

WORKDIR /app

# Copy the built JAR and license from the builder stage
COPY --from=builder /app/build/libs/*.jar app.jar
COPY --from=builder /app/LICENSE .

# Create directory for logs
RUN mkdir -p /app/logs

# Copy supervisor configuration
COPY supervisord.conf /etc/supervisor/conf.d/app.conf

# Start supervisor
CMD ["/usr/bin/supervisord", "-n", "-c", "/etc/supervisor/supervisord.conf"] 