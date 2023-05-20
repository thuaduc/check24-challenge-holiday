# Holiday

# Update

mvn clean package  
docker build -t thuaduc24042001/holiday:latest .  
docker push thuaduc24042001/holiday:latest

# Run

# Check running at port

sudo lsof -i :3000  
kill -9 <PID>

# Database

docker run -d -p 5432:5432 -v holiday:/var/lib/postgresql/data -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres
--name=holiday postgres:latest  
