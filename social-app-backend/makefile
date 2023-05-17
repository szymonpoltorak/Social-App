run = docker run
remove = docker rm
remove-image = docker image rm
build = docker build
app-name = social-app-backend

.PHONY: all, db, stop, clean, spring, spring-image

all: spring

spring:
	docker-compose up

db:
	$(build) -t social-app-postgres .
	$(run) --name social-app-postgres -p 5432:5432 -d social-app-postgres

stop:
	$(stop) $(container)
	$(stop) $(app-name)

clean:
	$(remove) social-app-backend social-app-postgres -f
	$(remove-image) $(app-name) -f
