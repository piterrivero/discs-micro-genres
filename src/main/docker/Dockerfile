FROM openjdk

VOLUME /tmp
ADD maven/genres-0.0.1-SNAPSHOT.jar genres.jar
RUN sh -c 'touch /genres.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/genres.jar"]
