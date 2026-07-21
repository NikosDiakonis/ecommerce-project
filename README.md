# QuarkusShop

A learning-focused e-commerce backend built with a TDD-first workflow,
mentored code reviews, and RFC 9457-compliant error handling.

## What it does

A REST API for an e-commerce platform, organized around four domains:

- **Catalog** — product management (CRUD, unique SKU/name constraints) — *implemented*
- **Cart & Pricing** — cart operations, priority-based discount logic — *planned*
- **Payments** — payment processing — *planned*
- **Orders & Notifications** — order lifecycle and notifications — *planned*

## Tech stack

- **Java** / **Quarkus** — application framework
- **Hibernate Panache** — ORM / persistence
- **JAX-RS** — REST endpoints
- **PostgreSQL** — database
- **Gradle** — build tool
- **JUnit 5** + **REST-assured** — unit & integration testing
- **OpenAPI / Swagger** — API documentation
- **Jakarta Bean Validation** — request validation

## Running the application in dev mode

Run in dev mode (live coding enabled):

```shell script
./gradlew quarkusDev
```

> **NOTE:** Quarkus Dev UI is available in dev mode at <http://localhost:8080/q/dev/>.

## Packaging and running the application

Package the application:

```shell script
./gradlew build
```

This produces `quarkus-run.jar` in `build/quarkus-app/`. Run it with:

```shell script
java -jar build/quarkus-app/quarkus-run.jar
```