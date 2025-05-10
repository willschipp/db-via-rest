FROM eclipse-temurin:latest

RUN mkdir /app

COPY ./build/libs/*.jar /app/app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app/app.jar"]