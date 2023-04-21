FROM eclipse-temurin:19.0.2_7-jdk-alpine
EXPOSE 5000
#ARG JAR_FILE=target/VehicleParkingSystem-0.0.1-SNAPSHOT.jar
ADD target/hive.jar hive.jar
ENTRYPOINT ["java","-Xmx512m","-jar","hive.jar"]