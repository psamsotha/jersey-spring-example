Jersey Spring (with Spring Data JPA) Example
==============

Blog example from [Using Spring With Jersey ][1]

[1]: https://psamsotha.github.io/jersey/2015/10/19/why-use-spring-with-jersey.html


### Run

    mvn jetty:run

### Endpoints

| Method | URL | Description |
| --- | --- | --- |
| GET | http://localhost:8080/api/customers | Get all customers |
| GET | http://localhost:8080/api/customers/{id} | Get customer by id |
| POST | http://localhost:8080/api/customers | Create a customer |
| PUT | http://localhost:8080/api/customers/{id} | Update a customer |
| DELETE | http://localhost:8080/api/customers/{id} | Delete a customer |

