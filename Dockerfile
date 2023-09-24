FROM gradle:8.1.0-jdk17 AS build
ARG GITLAB_TOKEN_READ_CI
MAINTAINER Alexander <BigTows> Chapchuk
COPY --chown=gradle:gradle . /home/gradle/project
WORKDIR /home/gradle/project

RUN gradle clean build -x test

FROM openjdk:17

RUN mkdir /app
COPY --from=build /home/gradle/project/build/libs/*.jar /app/application.jar
CMD ["java", "-jar","/app/application.jar"]