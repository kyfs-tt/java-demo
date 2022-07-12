FROM maven:3-jdk-11 AS build
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package -Dmaven.test.skip=true

FROM gcr.io/distroless/java
COPY --from=build /usr/src/app/target/connie-demo-0.0.1-SNAPSHOT.jar /usr/app/connie-demo-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/app/connie-demo-0.0.1-SNAPSHOT.jar"]
