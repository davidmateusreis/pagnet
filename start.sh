docker-compose down

docker build -t backend-pagnet:latest ./backend

docker build -t frontend-pagnet:latest ./frontend

docker-compose up --build --force-recreate --remove-orphans
