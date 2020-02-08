FROM openjdk:8-jre-alpine
COPY target/energy-consumption-per-village-0.0.1-SNAPSHOT.jar app.jar
COPY entrypoint.sh /usr/local/bin/
ENTRYPOINT ["entrypoint.sh"]
CMD ["default"]