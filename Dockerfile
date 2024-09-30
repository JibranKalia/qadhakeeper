# ---- Build Stage ----
FROM amazoncorretto:21-alpine-jdk AS build
WORKDIR /app

# Copy Gradle wrapper and necessary files to cache dependencies
COPY gradlew gradlew
COPY gradle gradle
COPY build.gradle.kts settings.gradle.kts /app/

# Make the gradlew script executable
RUN chmod +x ./gradlew

# Download dependencies without building the project
RUN ./gradlew dependencies --no-daemon || true

# Copy the source code
COPY src /app/src

# Build the project without running tests
RUN ./gradlew clean bootJar -x test --no-daemon

# ---- Runtime Stage ----
FROM amazoncorretto:21-alpine-jdk AS base
WORKDIR /app

# Copy the executable JAR from the build stage
COPY --from=build /app/build/libs/*SNAPSHOT.jar /app/app.jar

# Expose the application port
EXPOSE 8080

HEALTHCHECK --interval=30s --timeout=10s --start-period=10s --retries=3 \
  CMD wget --no-verbose --tries=1 --spider http://localhost:8080/actuator/health || exit 1

# Set JVM options and run the application
ENTRYPOINT ["java", \
    "-XshowSettings:vm", \
    "-XX:+PrintFlagsFinal", \
    "-jar", \
    "app.jar"]
