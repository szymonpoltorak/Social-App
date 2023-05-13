compose = docker-compose
remove = docker -f rm
containers = social-app-frontend social-app-backend social-app-postgres

.PHONY: all, build, clean, stop

all: run

run:
	$(compose) up

build:
	$(compose) build

stop:
	$(compose) down

clean:
	$(remove) $(containers)
