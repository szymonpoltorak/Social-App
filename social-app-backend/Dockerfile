FROM maven:3.9.0-eclipse-temurin-17-alpine AS build

WORKDIR /home/app

COPY pom.xml .

COPY src/ src/

RUN mvn -f /home/app/pom.xml clean package -X

FROM openjdk:21-jdk-slim

COPY --from=build /home/app/target/social-app-backend-0.0.1-SNAPSHOT.jar /usr/local/lib/app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/usr/local/lib/app.jar"]
