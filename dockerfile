#FROM adoptopenjdk:8-jre-hotspot
FROM openjdk:8-jdk-alpine

# Set maintainer details
MAINTAINER Javier Lobo <franciscojavier.lobo@gmail.com>


ARG JAR_FILE=target/dummy-0.0.1-SNAPSHOT.jar
ARG JAR_LIB_FILE=target/lib/

# cd /usr/local/runme
WORKDIR /usr/local/runme

# copy target/find-links.jar /usr/local/runme/app.jar
COPY ${JAR_FILE} app.jar
#COPY target/*.jar app.jar


# copy project dependencies
# cp -rf target/lib/  /usr/local/runme/lib
#ADD ${JAR_LIB_FILE} lib/

# java -jar /usr/local/runme/app.jar
#ENTRYPOINT ["java","-jar","app.jar"]
CMD ["java", "-jar", "app.jar"]
