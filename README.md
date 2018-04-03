# Worldpay Offer Code Screen

A RESTful service to create 'Offers'.

## Design

### Design Principles

1. [12 Factor application](https://12factor.net/)

### API


### Further work

1. Add logging
1. Use an immutable object framework such as Immutables to provide good equals, hashCode and toString implementations
1. Add source verification e.g. Checkstyle/PMD
1. use something other than DatatypeConverter in IdGenerator in preparation for Java 9 modules

## Assumptions

1. Deployment packaging is out of scope, but the artifact must be deployable in a cloud environment
1. An offer is a unique combination of description, price and currency (such that an MD5 hash of these fields will produce a unique identifier)
1. Timezone support not required

## Prerequisites

* Java 8

## Develop/Test/Build

The project builds using maven. Run the following to build and test the project:

```
./mvnw install
```

## Run

The project produces an executable jar file listening on port 8080 which can then be deployed as required (e.g. dockerized, pushed to cloud foundry, run stand-alone).