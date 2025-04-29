FROM mcr.microsoft.com/openjdk/jdk:21-ubuntu
COPY build/libs/service-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
