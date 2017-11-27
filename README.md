# Rainy Hills
Rainy Hills problem solver. The application takes an integer array as an 
input, and calculates the volume of water which remained after the rain, 
in units.

### System requirements
* JDK 8;
* Maven 3.5;
* The `8080` port must not be occupied.

*Note: the app was tested with JDK 1.8.0_144 and Maven 3.5.2.*

### Build & deploy
Perform the next command to build the the project:

    mvn clean package
Start the project via:

    java -jar target/rainy-hills-swarm.jar
Access the app in browser using 
[http://localhost:8080/](http://localhost:8080/) . 

Alternatively the app could be built and started by single command:

    mvn clean wildfly-swarm:run

### Application structure
The application is split into frontend part writen with `html/js/css` and 
backend part writen with `Java 8` + `Wildfly Swarm`. The frontend accesses 
the backend  via REST API calls.

The backend part is logically split into service level and REST API level.
The service part is represented by 
[SolvingService](./src/main/java/com/shpota/rainyhills/service/SolvingService.java).
The REST part is represented by 
[SolvingEndpoint](./src/main/java/com/shpota/rainyhills/rest/SolvingEndpoint.java).
The endpoint and the service are connected via CDI injection.

### Test coverage
The application code is covered with unit and integration tests. Units tests are 
implemented with `JUnit` and `Mockito`. Integration tests are based on `Arquillian`.
Integration tests are implemented in a [separate submodule](./src/it). They are not 
performed during build process, and should be started separately via:

    mvn integration-test

### Algorithm complexity
The algorithm has *O(n)* time complexity because it passes the input array 
3 times performing constant number of operations during each pass.
Memory complexity is also *O(n)* because the algorithm operates with 3 arrays
and acquires memory only constant number of times during bypass.

###### Improvements
It is not possible to decrease the time/memory complexity of the algorithm. 
The optimal solution will still have *O(n)* time and memory complexity. However 
there is still area of improvements. For instance it is possible to calculate
the end value during second pass of the array (once we calculate both right 
top and left top values we can already accumulate the difference). But I left 
the implementation the way it is because it holds good balance between 
complexity and code readability.
