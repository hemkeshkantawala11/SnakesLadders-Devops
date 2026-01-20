# Snakes and Ladders – DevOps CI/CD Project

A **Java-based terminal (CLI) Snakes and Ladders game** with a **production-grade CI/CD pipeline** implemented using **GitHub Actions**, **Docker**, **Kubernetes**, and **AWS EC2**.
This project demonstrates **real-world DevOps and DevSecOps practices** applied to a **non-web, interactive application**.

---

## Project Overview

This project focuses on **automation, security, and reliability** in software delivery rather than just application development.

### Key Highlights

* Interactive **Java CLI application**
* **Unit tests** for business logic validation
* **CI pipeline** with security and quality gates
* **Dockerized application**
* **Kubernetes-based deployment**
* **CI and CD separation** (Build once, deploy many times)
* Hosted on **AWS EC2**

---

## Application Details

| Attribute  | Description                      |
| ---------- | -------------------------------- |
| Language   | Java 17                          |
| Build Tool | Maven                            |
| Interface  | Terminal (CLI)                   |
| Input      | STDIN                            |
| Output     | STDOUT                           |
| Networking | None                             |
| Runtime    | Long-running interactive process |

The game includes:

* Dice rolling logic
* Player turns
* Snake and ladder movement
* Win condition validation

---

## 🏗️ CI/CD Architecture

```
Developer Push (main)
        |
        v
GitHub Actions - CI
- Maven Build
- Unit Tests
- CodeQL (SAST)
- Docker Build
- Runtime Smoke Test
- Trivy Image Scan
- DockerHub Push
        |
        v
GitHub Actions - CD
- Kubernetes Deployment
- Rollout Verification
        |
        v
Kubernetes Cluster (AWS EC2)
```

---

## 🔄 Continuous Integration (CI)

**Workflow:** `.github/workflows/ci.yml`

### CI Triggers

* Push to `main`
* Manual trigger (`workflow_dispatch`)

### CI Stages & Purpose

| Stage              | Purpose                         |
| ------------------ | ------------------------------- |
| Checkout           | Fetch source code               |
| Java Setup         | Ensure consistent JDK           |
| Maven Build        | Package application             |
| Unit Tests         | Validate game logic             |
| CodeQL             | Static security analysis (SAST) |
| Docker Build       | Create immutable image          |
| Container Run Test | Verify container startup        |
| Trivy Scan         | Scan image vulnerabilities      |
| DockerHub Push     | Publish trusted image           |

Security follows **shift-left DevSecOps principles** ensuring unsafe artifacts never reach deployment.

---

## 🚀 Continuous Deployment (CD)

**Workflow:** `.github/workflows/cd.yml`

### CD Trigger

* Automatically triggered **only after successful CI**

### CD Stages

* Configure Kubernetes access
* Deploy application using declarative YAML
* Verify rollout status

CI and CD are **fully decoupled**, enforcing controlled artifact promotion.

---

## 🐳 Dockerization

### Dockerfile Highlights

* Multi-stage build
* Maven-based build stage
* Lightweight JRE runtime image
* Immutable JAR artifact

### Build Image Locally

```bash
docker build -t snakeladder .
```

### Run Container Locally

```bash
docker run -it snakeladder
```
---

## ☸️ Kubernetes Deployment

### Deployment Strategy

* Uses **Deployment** (not Job or Service)
* Suitable for long-running interactive CLI apps
* No exposed ports or networking

### Deploy to Cluster

```bash
kubectl apply -f k8s/deployment.yaml
```

### Access the Game

```bash
kubectl get pods
kubectl attach -it <pod-name>
```
---

## Unit Testing

Unit tests are implemented using **JUnit** and executed automatically during CI.

### Test Coverage Includes

* Dice value validation
* Player movement logic
* Snake and ladder transitions
* Win condition checks

### Run Tests Locally

```bash
mvn test
```

---

## Security & DevSecOps

Security is integrated **early and continuously**:

* **CodeQL (SAST)** – Detects insecure coding patterns
* **Trivy** – Scans Docker image for OS & library vulnerabilities
* **Container runtime testing** – Prevents broken images
* **Secrets via GitHub Secrets** – No hardcoded credentials

---

## Steps to Run the Project

You can run this project **locally**, using **Docker**, or via **Kubernetes**.

---

### 1. Run Locally (Without Docker)

**Prerequisites**

* Java 17
* Maven

**Steps**

```bash
git clone https://github.com/hemkeshkantawala11/SnakesLadders-Devops.git
cd SnakesLadders-Devops

mvn clean package
java -jar target/snake-ladder-1.0.0.jar
```

The game will start in the terminal and accept interactive input.

---

### 2. Run Using Docker (Local Container)

**Prerequisites**

* Docker installed and running

**Steps**

```bash
git clone https://github.com/hemkeshkantawala11/SnakesLadders-Devops.git
cd SnakesLadders-Devops

docker build -t snakeladder .
docker run -it snakeladder
```

The CLI game runs interactively inside the container.

---

### 3. Run Using Kubernetes (Production-like Setup)

**Prerequisites**

* Kubernetes cluster (local or AWS EC2)
* `kubectl` configured

**Steps**

```bash
kubectl apply -f k8s/deployment.yaml
kubectl get pods
kubectl attach -it <pod-name>
```

The game is accessed via STDIN/STDOUT using `kubectl attach`.

---

### 4. Running CI Pipeline (GitHub Actions)

The **CI pipeline runs automatically** on:

* Push to `main` branch
* Manual trigger (`workflow_dispatch`)

CI includes:

* Maven build
* Unit tests
* CodeQL (SAST)
* Docker image build
* Runtime smoke test
* Trivy image scan
* DockerHub push

*No manual steps required.*

---

### 5. Running CD Pipeline (Kubernetes Deployment)

The **CD pipeline triggers automatically** after CI succeeds.

It:

* Connects to the Kubernetes cluster
* Deploys the latest trusted image
* Verifies deployment rollout

---

### 6. Required GitHub Secrets (Mandatory)

Set the following secrets in the GitHub repository:

| Secret Name          | Description               |
| -------------------- | ------------------------- |
| `DOCKERHUB_USERNAME` | DockerHub username        |
| `DOCKERHUB_TOKEN`    | DockerHub access token    |
| `KUBECONFIG`         | Base64-encoded kubeconfig |

---

### 7. Running Unit Tests Only

```bash
mvn test
```

---

## Repository Structure

```
.
├── .github/workflows
│   ├── ci.yml
│   └── cd.yml
├── k8s
│   └── deployment.yaml
├── src
│   ├── main
│   └── test
├── Dockerfile
├── pom.xml
└── README.md
```

---

## Expected Outcomes

* Automated CI/CD pipeline
* Secure and reproducible builds
* Kubernetes deployment without manual steps
* Clear separation of responsibilities
* Production-aligned DevOps workflow

---

## Key Learnings

* Kubernetes is not limited to web services
* CI/CD is about **decisions**, not YAML size
* Security gates must block promotion
* CLI workloads require different deployment thinking

---


## 👨‍💻 Author

**Hemkesh Kantawala**
DevOps Project – CI/CD & DevSecOps
GitHub: [https://github.com/hemkeshkantawala11](https://github.com/hemkeshkantawala11)

---

