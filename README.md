# Vietnamesecolour API

This app was created with Bootify.io - tips on working with the code [can be found here](https://bootify.io/next-steps/). Feel free to contact us for further questions.

## Development

During development it is recommended to use the profile `dev`. In IntelliJ, `-Dspring.profiles.active=dev` can be added in the VM options of the Run Configuration after enabling this property in "Modify options".

Update your local database connection in `application.yml` or create your own `application-dev.yml` file to override settings for development.

Lombok must be supported by your IDE. For this, in IntelliJ install the Lombok plugin and enable annotation processing - [learn more](https://bootify.io/next-steps/spring-boot-with-lombok.html).

After starting the application it is accessible under `localhost:8080`.

## Environment variables
- `JDBC_DATABASE_PASSWORD=`
- `JDBC_DATABASE_URL=jdbc:mysql://localhost:3306/xxx`
- `JDBC_DATABASE_USERNAME=testroot`
- `UPLOAD_DIR=uploads/` or you can specify absolute path

## Build

The application can be built using the following command:

```
mvnw clean package
```

The application can then be started with the following command - here with the profile `production`:

```
java -Dspring.profiles.active=production -jar ./target/my-app-0.0.1-SNAPSHOT.jar
```

## Further readings

* [Maven docs](https://maven.apache.org/guides/index.html)  
* [Spring Boot reference](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)  
* [Spring Data JPA reference](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)  
