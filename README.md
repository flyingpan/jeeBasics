# JEE Basics
This projct helps developers that newly start with a JEE/EJB project. It contains different system tests that fail. Each of the test shows a pittfall a developer might fall into that has not yet worked with JEE/EJB.

## Setup
In order the to be able to run the tests a JEE Container must be installed and started locally. The samples were tested with Wildfly 10.0.0.Final. To keep things simple I did not use Arquillian for testing. After each fixed test the ear must be redeployed and the Test must be reexeccuted.

### Setup steps
+ Install Wildfly 10.0.0.Final
+ Start the wildfly Server (/bin/standalone.bat; /bin/standalone.sh)
+ deploy the ear 
  + run 

    ```maven
    mvn clean install wildfly:deploy
    ```
+ Execute the test
+ Understand the pitfall
+ Fix the problem
+ Redeploy the ear
  + run 

    ```maven
    mvn clean install wildfly:undeploy wildfly:deploy
    ```
+ Execute the test -> green bar

## Tests
### RMI (Remote Method Invocation)
When communicating with the Services over RMI all Objects must be Serializable. That makes sense, but is often forgotten. On the client not only the interface must be available on the classpath but also the implementation.

### Persistent Bag
The list implementation that Hibernate returnes is Persistent Bag.
1. The Persistent Bag is serializable, but normally it is not on the classpath, as it is not part of the jre. It can be found in 
    ```
    org.hibernate:hibernate-core
    ```
1. Persistent Bag does not guarantee the order. See details here
```
https://www.kingsware.de/2011/03/15/ordering-collections-with-jpa/
```
## Solutions
