FROM openjdk:17
ADD target/messenger-0.1.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]