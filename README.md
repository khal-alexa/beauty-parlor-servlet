# Beauty parlor application

Beauty parlor app is web-service for beauty services booking and management.

###  Features:

- role-based authentication and authorization for Admin, Guest, Client, Specialist
- pagination
- i18n
- generic CRUD operations
- persistence layer for an application using JDBC API
- validation
- FrontController pattern

###  Technology stack:
- Java 8
- Servlet API 4.0
- JSP, HTML, JSTL
- Apache Tomcat 9.0
- MySQL

###  Requirements:
- Java 8
- Maven
- Apache Tomcat
- MySQL

###  Running notes:
- create DB in MySQL client:
```
CREATE DATABASE beauty CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```
- use db credentials stored in resources/db.properties file or change them with yours
- run the project, app is using:
```
http://localhost:8080/
```
- you can use test credentials:

username: admin\
password: Admin2020\
role: ADMIN


username: client\
password: Client2020\
role: CLIENT

username: specialist\
password: Specialist2020\
role: SPECIALIST
