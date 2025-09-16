FROM openjdk:17-slim

WORKDIR /app

COPY /target/demo-0.0.1-SNAPSHOT.jar demo-app.jar

ENTRYPOINT ["java","-jar","demo-app.jar"]