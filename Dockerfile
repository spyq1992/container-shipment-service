# Use an official OpenJDK runtime as a parent image
FROM maven:3.9.1-eclipse-temurin-17 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

#
# Package stage
#

FROM openjdk:17-oracle
COPY --from=build /home/app/target/container-shipment-0.0.1-SNAPSHOT.jar /usr/local/lib/container-shipment.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/container-shipment.jar"]