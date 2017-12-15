package generator;

import config.Configuration;
import java.util.*;

public class ClassMethodGenerator {
    Configuration configuration;
    String methodName;
    Random rand  = new Random();
    List<FieldGenerator> parameterList = new ArrayList<>();
    List<ClassMethodGenerator> methodList = new ArrayList<>();
    HashSet<Integer> methodCallIndex= new HashSet<>();
    String methodsParameters = "";

    public ClassMethodGenerator(String methodName, Configuration configuration, boolean isInterfaceMethod) {
        this.methodName=methodName;
        this.configuration=configuration;
        if(!isInterfaceMethod){
            methodsParameters = parameterGenerator();
        }
    }

    //Generating constructor for the class
    String generateConstructor(){
        String myConstructor = "\tpublic "+methodName+"(){\n\t}\n\n";
        return myConstructor;

    }

    //Generating method for the class
     String generate( List<ClassMethodGenerator> methodList) {
        this.methodList=methodList;
        StringBuilder myMethod = new StringBuilder("\tpublic void "+methodName+"("+methodsParameters+") {\n");
        /*ExpressionGenerator eg = new ExpressionGenerator();
        LinkedList exp = eg.generateExpression();
        for(int i = 0; i < exp.size(); i++){
            myMethod+=exp.get(i);
        }
        myMethod+=";\n";
        */

        ExpressionGenerator eg = new ExpressionGenerator();
        String expression = eg.generateExpression();
         myMethod.append(expression);
         generateMethodBody(myMethod);



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
            parameterDeclarations.append(parameterList.get(i).generate()+",");
            i++;
        }
        parameterDeclarations.append(parameterList.get(i).generate());

        return parameterDeclarations.toString();
    }

    private String getConditionalLoop(){
        String condLoop = "";
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

/*   public static void main(String[] args) {
        ClassMethodGenerator classMethodGenerator = new ClassMethodGenerator("myRecursiveMethod",new Configuration());
        System.out.println(classMethodGenerator.generate(false,));
    }*/

 }
