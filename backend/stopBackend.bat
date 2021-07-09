@echo off

echo stopping backend
docker-compose --log-level ERROR -p fiware down -v --remove-orphans