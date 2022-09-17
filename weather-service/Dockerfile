FROM maven:3.8.6-openjdk-18-slim as target
FROM maven:openjdk as build
ENV HOME=/usr/app
RUN mkdir -p $HOME
WORKDIR $HOME
COPY pom.xml .
RUN mvn dependency:go-offline

COPY src/ /build/src/
RUN mvn package

FROM openjdk:15
COPY --from=build /usr/app/target/*.jar /app/app.jar
ENTRYPOINT java -jar /app/app.jar