package generator;

import config.Configuration;

/**
 * Builds method signatures for interfaces
 */

public class MethodGenerator {

    Configuration configuration;
    String methodName;

    public MethodGenerator(String methodName, Configuration configuration) {
        this.methodName=methodName;
        this.configuration=configuration;
    }

    //Creating method for interface
    public String generate() {
        String myMethod = "\tpublic void "+methodName+"();\n";
        return myMethod;
    }

}
