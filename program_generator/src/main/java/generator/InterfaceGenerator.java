package generator;

import config.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
    InterfaceGenerator creates Interface and populates it with fields and method declarations
 */

public class InterfaceGenerator {
    private List<InterfaceGenerator> interfaceList;
    private int interfaceCount;
    Configuration configuration;
    public String interfaceName;
    List<MethodGenerator> methodList = new ArrayList<>();

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
        String myInterface = "public interface " + interfaceName;
        if (interfaceCount > 1) {
            int noOfInheritences = rand.nextInt(maxInheritenceDepth-minInheritenceDepth)+minInheritenceDepth;
            if (interfaceCount == 1)
                myInterface += " extends " + this.interfaceList.get(0).interfaceName;
            else if (noOfInheritences < interfaceCount) {
                myInterface += " extends ";
                for(int i=1; i <= noOfInheritences; i++) {
                    myInterface += this.interfaceList.get(interfaceList.size()-i).interfaceName;
                    if (i < noOfInheritences)
                        myInterface += ",";
                }
            }
        }
        myInterface += "{\n";;
        for(MethodGenerator method: methodList) {
           myInterface += method.generate();
        }
        myInterface+="}\n";
        return myInterface;
    }
}
