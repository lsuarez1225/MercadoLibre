FROM openjdk:8-jdk-slim
COPY "./target/meli-test-0.0.1.jar" "app.jar"
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]