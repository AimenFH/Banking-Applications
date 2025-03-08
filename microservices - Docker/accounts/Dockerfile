# Start with a base image containing Java runtime
FROM openjdk:17-jdk-slim

MAINTAINER aimen.el-sayed@stud.fh-campuswien.ac.at

# Add the application's jar to the imagie
COPY target/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar

# Run the jar file
ENTRYPOINT ["java","-jar","accounts-0.0.1-SNAPSHOT.jar"]