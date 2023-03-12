.PHONY: all, image, build, start, stop, clean. clean-container, clean-image

all: image

image: build
	docker run -d -p 4200:80 social-app-frontend:latest --name social-app-frontend

build:
	docker build -t social-app-frontend .

stop:
	docker stop social-app-frontend

start:
	docker start social-app-frontend

clean-container:
	docker rm social-app-frontend -f

clean-image:
	docker image rm social-app-frontend -f

clean: clean-image, clean-container
