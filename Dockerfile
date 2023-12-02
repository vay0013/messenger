FROM maven AS stage1
WORKDIR /app
COPY pom.xml /app
COPY . /app
RUN mvn clean package
#    -DskipTests

FROM openjdk:17 AS final
COPY --from=stage1 app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]