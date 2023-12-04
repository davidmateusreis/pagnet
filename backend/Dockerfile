FROM eclipse-temurin:17.0.8.1_1-jdk-jammy
VOLUME /tmp
COPY . .
RUN chmod +x mvnw && ./mvnw clean install -DskipTests
ENTRYPOINT [ "java", "-jar", "target/backend-0.0.1-SNAPSHOT.jar" ]

