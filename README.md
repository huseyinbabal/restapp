# Introduction
Demo Project for my talk [10 Golden Rules for Spring Boot RESTful API](https://bit.ly/spring-boot-practices)

# Outline
This project covers following topics;

- Spring Boot CLI
- Mock MVC Test
- RestController
- Validations
- Exception Handling
- Application Configuration
- Database Interaction
- 2nd Level Cache
- Service-to-Service Communication
- Distributed Tracing & Observability

# Howto Run Project

## Run Zipkin locally

`docker run -d -p 9411:9411 openzipkin/zipkin`

## Run Mysql locally

`docker run --name=mysql-restapp -d -p 3306:3306 -v $HOME/docker/volumes/mysqlrestapp:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=verysecretpass -e MYSQL_DATABASE=restapp mysql:5.7`

## Environment properties

```
export SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/restapp
export SPRING_DATASOURCE_USERNAME=root
export SPRING_DATASOURCE_PASSWORD=verysecretpass
```

## Run project

`./gradlew bootRun`
