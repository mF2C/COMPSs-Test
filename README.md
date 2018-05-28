# Project Description
Test application for the COMPSs runtime. The application receives as a parameter an integer that indicates the number of tasks to execute on the infrastructure. In the example described in this README, the infrastructure is composed of 2 containers: one playing the role of the master and another one acting as the worker node. The master node receives the _StartApplication_ request, instruments the code of the application and runs it creating as many tasks as indicated in the parameter. These tasks are submitted to the worker node.

## Requirements

Have Docker and docker-compose installed in the system and a DataClay cluster up and running.

To deploy a DataClay testbed, clone the git repository and run the docker composition used for running DataClay demos.

  _git clone https://github.com/mF2C/DataClay.git_ 
  
  _cd DataClay/orchestration_ 
  
  _docker-compose down && docker-compose up && docker-compose down_ 

## Image Building (optional)
Although the application image is publicly available from the public Docker image registry and can be pulled from there, this repository contains the script and Dockerfile used for generating it as an example. Users can create such image locally by executing the _build\_image_ script located within the builder folder. However, it will still require Internet connection to pull the mf2c/compss-agent from the Docker registry if it is not cached yet.

  _git clone https://github.com/mF2C/COMPSs-Test.git_
  
  _./COMPSs-Test/builder/build\_image_

## COMPSs-Test Execution
### **Execution step 1: Deploying the infrastructure**
  Instantiate a Docker container that plays the role of worker
  
  _docker run --rm -it --network 'host' --env MF2C_HOST=127.0.0.2 --env PORT=46101 --name worker -w /tmp mf2c/compss-dataclay-test:latest_

  Instantiate a Docker container that plays the role of master. 
  
  _docker run --rm -it --network 'host' --env MF2C_HOST=127.0.0.1 --env PORT=46100 --name master -w /tmp mf2c/compss-dataclay-test:latest_

  WARNING: make sure that both, master and worker, IP addresses match the IP address passed as MF2C_HOST

### **Execution step 2: Run the application**

To execute COMPSs you have to send a HTTP PUT request to the _StartApplication_ method. The body of the request has to be in XML format. Please, make sure that the IP addresses of the resources described in the XML match the IP addresses of the containers.


```
curl -X PUT -H "Content-Type: application/xml" -d '<startApplication>
    <ceiClass>es.bsc.compss.test.TestItf</ceiClass>
    <className>es.bsc.compss.test.Test</className>
    <hasResult>false</hasResult>
    <methodName>wholeTest</methodName>
    <parameters>
        <params paramId="0">
            <direction>IN</direction>
            <type>INT_T</type>
            <element paramId="0">
                <className>java.lang.Integer</className>
                <value xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xs="http://www.w3.org/2001/XMLSchema" xsi:type="xs:int">3</value>
            </element>
        </params>
        <params paramId="1">
            <direction>IN</direction>
            <type>INT_T</type>
            <element paramId="0">
                <className>java.lang.Integer</className>
                <value xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xs="http://www.w3.org/2001/XMLSchema" xsi:type="xs:int">5</value>
            </element>
        </params>
    </parameters>
    <resources>
        <resource name="127.0.0.1:46100">
            <description>
                <memorySize>4.0</memorySize>
                <memoryType>[unassigned]</memoryType>
                <operatingSystemDistribution>[unassigned]</operatingSystemDistribution>
                <operatingSystemType>[unassigned]</operatingSystemType>
                <operatingSystemVersion>[unassigned]</operatingSystemVersion>
                <pricePerUnit>-1.0</pricePerUnit>
                <priceTimeUnit>-1</priceTimeUnit>
                <processors>
                    <architecture>[unassigned]</architecture>
                    <computingUnits>2</computingUnits>
                    <internalMemory>-1.0</internalMemory>
                    <name>[unassigned]</name>
                    <propName>[unassigned]</propName>
                    <propValue>[unassigned]</propValue>
                    <speed>-1.0</speed>
                    <type>CPU</type>
                </processors>
                <storageSize>-1.0</storageSize>
                <storageType>[unassigned]</storageType>
                <value>0.0</value>
                <wallClockLimit>-1</wallClockLimit>
            </description>
        </resource>
        <resource name="127.0.0.2:46101">
            <description>
                <memorySize>4.0</memorySize>
                <memoryType>[unassigned]</memoryType>
                <operatingSystemDistribution>[unassigned]</operatingSystemDistribution>
                <operatingSystemType>[unassigned]</operatingSystemType>
                <operatingSystemVersion>[unassigned]</operatingSystemVersion>
                <pricePerUnit>-1.0</pricePerUnit>
                <priceTimeUnit>-1</priceTimeUnit>
                <processors>
                    <architecture>[unassigned]</architecture>
                    <computingUnits>1</computingUnits>
                    <internalMemory>-1.0</internalMemory>
                    <name>[unassigned]</name>
                    <propName>[unassigned]</propName>
                    <propValue>[unassigned]</propValue>
                    <speed>-1.0</speed>
                    <type>CPU</type>
                </processors>
                <storageSize>-1.0</storageSize>
                <storageType>[unassigned]</storageType>
                <value>0.0</value>
                <wallClockLimit>-1</wallClockLimit>
            </description>
        </resource>
    </resources>
    <serviceInstanceId>c75a34da-3a1f-4d32-95de-6cc272e50a7f</serviceInstanceId>
</startApplication>' http://localhost:46100/COMPSs/startApplication
```

