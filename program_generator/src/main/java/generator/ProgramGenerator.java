package generator;

import config.Configuration;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Contains implementation to create interfaces and classes
 */

public class ProgramGenerator {
    Configuration configuration;
    List<InterfaceGenerator> interfaceList = new ArrayList<>();
    List<ClassGenerator> classList = new ArrayList<>();
    private int classCount = 0;
    private int interfaceCount = 0;

    public ProgramGenerator() {
        configuration = new Configuration();
    }

    //Creates and writes Interfaces into .java file in directory ./generatedFiles/*.java
    public void generateInterfaces() throws FileNotFoundException {
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
    public void generateClasses() throws FileNotFoundException {
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
}
