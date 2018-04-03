# Worldpay Offer Code Screen

A RESTful service to create 'Offers'.

## Design

### Design Principles

1. [12 Factor application](https://12factor.net/)

### API



## Assumptions

1. Deployment packaging is out of scope, but the artifact must be deployable in a cloud environment

## Prerequisites

* Java 8

## Develop/Test/Build

The project builds using maven. Run the following to build and test the project:

```
./mvnw install
```

## Run

The project produces an executable jar file listening on port 8080 which can then be deployed as required (e.g. dockerized, pushed to cloud foundry, run stand-alone).