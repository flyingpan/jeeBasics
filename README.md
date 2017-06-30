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
When communicating with the Services over RMI all Objects must be Serializable. That makes sense, but is often forgotten. On the client not only the interface must be available on the classpath but also the implementation. That applies also to thrown Runtime Exceptions. Even if an Exception is caught and wrapped
```
try{

} catch (SomeException se){
    throw new RuntimeException(se);
}
```
the wrapped Exceptions must be available on the clients Classpath.

### Persistent Bag (Hibernate)
The list implementation that Hibernate returnes is Persistent Bag.
1. The Persistent Bag is serializable, but normally it is not on the classpath, as it is not part of the jre. It can be found in 
    ```
    org.hibernate:hibernate-core
    ```
1. Persistent Bag does not guarantee the order. See details here
```
https://www.kingsware.de/2011/03/15/ordering-collections-with-jpa/
```

### Lazy Loading (Hibernate)
A user has multiple tasks. Not each time a user is loaded its tasks are required as well. Therefore JPA supports lazy loading. 
```
@OneToMany(fetch=FetchType.EAGER)
@OneToMany(fetch=FetchType.LAZY)
```
The tasks are lazily loaded when they are accessed. This only works within the PersistenceContext. Outside a LazyInizializationException is thrown.

There are multiple possibilities to ensure to not get LazyInizializationExceptions when a lazy loaded Collection is required outside the PersistenceContext:
+ Always load EAGER
+ Ensure the list is accessed within the PersistenceContext
+ Do not send the entity to the client but map it to a TO.

### Entity Manager
TBD

### Auto persist changes on managed objects
When changing objects that are managed, the changes are automatically committed when exiting the transaction. No explicit persist must be called. In order to discard the changes the transaction must be rolled back.

### Lost update
Hibernate supports optimistic locking, but to use it a field must be available that is annotated with @Version. If it is forgotten lost updates might occur.

### StatelessSessionBean
The container does not ensure that there is not state on a stateless session bean. It is a contract that the developer agrees on. Violating the contract can lead to difficult non deterministic behaviour.

### Annotations



## Solutions
