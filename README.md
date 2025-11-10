This project implements the Koerber Java Microservices Assignment using Spring Boot, Maven, and REST-based interservice communication.

# order-inventory-microservices-assignment
Two Spring Boot microservices communicating via REST:
- inventory-service (port 8081)
- order-service (port 8080)

## Build
mvn clean package

## Run
java -jar inventory-service/target/inventory-service-0.0.1-SNAPSHOT.jar
java -jar order-service/target/order-service-0.0.1-SNAPSHOT.jar

## Endpoints
| Service   | Endpoint               | Method | Description                     |
|-----------|------------------------|--------|---------------------------------|
| Inventory | /inventory/{productId} | GET    | List batches by expiry          |
| Inventory | /inventory/update      | POST   | Update inventory                |
| Order     | /order                 | POST   | Place order and update inventory|

