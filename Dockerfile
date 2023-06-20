FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/realDemo-0.0.1-SNAPSHOT.war questionnaireProject.war
ENTRYPOINT ["java","-war","/questionnaireProject.war"]