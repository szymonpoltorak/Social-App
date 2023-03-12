db-password = -e POSTGRES_PASSWORD=none
db-username = -e POSTGRES_USER=root
port-map = -p 5000:5432
postgres-image = postgres:15.2-alpine3.17
run = docker run
pull = docker pull
stop = docker stop
remove = docker rm
container-name = social-app-postgres

.PHONY: all, db, stop, clean

all: db

pull:
	$(pull) $(postgres-image)

db:
	$(run) --name $(container) $(username) $(password) -d $(portmap) $(postgres-image)

stop:
	$(stop) $(container)

clean:
	$(remove) $(container)
