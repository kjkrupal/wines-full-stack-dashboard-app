FROM openjdk:11
EXPOSE 8083
RUN mkdir /app
COPY build/libs/*.jar /app/auth.jar
ENTRYPOINT ["java", "-jar", "/app/auth.jar"]