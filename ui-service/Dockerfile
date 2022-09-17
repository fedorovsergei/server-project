FROM maven:openjdk as build
ENV HOME=/usr/app
RUN mkdir -p $HOME
WORKDIR $HOME
ADD . $HOME
USER root
RUN --mount=type=cache,target=/root/.m2 mvn -f /home/build/pom.xml clean compile

FROM openjdk:15
COPY --from=build /usr/app/target/*.jar /app/app.jar
ENTRYPOINT java -jar /app/app.jar