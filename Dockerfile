FROM mcr.microsoft.com/openjdk/jdk:21-mariner
COPY /build/libs/service-0.0.1-SNAPSHOT-plain.jar service-0.0.1-SNAPSHOT-plain.jar
ENTRYPOINT ["java", "-jar", "service-0.0.1-SNAPSHOT-plain.jar"]
