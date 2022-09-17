#FROM maven:3.8.6-openjdk-18-slim as target
#ENV HOME=/usr/app
#RUN mkdir -p $HOME
#WORKDIR $HOME
#COPY pom.xml .
#RUN mvn dependency:go-offline
#
#COPY src/ /build/src/
#RUN mvn package
#
#FROM openjdk:15
#COPY --from=build /usr/app/target/*.jar /app/app.jar
#ENTRYPOINT java -jar /app/app.jar
#****************

#FROM maven:3.6-adoptopenjdk-15 as target
#ENV HOME=/usr/app
#RUN mkdir -p $HOME
#WORKDIR $HOME
#COPY pom.xml .
#RUN mvn dependency:go-offline
#
#COPY src/ $HOME/src/
#RUN mvn package
#
#FROM openjdk:15
#EXPOSE 4567
#CMD exec java $JAVA_OPTS -jar /app/app.jar
#COPY --from=target $HOME/target/*.jar /app/app.jar

#*****************
FROM maven:3.6-adoptopenjdk-15 as target
WORKDIR /build
COPY pom.xml .
RUN mvn dependency:go-offline

COPY src/ /build/src/
RUN mvn package

# Step : Package image
FROM openjdk:15
EXPOSE 4567
CMD exec java $JAVA_OPTS -jar /app/my-app.jar
COPY --from=target /build/target/*.jar /app/my-app.jar