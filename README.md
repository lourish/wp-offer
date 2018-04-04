# Worldpay Offer Code Screen

A RESTful service to create 'Offers'.

## Design

### Design Principles

1. [12 Factor application](https://12factor.net/)
1. Repeated requests to state-changing operations have no effect (ie calling create with the same parameters a second time does not create a duplicate)

Redis has been chosen as the persistent storage based on its scalability and ability to automatically expire keys which is used to expire offers.

### API

The Controller tests serve as the API documentation using Spring REST docs. The generated documentation is generated with the build and written to the target/generated-snippets folder as markdown.


### Further possible work

1. Add logging
1. Use an immutable object framework such as Immutables to provide maintainable equals, hashCode and toString implementations
1. Add source verification e.g. Checkstyle/PMD
1. Improve API error info (standardise errors, user-friendly text)
1. Version API in URL
1. Add a service layer when needed for further business logic
1. Further work required on documentation and integration into the application or update the documentation in src/resources as part of the build

## Assumptions

1. Deployment and packaging is out of scope, but the artifact must be deployable in a cloud environment
1. An offer is a unique combination of description, price and currency (such that an MD5 hash of these fields will produce a unique identifier)
1. Timezone support not required, all requests are in local timezone
1. Multiple offers may be made with the same parameters
1. To query an offer means to list and get by id
1. The application must be highly available and scalable (hence the choice of redis)

## Prerequisites

* Java 8

## Develop/Test/Build

The project builds using maven. Run the following to build and test the project:

```
./mvnw install
```

Integration tests use an in-memory Redis server for persistence. The port can be configured by setting the property `spring.redis.port` (e.g. as a JVM -D flag)

This project has been built on macOS X with the Oracle JDK

## Run

The project produces an executable jar file listening on port 8080 which can then be deployed as required (e.g. dockerized, pushed to cloud foundry, run stand-alone).

The running application requires a Redis instance for storage. Point it to a  Redis server:

```
java -jar target/wp-offer-0.0.1-SNAPSHOT.jar -Dspring.redis.port=6379 -Dspring.redis.host=localhost
```