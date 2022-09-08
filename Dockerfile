FROM openjdk:8-jdk-alpine
EXPOSE 8080
ADD /build/libs/docker-k8s-app-0.0.1-SNAPSHOT.jar docker-k8s-app.jar

ENTRYPOINT ["java","-jar","/docker-k8s-app.jar"]