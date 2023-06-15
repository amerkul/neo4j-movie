FROM openjdk:18.0 as build
LABEL maintainer="Anna Merkul <anna.merkul@bk.ru>"
ARG JAR_FILE=/target/*.jar
COPY ${JAR_FILE} application.jar
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf /application.jar)
FROM openjdk:18.0
VOLUME /tmp
ARG DEPENDENCY=/target/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java", "-cp", "app:app/lib/*", "com.example.neo4j.Neo4jApplication"]
