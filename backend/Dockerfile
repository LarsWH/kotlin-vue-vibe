FROM gradle:8.5-jdk17 AS build
WORKDIR /app
#COPY . .
#RUN gradle build --no-daemon --info --stacktrace

#FROM eclipse-temurin:17-jre
#WORKDIR /app
#COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8082
#ENTRYPOINT ["java", "-jar", "app.jar"]
#ENTRYPOINT ["tail", "-f", "/dev/null"]