FROM maven:openjdk as build
ENV HOME=/usr/app
RUN mkdir -p $HOME
WORKDIR $HOME
ADD . $HOME
RUN mvn package

FROM openjdk:15
COPY --from=build /usr/app/target/*.jar /app/compare-service.jar
ENTRYPOINT java -jar /app/compare-service.jar