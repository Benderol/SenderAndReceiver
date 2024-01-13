# Sender And Receiver

This repository contains a Java implementation of a message broker using Apache Kafka. Kafka is a distributed event streaming platform that provides high-throughput, fault-tolerant, and scalable publish-subscribe messaging.

## Prerequisites
- Java Development Kit (JDK)
- Apache Maven
- My SQL
- Apache Kafka

## Database setup
- Username: root
- Password: root
- Schema name: microservices

## Kafka

For using application on Windows you have to download Apache Kafka and then run zookeeper and kafka server using following commands:

Zookeeper:
```bash
    .\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties
```

Kafka server:
```bash
    .\bin\windows\kafka-server-start.bat ./config/server.properties
```

The API needs two ports:
- http://localhost:8080 - for sending data
- http://localhost:9090 - for receiving data

## API Endpoints

### Send task
- **Endpoint:** /publish
- **Method:** POST
- **Description:** Send username and task from one project to another
- **Kafka topic:** sender
- **Example:** http://localhost:8080/publish
- **Example request:**
```json
    "username" : "benderol",
    "task" : "do something"
```
- **Explanation:** using this endpoint you send two serialized strings (username and data) to another project (UserReceiver) which deserialize this data and put it into database

### Send user
- **Endpoint:** /add-user
- **Method:** POST
- **Description:** Send user
- **Kafka topic:** userSender
- **Example:** http://localhost:8080/add-user
- **Example request:**
```json
    "username" : "benderol",
    "firstName" : "Dan",
    "lastName" : "Yatluk"
```
### Complete users task
- **Endpoint:** /user/complete-task/{userId}
- **Method:** GET
- **Description:** Complete users task
- **Example:** http://localhost:9090/user/complete-task/1
