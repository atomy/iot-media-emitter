#!/bin/bash

set -e

if [[ ! -f "docker-compose.yml" ]]
then
  echo "Missing file *docker-compose.yml*!"
  exit 1
fi

ips=( "2003:d4:1f23:ba00:5ff:4195:12d:ae12" "2003:d4:1f23:ba00:f4d0:9c00:206f:69fa" "2003:d4:1f23:ba00:2427:f7a3:3c10:c4ad" )

for ip in "${ips[@]}"
do
    echo "Deploying to: ${ip}..."
    scp -o StrictHostKeyChecking=no ~/.docker/config.json pi@${ip}:~/.docker/config.json
    scp -o StrictHostKeyChecking=no docker-compose.yml pi@${ip}:/home/pi/apps/iot-media-emitter/docker-compose.yml
    ssh -o StrictHostKeyChecking=no pi@${ip} "echo \`hostname\` > /home/pi/apps/iot-media-emitter/hostname"
    ssh -o StrictHostKeyChecking=no pi@${ip} "cd /home/pi/apps/iot-media-emitter && docker-compose pull && docker-compose up -d"
    echo "Deploying to: ${ip}... DONE"
done