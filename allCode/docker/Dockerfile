FROM node:16-alpine as builder

WORKDIR /project

COPY frontend/ .

RUN npm install
RUN npm run build -- --prod --base-href="/new/"

FROM maven:3-openjdk-17 as backend

WORKDIR /project

COPY Backend/idealTrip/pom.xml /project

COPY Backend/idealTrip/src /project/src

COPY --from=builder /project/dist/* /project/src/main/resources/public/new

RUN mvn package -DskipTests

FROM openjdk:17

ENV JAVA_TOOL_OPTIONS="-Xss256K -XX:ReservedCodeCacheSize=64M -XX:MaxMetaspaceSize=100000K -Xmx64M"

WORKDIR /usr/src/app/

COPY --from=backend /project/target/*.jar /usr/src/app/

EXPOSE 8443

CMD ["java", "-jar", "idealtrip-0.0.1-SNAPSHOT.jar" ]