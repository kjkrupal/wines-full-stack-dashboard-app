FROM openjdk:11
EXPOSE 8080
RUN mkdir /app
COPY build/libs/*.jar /app/wineservice.jar
ENTRYPOINT ["java", "-jar", "/app/wineservice.jar"]