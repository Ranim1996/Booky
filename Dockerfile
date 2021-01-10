FROM openjdk:12
WORKDIR /
ADD build/libs/BookyApp-1.0-SNAPSHOT-all.jar BookyApp.jar
EXPOSE 9000
ENTRYPOINT ["java", "-jar", "BookyApp.jar"]
