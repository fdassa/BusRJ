@echo off

echo Pulling docker images
start /B /wait docker pull curlimages/curl

echo Pulling fiware
start /B /wait docker-compose --log-level ERROR -p fiware pull

echo Stopping fiware if it exists
start /B /wait docker-compose --log-level ERROR -p fiware down -v --remove-orphans

echo Starting fiware
start /B /wait docker-compose --log-level ERROR -p fiware up -d --remove-orphans

echo Adding database indexes
start /B /wait docker exec  db-mongo mongo --eval "conn = new Mongo();db.createCollection('orion'); db = conn.getDB('orion'); db.createCollection('entities'); db.entities.createIndex({'_id.servicePath': 1, '_id.id': 1, '_id.type': 1}, {unique: true}); db.entities.createIndex({'_id.type': 1}); db.entities.createIndex({'_id.id': 1});"

echo Starting backend
start /B /wait node app.js