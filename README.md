# robot
The REST Api has been created using Springboot and Documented API's Using swagger.
It was Developed Using Intellij IDEA.
MySQL database was used to create this.

To Run The application you need to do the following:

On the project folder under src/main/resources

EDIT THE FOLLOWING:

#Create Database with name "**robot_production**"
spring.datasource.url=jdbc:mysql://127.0.0.1:3306/**robot_production**?autoReconnect=true&&zeroDateTimeBehavior=convertToNull

#You can modify the db user to suit your MySQL user
spring.datasource.username=root

#spring.datasource.password= CHANGE TO USER(ABOVE) PASSWORD and uncomment by removing hash, leave it this way if no password is set. 

NB; in my case I used the XAMPP MySQL which user is root and has no password


The Hibernate ORM handles database tables creation and other db operations.


To run the Application you can build and run
