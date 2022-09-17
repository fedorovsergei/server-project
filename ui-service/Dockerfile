FROM maven:3.6-adoptopenjdk-15 as target
WORKDIR /build
COPY pom.xml .
RUN mvn dependency:go-offline

COPY src/ /build/src/
RUN mvn package

FROM openjdk:15
CMD exec java $JAVA_OPTS -jar /app/my-app.jar
COPY --from=target /build/target/*.jar /app/my-app.jar