#!/bin/bash

# Update package list and install Docker for Amazon Linux
sudo yum update -y
sudo yum install -y docker
#test
# Ensure Docker is running
sudo systemctl start docker
sudo systemctl enable docker

# Download and install Docker Compose
sudo curl -L "https://github.com/docker/compose/releases/latest/download/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
sudo chmod +x /usr/local/bin/docker-compose

# Log into Docker Hub (using credentials passed from GitHub Actions)
sudo docker login -u $1 -p $2

# Pull the latest image and run the compose file
sudo /usr/local/bin/docker-compose -f /home/ec2-user/compose.yaml pull
sudo /usr/local/bin/docker-compose -f /home/ec2-user/compose.yaml up -d