package generator;

import config.Configuration;
import java.util.*;

/**
 * Generates method implementation for a class' methods.
 */

public class ClassMethodGenerator {
    Configuration configuration;
    String methodName;
    Random rand  = new Random();
    List<FieldGenerator> parameterList = new ArrayList<>();
    List<ClassMethodGenerator> methodList = new ArrayList<>();
    HashSet<Integer> methodCallIndex= new HashSet<>();
    String methodsParameters = "";
    String accessmodifier = "public";
    String returnType = "void";
    String returnVariable = "";
    boolean isInterfaceMethod;

    public ClassMethodGenerator(String methodName, Configuration configuration, boolean isInterfaceMethod) {
        this.methodName=methodName;
        this.configuration=configuration;
        if(!isInterfaceMethod){
            methodsParameters = parameterGenerator();
        }
        this.isInterfaceMethod = isInterfaceMethod;
    }

    //Generating constructor for the class
    String generateConstructor(){
        String myConstructor = "\tpublic "+methodName+"(){\n\t}\n\n";
        return myConstructor;

    }

    //Generating method for the class
     String generate( List<ClassMethodGenerator> methodList) {
        this.methodList=methodList;
         FieldGenerator returnField;
         String returnFieldString="";

        if(!isInterfaceMethod) {
            accessmodifier = new AccessModifierGenerator().getAccessModifier();
            float returnTypeProbability = configuration.getReturnTypeProbability();
            if(rand.nextDouble() <= returnTypeProbability){
                returnField  = new FieldGenerator(new IdentifierGenerator().getVariableName(), configuration);
                returnFieldString = returnField.generate("return variable");
                returnType = returnField.type;
                returnVariable = returnField.name;
            }
        }
        StringBuilder myMethod = new StringBuilder("\t"+accessmodifier+" "+returnType+" "+methodName+"("+methodsParameters+") {\n");

        //Get a random expressions
        ExpressionGenerator eg = new ExpressionGenerator();
        String expression = eg.generateExpression();
        myMethod.append(expression);

         if(!returnType.equals("void")){
             myMethod.append("\t\t"+returnFieldString+"="+(new FieldGenerator(returnVariable,configuration).initialiseVariable(returnType)+";\n"));
         }

        //Get method body
        generateMethodBody(myMethod);

        //Append return variable if type is not void
         if(!returnType.equals("void")){
             myMethod.append("\t\treturn "+returnVariable+";\n");
         }
        myMethod.append("\t}\n\n");

        return myMethod.toString();
    }

    private void generateMethodBody(StringBuilder myMethod) {
        //Create recursive method invocation of this method
        float recursionProbability = configuration.getRecursionProbability();
        if( new Random().nextDouble() <= recursionProbability ) {
             MethodInvocationGenerator invokeMethod = new MethodInvocationGenerator(this, configuration);
             myMethod.append(invokeMethod.methodCall());
         }

        //Invoke other methods from this method
        int maxMethodCall = configuration.getMaxAllowedMethodCalls();
        int noOfMethodCalls=0;
        if(methodList!=null) {
            noOfMethodCalls = rand.nextInt(maxMethodCall < methodList.size() ? maxMethodCall : methodList.size());
            String isIndirectRecursion = configuration.getAllowIndirectRecursion();
            for (int i = 0; i < noOfMethodCalls; i++) {
                if (isIndirectRecursion.equals("no")) {
                    //myMethod.append(invokeMethod.methodCall());
                } else {
                    int randIndex = rand.nextInt(methodList.size());
                    String methodCallName = methodList.get(randIndex).methodName;
                    if (!methodCallName.equals(this.methodName) && methodCallIndex.add(randIndex)) {
                        // methodCallIndex.add(randIndex);
                        MethodInvocationGenerator invokeMethod = new MethodInvocationGenerator(methodList.get(randIndex), configuration);
                        myMethod.append(invokeMethod.methodCall());
                    }
                }
            }
        }

        //Getting conditional statements
        for(int i=0; i<rand.nextInt(3); i++) {
           myMethod.append(getConditionalLoop());
        }

    }


    private String parameterGenerator() {
        StringBuilder parameterDeclarations = new StringBuilder();
        int minNoOfParameters = configuration.getMinNoOfParametersPerMethod();
        int maxNoOfParameters = configuration.getMaxNoOfParametersPerMethod();
        int noOfParameters = rand.nextInt(maxNoOfParameters-minNoOfParameters)+minNoOfParameters;
        for(int i=0; i<noOfParameters; i++){
            FieldGenerator fieldGenerator = new FieldGenerator(new IdentifierGenerator().getVariableName(), configuration);
            parameterList.add(fieldGenerator);
        }
        int i=0;
        while(i<parameterList.size()-1){
            parameterDeclarations.append(parameterList.get(i).generate("parameter")+",");
            i++;
        }
        parameterDeclarations.append(parameterList.get(i).generate("parameter"));

        return parameterDeclarations.toString();
    }

    private String getConditionalLoop(){
        String condLoop ="";
        ConditionalLoopGenerator conditionalLoopGenerator = new ConditionalLoopGenerator(configuration);
        int randomType = rand.nextInt(3);
        if(randomType==0){ //For loop
            condLoop=conditionalLoopGenerator.generateForLoop();
        }
        else if(randomType==1){ //While loop
            condLoop=conditionalLoopGenerator.generateWhileLoop();
        } else {
            condLoop = conditionalLoopGenerator.generateIfStatements();
        }
        return condLoop;
    }
 }
