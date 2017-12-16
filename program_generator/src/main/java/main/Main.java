package main;

import config.Configuration;
import generator.ClassGenerator;
import generator.InterfaceGenerator;
import generator.IdentifierGenerator;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/*
    Main Class to be run.
    Consists of generateClasses() and generateInterfaces() functions
 */

public class Main {

    Configuration configuration;
    List<InterfaceGenerator> interfaceList = new ArrayList<>();
    List<ClassGenerator> classList = new ArrayList<>();
    private int classCount = 0;
    private int interfaceCount = 0;

    public Main() {
         configuration = new Configuration();
    }

    //Creates and writes Interfaces into .java file in directory ./generatedFiles/*.java
    private void generateInterfaces() throws FileNotFoundException {
        Random rand = new Random();
        int maxInterfaces = configuration.getMaxInterfacesToImplement();
        int minInterfaces = configuration.getMinInterfacesToImplement();
        int noOfInterfaces = rand.nextInt(maxInterfaces-minInterfaces)+minInterfaces;
        for(int i=0;i<noOfInterfaces;i++) {
            IdentifierGenerator identifierGenerator = new IdentifierGenerator();
            String interfaceName = identifierGenerator.getClassOrInterfaceName();
            interfaceCount++;
            InterfaceGenerator myInterface = new InterfaceGenerator(interfaceName, configuration, interfaceCount, interfaceList);
            String generatedInterface = myInterface.generate();
            PrintStream out = new PrintStream(new FileOutputStream("./generatedFiles/" + interfaceName + ".java"));
            System.setOut(out);
            System.out.println(generatedInterface);
            interfaceList.add(myInterface);
        }
/*        for(InterfaceGenerator myinterface: interfaceList)
            System.out.println(myinterface.interfaceName);*/
    }

    //Creates and writes Classes into .java file in directory ./generatedFiles/*.java
    private void generateClasses() throws FileNotFoundException {
        int noOfClasses = configuration.getNoOfClasses();
        for(int i = 0; i < noOfClasses; i++) {
            String classPrefix = configuration.getClassNamePrefix();
            IdentifierGenerator vg = new IdentifierGenerator();
            String className = vg.getClassOrInterfaceName();
            classCount++;
            ClassGenerator myClass = new ClassGenerator(className, configuration);
            String generatedClass = myClass.generate(classCount, classList, interfaceList, interfaceCount);
            PrintStream out = new PrintStream(new FileOutputStream("./generatedFiles/" + classPrefix + className + ".java"));
            System.setOut(out);
            System.out.println(generatedClass);
            classList.add(myClass);
        }

    }

    //Controls flow of Program Generator
    public static void main(String[] args) throws IOException, InterruptedException {
        Main myMain = new Main();
        new File("./generatedFiles").mkdir();
        myMain.generateInterfaces();
        myMain.generateClasses();

        ProcessBuilder pb = new ProcessBuilder("ls", "./generatedFiles/");
        Process process = pb.start();
        process.waitFor(100, TimeUnit.SECONDS);
    }
}
