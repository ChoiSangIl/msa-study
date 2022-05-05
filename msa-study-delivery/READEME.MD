## SERVICE DISCOVERY
```
./mvnw clean package
docker build -t service-discovery .
docker run -d -p 8761:8761 service-discovery
```
