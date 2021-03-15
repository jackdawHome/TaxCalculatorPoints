# TaxCalculatorPoints

Command Line application for calculating taxes based on annual salary.

The application 
* prompts user for two parameters:
   * mandatory annual salary
   * optional tax year
* calculates and displays the total taxes owed for the salary.
* displays the amount of taxes owed per band.
* displays the effective rate  

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

The application uses an external server to get tax brackets for a specific year. The server could be found at https://github.com/Points/interview-test-server.
To run TaxCalculator from a command line java 11 should be installed.

Alternatively, you can use Docker images  provided in the Docker repository (see [Installing](#installing)) 

### Installing

Providing that java 11 is available on your computer and the server component (interview-test-server) is already installed, download the following jar file:
```
target/TaxCalculatorPoints-1.0-SNAPSHOT-jar-with-dependencies.jar
```

If using dockers, installation can be automated by downloading the docker-compose.yml file and running
```
docker-compose up -d
```

This will install both the server and the applicaton component images and run them in containers. To use the application it will be required to issue:
```
docker attach <application-container-name>
```

### Using the Application

```
java -jar /<your_folder>/TaxCalculatorPoints.jar
```
Here <your_folder> is the name of the folder where you downloaded the jar file.

If you are using docker images run the following command: 

```
java -jar /opt/app/TaxCalculatorPoints.jar
```
