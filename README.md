# E-commerce Platform API

Spring Boot 3 / Java 17 e-commerce backend foundation with JWT security, MySQL migrations, and Docker deployment.

Start with [the build-from-scratch guide](BUILD_FROM_SCRATCH.md). The quickest local deployment is:

```powershell
Copy-Item .env.example .env
# Replace the placeholder secrets in .env, then:
docker compose up --build
```

Verify it at `http://localhost:8080/api/v1/health`.

> This repository is currently a backend foundation, not a complete customer-facing full-stack shop. See the guide for what remains to implement.
