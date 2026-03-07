# Read Me First

The following was discovered as part of building this project:

* The JVM level was changed from '25' to '24' as the Kotlin version does not support Java 25 yet.

# Getting Started

### Reference Documentation

For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/4.0.3/gradle-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/4.0.3/gradle-plugin/packaging-oci-image.html)
* [Spring Data JPA](https://docs.spring.io/spring-boot/4.0.3/reference/data/sql.html#data.sql.jpa-and-spring-data)
* [Spring Security](https://docs.spring.io/spring-boot/4.0.3/reference/web/spring-security.html)
* [Spring Web](https://docs.spring.io/spring-boot/4.0.3/reference/web/servlet.html)

### How To Deploy
* cd web-app
* npm run build
* copy files from web-app/dist into src/main/resources/static
* gradle bootJar
* copy jar file from build/libs to docker folder and rename to wedding-invitation.jar
* zip all content of the folder and transfer to the server
* scp /path-to-docker-folder/docker/application-production.zip username@server-ip:~/compose/weddign/application.zip
* ssh into the server
* cd compose
* build a docker image docker build -t wedding-invitation -f wedding/wedding.dockerfile wedding
* cd wedding
* docker compose up -d


### Guides

The following guides illustrate how to use some features concretely:

* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Securing a Web Application](https://spring.io/guides/gs/securing-web/)
* [Spring Boot and OAuth2](https://spring.io/guides/tutorials/spring-boot-oauth2/)
* [Authenticating a User with LDAP](https://spring.io/guides/gs/authenticating-ldap/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)

### Additional Links

These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)

