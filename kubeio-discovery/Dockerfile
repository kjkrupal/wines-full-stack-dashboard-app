FROM openjdk:11
EXPOSE 8761
RUN mkdir /app
COPY build/libs/*.jar /app/discovery.jar
ENTRYPOINT ["java", "-jar", "/app/discovery.jar"]