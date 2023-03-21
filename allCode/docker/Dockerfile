FROM maven:3-openjdk-17 as builder

WORKDIR /project

COPY Backend/idealTrip/pom.xml /project/

COPY Backend/idealTrip/src /project/src

RUN mvn package -DskipTests=true

FROM openjdk:17

ENV JAVA_TOOL_OPTIONS="-Xss256K -XX:ReservedCodeCacheSize=64M -XX:MaxMetaspaceSize=100000K -Xmx64M"

WORKDIR /usr/src/app/

COPY --from=builder /project/target/*.jar /usr/src/app/

EXPOSE 8443

CMD ["java", "-jar", "idealtrip-0.0.1-SNAPSHOT.jar" ]