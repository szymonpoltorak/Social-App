build = docker build
compose = docker-compose
start = docker start
app-name = social-app-frontend
container-remove = docker rm
image-remove = docker image rm

.PHONY: all, image, build, start, stop, clean. clean-container, clean-image

all: image

image:
	$(compose) up

stop:
	$(compose) down

start:
	$(start) $(app-name)

clean: clean-image clean-container
	$(container-remove) -f frontend
	$(image-remove) -f $(app-name)
