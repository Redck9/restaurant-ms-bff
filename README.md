# 🍽️ Restaurant-ms-bff

## Overview

This is the Backend-for-Frontend (BFF) component of the Redck Restaurant Microservices application — a full-stack, real-time web platform for restaurant management and customer engagement.

The BFF acts as a gateway between the frontend (built with React) and the backend microservices (restaurants, tables, chairs, etc.). It streamlines API responses and handles user authentication, providing a secure and optimized interface tailored to frontend needs.

## 🔧 Main Responsibilities

- User Authentication & Role-Based Access Control
Implements secure login and permission-based access using Spring Security and PostgreSQL.

 - API Aggregation & Gateway Logic
Serves as a centralized access point to multiple backend microservices (e.g., restaurant, tables, chairs).

## 📦 Technologies Used

 - Java (Spring Boot)

 - PostgreSQL (for user data and authentication)

 - Maven

 - Docker

## 🚀 How to Build and Run

### 🔨 Step 1: Build the Application JAR

Make sure you have Gradle installed or use the Gradle wrapper.

From the root directory of the BFF project, run:

    ./gradlew build

This will generate the *.jar file in the build/libs/ directory, which the Dockerfile will use.

> **_NOTE:_**  Make sure the Dockerfile references the correct path to the built .jar (typically something like build/libs/*.jar).

### 🐳 Step 2: Build the Docker Image

Once the build is complete, build the Docker image using:

    docker build -t redck-restaurant-ms-bff.jar .

### 🧱 Step 3: Start the Containers

Navigate to the directory containing the docker-compose.yaml file and run:

    docker compose up -d

This will launch the BFF service and any other services defined in the compose file.
