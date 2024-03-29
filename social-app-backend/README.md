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

## OAuth2

To use OAuth2 you need to set up your own google client id and secret.
Store them as environment variables:

```properties
GOOGLE_CLIENT_ID=
GOOGLE_CLIENT_SECRET=
GITHUB_CLIENT_ID=
GITHUB_CLIENT_SECRET=
```

Login urls are:

```
http://localhost:8080/oauth2/authorization/google
http://localhost:8080/oauth2/authorization/github

http://backend:8080/oauth2/authorization/google
http://backend:8080/oauth2/authorization/github
```

Callback urls on backend are:

```
http://localhost:8080/login/oauth2/code/google
http://localhost:8080/login/oauth2/code/github

http://backend:8080/login/oauth2/code/google
http://backend:8080/login/oauth2/code/github
```

Auth token are refresh token will be sent to frontend as request params.

These are urls that you need to set up in your google and github developer console.

If you want to run this in docker container environment you need to change the frontend url in the class
below to `http://frontend:4200`.

```java
/**
 * This class defines constants for redirect URLs used in the authentication process.
 */
public final class RedirectUrls {
    /**
     * The URL to redirect to in case of authentication failure.
     */
    public static final String FAILURE_URL = "http://frontend:4200/auth/login";

    /**
     * The URL to redirect to in case of authentication success.
     */
    public static final String SUCCESS_URL = "http://frontend:4200/oauth";

    private RedirectUrls() {
    }
}
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

```bash
mvn clean package
```

And at last run jar file in `target/`.

```bash
java -jar target/social-app-backend-0.0.1-SNAPSHOT.jar
```

* Using maven simply just run using this command:

```bash
mvn clean spring-boot:run
```

## Important

If you want to run app inside Intellij you need to declare environment variable like this:

```properties
SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/socialapp
```

And if you want to run tests inside Intellij you need to line above to your tests run configuration.

## Documentation

There are two different documentation tools included in this project.

* First is JavaDoc which will generate documentation for whole code based on docstrings,
  To generate docs use command:

```bash
mvn clean javadoc:javadoc
```

And then run `target/site/apidocs/index.html`.

* Api documentation created via swagger. When you run your spring application go to
  `http://localhost:8080/swagger-ui/index.html` and you will se your api documentation.

## Troubleshooting

* I am getting an error on start of app about filters and connections.
  This means you have not `socialapp` database in your docker container.
