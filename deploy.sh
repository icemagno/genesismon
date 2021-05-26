#! /bin/sh

mvn clean package

docker ps -a | awk '{ print $1,$2 }' | grep magnoabreu/genesismon:1.0 | awk '{print $1 }' | xargs -I {} docker rm -f {}
docker rmi magnoabreu/genesismon:1.0
docker build --tag=magnoabreu/genesismon:1.0 --rm=true .

docker run --name genesismon --hostname=genesismon --network rede_interna \
-v /etc/localtime:/etc/localtime:ro \
-p 8087:8080 \
-d magnoabreu/genesismon:1.0


