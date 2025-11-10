# Order-Inventory Microservices Assignment

This project demonstrates a two-service microservices architecture built with Spring Boot 3, Java 17, and Maven.  
It models a simple interaction between an Order Service (placing customer orders) and an Inventory Service (tracking stock and expiry of items).

Both services communicate using REST APIs and persist their data in independent H2 in-memory databases.

---

## Architecture Overview

The application is organized as a Maven multi-module project:

order-inventory-microservices-assignment/
├── pom.xml # Parent POM managing dependencies
├── order-service/ # Handles orders, calls inventory via REST
└── inventory-service/ # Manages stock and expiry info

yaml
Copy code

Each microservice is a standalone Spring Boot application with its own controller, service, repository, and model layers.

---

## Technology Stack

| Layer | Technology |
|-------|-------------|
| Language | Java 17 |
| Framework | Spring Boot 3.1.x |
| Build Tool | Maven |
| Database | H2 (in-memory) |
| Testing | JUnit 5, Mockito |
| Communication | REST (using RestTemplate) |

---

## Getting Started

### 1. Prerequisites
- Java 17 installed  
- Maven 3.8+  
- Git (for cloning)

Verify installation:
```bash
java -version
mvn -version
2. Clone the Repository
bash
Copy code
git clone https://github.com/Chiko-Kreps/order-inventory-microservices-assignment.git
cd order-inventory-microservices-assignment
3. Build the Project
Run from the project root:

bash
Copy code
mvn clean package -DskipTests
This command builds both microservices and creates runnable JARs under:

pgsql
Copy code
inventory-service/target/inventory-service-0.0.1-SNAPSHOT.jar
order-service/target/order-service-0.0.1-SNAPSHOT.jar
Running the Services
Inventory Service
bash
Copy code
cd inventory-service
java -jar target/inventory-service-0.0.1-SNAPSHOT.jar
Runs on port 8081

Order Service
Open another terminal:

bash
Copy code
cd order-service
java -jar target/order-service-0.0.1-SNAPSHOT.jar
Runs on port 8080

API Documentation
Inventory Service (Port 8081)
Method	Endpoint	Description
GET	/inventory/{productId}	Retrieves inventory batches for a product, sorted by expiry date.
POST	/inventory/update	Updates inventory after an order (reduces quantity).

Example Request:

bash
Copy code
curl -X GET http://localhost:8081/inventory/1
Example Response:

json
Copy code
[
  {
    "id": 1,
    "productId": 1,
    "quantity": 50,
    "expiryDate": "2026-11-01"
  }
]
Order Service (Port 8080)
Method	Endpoint	Description
POST	/order	Places an order and triggers inventory update through REST call.

Example Request:

bash
Copy code
curl -X POST http://localhost:8080/order \
     -H "Content-Type: application/json" \
     -d '{"productId":1,"quantity":5}'
Example Response:

json
Copy code
{
  "id": 1,
  "productId": 1,
  "quantity": 5,
  "status": "PLACED"
}
Application Flow
Inventory Service stores product batches with quantities and expiry dates.

Order Service receives an order request, calls /inventory/update on Inventory Service to reserve stock.

Inventory responds with success/failure; the order is saved with a corresponding PLACED or FAILED status.

Both services maintain isolated databases but stay in sync via REST.

Testing the Application
Unit Tests
Each service includes JUnit and Mockito-based unit tests.
Run all tests:

bash
Copy code
mvn test
Manual Verification
Start both services.

Access H2 Console:

Inventory: http://localhost:8081/h2-console
JDBC URL: jdbc:h2:mem:inventorydb

Order: http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:orderdb

Add inventory records manually, then place an order to verify the interaction.

Sample Workflow
bash
Copy code
# Add initial stock
INSERT INTO inventory_batches (product_id, quantity, expiry_date)
VALUES (1, 100, DATE '2026-11-01');

# Place an order
curl -X POST http://localhost:8080/order \
     -H "Content-Type: application/json" \
     -d '{"productId":1,"quantity":10}'

# Check remaining stock
curl http://localhost:8081/inventory/1
Expected result: Quantity decreases by 10.

Folder Structure
swift
Copy code
order-inventory-microservices-assignment/
├── inventory-service/
│   ├── src/main/java/com/example/inventory/
│   │   ├── controller/
│   │   ├── service/
│   │   ├── repository/
│   │   └── model/
│   ├── src/test/java/com/example/inventory/
│   └── pom.xml
│
├── order-service/
│   ├── src/main/java/com/example/order/
│   │   ├── controller/
│   │   ├── service/
│   │   ├── repository/
│   │   └── model/
│   ├── src/test/java/com/example/order/
│   └── pom.xml
│
└── pom.xml
Design Highlights
Independent, loosely coupled microservices.

Factory Pattern used in InventoryHandlerFactory for flexible update logic.

DTOs used for safe data transfer between layers.

Clean, layered architecture (Controller → Service → Repository → Entity).