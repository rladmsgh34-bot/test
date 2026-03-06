# Docker Compose Multi-Service Application

This project demonstrates a multi-service application orchestrated with Docker Compose, featuring:
- **PostgreSQL**: Database service
- **Apache Zookeeper**: Distributed coordination for Kafka
- **Apache Kafka**: Distributed streaming platform
- **Spring Boot Backend**: RESTful API service
- **Vue.js Frontend**: Web application

## Services and Ports

| Service              | Status   | Internal Port | External Port | URL (from host)                      |
|----------------------|----------|---------------|---------------|--------------------------------------|
| `test-postgresql`    | Healthy  | 5432          | 5432          | `localhost:5432`                     |
| `test-zookeeper`     | Healthy  | 2181          | 2181          | `localhost:2181`                     |
| `test-kafka`         | Healthy  | 9092          | 9092, 9093    | `localhost:9092`, `localhost:9093`   |
| `test-backend`       | Running  | 8080          | 8088          | `http://localhost:8088`              |
| `test-frontend`      | Running  | 80            | 8003          | `http://localhost:8003`              |

## Setup and Run

1.  **Clone the repository:**
    ```bash
    git clone https://github.com/rladmsgh34-bot/test.git
    cd test
    ```

2.  **Build and start all services:**
    ```bash
    docker compose up --build -d
    ```

3.  **Verify service status:**
    ```bash
    docker compose ps -a
    ```
    All services should show `Up (healthy)` or `Up (running)` status.

## External Access

To access the services from outside the server (e.g., from your local machine):

### 1. **GCP Firewall Rules:**
Ensure that the following TCP ports are open in your GCP firewall rules for your instance:
-   `8003` (for Frontend)
-   `8088` (for Backend)

Set the `Source IP ranges` to `0.0.0.0/0` to allow access from any IP address (for testing purposes; restrict as needed for production).

### 2. **Access URLs:**
Once firewall rules are configured, you can access the services using your server's external IP address (`34.64.123.114`) or your DuckDNS domain (`uni34.duckdns.org`):

-   **Frontend**: `http://34.64.123.114:8003` (or `http://uni34.duckdns.org:8003`)
-   **Backend API**: `http://34.64.123.114:8088` (or `http://uni34.duckdns.org:8088`)

### 3. **Spring Boot Backend Endpoints:**
-   **Root**: `http://34.64.123.114:8088/` (Returns "Backend is running!")
-   **Hello API**: `http://34.64.123.114:8088/api/hello` (Returns "Hello from Spring Boot Backend!")

## Troubleshooting

-   **Port Conflicts:** If you encounter `port is already allocated` errors, ensure no other processes are using the required ports (8003, 8088, 5678, 2181, 9092, 9093, 5432).
-   **Dify Nginx Error (8000/8443):** If you had a separate Nginx instance for Dify, it might be conflicting with the current Docker Compose setup. Stop and remove any old Nginx containers if necessary.
-   **Kafka Unhealthy:** Verify Kafka listener settings in `docker-compose.yml` (`KAFKA_LISTENERS`, `KAFKA_ADVERTISED_LISTENERS`). A full `docker compose down` followed by `docker compose up --build -d` often resolves network-related issues.

