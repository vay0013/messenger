FROM maven AS stage1
WORKDIR /app
COPY pom.xml /app
RUN mvn dependency:resolve
COPY . /app
RUN mvn clean
RUN mvn package -DskipTests

FROM openjdk:17 AS final
COPY --from=stage1 app/target/messenger-0.1.jar app.jar
EXPOSE 8084
ENTRYPOINT ["java", "-jar", "app.jar"]