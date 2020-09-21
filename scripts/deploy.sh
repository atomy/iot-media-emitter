#!/bin/bash

set -e

if [[ ! -f "docker-compose.yml" ]]
then
  echo "Missing file *docker-compose.yml*!"
  exit 1
fi

ips=( "192.168.2.20" "192.168.2.21" "192.168.2.22" )

for ip in "${ips[@]}"
do
    echo "Deploying to: ${ip}..."
    scp -o StrictHostKeyChecking=no ~/.docker/config.json pi@${ip}:~/.docker/config.json
    scp -o StrictHostKeyChecking=no docker-compose.yml pi@${ip}:/home/pi/apps/iot-media-emitter/docker-compose.yml
    ssh -o StrictHostKeyChecking=no pi@${ip} "echo \`hostname\` > /home/pi/apps/iot-media-emitter/hostname"
    ssh -o StrictHostKeyChecking=no pi@${ip} "cd /home/pi/apps/iot-media-emitter && docker-compose pull && docker-compose up -d"
    echo "Deploying to: ${ip}... DONE"
done