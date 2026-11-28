FROM eclipse-temurin:25-jre-ubi10-minimal AS run-only
WORKDIR /app

COPY target/*.jar app.jar
#port application
EXPOSE 9080
RUN ["chmod", "+x", "app.jar"]
ENTRYPOINT ["java", "-jar", "app.jar"]