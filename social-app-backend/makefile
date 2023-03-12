.PHONY: all, db, stop, clean

all: db

pull:
	docker pull postgres:15.2-alpine3.17

db:
	docker run --name social-app-postgres -e POSTGRES_USER=root -e POSTGRES_PASSWORD=none -d -p 5000:5432 postgres:15.2-alpine3.17

stop:
	docker stop social-app-postgres

clean:
	docker rm social-app-postgres
