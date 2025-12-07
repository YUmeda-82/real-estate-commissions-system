#!/bin/bash

GREEN='\033[0;32m'
NC='\033[0m'

echo -e "${GREEN} Starting Commission System Environment...${NC}"

echo -e "${GREEN} Starting Docker Containers (Postgre + Kafka)...${NC}"
docker compose up -d

echo -e "${GREEN} Waiting for systems to initialize...${NC}"
sleep 10

echo -e "${GREEN} Starting Spring Boot application...${NC}"
./mvnw spring-boot:run