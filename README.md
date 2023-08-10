# CQRS Design Pattern with Spring Boot &amp; Apache Kafka example
## Command and Query Responsibility Segregation, a pattern that separates read and update operations for a data store. 
### Implementing CQRS in our application can maximize its performance, scalability, and security

🚊Segregation of two microservices for decoupling create/update operations from read operations. Each microservice uses its own database

> When we are doing create or update operations in product-command-service we are publishing create or update events in Kafka so the product-query-service can read the updated data
> product-command-service acts as a Publisher and product-query-service acts as a Subscriber and their communication it's done through Kafka events (create, update, read)

👨‍💻 Technologies used:
- Spring - Java Spring
* Spring Data JPA - data access layer
+ MySQL
- Kafka - Spring for Apache Kafka
* Docker




