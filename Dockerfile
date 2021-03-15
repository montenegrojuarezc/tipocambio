FROM openjdk:11-jre
LABEL maintainer="BCP-EXAMEN"
COPY build/libs/tipocambio-*SNAPSHOT.jar /opt/tipocambio.jar
ENTRYPOINT ["java", "-Djava.file.encoding=UTF-8","-jar","/opt/tipocambio.jar"]