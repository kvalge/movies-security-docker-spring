# Movie Project
The movie application which allows to manage authenticated and authorized users and movies.  
Back-end project to github.com/kvalge/movies-security-docker-angular.  
Used IDE: IntelliJ 2023.1 EAP.  

### Containerization
Created Dockerfile and docker-compose.yml to containerize application and Postgres db.  

### Project structure
analytics directory to return statistical analysis of categories (genres) and movies.  
config to manage authentication and authorization.  
domain to manage functionalities.  
infrastructure to manage exceptions.  
login to authenticate users logins.  
validation to handle user input validation methods.  
javadoc directory with index.html file to get general overview of the project.  

### Functionalities
category: for movie categories (genres) with add, get all, get by name and delete functionalities.  
movie: add, get by name, get all, update and delete functionalities.  
moviedetails: to add and manage detailed info to movies.  
role: add new and delete functionalities. Roles are ADMIN and USER.  
user: get all, get by username and delete functionalities.   
register: register user functionality.  
rental: add and manage rental data.  

### Testing
For unit testing it is used the Postgres db or Mockito framework.  

------------------------------

### Reference Documentation
The JVM level was changed from '11' to '17', review the [JDK Version Range](https://github.com/spring-projects/spring-framework/wiki/Spring-Framework-Versions#jdk-version-range) on the wiki for more details.

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.0.2/gradle-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.0.2/gradle-plugin/reference/html/#build-image)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/3.0.2/reference/htmlsingle/#data.sql.jpa-and-spring-data)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.0.2/reference/htmlsingle/#web)
* [Validation](https://docs.spring.io/spring-boot/docs/3.0.2/reference/htmlsingle/#io.validation)

### Guides
The following guides illustrate how to use some features concretely:

* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Validation](https://spring.io/guides/gs/validating-form-input/)

### Additional Links
These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)

