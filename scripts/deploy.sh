#!/bin/bash

set -e

if [[ ! -f "docker-compose.yml" ]]
then
  echo "Missing file *docker-compose.yml*!"
  exit 1
fi

ips=( "192.168.2.21" "192.168.2.22" )

for ip in "${ips[@]}"
do
    echo "Deyploing to: ${ip}..."
    scp -o StrictHostKeyChecking=no ~/.docker/config.json pi@${ip}:~/.docker/config.json
    scp -o StrictHostKeyChecking=no docker-compose.yml pi@${ip}:/home/pi/apps/nats/docker-compose.yml
    ssh -o StrictHostKeyChecking=no pi@${ip} "cd /home/pi/apps/nats && docker-compose pull && docker-compose up -d"
    echo "Deyploing to: ${ip}... DONE"
done