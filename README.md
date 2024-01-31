### Package the application

* Open a terminal or command prompt. 
* Navigate to the root directory of your Spring Boot project. 
* Run "mvn clean package" to build the project and create a JAR file:
* This will create an executable JAR file in the target directory.

### Running the Jar
After packaging, you can run the application using the following command:

* java -jar target/your-application-name.jar

Alternatively with globally installed maven

* mvn spring-boot:run

### API Endpoints

### Check Conference Room Availability

**Endpoint:**
POST http://localhost:8080/api/v1/conference-rooms/check-availability

**Request body:**

```json
{
  "startTime": "23:00:00",
  "endTime": "23:15:00"
}    
```
**Endpoint:**
http://localhost:8080/api/v1/bookings/submit-new

**Request body:**

```json
{
  "roomId": 1,
  "startTime": "22:00:00",
  "endTime": "22:15:00",
  "numberOfAttendees": 2
}
```