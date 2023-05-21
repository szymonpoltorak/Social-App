# Social App

<div align="center">
    <img src=".github/screenshots/preview.png" alt="Preview">
</div>

This is a full stack application being created during fourth semester of Computer Science studies at Warsaw University Of Technology.
Goal is to create a social media app covering frontend and backend side of such site.

## Technology stack

I used various technologies which I will list below.

### FrontEnd

* Angular 15.2.0,
* TypeScript 4.9.5,
* SCSS 1.58.3,
* Angular Material.

### BackEnd

* SpringBoot 3.0.4,
* Java JDK 17.0.6 (language level Java 11),
* Apache Maven 3.8.5,
* SpringData JPA,
* SpringSecurity,
* PostGres SQL,
* lombok,
* javadoc,
* jwt,
* jackson,
* swagger,
* logback.

### Others

* makefile,
* docker,
* postman.

## Installation of docker

To install docker use:

* On Linux:

```bash
sudo apt install -y docker
```

* On Windows:

```psh
winget install Docker.DockerDesktop
```

## Makefile commands

To simplify the development and usage of this app I used docker and makefile.
Makefile file has few commands which can be executed from the root directory.
Makefile scripts may need administrator priviliges due to *docker* command usage.

* `make`, `make all`, `make build` - uses *docker-compose* to build the whole project.
Frontend of the app will be available at `localhost:4200`,
database at `localhost:5000` and backend at `localhost:8080`

* `make clean` - command used to remove with force images created ,
during *docker-compose* build

* `make stop` - command used to turn off *docker-compose* image.

## Screenshots

* HomePage,

<div align="center">
    <img src=".github/screenshots/home.png" alt="HomePage">
</div>

* LoginPage,

<div align="center">
    <img src=".github/screenshots/login.png" alt="LoginPage">
</div>

* RegisterPage,

<div align="center">
    <img src=".github/screenshots/register.png" alt="RegisterPage">
</div>

* NotFoundPage,

<div align="center">
    <img src=".github/screenshots/not-found.png" alt="NotFoundPage">
</div>

* Posts with comments,

<div align="center">
    <img src=".github/screenshots/posts-comments.png" alt="Posts with comments">
</div>

* User details modification,

<div align="center">
    <img src=".github/screenshots/details-modify.png" alt="User details modification">
</div>
