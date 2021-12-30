FROM maven
RUN mvn pom.xml clean;


FROM openjdk
RUN mvn package
WORKDIR app
ARG JSON_FILE=srcfiles/dictionary.json
COPY target/gafalag.jar app.jar
COPY ${JSON_FILE} dictionary.json
#
# Build stage
#
FROM maven:3.8.4-openjdk-17-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
# build with prod profile
RUN mvn -f /home/app/pom.xml clean package -Pprod

#
# Package stage
#
FROM openjdk:17-slim
COPY --from=build /home/app/target/bp-web-backend-1.0-SNAPSHOT.jar ./bp-web-backend.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","./bp-web-backend.jar"]
ENTRYPOINT ["java","-jar","/app.jar","dictionary.json"]