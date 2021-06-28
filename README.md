# shopper cart service

The System has be been built using Spring boot framework, with an exposed endpoint/REST API  
* host/api/v1/cart, where host is the server where the system has been deployed or running, v1 is for the versioning of the API
* for local testing or postman testing : use localhost:8090/api/v1/cart 

## Requirements

* Java 1.8
* Gradle 4.10.3

## How to run it?
Do :> gradle bootRun

## Improvement to be covered

due to limit time this system is developed in 2hrs max

for relational db design check /src/main/resources/initial.sql

current system is running over the key value pair of [User, List<ShoppingCartItem>]

  
  
  
  

