# Sample Rest Application


The sample application is based on Spring 4 (Annotation only approach) and demonstrates how
the Patrix architectural principles can be realized with a contemporary and test driven approach.

Currently the project consists of 4 different modules, and all of them have the same parent.

The parent project keeps configuration regarding one "Web Application" in this case the Sample Web Application.
The parent pom.xml dictates versions of the project as such, but also for all third-party dependencies and plugins, i.e
each dependency and plugin version is specified at one location.

### TO DO (not yet demonstrated)

* Proper application error handling
* Trace logging
* Fast-lane queries
* Interaction with "frontend" and shared authentication

### Quick start

##### Pre-Requisites

* Maven 3.X
* Java 1.7
* Project Lombok (download and click on jar to install full IDE support for Eclipse)

###### Common maven build commands:

*generate eclipse support*

	mvn eclipse:eclipse

*clean and rebuild all (runs all test cases)*

	mvn clean install

*run the Web application using an embedded tomcat test application server*

	cd web
	mvn tomcat7:run


*The project modules are:*

 1. persistence - shared platform module with all entities and repositories (might very well be autonomous)
 2. util - shared platform module with nice to have utilities (might very well be autonomous)
 3. timeregistration - example of a business domain with Rest API Controllers and Business Services
 4. web - the Web application stuff with security, error handling and application wiring


Common for all modules is the existence of a "*.config" package which contains annotation driven configuration beans.

#### Persistence

Plain JPA entities, check out configuration with support for a embedded test H2 database as well as a JBOSS JNDI data
source based production configuration. Repositories are pluggable using
a component scan approach. Though, at this time it's not utilized.


#### Util

The util currently contains a service with Java mapping functions (based on dozer), and also a way to get the current
authorized and authenticated user (might be further considered).
Of course shall the user be a "Patricia" specific bean and not based on a Spring interface as it's right now.
Furthermore the util package also contains a service to lookup and authorize users, which at this time is a test version
and therefore only available when the spring profile test has been activated.
The idea is to control what mechanism to use by enabling different named profiles.
An alternative solution would be to use component scan packages for different
implementations.

#### Timeregistration

This is a business module and the api is divided into the public "messsages" and "rest" APIs. Check out usage
of the MockMvc to unit test Rest APIs at some kind of integration level.


#### Web

Contains security aspects, and error handling. Both are very rudimentary at this stage but at least a starting point.
The module also support integration tests at a functional level and these are automatically
invoked during "mvn install", i.e. a real tomcat instance starts and runs during the test. The web application
might also be started locally "mvn tomcat7:run" which makes it possible to manually launch and monitor end-to-end integration tests from the IDE (Eclipse etc).







