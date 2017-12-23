# Course project Random Program Generator
### git@bitbucket.org:jchou4/unaiza_faiz_lakshmimanaswi_karra_james_chou_course_project.git
### Description: To create a random Java program generator
### Grade: 25%

## Group Members
### James Chou - jchou4@uic.edu
### Lakshmi Manaswi Karra - lkarra2@uic.edu
### Unaiza Faiz - ufaiz2@uic.edu

# Steps to Run the project
1. Clone the project at git@bitbucket.org:jchou4/unaiza_faiz_lakshmimanaswi_karra_james_chou_course_project.git
2. Open the program_generator folder as an existing project in IntelliJ (or a similar IDE)
3. Set up JDK to 1.8 and SDK to Java 8 - (Lambda, type notations etc)
4. Compile and Run main.Main OR you may also build gradle and then run main.Main
5. Run the shell script javacompile.sh (Make sure javacompile.sh has executable permission)

## The Output
The randomly generated code can be found in the folder: ./generatedFiles/
Once the shell script "javacompile.sh" is run, the .class files can also be found in this same folder.

# Code Structure
```src
|------ main
|------ java
|--------- config
|--------------- Configuration.java
|--------- generator
|--------------- ClassGenerator.java
|--------------- ClassMethodGenerator.java
|--------------- ConditionalLoopGenerator.java
|--------------- ExpressionGenerator.java
|--------------- FieldGenerator.java
|--------------- IdentifierGenerator.java
|--------------- InterfaceGenerator.java
|--------------- MethodGenerator.java
|--------------- MethodInvocationGenerator.java
|--------------- ProgramGenerator.java
|--------------- AccessModifierGenerator.java
|--------- main
|--------------- Main.java
|--------- productionrules
|--------------- ProductionRules.java
```
## Package config
- The config package extracts data from the given configuration file "./generator.xml"
- It contains getters() for each and every value that is provided in the configuration file. This will help us test the boundaries and limitations
of the randomly generated code.

## Package generator
- The generator package consists of all the programming construct generators.
- The function of each is described in detail below:

1. ProgramGenerator
Generates all the classes and interfaces for the program.

2. ClassGenerator
This class creates a randomly generated Class and populates it with fields and methods.
It contains methods of interfaces which it also implements.
Depending on specified probability, it overrides the superclass methods.

3. ClassMethodGenerator
This class generates method implementation for all the class' methods.

4. InterfaceGenerator
This class creates a randomly generated Interface and populates it with fields and method declarations.

5. MethodGenerator
This class generates methods including thier definition.

6. MethodInvocationGenerator
This class invokes methods that have been created previously.

7. FieldGenerator
This class creates a field declaration.

8. ConditionalLoopGenerator
This class generates conditional loop statements: FOR loop, WHILE loop, and Nested if

9. ExpressionGenerator
Generates expressions based on production rule set in package productionrules.

10. IdentifierGenerator
This class creates and returns a valid identifier.

11. AccessModifierGenerator
This class returns an access modifier randomly.

## Package main
Contains the main class from where the program is run.

## Package productionrules
Implements the following production rules:
<expression> ::= <expression> + <expression>    //production rule 1
<expression> ::= <expression> - <expression>    //production rule 2
<expression> ::= <expression> * <expression>    //production rule 3
<expression> ::= ( <expression> )                //production rule 4
<expression> ::= <number> | <var>                //production rule 5
<number>        ::= 0 | [1-9][0-9]*                //production rule 6
<var>        ::= [_a-zA-Z][_a-zA-Z0-9]{1,255} //production rule 7


## Object Oriented Constructs
In this project, the randomly generated program implements various Object Oriented Constructs. Below is an overview of each construct
and and how it has been implemented.

## Inheritance, Overriding and Hiding JLS 8.4.8
In the current implementation of our project, the following specifications are being used from JLS 8.4
1. Each Interface randomly extends other interfaces
2. Each class randomly extends other classes and implements 0 or more interfaces

All the inheritance is random and can be specified in the configuration file. Some of the specified variables are:
1. max and min interfaces to implement
2. number of Inheritance chains
3. max and min inheritance depth

### Overriding
In the subclass, the public and protected methods of the superclass are over-ridden.

## Overloading JLS 8.4.9
Methods inherited by classes are being over-loaded with a specified probability. (Currently 0.25, can be changed in the configuration file)

## Recursion
We have implemented recursion with a probability specified in the configuration file.

## Access Modifiers
Each method is defined with an access modifier - public, private or protected or default.

## Constructor Body JLS 8.8.7
Within the construtor of a subclass, super() has been implemented with a probabilty of 0.5 for the time being.

## Field declarations JLS 8.3
Each Class and Interface has randomly generated field declarations, limit is specified by minNoOfClassFields and maxNoOfClassFields,
specified in the configuration file.

## Field Modifiers JLS 8.3.1
Currently, we are implementing:
1. public, private and protected - where each field gets a randomly generated modifier and the probability of each is 1/3.
2. static and non static - where probability is 0.5

## Method Declaration JLS 8.4
The class MethodGenerator creates a method including method header and definition. The class consists of the following:
1. Public, Private and Protected
2. Return type
3. Parameter list
4. Local Variables
5. Conditional Loops
6. Conditional statement (Nested If)
7. Return statement
### Each of these are randomly generated, with the max and min values specified in the configuration (generator.xml)

### Untraceable errors that occurred:
1. When a method over-loading occurs and the method is being called, in an odd case, the compiler could not distinguish between
methodName(int, int) and methodName(byte, long)

