FROM openjdk:17-jdk

WORKDIR /app

COPY target/pizzazoApp.jar pizzazoApp.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "pizzazoApp.jar"]
