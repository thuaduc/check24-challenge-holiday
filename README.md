docker run --name holiday-postgres -p 5432:5432 -v holiday-data:/var/lib/postgresql/data -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=admin postgres:12.2-alpine
