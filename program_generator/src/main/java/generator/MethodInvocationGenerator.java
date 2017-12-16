package generator;

import config.Configuration;

import java.util.HashMap;
import java.util.Random;

/**
 *  Class Used to generate a method invocation call
 */

public class MethodInvocationGenerator {
    ClassMethodGenerator classMethodGenerator;
    Configuration configuration;

     MethodInvocationGenerator(ClassMethodGenerator classMethodGenerator, Configuration configuration){
        this.classMethodGenerator = classMethodGenerator;
        this.configuration = configuration;
    }

    //Create a string with required byte and object types plus invoking the method that is passed
     String methodCall() {
        HashMap<String, String> parameterObjects = new HashMap<>();
        StringBuilder methodCallBuilder = new StringBuilder();

        /*In the method parameter if the type is an object or byte then
          create that data type identifier first and add that type and identifier name into the hash map
          to be used later in the invocation of method*/
        int objectCount=0;
        int byteCount=0;
        for(FieldGenerator field: classMethodGenerator.parameterList) {
            String type = field.type;
            if(type.equals("byte")){
                IdentifierGenerator identifierGenerator = new IdentifierGenerator();
                String byteIdentifier = identifierGenerator.getVariableName();
                methodCallBuilder.append("\t\tbyte "+ byteIdentifier +"=100;\n");
                parameterObjects.put("byte"+byteCount,byteIdentifier);
                byteCount++;
            }
            else if (type.equals("Object")) {
                IdentifierGenerator identifierGenerator = new IdentifierGenerator();
                String objIdentifier = identifierGenerator.getVariableName();
                methodCallBuilder.append("\t\tObject "+ objIdentifier +"= new Object();\n");
                parameterObjects.put("Object"+objectCount,objIdentifier);
                objectCount++;
            }
        }

        //Beginning building of the method invocation string
        methodCallBuilder.append("\t\t"+classMethodGenerator.methodName+"(");
        int maxIntValue = configuration.getIntMaxValue();
        StringBuilder parameterValues = new StringBuilder();
        Random rand = new Random();
        int i = 0;
        objectCount=0;
        byteCount=0;
        for (FieldGenerator field : classMethodGenerator.parameterList) {
            String type = field.type;
            switch (type) {
                case "short":
                    short randValue = (short) (rand.nextInt(Short.MAX_VALUE) + 1);
                    parameterValues.append("(short) "+randValue);
                    break;

                case "byte":
                    parameterValues.append(parameterObjects.get("byte"+byteCount));
                    byteCount++;
                    break;

                case "char":
                    char randChar = (char) (rand.nextInt(26) + 97);
                    parameterValues.append("'" + randChar + "'");
                    break;

                case "int":
                    int randInt = rand.nextInt(maxIntValue + 1);
                    parameterValues.append(randInt);
                    break;

                case "long":
                    long range = 1234567L;
                    long randLong = (long)(rand.nextDouble()*range);
                    parameterValues.append(randLong);
                    break;

                case "float":
                    float randFloat = rand.nextFloat();
                    parameterValues.append("(float) "+randFloat);
                    break;

                case "double":
                    double randDouble = rand.nextDouble();
                    parameterValues.append(randDouble);
                    break;

                case "String":
                    parameterValues.append("\"" + new IdentifierGenerator().getVariableName() + "\"");
                    break;

                case "Object":
                    parameterValues.append(parameterObjects.get("Object"+objectCount));
                    objectCount++;
                    break;
            }
            i++;
            if (i != classMethodGenerator.parameterList.size()) {
                parameterValues.append(",");
            }

        }

        methodCallBuilder.append(parameterValues.toString()+");\n");
        return methodCallBuilder.toString();
    }
}
