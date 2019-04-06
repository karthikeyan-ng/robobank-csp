# Rabo Bank Customer Statement Processor
This is a java web application that runs the Customer Statement Processor for Robo Bank. 

## About the Application
* User can upload a Customer Statement in csv or xml file formats.
* Application validates the information and display validation results. 

## Install & Running
 
### Prerequisites
* [Java 1.8](http://www.oracle.com/technetwork/java/javase/downloads/index.html)  - Programming language
* [Maven 3.5.0](https://maven.apache.org/download.cgi) - Build tool


### Pull from git 
```
$ git clone https://github.com/karthikeyan-ng/robobank-csp
$ cd robobank-csp
```

### Build & run 

* Run test
```
$ mvn test
```

* Run the web server on dev mode
```
$ mvn spring-boot:run
```

* Run the web server on prod mode
```
$ mvn spring-boot:run -Dspring-boot.run.profiles=prod
```

### API documentation
After running the project on dev environment and browse
[http://localhost:7070/swagger-ui.html](http://localhost:7070/swagger-ui.html)

## Built With
* [Spring boot 1.5.19](https://projects.spring.io/spring-boot/) - Backed Framework
* [Maven](https://maven.apache.org/) - Dependency Management

## Authors

* **Karthikeyan Nithiyanandam**

## License

This project is licensed under the MIT License

