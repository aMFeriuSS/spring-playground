# Spring Playground

A Spring Boot application demonstrating cookie-based visitor tracking with environment-aware configuration.

## Prerequisites

- Java 17 or higher
- Gradle installed on your machine

## Getting Started

1. Clone the repository:
```bash
git clone git@github.com:aMFeriuSS/spring-playground.git
cd spring-playground
```

2. Start the application using Gradle wrapper:
```bash
./gradlew bootRun
```

The application will start in development mode by default. To run in production mode:
```bash
./gradlew bootRun --args='--spring.profiles.active=prod'
```

## Features

- Cookie-based visitor tracking
- Environment-aware configuration (dev/prod)
- Secure cookie handling
- Cross-browser compatibility

## Development vs Production

- **Development Mode** (default):
  - HTTP connections allowed
  - Secure cookie flag disabled
  - Suitable for local development

- **Production Mode**:
  - HTTPS required
  - Secure cookie flag enabled
  - Enhanced security settings 