# itcluster-java9

## Project description
**Project**:Monitoring of the existing forest zone in the Frankivsk region
**Goal**: identify and display (on the graph) the historical change in green space.

**Tasks**
* user management (admin, moderators, subscribers)
* history' analytics (tables)
* API to upload data (REST (json), csv)
* Fixture data generator (refresh data on daily basis)
* telegram chat channel/bot for subscribers (latest changes - i.e. warning: missed 10ha in Tysmenytsia Raion)

[![Build Status]()]()
[![Sonar Gate]()]()

## Requirements

 * Java 11

## Building Instructions

 * `./mvnw package` -- build executable jar

## Running Tests
 
 * `./mvnw test` -- all unit tests only
 * `./mvnw test -Dtest=ExampleTest` -- single unit test
 
## Running App

 * `sh target/bin/webapp`
 or
 * launch `ua.if.itcluster.Launcher` (entry point)
 * add `mvn package` | `mvn clean package` command into launcher
 * add to VM options `-Djava.util.logging.manager=org.apache.logging.log4j.jul.LogManager` for logging tomcat server

## Deploy to Heroku

* `mvn heroku:deploy` -- deploy to your app (host)