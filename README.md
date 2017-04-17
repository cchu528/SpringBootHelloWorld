# SpringBootHelloWorld
This project contains code regarding how to use Spring Boot and Spring Data to build services and REST APIs.

RunningInformationAnalysisService

-- Port 9002

-- Supports bulk runningInformations upload.

   Example: Once running-information-analysis-service is up and ready for service, go to the directory running-information-analysis-service, type
   
      "./upload-runningInformation.sh"
      
   to upload runningInformation records in the runningInformations.json.
   
-- Supports retrieve all running information records.

    http://localhost:9002/runningInformations

-- Supports delete runningInformation record by runningId. 

    http://localhost:9002/runningInformations/runningId/{runningId}method: Http delete

-- Supports retrieve runningInformation record by runningId. 

    http://localhost:9002/runningInformations/runningId/{runningId}Method: Http Get

-- Supports to retreve runningInfomration reocrd by HealthWarnningLevel.

    http://localhost:9002/runningInformations/healthWarningLevel/{HealthWarnningLevel} 
    
    where HealthWarningLevel is HIGH, NORMAL, or LOW.



How to run:

-- Launch MySQL docker: 

   Go to the directory running-information-analysis-service, type 
       "docker-compose up -d".

-- Connect to MySQL server in the docker container.

   You will need to download MySQL client to connect MySQL server in the docker container.

      Linux 
        sudo apt-get install mysql-client 
        
      Mac
        1. Download binary from https://dev.mysql.com/downloads/mysql/ 
        2. Install 
        3. Export mysql to PATH export PATH=$PATH:/usr/local/mysql/bin

   Go to the directory running-information-analysis-service, type "./connect_mysql.sh" to connect to MySQL server

   Once you connect to MySQL server console, type
   
        "show databases;"
        
   to check whether a database running_information_analysis_db exits or not. If it doesn't exist, type
   
        "create database running_information_analysis_db;"
        
   to create the database. Then to exit the Mysql server sonsole, type
   
        "\q".

-- Build Application .jar file

   Go to the directory running-information-analysis-service, type
   
        "mvn clean install"
        
   to build application .jar file.
   
-- Launch Spring Application

   Go to target directory and type
   
        "java -jar running-information-analysis-service-1.0.0.BUILD-SNAPSHOT.jar"
        
   on the command line.
