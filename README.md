# Hyperledger Fabric Java Reference Implementation

This is a reference implentation of Java based Chaincode and Java SDK to query the chaincode. This can be used for extending Hyperledger Fabric for Java based web development using Spring Boot. It work with version 1.4.4 of Hyperledger Fabric. This is based on Fabcar implementation from fabric-samples. There was a need for a proper reference implementation of java based chaincode development environment, hence this project. There is a definite cost benefit of Java support for Hyperledger Fabric in Enterprise.

Just to give some background, Hyperledger Fabric is GOLANG based and most of the chaincode examples works out of the box with GOLANG, some of them also works with Node and Javascript. But this is no proper reference of completely Java based Chaincode development environment and this project addresses that. 

I feel that Hyperledger Fabric and Java is a perfect recipe for enterprise Blockchain development and yet there is so less material around this, and I wanted to fulfil this.

If you are in a hurry you can run `sh testJavaFabric.sh`, it will deploy the chaincode into hyperledger ecosystem and run the `mvn test` to run the java sdk test to communicate with java chaincode. Details is as below.

Reference implementation is based on [fabric-samples](https://github.com/hyperledger/fabric-samples), below is the steps to setup java development environment.

* `git clone https://github.com/skprasadu/hyperledger-fabric-blockchain-java-impl.git` to your machine
* `git clone https://github.com/hyperledger/fabric-samples` to your machine. This has been tested with Mac or Ubuntu 16.04 
* go to ./chaincode folder under fabric-samples folder and create a new folder called java-chaincode under that create a folder called Java
* copy the content of java-chaincode folder from hyperledger-fabric-blockchain-java-impl from step 1 to Java folder above 
* go back to fabric-samples folder and create a new folder called java-chaincode folder and copy the startFabric.sh file into the root of java-chaincode 
* copy the java-server folder from hyperledger-fabric-blockchain-java-impl to java-chaincode above
* now run the command `./startFabric.sh java` under java-chaincode folder, it will take 5mins to setup the hyperlegder fabric
* once it is done, go to java-server and run `mvn test` it will do the following:
  - query a list of cars
  - insert a new car
  - change owner for the car

Next steps is to setup a Spring Boot based web application with React to insert new car, change ownership and list all cars.
