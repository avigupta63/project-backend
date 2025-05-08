FROM openjdk:17-jdk-slim
COPY target/* /opt/
EXPOSE 8080
CMD java -jar /opt/spring-backend-v1.jar