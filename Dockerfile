FROM openjdk:8-jre-alpine

ENV JAVA_OPTS=""

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

CMD echo "Iniciando..." && \
    java ${JAVA_OPTS} -Djava.security.egd=file:/dev/./urandom -jar /app.jar

ENTRYPOINT ["java","-jar","/app.jar"]