FROM openjdk:14

EXPOSE 8005

ADD target/client.jar client.jar

ENTRYPOINT ["java", "-jar", "/client.jar"]