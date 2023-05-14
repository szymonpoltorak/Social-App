# Social App Backend

This is a backend part of the social media app written in Spring.

## Makefile commands

Here I list a commands provided in makefile file.

* `make db` - runs an image of postgres docker image,

* `make`, `make all`, `make spring` - runs and builds a backend spring image,

* `make clean` - removes docker process and images created by the makefile,

## Database configuring

In order to configure database settings you have to go to `src/main/resources/application.properties`.
To change username change this line:

```properties
spring.datasource.username=postgres
```

To change database password:

```properties
spring.datasource.password=none
```

To change database url:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5000/socialapp
```

You have to also change makefile lines:

```properties
db-password=-e POSTGRES_PASSWORD=none
```

In `application.properties` you can also specify `jwt` expiration time and secret encoding key.
Key can be generated on this [site](https://www.allkeysgenerator.com/).

```properties
security.jwt.expiration-time=
security.jwt.encoding-key=
```

If you leave settings as it is you will get 1 min expiration time to change it to a day write this line:

```properties
security.jwt.expiration-time=86400000
```

## How to run without docker

You have to install Java in version `at least` 17 and maven.

On linux:

```bash
sudo apt install -y openjdk
sudo apt install -y maven
```
* Using jar:

Then run maven lifecycle

```maven
mvn clean package
```

And at last run jar file in `target/`.

```bash
java -jar target/social-app-backend-0.0.1-SNAPSHOT.jar
```

* Using maven simply just run using this command:

```maven
mvn clean spring-boot:run
```

## Documentation

There are two different documentation tools included in this project.

* First is JavaDoc which will generate documentation for whole code based on docstrings,
  To generate docs use command:

```maven
mvn clean javadoc:javadoc
```

And then run `target/site/apidocs/index.html`.

* Api documentation created via swagger. When you run your spring application go to
  `http://localhost/swagger-ui/index.html` and you will se your api documentation.

## Troubleshooting

* I am getting an error on start of app about filters and connections.
This means you have not `socialapp` database in your docker container.
