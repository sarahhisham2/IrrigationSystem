# Irrigation System

This project is an Irrigation System implemented in Java using Spring Boot. It allows you to manage plots, sensors, and time slots for automated irrigation.

## Prerequisites

Before running the project, please ensure that you have the following installed:

- MySQL: You should have MySQL installed on your machine. If you don't have MySQL installed, you can download XAMPP (https://www.apachefriends.org/) which includes MySQL. Start the MySQL service after installation.

## Getting Started

To get the solution running on your machine, follow these steps:

1. Create a database: Open your preferred web browser and visit `http://localhost/phpmyadmin` to access phpMyAdmin. Create a new database called `irrigation`.

2. Clone the repository: Clone this GitHub repository to your local machine.

3. Import the project: Open IntelliJ IDEA (or your preferred Java IDE) and import the project by selecting the root directory of the cloned repository.

4. Configure the database connection: Open the `src/main/resources/application.properties` file and update the database connection properties according to your MySQL configuration. Set the `spring.datasource.url`, `spring.datasource.username`, and `spring.datasource.password` properties.

5. Run the application: Open the `src/main/java/irrigation/application/Application.java` file and run it as a Java application. This will start the Spring Boot application and make it accessible at `http://localhost:8080`.

6. Test the application: Open the `src/test/java/controller/PlotControllerTest.java` file and run it as a JUnit test. This will run the unit tests for the PlotController class.

Note: Make sure you have configured the database connection correctly to avoid any errors in the tests.

7. Re-running the application: If you want to re-run the application and start with a fresh database, follow these steps:

- Stop the running application.
- In phpMyAdmin, select the `irrigation` database.
- Click on the "SQL" tab and enter the following command to drop all tables from the database:

  ```
  DROP TABLE IF EXISTS plot;
  DROP TABLE IF EXISTS sensor;
  DROP TABLE IF EXISTS time_slot;
  ```

- Click the "Go" button to execute the SQL command and drop the tables.
- Start the application again by running the `Application.java` file.


