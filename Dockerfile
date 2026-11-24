FROM eclipse-temurin:21-jre-alpine AS run-only
WORKDIR /app

COPY target/*.jar app.jar
#port application
EXPOSE 9080
RUN ["chmod", "+x", "app.jar"]
ENTRYPOINT ["java", "-jar", "app.jar"]