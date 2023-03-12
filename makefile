compose = docker-compose
remove = docker -f rm
containers = social-app-frontend social-app-backend social-app-postgres

.PHONY: all, build, clean, stop

all: build

build:
	$(compose) up

stop:
	$(compose) down

clean:
	$(remove) $(containers)
