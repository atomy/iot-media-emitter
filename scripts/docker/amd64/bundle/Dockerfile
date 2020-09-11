FROM openjdk:11-jre

ADD build/libs/app-all-1.0.0.jar /app/app.jar
ADD current_version /app/current_version

CMD ["java", "-jar", "/app/app.jar"]