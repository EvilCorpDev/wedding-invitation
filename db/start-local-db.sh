docker run \
    -d \
    --rm \
    -p 3000:5432 \
    --name wedding-postgresql \
    -e POSTGRES_DB=wedding \
    -e POSTGRES_USER=admin \
    -e POSTGRES_PASSWORD=admin \
    postgres:18-alpine