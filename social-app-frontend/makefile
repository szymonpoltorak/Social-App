build = docker build
run = docker run
stop = docker stop
start = docker start
app-name = $(app-name)
process-remove = docker rm
image-remove = docker image rm
port-map = -p 4200:80

.PHONY: all, image, build, start, stop, clean. clean-container, clean-image

all: image

image: build
	$(run) -d $(port-map) $(app-name):latest --name $(app-name)

build:
	$(build) -t $(app-name) .

stop:
	$(stop) $(app-name)

start:
	$(start) $(app-name)

clean-container:
	$(process-remove) $(app-name) -f

clean-image:
	$(image-remove) $(app-name) -f

clean: clean-image, clean-container
