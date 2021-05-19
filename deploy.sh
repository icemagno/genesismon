#! /bin/sh

mvn clean package

docker ps -a | awk '{ print $1,$2 }' | grep magnoabreu/cryptoboard:1.0 | awk '{print $1 }' | xargs -I {} docker rm -f {}
docker rmi magnoabreu/cryptoboard:1.0
docker build --tag=magnoabreu/cryptoboard:1.0 --rm=true .

docker run --name cryptoboard --hostname=cryptoboard --network rede_interna \
-v /etc/localtime:/etc/localtime:ro \
-e DB_USER=postgres \
-e DB_PASSWORD=antares2 \
-e DB_HOST=postgres \
-e DB_PORT=5432 \
-e DB_NAME=loginserver \
-p 8080:8080 \
-d magnoabreu/cryptoboard:1.0


