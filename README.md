# Technical test

This project has the endpoint `POST`  was requested and another extra endpoint `GET` to verify the result from the previous.


# Topics

- H2 database
- Gradle
- Hibernate
- Unit tests
- Validations
- General diagram
- SpringBoot (mainly Actuator and Security)

## Installation and deployment
Run the following commands:

`./gradlew build` 
`./gradlew bootRun` 

## API Endpoints
There is a JSON file `nisum-exam.postman_collection.json` to import in Postman

- POST: The endpoint to create an user without token and you can get the request from JSON file

`http://localhost:8080/open-api/v1/users` 

- GET: The endpoint to get all users with token

`http://localhost:8080/api/v1/users` 
