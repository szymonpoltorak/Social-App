build = docker build
compose = docker-compose
start = docker start
app-name = social-app-frontend
process-remove = docker rm
image-remove = docker image rm

.PHONY: all, image, build, start, stop, clean. clean-container, clean-image

all: image

image:
	$(compose) up

stop:
	$(compose) down

start:
	$(start) $(app-name)

clean-container:
	$(process-remove) $(app-name) -f

clean-image:
	$(image-remove) $(app-name) -f

clean: clean-image clean-container
