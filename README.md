# action-monitor
I've created a Spring Boot application to monitor the inserts in a database table and notify all the browsers with a session established every time the contents of the table change.

## Used technologies
* Spring Boot
* Maven 3
* SL4j for logging
* H2 as in-memory database
* ActiveMQ
* Spring WebSocket
* Mockito

## How-To execute
Hence this is a simple Spring Boot application, you can start it wit the followgin command:
* mvn spring-boot:run 

## How-To test
* Open http://localhost:8079/index.html to connect to the server and receive messages through websocket
* H2 developer console: http://localhost:8079/h2-console

## Technical details
### Monitoring the database
I choose H2 as database provider, what can be configured on an easy way and it can run in-memory.
*Schema:*
* Tables:
  * T_PERSON (ID, NAME)

The task was about to monitor the inserts in the database, so in my understanding it means that the appliation has to catch all of the inserts even it was done by JPA or directly from SQL (via H2 developer console).
To provide this functionality I wrote TRIGGERs, what capture all the INSERT, DELETE, UPDATE messages and send information about the change.
At H2 the triggers has to be in Java, so I've created them under "com.betvictor.action_monitor.db.h2.triggers" package. The triggers collect the data from the proper resultSet objects and send the messages to the ActiveMQ queue.
H2 triggers are instantiated by H2 framework, not by Spring, so I had to provide a simple utility class, SpringContext.class, to create a bridge between the Spring-managed world and the H2 triggers. Without it I wouldn't be able to send messages to the ActiveMQ.

With this solution the triggers will be executed in the following cases:
* By executing direct INSERT, UPDATE, DELETE SQL statements from H2 developer console
* By executing JPA insert, update, delete statements from code

#### How-To monitor new table
* You need create the "AFTER" triggers for each action (insert, delete, update, or even select) in H2
* Implement the trigger in Java, by extending AbstractEntityTriggerAdapter class to send message to the message queue.

### Message queue
The messaging system had been implemented with ActiveMQ. There are two classes what provides the message handling.
* TableChangeMessageProducer: Through this component you can push new messages to the message queue. It converts the given TableChangeMessage method argument object to JSON, and send the JSON string to the queue.
* TableChangeMessageConsumer: It receives the JSON messages, and sends immediately to the WebSocket clients.

### Endpoints
* REST
  * Health check page: http://localhost:8079/healthCheck
  * Query the Person table: http://localhost:8079/findAll
* WebSocket
  * Registration endpoint: http://localhost:8079/active-monitor/connect
  * Broker channel to receive notification messages: http://localhost:8079/active-monitor/updates
  
