package generator;

import config.Configuration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
    ClassGenerator creates Class and populates it with fields and methods
 */

public class ClassGenerator {
    private List<ClassGenerator> classList;
    Configuration configuration;
    public String className;
    List<ClassMethodGenerator> methodList = new ArrayList<>();
    List<FieldGenerator> fieldList = new ArrayList<>();
    List<FieldGenerator> arrayList = new ArrayList<>();
    private int classCount;
    private List<InterfaceGenerator> interfaceList;
    private int interfaceCount;
    List<InterfaceGenerator> implementsInterface = new ArrayList<>();
    private boolean isOverriding = false;
    ClassMethodGenerator overridingMethod;

    //Constructor that creates the class
    public ClassGenerator(String className, Configuration configuration) {
        this.configuration=configuration;
        this.className = className;

    }

    //Method called to build the class into a String -> classContent
    public String generate(int classCount, List<ClassGenerator> classList, List<InterfaceGenerator> interfaceList, int interfaceCount) {
        Random rand = new Random();
        this.classCount = classCount;
        this.classList = classList;
        this.interfaceList = interfaceList;
        this.interfaceCount = interfaceCount;
        int inheritanceCheck = rand.nextInt(3);
        String classPrefix = configuration.getClassNamePrefix();
        className = classPrefix + className;
        //Creating field list
        int minNoOfFields = configuration.getMinNoOfClassFields();
        int maxNoOfFields = configuration.getMaxNoOfClassFields();
        int noOfFields = rand.nextInt(maxNoOfFields - minNoOfFields) + minNoOfFields;
        for(int i=0; i<noOfFields; i++){
            IdentifierGenerator fieldNameGenerator = new IdentifierGenerator();
            String fieldName = fieldNameGenerator.getVariableName();
            FieldGenerator field  = new FieldGenerator(fieldName, configuration);
            fieldList.add(field);
        }
        //Creating ArrayList
        int maxNoOfArrays = configuration.getMaxNoOfArrays();
        for (int i = 0; i < maxNoOfArrays; i++) {
            IdentifierGenerator arrayNameGenerator = new IdentifierGenerator();
            String arrayName = arrayNameGenerator.getVariableName();
            FieldGenerator field  = new FieldGenerator(arrayName, configuration);
            arrayList.add(field);
        }
        //Creating constructor for the class
        ClassMethodGenerator constructor = new ClassMethodGenerator(className, configuration,false);
        methodList.add(constructor);
        //Creating method list
        int maxMethods = configuration.getMaxNoOfMethodsPerClass();
        int noOfMethods = rand.nextInt(maxMethods) + 1;
        for(int i = 0; i < noOfMethods;i++) {
            IdentifierGenerator vg = new IdentifierGenerator();
            String methodName = vg.getVariableName();
            ClassMethodGenerator method = new ClassMethodGenerator(methodName, configuration, false);
            methodList.add(method);
            //Polymorphism - Generating methods that are overloading an existing method
            float overloadingProbability = configuration.getOverloadingProbability();
            if(rand.nextDouble()<=overloadingProbability ){
                ClassMethodGenerator classMethodGenerator = new ClassMethodGenerator(method.methodName, configuration, false);
                methodList.add(classMethodGenerator);
                i++;
            }
        }

        String classContent = "public class " + className;
        if (classCount > 1 && interfaceCount > 1) {
            if (inheritanceCheck == 0) {
                classContent += " extends " + this.classList.get(this.classCount - 2).className;
                //Get a random method present in super class such that the subclass can override the method
                overridingMethod = this.classList.get(this.classCount - 2).methodList.get(rand.nextInt(this.classList.get(this.classCount - 2).methodList.size()));
                methodList.add(overridingMethod);
                isOverriding = true;
            }else if (inheritanceCheck == 1) {
                classContent += " implements " + this.interfaceList.get(this.interfaceCount - 2).interfaceName;
                implementsInterface.add(this.interfaceList.get(this.interfaceCount - 2));
            } else {
                classContent += " extends " + this.classList.get(this.classCount -2).className + " implements " + this.interfaceList.get(this.interfaceCount-2).interfaceName;
                implementsInterface.add(this.interfaceList.get(this.interfaceCount - 2));
                //Get a random method present in super class such that the subclass can override the method
                 overridingMethod = this.classList.get(this.classCount - 2).methodList.get(rand.nextInt(this.classList.get(this.classCount - 2).methodList.size()));
                isOverriding = true;
            }
        }
        classContent += "{\n";
        //generating fields;
        for(FieldGenerator field: fieldList){
            classContent += "\t"+field.generate()+";\n";
        }
        for(FieldGenerator field: arrayList) {
            classContent += "\t" + field.generateArray() + ";\n";
        }
        //adding interface methods to the method list
        int interfaceMethodIndex=0;
        if(!implementsInterface.isEmpty()) {
            interfaceMethodIndex = methodList.size();
            for (InterfaceGenerator interfaceGenerator : implementsInterface) {
                for (MethodGenerator methodGenerator : interfaceGenerator.methodList) {
                    ClassMethodGenerator classMethodGenerator = new ClassMethodGenerator(methodGenerator.methodName, configuration,true);
                    methodList.add(classMethodGenerator);
                }
            }
            if(isOverriding){
                methodList.add(overridingMethod);
            }
        }
        classContent += "\n";
       // Boolean isClassConstructor = true;
        int methodIndex = 0;
        classContent +=methodList.get(0).generateConstructor();
        methodList.remove(methodList.get(0));
        for(ClassMethodGenerator method: methodList) {
            if(methodIndex==methodList.size()-1 && isOverriding){
                classContent += "\t\tpublic void "+method.methodName+"("+method.methodsParameters+"){\n\t\t//Overriding method\n\t\t}\n";
            }
            else if (methodIndex<interfaceMethodIndex){
                classContent += method.generate(methodList);
            } else {
                classContent += method.generate( methodList);
            }
            methodIndex++;
        }
        classContent+="}\n";
        return classContent;
    }

    public static void main(String[] args) {
        ClassGenerator classGenerator = new ClassGenerator("newClass", new Configuration());
        System.out.println(classGenerator.generate(1,null,null,0));
    }
}
