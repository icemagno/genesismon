FROM openjdk:8-jdk-alpine
MAINTAINER magno.mabreu@gmail.com
COPY ./target/cryptoboard-1.0.war /opt/lib/
ENV LANG=pt_BR.utf8 
ENTRYPOINT ["java"]
CMD ["-jar", "/opt/lib/cryptoboard-1.0.war"]
