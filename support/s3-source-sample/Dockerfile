FROM eclipse-temurin:17-jdk-alpine

RUN mkdir -p /usr/share/s3-source-sample
ADD build/libs/s3-source-sample-1.0-SNAPSHOT.jar /usr/share/s3-source-sample/

WORKDIR /usr/share/s3-source-sample
CMD ["java", "-jar", "s3-source-sample-1.0-SNAPSHOT.jar"]
