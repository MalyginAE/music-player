FROM maven:3.9.6-amazoncorretto-21-debian-bookworm
RUN apt-get update &&  apt install curl -y
COPY . /usr/src/app
WORKDIR /usr/src/app
HEALTHCHECK --start-period=30s --interval=5s CMD curl http://localhost:8080/actuator/health || exit 1
CMD ["mvn","clean","spring-boot:run"]
