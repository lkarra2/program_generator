package generator;

import config.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
    InterfaceGenerator creates Interface and populates it with fields and method declarations
 */

public class InterfaceGenerator {
    private List<InterfaceGenerator> interfaceList;
    private int interfaceCount;
    Configuration configuration;
    public String interfaceName;
    List<MethodGenerator> methodList = new ArrayList<>();
    List<InterfaceGenerator> extendsInterface = new ArrayList<>();

    //Construtor that creates the interface
    public InterfaceGenerator(String interfaceName, Configuration configuration, int interfaceCount, List<InterfaceGenerator> interfaceList) {
        this.configuration=configuration;
        this.interfaceName=interfaceName;
        this.interfaceCount = interfaceCount;
        this.interfaceList = interfaceList;
        int maxMethods = configuration.getMaxNoOfMethodsPerInterface();
        Random rand = new Random();
        int noOfMethods = rand.nextInt(maxMethods) + 1;
        for(int i=0; i<noOfMethods; i++) {
            IdentifierGenerator vg = new IdentifierGenerator();
            String methodName = vg.getVariableName();
            MethodGenerator method = new MethodGenerator(methodName, configuration);
            methodList.add(method);
        }
    }

    //Method called to build the interface into a String -> myInterface
    public String generate() {
        int maxInheritenceDepth = configuration.getMaxInheritanceDepth();
        int minInheritenceDepth = configuration.getMinInheritanceDepth();
        Random rand = new Random();
        StringBuilder myInterface = new StringBuilder("public interface " + interfaceName);
        //Creating an extends relation for the interface
        if (interfaceCount > 1) {
            int noOfInheritences = rand.nextInt(maxInheritenceDepth-minInheritenceDepth)+minInheritenceDepth;
            if (interfaceCount == 1) {
                InterfaceGenerator interfaceGenerator = this.interfaceList.get(0);
                myInterface.append(" extends " + interfaceGenerator.interfaceName);
                extendsInterface.add(interfaceGenerator);
            }else if (noOfInheritences < interfaceCount) {
                myInterface.append(" extends ");
                for(int i=1; i <= noOfInheritences; i++) {
                    InterfaceGenerator interfaceGenerator = this.interfaceList.get(interfaceList.size()-i);
                    myInterface.append(interfaceGenerator.interfaceName);
                    extendsInterface.add(interfaceGenerator);
                    if (i < noOfInheritences)
                        myInterface.append(",");
                }
            }
        }
        myInterface.append("{\n");;
        for(MethodGenerator method: methodList) {
           myInterface.append(method.generate());
        }
        myInterface.append("}\n");
        return myInterface.toString();
    }
}
