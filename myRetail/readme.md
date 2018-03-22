# myRetail Restful Service 

This Case study represent a Proof of Concept for a Product Api, which aggregate product data from multiple sources and return it as JSON to the caller.   

## Getting Started

In Eclipse or Any development IDE for java, Import as Maven Project, either build manually or by default auto build is enabled. 
I used spring boot application, when the maven build is done, simply run as Java Application on the main class.
No more configuration required as the App server and database are already embedded into app.

### Prerequisites

```
Java 1.7 or 1.8
IDE like Eclipse 
Spring Boot 1.5.10
Egit(for Eclipse) or Git bash
Tomcat App Server
MongoDb Database
Web Services Testing Engine like Postman

Target Service: https://redsky.target.com/v2/pdp/tcin/{id} 
```

### Installing and Demo

Once the application is up and running, i'm loading a sample input on startup. 


Sample Input's & Output's

GET Request - 
This service returns the product details based on the id. 

```
Url: http://localhost:8080/myRetail/products/13860428
Request Method: GET
Response: {
            "id": 13860428,
            "name": "The Big Lebowski (Blu-ray)",
            "current_price": {
              "price": 13.49,
              "currency_code": "USD"
            }
          }
```

PUT Request - 
This service used to Modify Product Price, which updates the previous price of product in database and returns Product details.

```
Url: http://localhost:8080/myRetail/products/13860428
Content-Type: application/json
Request Method: PUT
Request Body: {
            "id": 13860428,
            "name": "The Big Lebowski (Blu-ray)",
            "current_price": {
              "price": 14.99,
              "currency_code": "USD"
            }
          }
          
Response Body: {
            "id": 13860428,
            "name": "The Big Lebowski (Blu-ray)",
            "current_price": {
              "price": 14.99,
              "currency_code": "USD"
            }
          }          
```
Note: If there is no response from database or target api  ProductNotFound exception is thrown. 

## Built With

* [SpringBoot](https://projects.spring.io/spring-boot/) - The web framework used
* [Spring Initializr](https://start.spring.io/) - Generate's Project with starter Dependencies
* [Maven](https://maven.apache.org/) - Dependency Management
