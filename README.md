# assigment
Task1 and Task2 Assignement


**Task1**
==========
**Service details :**
mainapp - Service1 - to communicate with service 1 and service 2 and send the combined response.
helloapp - Service2 - A get method is implemented to provide "Hello! " as the response 
combinerapp - Service3 - A post method is implemented to take the json as input and provide the concatinated output.

**Below are the assignment expectation met for task1**
1. Have included sleuth for tacing the request.
2. Have used zipkin server as a log data collection server for distributed tracing and also for tracking the requests with the traceid. 
   Request timing data is also analyzed using this server.
3. Have adopted resttemplate to communicate between the servers internally.
4. Have user Eureka server/client for service discovery using the appnames instead of providing the urls.
5. Have implemented relevent exception handlers to validate the input data and also validate the responses from the internal servers
6. Swagger UI is included to visualize and interact with service1 resources.
7. Have deployed the applications on EC2 instance by configuring the relevent security groups for the services in a free tier account(Attached screen shots).
8. Have used public DNS adress for accessing the service1 from swagger ui to execute the get and post calls.
9. I Should have used Hosted DNS name for the eureka server as I have ended up in changing and configuring the public DNS adress of eureka server in all the services(properties) every time I deploy the eureka server.


**EUREKA**
<img width="927" alt="image" src="https://user-images.githubusercontent.com/120272973/207142477-e85fff1d-b09e-4298-8b10-fdf315d5dccf.png">

**Get Method of service 1**
<img width="917" alt="image" src="https://user-images.githubusercontent.com/120272973/207142843-d82191c5-f65a-4d58-abee-6dc1521e1e2d.png">

**Post Method of service 1**
<img width="917" alt="image" src="https://user-images.githubusercontent.com/120272973/207142723-cccb6e2a-d98d-45d7-a160-e419b0a16d48.png">

**Zipkin Server**
<img width="954" alt="image" src="https://user-images.githubusercontent.com/120272973/207142380-508e2200-356e-43d0-80db-a635177e4aa6.png">

**Task2**
==========
**Service details :**
centimeapp - to get the role data based on ID and also to get the whole data in nested format.
**Below are the assignment expectation met for task2**
1. Hosted local h2 database for the microservice used and provided the required properties to connect to db.
2. Created an entity named Role to autocreate the table in db. 
3. Have created a sql file to manually insert all the data provided into the database table.
4. Have implemented the algorithm to form the expected relationship in RoleService.java class.
   Algo - a. fetch all the available data from table
          b. group the data based on the parentid using streams
          c. iterate over the grouped data for each paraent id and create the top level object.
          d. A recursive method is implemented to build the subclasses(recursively) for the nested IDs.
          c. Took care to not to duplicate the objects which are already considered for subclass nesting.
5. Have implemeted custom @LogMethodParam annotation to print the parameters passed to the methods.
Note : Have faced issues in using lombok(Thought of resoving later), and ended up in committing annotations as well as setters/getters.



**Custom Annotation Loggers**
2022-12-12 23:28:08.070  INFO 28084 --- [nio-9004-exec-4] c.c.custom.annotation.MethodParamLogger  : Name of the methos findByUserId
2022-12-12 23:28:08.070  INFO 28084 --- [nio-9004-exec-4] c.c.custom.annotation.MethodParamLogger  : {
  "id": 1
}

**Below is the response downloaded from swagger ui**
[
  {
    "Name": "Warrior",
    "Sub Classes": [
      {
        "Name": "Fighter"
      },
      {
        "Name": "Paladin"
      },
      {
        "Name": "Ranger"
      }
    ]
  },
  {
    "Name": "Wizard",
    "Sub Classes": [
      {
        "Name": "Mage"
      },
      {
        "Name": "Specialist wizard"
      }
    ]
  },
  {
    "Name": "Priest",
    "Sub Classes": [
      {
        "Name": "Cleric"
      },
      {
        "Name": "Druid"
      },
      {
        "Name": "Priest of specific mythos"
      }
    ]
  },
  {
    "Name": "Rogue",
    "Sub Classes": [
      {
        "Name": "Thief",
        "Sub Classes": [
          {
            "Name": "Assassin"
          }
        ]
      },
      {
        "Name": "Bard"
      }
    ]
  }
]



<img width="922" alt="image" src="https://user-images.githubusercontent.com/120272973/207113381-077dff43-297b-433f-af2f-26780b97facb.png">

<img width="923" alt="image" src="https://user-images.githubusercontent.com/120272973/207113612-68ac640f-7392-4ec8-b865-4f7a1e87554e.png">


