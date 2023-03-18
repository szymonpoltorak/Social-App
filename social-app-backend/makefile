port-map-db = -p 5000:5432
postgres-image = postgres:15.2-alpine3.17
run = docker run
pull = docker pull
stop = docker stop
remove = docker rm
remove-image = docker image rm
container = social-app-postgres
build = docker build
app-name = social-app-backend
dockerfile-dir = .
port-map-spring= -p 8080:8080

.PHONY: all, db, stop, clean, spring, spring-image

all: db

spring: db, spring
	$(run) --name $(app-name) -d $(port-map-spring) $(social-app-backed):latest

spring-image:
	$(build) -t $(app-name) $(dockerfile-dir)

pull:
	$(pull) $(postgres-image)

db:
	$(run) --name $(container) -e POSTGRES_USER=root -e POSTGRES_PASSWORD=none -d $(port-map-db) $(postgres-image)

stop:
	$(stop) $(container)
	$(stop) $(app-name)

clean:
	$(remove) $(container) -f
	$(remove-image) $(app-name) -f
