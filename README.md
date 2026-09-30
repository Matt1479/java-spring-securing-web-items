# Securing Web Items

A Spring Boot application for practicing server-side web development with Spring MVC, Thymeleaf and Spring Security.

The application provides a secured, server-rendered web interface for viewing and deleting items.
Users must authenticate to access the application, while administrators have additional permissions to delete items.

Authentication uses in-memory users with USER and ADMIN roles.

Check out my [notes](/docs/notes.md).

## Tech Stack

- Java
- Spring Boot:
    - **Spring Web**
    - Spring Security
    - **Thymeleaf**
    - Thymeleaf Extras Spring Security 6
    - Optional: Spring Boot DevTools
- Maven

## Features

- Authentication with Spring Security
- In-memory users and roles
- Role-based authorization
- Server-rendered HTML pages using Spring MVC and Thymeleaf
- Secured item management
- Admin-only item deletion
- Thymeleaf Spring Security integration
- HTML form handling
- HTTP method overriding for DELETE forms using Spring's hidden method support
- In-memory `HashMap` data store

All data is stored in memory, so it is lost when the application stops.

## Users

| Username | Password | Role |
|---|---|---|
| sampleuser | sampleuserpassword | USER |
| sampleadmin | sampleadminpassword | ADMIN |

## Web Interface

The web interface is available at:

`http://localhost:8080/`

## MVC Routes

| Method | Endpoint | Description | Access |
|---|---|---|---|
| GET | `/` | Home page | Public |
| GET | `/home` | Home page | Public |
| GET | `/login` | Login page | Public |
| GET | `/items` | View all items | Authenticated |
| DELETE | `/items/{id}` | Delete an item | Admin |

## Run the Application

Run the application with Maven:

```bash
./mvnw spring-boot:run
```

Then visit:

`http://localhost:8080/`

## Build the Application

Build the JAR file:

```bash
./mvnw clean package
```

Run the JAR file:

```bash
java -jar target/securing-web-items-0.0.1-SNAPSHOT.jar
```

## References / Tutorials

- [Serving Web Content With Spring MVC](https://spring.io/guides/gs/serving-web-content)
- [Securing a Web Application](https://spring.io/guides/gs/securing-web)
- [Tutorial: Thymeleaf (2.1) + Spring](https://www.thymeleaf.org/doc/tutorials/2.1/thymeleafspring.html)
- [Tutorial: Using Thymeleaf (3.1)](https://www.thymeleaf.org/doc/tutorials/3.1/usingthymeleaf.html)

### Additional

- [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service)
- [Consuming a RESTful Web Service](https://spring.io/guides/gs/consuming-rest)
