# Build and run this project from scratch

This repository is a Spring Boot **backend API**. It does not contain a React, Angular, or other browser frontend. The API is mounted below `/api/v1`; the first deployment check is `GET /api/v1/health`.

## 1. Learn the moving parts

| Part | What it does |
| --- | --- |
| `pom.xml` | Declares Java 17, Spring Boot, and external libraries. |
| `src/main/java` | Java application code: entities, security, configuration, and HTTP controllers. |
| `src/main/resources/application*.yml` | Runtime settings. The `prod` profile takes its secrets from environment variables. |
| `src/main/resources/db/migration` | Versioned SQL migrations that create the database schema. |
| `Dockerfile` and `compose.yaml` | Repeatable production-like build and local deployment. |

Spring starts, Flyway applies the migration, Hibernate checks the Java entities against that schema, then the HTTP server starts. If the schema and entities disagree, startup fails early instead of silently altering production data.

## 2. Prerequisites

The simplest path only needs Docker Desktop with Docker Compose enabled. For running without Docker, install a JDK 17 and Maven 3.9+ and ensure `java -version` and `mvn -version` work in a new terminal.

## 3. Run it locally with Docker

1. Copy `.env.example` to `.env`.
2. Replace the three placeholder secret values. `JWT_SECRET` must be at least 64 random characters because the application signs HS512 JWTs.
3. From the repository root, run:

   ```powershell
   docker compose up --build
   ```

4. In another terminal, check the service:

   ```powershell
   Invoke-RestMethod http://localhost:8080/api/v1/health
   ```

   Expected result: a JSON object whose `status` is `UP`.

5. Stop it with `docker compose down`. Add `--volumes` only when you intentionally want to remove the local MySQL data.

## 4. Run it without Docker

Create a MySQL 8 database named `ecommerce_db`, then set the connection settings as environment variables or update a local profile. From PowerShell:

```powershell
$env:SPRING_PROFILES_ACTIVE = 'dev'
$env:SPRING_DATASOURCE_URL = 'jdbc:mysql://localhost:3306/ecommerce_db_dev?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true'
$env:SPRING_DATASOURCE_USERNAME = 'root'
$env:SPRING_DATASOURCE_PASSWORD = 'your-password'
mvn clean verify
mvn spring-boot:run
```

The application automatically applies `V1__initial_schema.sql`. Do not use `ddl-auto=update` in production: migrations are the record of intentional database changes.

## 5. Build an e-commerce feature correctly

Use this order for each feature, for example a product catalog:

1. Add a Flyway migration (`V2__...sql`) for the database change.
2. Add or adjust the JPA entity to match the migration.
3. Add a repository interface for database queries.
4. Add a service for business rules and transactions.
5. Add request/response DTOs and a controller endpoint.
6. Add unit tests for the service and integration tests for the endpoint.
7. Run `mvn clean verify`, then exercise the endpoint manually or in CI.

The repository currently provides the database model, JWT security foundation, health endpoint, and deployment packaging. Catalog CRUD, registration/login, cart, orders, payments, reviews, and a browser UI still need implementation before calling it a complete customer-facing e-commerce product.

## 6. Deploy safely

Build an immutable image with `docker build -t ecommerce-platform:1.0.0 .`, push it to your registry, and deploy it together with a managed MySQL 8 database. Set `SPRING_PROFILES_ACTIVE=prod` and provide `DB_URL`, `DB_USERNAME`, `DB_PASSWORD`, and `JWT_SECRET` through the platform's secret manager. Do not bake secrets into the image or commit `.env`.

After deployment, make the platform health check call `/api/v1/health`. Back up the database before applying new migrations, run migrations once per release, and use HTTPS through the hosting platform or a reverse proxy.
