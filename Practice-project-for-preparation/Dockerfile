#FROM eclipse-temurin:17-jre
#WORKDIR /app
#ARG JAR_FOLDER=target/*.jar
#ARG ACTIVE_PROFILE=dev
#COPY ${JAR_FOLDER} app.jar
#ENV SPRING_ACTIVE_PROFILES=${ACTIVE_PROFILE}
#ENTRYPOINT ["java", "-jar", "app.jar"]

FROM maven:3.9.16-eclipse-temurin-17 AS build
WORKDIR /apps
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre
WORKDIR /apps
COPY --from=build /apps/target/*.jar app.jar
EXPOSE 9091
ENTRYPOINT ["java", "-jar", "app.jar"]

#ARG ACTIVE_PROFILE=dev
#docker build --build-arg ACTIVE_PROFILE=dev -t myapp .