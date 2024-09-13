FROM maven:3.9.9-eclipse-temurin-17-alpine AS build
WORKDIR /app
COPY . .
RUN ulimit -c unlimited && mvn package
FROM tomcat
COPY --from=build /app/target/*.war $CATALINA_HOME/webapps/ROOT.war
CMD ["catalina.sh", "run"]