#!/bin/sh

cd backend-java
sudo ./gradlew build
cd ..
DOCKER_BUILDKIT=1 docker build --progress=plain -t timeroom/backend-spring-boot-docker .
sudo docker stop timeroom-backend
sudo docker rm timeroom-backend
sudo docker run -d -p 8080:8080 --name timeroom-backend --network host timeroom/backend-spring-boot-docker
docker image prune -f


