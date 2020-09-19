# performance-tool
simple performance tool

## sample run command
java -jar -Dspring.datasource.username=$SPRING_DATASOURCE_USERNAME -Dspring.datasource.password=$SPRING_DATASOURCE_PASSWORD -Dspring.datasource.url=$SPRING_DATASOURCE_URL -Dspring.jpa.hibernate.ddl-auto=create -Dspring.cache.type=NONE performance-tool-0.0.1-SNAPSHOT.jar --count 2000 --create --read --update --delete
