FROM openjdk:8
VOLUME /tmp
EXPOSE 8004
ADD ./target/springboot-servicio-account-current-0.0.1-SNAPSHOT.jar current-account.jar
ENTRYPOINT ["java","-jar","/current-account.jar"]