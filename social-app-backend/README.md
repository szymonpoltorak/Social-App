# Social App Backend

This is a backend part of the social media app written in Spring.

## Makefile commands

Here I list a commands provided in makefile file.

* `make`, `make all`, `make db` - runs an image of postgres docker image,

* `make spring-image` - build an image of backend spring app,

* `make spring` - runs and builds a backend spring image,

* `make clean` - removes docker process and images created by the makefile,

* `make stop` - stops the postgres db and spring image processes.

## Database configuring

In order to configure database settings you have to go to `src/main/resources/application.properties`.
To change username change this line:

```properties
spring.datasource.username=root
```

To change database password:

```properties
spring.datasource.password=none
```

To change database url:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5000/postgres
```

You have to also change makefile lines:

```properties
db-password = -e POSTGRES_PASSWORD=none
db-username = -e POSTGRES_USER=root
```
