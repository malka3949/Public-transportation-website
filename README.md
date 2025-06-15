# 🚌 Public Transport Management System – Spring Boot Backend

This project is a backend service for managing a public bus transport system, similar to Israel's "Kav-Kav" (קל-קו). It includes route management, ticket handling, passenger information, and real-time updates using a RESTful API architecture.

## 🚀 Technologies Used

- Java 21
- Spring Boot 3.4.3
- Spring Web (REST)
- Spring Data JPA
- H2 Database (in-memory)
- Lombok
- ModelMapper
- SpringDoc OpenAPI UI

## 🧱 Project Structure

```
src/
 └── main/
     ├── java/com/example/…       # Controllers, Services, Repositories
     └── resources/                # application.properties, static files
 └── test/                        # Unit tests
```

## ▶️ Getting Started

### Prerequisites:
- Java 21+
- Maven 3.8+

### Run the App:
```bash
./mvnw spring-boot:run
```

Once the server is running, open:

- 📘 API Docs: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
- 💾 H2 Console: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)

## 📂 Database

- In-memory H2 database is used (no setup needed).
- Default settings are located in `application.properties`.

## 🧪 Running Tests

```bash
./mvnw test
```

## 📄 Notes

- Lombok must be supported by your IDE (enable annotation processing).
- Swagger UI is provided by `springdoc-openapi-starter-webmvc-ui`.

---

> Created as part of a lesson project using Spring Boot and Maven.
