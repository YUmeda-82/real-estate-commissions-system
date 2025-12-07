# Real Estate Commission System 🏢

Distributed, event-driven microservice for the managing of real estate broker commissions. Built with a focus on high performance, reliability and applying DevOps best practices.

## 🛠 Tech Stack

* **Language**: Java 21.0.8
* **Framework**: Spring Boot 3.4.0
* **Database**: PostgreSQL 15 (Containerized)
* **Messaging**: Apache Kafka (Containerized)
* **Infrastructure**: Docker & Docker Compose
* **Automation**: Bash Scripting / Linux Shell

## 🏗 Architecture

The system implements an event-driven architecture.

1. A **Deal** is registered via API.
2. An event is published to a **Kafka** topic.
3. An asynchronous consumer processes the event to calculate the **Commission**, ensuring the sales flow is never blocked by financial processing.

## 📂 Project Documentation

This project was based on a software specification case study done in my IT associate's degree. 

* 📄 **Software Requirements Specification (SRS):** 
* [Check the Documentation Here!](docs/requirements/requirements-specification.md)
* [Original Academic Documentation](docs/academic/original-specification-ptbr.pdf)
* *Note: The original requirements document is in Portuguese-BR.*

## Project Roadmap

- [x] Project Scaffolding & Infrastructure
- [ ] Broker management API (CRUD)
- [ ] Deal registration producer
- [ ] Comission calculation consumer
- [ ] Unit & Integration tests