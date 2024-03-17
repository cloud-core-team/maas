FROM openjdk:17-alpine

ADD target/dbaas-1.0.0-SNAPSHOT-runner.jar /app/
EXPOSE 8080

ENTRYPOINT ["/opt/openjdk-17/bin/java", "-jar", "/app/dbaas-1.0.0-SNAPSHOT-runner.jar"]