plugins {
	java
	id("org.springframework.boot") version "3.1.0"
	id("io.spring.dependency-management") version "1.1.0"
}

group = "com.redck"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
	targetCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.2.0")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-security:3.2.4")//N tinha versão
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	//implementation("org.springframework.data:spring-data-jpa:3.2.4")
	implementation("org.mapstruct:mapstruct:1.5.5.Final")
	testImplementation("com.h2database:h2:2.1.214")//1.4.200
	implementation("javax.persistence:javax.persistence-api:2.2")
	implementation("io.jsonwebtoken:jjwt:0.9.1")
	//implementation("jakarta.persistence:jakarta.persistence-api:3.1.0")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.1.0")//2.4.2
	implementation("org.postgresql:postgresql:42.7.3")//42.2.20
	implementation("org.springframework.boot:spring-boot-starter-jdbc:2.5.3")
	implementation("org.liquibase:liquibase-core:4.24.0")
	//implementation("org.hibernate:hibernate-annotations:3.5.6-Final")
	implementation("org.springframework.security:spring-security-config:6.1.2")
	implementation("org.thymeleaf:thymeleaf:3.1.2.RELEASE")
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
	annotationProcessor("org.mapstruct:mapstruct-processor:1.5.5.Final")
}

tasks.withType<Test> {
	useJUnitPlatform()
}