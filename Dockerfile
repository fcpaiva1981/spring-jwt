FROM openjdk:21-jdk

COPY target/JwtSprinSecurity-0.0.1-SNAPSHOT.jar /app/app.jar

EXPOSE 9080

CMD ["java", "-jar", "app/app.jar"]