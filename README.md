Problem Statement: 

GEDCOM DATA PARSER
=======================

GEDCOM is the "Genealogical Data Communication" file format. It is a plain-text electronic format used to transfer genealogical data.The purpose of this challenge is to develop a simple parser that can convert a GEDCOM file to XML.

##Design and Approach
=======================
1.TestDriven development approach.Mockito used along with Junit for unit tests.
2.Input processor and Transformer are flexibly designed using the factory design pattern to support multiple processors and transformers at any given point of time. Multiple input porcessors and transformers can be added dynamically  based on different formats and factory class to support this. 
3. Tree data structure is used to convert gedcom data to xml format to support hirearichal data structure.
4.Designed for extensibility using OpenClosed Principle. We have implemented the tree data structure to parse the gedcom data. To further improve the performance,we can extend the code by adding a new strategy  which can be plugged in dynamically. 
4.Coding to interfaces for fliexiblity and Interface Seggregation Principle is applied. Internally all the concrete classes to parse the document and find the transformer and input processors are handled by interfaces.
5.Utility classes are provided to support methods for creating formatting the xml files.
6.User defined custom exception classes are written for handling exceptions during parsing.


### Prerequisites

* JAVA1.8( set java_home in path)
* [Maven](https://maven.apache.org/) - Dependency Management
* GIT

## Getting Started
=======================

##BUILD & UNIT TESTS INSTRUCTIONS
=======================
1.Clone the repository using the following url.

https://github.com/gudapatin/GedcomDataParser.git

2.Navigate to the cloned directory and run the maven command

mvn clean install

3.This will run all unit tests and build the jar into the target directory:

.../target/GedcomDataParser-0.0.1-SNAPSHOT.jar
	
TESTCASE EXECUTION :
	To run test cases a suite class com..gedcom.GedcomParserTestSuite is provided to run all test cases. 

##EXECUTION INSTRUCTIONS
=======================

1. You can execute this either as a jar or as a maven execute.

1. Method  One:(Executable jar)
	Step1: Go to the target directory after the build.
	Step2 : Giving both input and output file
	 java -jar GedcomDataParser-0.0.1-SNAPSHOT.jar <<input file>> <<outputfile>>
	Example:java -jar GedcomDataParser-0.0.1-SNAPSHOT.jar c:\acconexworkspace\GedcomDataParser\GedcomDataInput.txt c:\acconexworkspace\GedcomDataParser\output.xml
2. Method  Two:(Running maven execute)	
	Step1:To be executed from the source directory.
	Step2 : Giving both input and output file
	 java -jar GedcomDataParser-0.0.1-SNAPSHOT.jar <<input file>> <<outputfile>>
	Example:mvn exec:java -Dexec.mainClass -Dexec.args="c:\acconexworkspace\GedcomDataParser\GedcomDataInput.txt c:\acconexworkspace\GedcomDataParser\output.xml

##Assumptions and Known issues
=======================
1.Invalid data or format in the input file stops the processing of the application.
2.Duplicate data not handled.
3.Input file will be in text format.

