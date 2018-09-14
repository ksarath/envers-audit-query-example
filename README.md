Envers Audit Query Example
---
In this example you can see how to use Envers for Auditing your tables.

This is a Spring Boot Application and it is configured with h2 (in-memory) database.

There are the following entities:

0. AuditEnversInfo      --> auditory master table
1. User                 --> User table
2. UserOrganisations    --> User Organisation mapping table with preferred attribute
3. Organisation         --> Organisation

For each table (entity) the framework will create the auditory tables.

How to run?
---

1. Since the current version, it is not necessary create manually the schema,
when the application startup, it will create the schema and the entities.

2. Compile the project with the following command:
   ```
   mvn clean install
   ```
3. You can run the application inside of your ide from `com.envers.ExampleEnversApplication.java` or
 from terminal with the following command: 
    ```
    mvn spring-boot:run
    ```


Rest endpoints
---
I used the postman as a client to test the endpoints, you can import the collection, the file is in:
```
/resources/endpoints/collection[postmanv2.1].json
```
Or if you prefer, you can see the Controllers inside of ```com.envers.web``` 
