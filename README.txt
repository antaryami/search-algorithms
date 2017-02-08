
What is it?
-------------------------------------------

This a node search api which finds the path in a given map using Astar search algorithm. It also writes the result to a output file with the highlighted path

The project is located under the src folder

Tools/Technologies Used
-------------------------------------------

JDK : 1.7
Junit : 4.9
IDE : Eclipse kepler
Maven : 3.0.4. You can use any version above 3.0.0 to build and test this project

How to use?
--------------------------------------------

step - 1 : Go to the directory of the application that contains pom.xml, it's located under src/node-search-api
step - 2 : To test the application before package it use below commands
		   mvn clean test
		   or
		   mvn test
		   It will generate a result_map.txt file in the same folder where the pom file is located
		   If you wish to test the small_map.txt the please uncomment the necessary code block in NodeSerachTest.java and run the above commands.
		   A sample result for small map also provided in the document section
step - 3 : To package the application 
		   mvn clean install
		   or
		   mvn clean install
		   Both the commands will run the test by default, to skip the test use below command
		   mvn clean install -Dmaven.test.skip=true 
		   				