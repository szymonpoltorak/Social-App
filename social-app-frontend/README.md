# Social App FrontEnd

This is a frontend part of my project. It was build using `angular cli`.

## I do not want to use docker

If you do not want to run docker just use command below and then start the angular.

```bash
npm install
```

## How to start frontend app process

To start watch mode of frontend only you can simply use this command (if you have angular installed and added to path).

```bash
ng serve
```

## Makefile commands

In this place there is also a dockerfile to build a frontend app.
Makefile file has few commands that are quality of life improvements.

* `make`, `make all`, `make build` - it builds a docker image of
  angular app,

* `make stop` - command used to stop a frontend app container,

* `make start` - command used to start an existing app container,

* `make clean-image` - command used to remove an image of social app frontend,

* `make clean-container` - command used to remove a container,

* `make clean` - command used to remove both container and image

## Sources and Inspirations

Error Page:

* [Source](https://www.mantralabsglobal.com/404)

Space Background:

* [Source](https://wallpapercave.com/black-space-wallpaper-hd)

Navbar icons:

* [Source](https://fonts.google.com/icons)

Social icons:

* [Source](https://icons8.com/icons/set/social-media)
