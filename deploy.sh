#!/bin/bash

# Update package list and install Docker & Docker Compose if they don't exist
sudo apt-get update -y
sudo apt-get install -y docker.io docker-compose-v2

# Ensure Docker is running
sudo systemctl start docker
sudo systemctl enable docker

# Log into Docker Hub (using credentials passed from GitHub Actions)
sudo docker login -u $1 -p $2

# Pull the latest image and run the compose file
sudo docker compose -f /home/ec2-user/compose.yaml pull
sudo docker compose -f /home/ec2-user/compose.yaml up -d