compose = docker-compose
remove = docker rm
remove-image = docker image rm

.PHONY: all, build, clean, stop

all: run

run:
	$(compose) up

build:
	$(compose) build

stop:
	$(compose) down

clean:
	$(remove) -f frontend backend postgres 2>/dev/null
	$(remove-image) -f social-app-postgres social-app-backend social-app-frontend 2>/dev/null
