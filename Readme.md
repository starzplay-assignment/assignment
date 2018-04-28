# Spring Boot, Jackson

Build Restful API for a simple media filter using Spring Boot, Jackson.

## Requirements

1. Java - 1.8.x

2. Maven - 3.x.x

3. Git


## Steps to Setup

**1. Clone the application**

```bash
git clone https://github.com/callicoder/spring-boot-mysql-rest-api-tutorial.git
```


**2. Build and run the app using maven**

```bash
mvn package -Dmaven.test.skip=true
java -jar target/java-assignment-1.0.0.jar
```

Alternatively, you can run the app without packaging it using -

```bash
mvn spring-boot:run
```

The app will start running at <http://localhost:8080>.

## Explore Rest APIs

The app defines following APIs.
	
    GET http://localhost:8080/media?filter=censoring&level=Censored 
    GET http://localhost:8080/media?filter=censoring&level=Uncensored
    
    Content-Type = application/x-www-form-urlencoded

You can test them using Advance Rest Client or any other rest client.


