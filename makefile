.PHONY: all, build, clean, stop

all: build

build:
	docker-compose up

stop:
	docker-compose down

clean:
	docker rm social-app-frontend social-app-backend social-app-postgres -f
