FROM openjdk:11
EXPOSE 8082
RUN mkdir /app
COPY build/libs/*.jar /app/gateway.jar
ENTRYPOINT ["java", "-jar", "/app/gateway.jar"]