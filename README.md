# Digital Trust Score System

A complete, production-ready, fully anonymous Digital Trust Score web application engineered to evaluate entity trustworthiness across seven weighted security and behavioral signal dimensions.

> **Zero Authentication Policy**: Built strictly without authentication, user accounts, JWT, OAuth, or password fields. Every visitor has immediate, unrestricted access to the complete trust calculator.

---

## 1. Project Overview

The **Digital Trust Score System** provides a transparent, open-access analytical engine for scoring entity trustworthiness on a scale of 0 to 100. It measures identity, security posture, account reliability, behavioral consistency, interaction history, reputation, and data consistency using a deterministic mathematical model.

The platform is designed to be maintainable in both **Eclipse** (for Spring Boot backend) and **VS Code** (for React frontend) without relying on automated code-generation platforms or proprietary cloud dependencies.

---

## 2. Architecture

The application adopts a decoupled client-server architecture:

```
+-------------------------------------------------------+
|                React + TypeScript Frontend            |
|       (Vite, Component UI, Glassmorphic Theme)        |
+---------------------------+---------------------------+
                            | REST API (HTTP/JSON)
                            v
+-------------------------------------------------------+
|               Spring Boot 3.x / Java Backend          |
|    - TrustController & HealthController               |
|    - TrustScoreService & TrustScoringEngine           |
|    - Jakarta Validation & Global Exception Handler    |
+---------------------------+---------------------------+
                            | Spring Data JPA
                            v
+-------------------------------------------------------+
|                 MySQL Database                        |
|            (digital_trust_score database)             |
|              table: trust_calculations                |
+-------------------------------------------------------+
```

---

## 3. Features

- **Instant Anonymous Trust Calculation**: Dual-control 0-100 sliders and numeric inputs.
- **7 Weighted Signal Dimensions**: Identity, Security, Reliability, Behavior, Interaction, Reputation, and Consistency.
- **Transparent Confidence Engine**: Evaluates standard deviation (indicator spread) and mean distance from neutral (decisiveness).
- **5 Classification Tiers**: `VERY_LOW`, `LOW`, `MODERATE`, `HIGH`, and `VERY_HIGH`.
- **Public REST API**: `/api/v1/trust/health`, `/api/v1/trust/calculate`, and `/api/v1/trust/methodology`.
- **Complete MySQL Persistence**: Automatically persists calculated scores without personal identity markers.
- **Developer Workspaces**: Eclipse project structure for Java backend, VS Code for React frontend.
- **Responsive Dark SaaS UI**: Tailored color palette (Navy, Slate, Sky Blue, Emerald, Amber, Rose; zero primary purple).

---

## 4. Technology Stack

### Backend
- **Java**: 17+ (JDK 24 compatible)
- **Framework**: Spring Boot 3.2+
- **Build Tool**: Apache Maven (via Maven Wrapper `mvnw.cmd`)
- **Web & Validation**: Spring Web MVC, Jakarta Validation (`@NotNull`, `@Min`, `@Max`)
- **ORM & Database**: Spring Data JPA, Hibernate, MySQL 8.0+ (H2 in-memory for unit testing)
- **IDE Support**: Eclipse / VS Code / IntelliJ IDEA

### Frontend
- **Framework**: React 18
- **Language**: TypeScript 5
- **Build Tool & Dev Server**: Vite 5
- **Styling**: Modern CSS3 Custom Properties & Responsive Flex/Grid Layout
- **Icons**: Lucide React
- **IDE Support**: VS Code

---

## 5. Folder Structure

```
digital-trust-score/
├── .env.example
├── README.md
├── backend/
│   ├── mvnw.cmd
│   ├── pom.xml
│   └── src/
│       ├── main/
│       │   ├── java/com/digitaltrust/
│       │   │   ├── DigitalTrustScoreApplication.java
│       │   │   ├── config/
│       │   │   │   └── CorsConfig.java
│       │   │   ├── controller/
│       │   │   │   ├── HealthController.java
│       │   │   │   └── TrustController.java
│       │   │   ├── dto/
│       │   │   │   ├── ErrorResponse.java
│       │   │   │   ├── TrustRequest.java
│       │   │   │   └── TrustResponse.java
│       │   │   ├── entity/
│       │   │   │   └── TrustCalculation.java
│       │   │   ├── exception/
│       │   │   │   └── GlobalExceptionHandler.java
│       │   │   ├── repository/
│       │   │   │   └── TrustCalculationRepository.java
│       │   │   ├── scoring/
│       │   │   │   └── TrustScoringEngine.java
│       │   │   └── service/
│       │   │       └── TrustScoreService.java
│       │   └── resources/
│       │       ├── application.properties
│       │       └── schema.sql
│       └── test/
│           ├── java/com/digitaltrust/
│           │   ├── controller/
│           │   │   └── TrustControllerTest.java
│           │   └── scoring/
│           │       └── TrustScoringEngineTest.java
│           └── resources/
│               └── application.properties
└── frontend/
    ├── .env.example
    ├── index.html
    ├── package.json
    ├── tsconfig.json
    ├── vite.config.ts
    └── src/
        ├── App.tsx
        ├── index.css
        ├── main.tsx
        ├── vite-env.d.ts
        ├── components/
        │   ├── ApiDocSection.tsx
        │   ├── Footer.tsx
        │   ├── Header.tsx
        │   ├── Hero.tsx
        │   ├── MethodologySection.tsx
        │   ├── ScoreResult.tsx
        │   ├── TrustCalculator.test.tsx
        │   └── TrustCalculator.tsx
        └── types/
            └── trust.ts
```

---

## 6. Prerequisites

Ensure you have the following installed on your machine:

- **JDK 17 or higher** (`java -version`)
- **Node.js 18 or higher** (`node -v`) & npm (`npm -v`)
- **MySQL Server 8.0+** running locally or accessible remotely
- **Eclipse IDE** (Java Enterprise Edition) and/or **VS Code**

---

## 7. Backend Setup

1. Open a terminal and navigate to `backend/`:
   ```bash
   cd backend
   ```
2. Build the project using Maven Wrapper:
   ```bash
   .\mvnw.cmd clean compile
   ```
3. To open in **Eclipse**:
   - Open Eclipse -> `File` -> `Import...`
   - Choose `Existing Maven Projects` -> Select the `backend/` folder -> Click `Finish`.

---

## 8. MySQL Setup

1. Start your MySQL Server daemon.
2. Log into MySQL client:
   ```sql
   mysql -u root -p
   ```
3. Execute the schema script located at `backend/src/main/resources/schema.sql`:
   ```sql
   CREATE DATABASE IF NOT EXISTS digital_trust_score;
   USE digital_trust_score;

   CREATE TABLE IF NOT EXISTS trust_calculations (
       id BIGINT AUTO_INCREMENT PRIMARY KEY,
       score DOUBLE NOT NULL,
       confidence DOUBLE NOT NULL,
       classification VARCHAR(20) NOT NULL,
       score_version VARCHAR(10) NOT NULL,
       identity_verification DOUBLE NOT NULL,
       security_posture DOUBLE NOT NULL,
       account_reliability DOUBLE NOT NULL,
       behavioral_consistency DOUBLE NOT NULL,
       interaction_history DOUBLE NOT NULL,
       reputation DOUBLE NOT NULL,
       data_consistency DOUBLE NOT NULL,
       created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
   ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
   ```

---

## 9. Frontend Setup

1. Navigate to `frontend/`:
   ```bash
   cd frontend
   ```
2. Install npm packages:
   ```bash
   npm install
   ```
3. Build production bundle to test Vite build:
   ```bash
   npm run build
   ```

---

## 10. Environment Variables

### Backend (`backend/src/main/resources/application.properties` or environment override):
- `DB_URL`: MySQL connection URL (default: `jdbc:mysql://localhost:3306/digital_trust_score`)
- `DB_USERNAME`: MySQL username (default: `root`)
- `DB_PASSWORD`: MySQL password (default: ``)
- `SERVER_PORT`: Backend HTTP port (default: `8080`)

### Frontend (`frontend/.env.example` -> `frontend/.env`):
- `VITE_API_URL`: Backend base API URL (default: `http://localhost:8080`)

---

## 11. Running Locally

### 1. Start the Backend Server
```bash
cd backend
.\mvnw.cmd spring-boot:run
```
The REST API will start on `http://localhost:8080`.

### 2. Start the Frontend Dev Server
```bash
cd frontend
npm run dev
```
Open your browser at `http://localhost:5173`.

---

## 12. API Documentation

### 1. Health Check
- **Endpoint**: `GET /api/v1/trust/health`
- **Response**:
  ```json
  {
    "status": "UP",
    "service": "Digital Trust Score System API",
    "version": "1.0",
    "timestamp": "2026-10-02T19:00:00"
  }
  ```

### 2. Calculate Trust Score
- **Endpoint**: `POST /api/v1/trust/calculate`
- **Request Body**:
  ```json
  {
    "identityVerification": 90,
    "securityPosture": 85,
    "accountReliability": 80,
    "behavioralConsistency": 75,
    "interactionHistory": 80,
    "reputation": 70,
    "dataConsistency": 85
  }
  ```
- **Response (HTTP 200 OK)**:
  ```json
  {
    "calculationId": 1,
    "score": 81.75,
    "confidence": 70.0,
    "classification": "HIGH",
    "scoreVersion": "1.0",
    "dimensions": {
      "identityVerification": 90.0,
      "securityPosture": 85.0,
      "accountReliability": 80.0,
      "behavioralConsistency": 75.0,
      "interactionHistory": 80.0,
      "reputation": 70.0,
      "dataConsistency": 85.0
    }
  }
  ```

### 3. Get Methodology
- **Endpoint**: `GET /api/v1/trust/methodology`
- **Response**: Returns weights, attribute descriptions, classification ranges, and confidence formula.

---

## 13. Scoring Methodology

### Formula
$$\text{Score} = (I \times 0.20) + (S \times 0.20) + (A \times 0.15) + (B \times 0.15) + (H \times 0.10) + (R \times 0.10) + (D \times 0.10)$$

Where $I, S, A, B, H, R, D \in [0, 100]$.

### Classification Ranges
- `0.00 – 39.99`: `VERY_LOW`
- `40.00 – 59.99`: `LOW`
- `60.00 – 74.99`: `MODERATE`
- `75.00 – 89.99`: `HIGH`
- `90.00 – 100.00`: `VERY_HIGH`

### Confidence Formula
$$\text{Confidence} = \text{Clamp}_{0}^{100}\left(100.0 - (\sigma \times 0.8) + (D \times 0.4)\right)$$
- $\sigma$: Sample standard deviation across 7 attributes.
- $D$: Mean absolute distance from neutral (50).

---

## 14. Database Schema

```sql
CREATE TABLE trust_calculations (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    score DOUBLE NOT NULL,
    confidence DOUBLE NOT NULL,
    classification VARCHAR(20) NOT NULL,
    score_version VARCHAR(10) NOT NULL,
    identity_verification DOUBLE NOT NULL,
    security_posture DOUBLE NOT NULL,
    account_reliability DOUBLE NOT NULL,
    behavioral_consistency DOUBLE NOT NULL,
    interaction_history DOUBLE NOT NULL,
    reputation DOUBLE NOT NULL,
    data_consistency DOUBLE NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);
```

---

## 15. Testing

### Run Backend Unit & Controller Tests
```bash
cd backend
.\mvnw.cmd test
```

### Run Frontend Vitest Unit Tests
```bash
cd frontend
npm test
```

---

## 16. Git Workflow

Recommended commit order:
1. `Initial Spring Boot backend`
2. `Add trust scoring engine`
3. `Add MySQL persistence`
4. `Add REST API`
5. `Add React frontend`
6. `Add calculator UI`
7. `Add methodology and API documentation`
8. `Add tests`
9. `Add README`

---

## 17. Security

- Input validation rejecting negative numbers, numbers >100, null values, and malformed JSON.
- Safe exception handling with zero stack trace leakage in production.
- Custom CORS configuration restricting origins and allowed HTTP methods.
- Rate limiting recommendation via reverse proxy (Nginx/Cloudflare).

---

## 18. Privacy

- **Zero Identifiable Data**: No IP tracking, personal tokens, emails, names, or credentials collected.
- **Anonymous Database Records**: `trust_calculations` stores only numerical score vectors.

---

## 19. Deployment

- **Backend**: Containerize using Spring Boot Maven Plugin Docker build or run jar (`java -jar target/digital-trust-score-backend-1.0.0.jar`).
- **Frontend**: Deploy `frontend/dist` static assets to Vercel, Netlify, or Nginx.

---

## 20. Troubleshooting

- **MySQL Connection Failed**: Ensure MySQL server is running and `DB_USERNAME` / `DB_PASSWORD` are correct in `application.properties`.
- **CORS Error**: Ensure `VITE_API_URL` matches backend address and `CorsConfig.java` allows dev origin.

---

## 21. Contributing

Pull requests are welcome! Ensure all backend JUnit tests (`.\mvnw.cmd test`) and frontend tests (`npm test`) pass before submitting PRs.

---

## 22. License

This project is open-source under the **MIT License**.
