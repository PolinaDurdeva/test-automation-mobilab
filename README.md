## UI & API Automation

UI Automation project for MobiLab.

The project has two maven modules:
- api-test
- ui-test

Tools:

- Java 13
- Maven
- Selenide
- Rest Assured
- TestNG
- Docker, Docker-compose
- Selenoid
- Allure
- Log4j2

## How to run 

You can run tests in 3 different ways using:
- IDE
- maven (version 3.6.3)
- docker (version 19.03.8 or higher)

### I. Run using IDE 

1. Open the project in IDE
2. Resolve dependencies
3. Navigate to a suite you want to run:
    - ui-test/src/test/resources/suites/ui-suite.xml 
    - api-test/src/test/resources/suites/api-suite.xml
4. Click the right button and run selected suite
 
 ### II. Run using maven
 
1. Navigate to test-automation-mobilab directory 
2. Run both suites using this command:
 
 ```
 mvn -T 2 -fn clean test
```
 
#### Generate Allure report

Navigate to `ui-test` or `api-test` directory
```
cd ui-test 

mvn allure:serve
```  
A URL to the report will be printed out in terminal (sometimes a browser opens automatically)

### III. Run using docker

Prepare configurations:

* Go to `apitest.properties` and set 

`app.url=http://host.docker.internal:8080`

* Go to `uitest.properties` and set 

`app.url=http://host.docker.internal:3000`

`app.api.url=http://host.docker.internal:8080`

```
chmod a+x start.sh
```

```$xslt
./start.sh
```

```$xslt
docker-compose up
```

### Generate Allure report:

Generate report for **ui-test**: 
http://localhost:5050/allure-docker-service/generate-report?project_id=ui-test

See the latest report: 
http://localhost:5050/allure-docker-service/latest-report?project_id=ui-test


Generate report for **api-test**: 
http://localhost:5050/allure-docker-service/generate-report?project_id=api-test

See the latest report: 
http://localhost:5050/allure-docker-service/latest-report?project_id=api-test

________________   
 ** For more documentation check here
 https://github.com/fescobar/allure-docker-service
____________
* Finish tests
```$bash
    docker-compose down
```

### Optional configurations:

#### How to enable video

1. Pull image manually:
    ```bash
    docker pull selenoid/video-recorder:latest-release
    ```
2. Go to `uitest.properties` and change `selenoid.video = true`

3. Run your test in containers and check folder selenoid/video


#### How to enable VNC

Go to `uitest.properties` and change `selenoid.VNC = true`

* See your running test sessions (only if VNC is enabled)

    http://localhost:9090
    
* See browsers status 

    http://localhost:4444/status       