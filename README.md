# simpleCMS

Installation

1. Clone SimpleCMS from git@github.com:brusoth09/simpleCMS.git
2. Open IntelliJ or any other IDE by opening pom.xml file (Maven should be installed and configured)
3. Then you have to create a database named "opencms" or else create datbase in another name and change the database name in properties file
4. You don't need to create tables and relations. Flyway database migration is enabled inthe project.
5. Navigate to /src/main/resources/database directory open database.properties and change your user name and password
6. Do a Maven clean and build.
7. Jetty server added as plugin in Maven. run jetty:run command(mvn jetty:run in console). It will run
8. Open the browser. Navigate to http://localhost:8080/simple-cms/index.jsp
9. Click Login button.
10. Login using default admin username and password
    username : admin
    password: 123456
11. Open http://localhost:8080/simple-cms/admin for admin view.
12. Open http://localhost:8080/simple-cms/home for home view.


Added Features
1. Spring MVC framework is used in the project.
2. Maven is used as build and management tool.
3. Spring Security used as security restriction.
4. Mysql is used as database.
5. Flyway is used for database migration.
6. Bootstrap is used front end framework.
7. For admin view
    a. admin can create a editor
    b. admin can suspend or enable user
8. For editor can access the WYSIWYG editor.
9. He can save the post to draft, can view preview, can make ready to publish.



