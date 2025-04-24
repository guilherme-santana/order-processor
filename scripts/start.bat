@echo off
echo 🔄 Subindo containers com Docker Compose...

docker compose build
docker compose up -d

echo ✅ Todos os serviços foram iniciados!
pause
