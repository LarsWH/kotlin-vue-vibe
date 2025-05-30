FROM postgres:16

# Download the Spring Session PostgreSQL schema
RUN apt-get update && apt-get install -y curl \
  && curl -o /docker-entrypoint-initdb.d/schema-postgresql.sql \
     https://raw.githubusercontent.com/spring-projects/spring-session/main/spring-session-jdbc/src/main/resources/org/springframework/session/jdbc/schema-postgresql.sql \
  && chmod 644 /docker-entrypoint-initdb.d/schema-postgresql.sql