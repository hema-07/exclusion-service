## Exclusion Application

##### Language  :  Java (Spring boot) 2.5.2
##### Database  :  H2 
##### Test      :  Unit Test
##### CI/CD     :  Jenkins
##### security  : Spring security 2.3.4.RELEASE

### Functionality


This is a Spring boot application which demonstrates returning list of users for registration.

I have used H2 database in this application. It accepts User modal as a request. 
 
    * When User request enters the endpoint, it passes to Validator method to validate the request.

    * After validation, validated request enters into User service.

    * Once it received list of blacklisted user from DB, 
    it returns the list as  response.

I have written test cases for success and failure scenario.

I have installed my own jenkins and created a simple pipeline that pushes my code to pipeline and build it.

### Run configuration

    Run this Exclusion application as spring boot application.

### REST POST endpoint for adding new user

This following values are hitting the endpoint as a request from user.

    USER_ID - Userid  details

    FIRST_NAME - User's first name

    LAST_NAME - User's last name

    DATE_OF_BIRTH - User's Date of Birth

    EMAIL - User's email id
    
    COUNTRY - User's country
    
    STATUS - User status ; New User- N; Blacklisted User: B;
    

---------------------------------------

    Use POST method for same endpoint to populate data in DB
    
    POST call : http://localhost:9092/exclusion/v1.0/user
    
    and try GET call to view the list of blacklisted users.
    
    GET call : http://localhost:9092/exclusion/v1.0/user
   

    {
        "users": [
            {
                "userId": "1",
                "userFirstName": "Mark",
                "userLastName": "Josh",
                "userDateOfBirth": "30/3/95",
                "userEmail": "mark@gmail.com",
                "userCountry": "UK",
                "userStatus": "B"
            },
            {
                "userId": "2",
                "userFirstName": "Hema",
                "userLastName": "Tamil",
                "userDateOfBirth": "30/2/95",
                "userEmail": "hema64@gmail.com",
                "userCountry": "UK",
                "userStatus": "B"
            }
        ]
    }
    
---------------------------------------
  
##### Headers: 
    
    Content-Type:application/json
    
    Accept:application/json
    

### Preparation 

Skeleton Project from  https://start.spring.io  

Jenkins: http://localhost:8080

Attached screenshot of my local Jenkins CI/CD page is in docs folder.
