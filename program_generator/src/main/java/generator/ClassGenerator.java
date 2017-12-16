package generator;

import config.Configuration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

/**
    ClassGenerator creates Class and populates it with fields and methods
    Also contains methods of interface that it implements
    And depending on probability overrides the superclass methods
 */

public class ClassGenerator {
    private List<ClassGenerator> classList;
    Configuration configuration;
    public String className;
    List<ClassMethodGenerator> methodList = new ArrayList<>();
    List<FieldGenerator> fieldList = new ArrayList<>();
    List<FieldGenerator> arrayList = new ArrayList<>();
    private int classCount;
    private int interfaceCount;
    private List<InterfaceGenerator> interfaceList;
    List<InterfaceGenerator> implementsInterface = new ArrayList<>();
    List<InterfaceGenerator> interfaceHierarchy = new ArrayList<>();
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

        //Start Building the class - getting signature of the class
        StringBuilder classContent = new StringBuilder();
        classContent.append(getClassSignature());

        //generating fields;
        for(FieldGenerator field: fieldList){
            classContent.append("\t"+field.generate("field")+";\n");
        }
        for(FieldGenerator field: arrayList) {
            classContent.append("\t" + field.generateArray() + ";\n");
        }

        //finding the hierarchy of interfaces whose methods the class needs to implement
        if(!implementsInterface.isEmpty()) {
            for(InterfaceGenerator interfaceGenerator: implementsInterface){
                interfaceHierarchy.add(interfaceGenerator);
                getInterfaceImplementList(interfaceGenerator);
            }
        }

       //Get constructor for the class
        int methodIndex = 0;
        classContent.append(methodList.get(0).generateConstructor());
        methodList.remove(methodList.get(0));

        //Get method bodies
        for(ClassMethodGenerator method: methodList) {
            if(methodIndex==methodList.size()-1 && isOverriding){
                classContent.append("\t\tpublic "+method.returnType+" "+method.methodName+"("+method.methodsParameters+"){\n");
                classContent.append("\t\t\t\t/* This is an Overriding Method */\n");
                if(!method.returnType.equals("void")){
                    String initialiseValue  = new FieldGenerator(method.returnVariable,configuration).initialiseVariable(method.returnType);
                    classContent.append("\t\t\t\t"+method.returnType+" "+method.returnVariable+"="+initialiseValue+";\n");
                    classContent.append("\t\t\t\treturn"+" "+method.returnVariable+";\n");
                }
                classContent.append("\t\t}\n");
            }
            else {
                classContent.append(method.generate(methodList));
            }
            methodIndex++;
        }

        //Implementing interface methods
        for(InterfaceGenerator interfaceGenerator: interfaceHierarchy) {
            for (MethodGenerator method : interfaceGenerator.methodList) {
                ClassMethodGenerator classMethodGenerator = new ClassMethodGenerator(method.methodName,configuration,true);
                classContent.append(classMethodGenerator.generate(methodList));
            }
        }
        classContent.append("}\n");
        return classContent.toString();
    }

    private String getClassSignature() {
        StringBuilder classContent = new StringBuilder();
        classContent.append("public class " + className);
        Random rand = new Random();
        int inheritanceCheck = rand.nextInt(3);
        if (classCount > 1 && interfaceCount > 1) {
            if (inheritanceCheck == 0) {
                classContent.append(" extends " + classList.get(classCount - 2).className);
                //Get a random method present in super class such that the subclass can override the method
                getMethodFromSuperclass();
            }else if (inheritanceCheck == 1) {
                classContent.append(" implements " + interfaceList.get(interfaceCount - 2).interfaceName);
                implementsInterface.add(interfaceList.get(interfaceCount - 2));
            } else {
                classContent.append(" extends " + classList.get(classCount -2).className + " implements " + interfaceList.get(interfaceCount-2).interfaceName);
                implementsInterface.add(interfaceList.get(interfaceCount - 2));
                //Get a random method present in super class such that the subclass can override the method
                getMethodFromSuperclass();
            }
        }
        classContent.append("{\n");
        return classContent.toString();
    }

    private void getMethodFromSuperclass() {
        int index = classCount-2;
        //overridingMethod = classList.get(index).methodList.get(new Random().nextInt(classList.get(index).methodList.size()));
        for(ClassMethodGenerator method: classList.get(index).methodList){
            if(!method.accessmodifier.equals("private")){
                overridingMethod = method;
                methodList.add(overridingMethod);
                isOverriding = true;
                break;
            }
        }
    }

    private void getInterfaceImplementList(InterfaceGenerator interfaceGenerator) {
        if(interfaceGenerator.extendsInterface==null){
            return;
        } else {
            for (InterfaceGenerator extendsInterface : interfaceGenerator.extendsInterface) {
                if (!interfaceHierarchy.contains(extendsInterface)) {
                    interfaceHierarchy.add(extendsInterface);
                    getInterfaceImplementList(extendsInterface);
               }
            }
        }
    }
}
