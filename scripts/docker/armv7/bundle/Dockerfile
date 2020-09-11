FROM arm32v7/openjdk:11-jre

ADD build/libs/app-all-1.0.0.jar /app/app.jar
ADD current_version /app/current_version
ADD hostname /app/hostname

WORKDIR /app

CMD ["java", "-jar", "/app/app.jar"]