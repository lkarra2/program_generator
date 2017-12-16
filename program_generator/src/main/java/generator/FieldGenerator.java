package generator;

import config.Configuration;
import java.util.List;
import java.util.Random;

/**
    FieldGenerator creates a field declaration
 */

public class FieldGenerator {
    String accessmodifier, isStatic, type, name;
    Configuration configuration;
    String arrayContent;
    Random rand = new Random();
    String fieldDeclaration;

    public FieldGenerator(String fieldName, Configuration configuration) {
        this.configuration=configuration;
        this.name=fieldName;
    }

    //Generate a field with the given field name using a random type from list of allowed types
    String generate(String isField){
        StringBuilder fieldDeclaration = new StringBuilder();
        if(isField.equals("field")) {
            AccessModifierGenerator accessModifierGenerator = new AccessModifierGenerator();
            accessmodifier = accessModifierGenerator.getAccessModifier();
            if (accessmodifier != "") {
                fieldDeclaration.append(accessmodifier + " ");
            }

            //adding static keyword based on probability
            double staticProbability = 0.5;
            if (rand.nextDouble() <= staticProbability) {
                isStatic = "static";
                fieldDeclaration.append(isStatic+" ");
            }
        }
        List<String> allowedTypes = configuration.getAllowedTypes();
        int randomType = rand.nextInt(allowedTypes.size());
        type = allowedTypes.get(randomType);
        fieldDeclaration.append(type+" "+name);
        this.fieldDeclaration = fieldDeclaration.toString();
        return fieldDeclaration.toString();
    }


    public String generateArray() {
        List<String> allowedTypes =  configuration.getAllowedTypes();
        String allowArray = configuration.getAllowArray();
        int maxArraySize = configuration.getMaximumArraySize();
        int arraySize = rand.nextInt(maxArraySize);
        if (allowArray.equals("yes")) {
            int randomType = rand.nextInt(allowedTypes.size());
            String arrayType = allowedTypes.get(randomType);
            arrayContent = arrayType + "[] " + name + "= new "+arrayType+ "["+arraySize+"]";
        }
        return arrayContent;
    }

    public String initialiseVariable(String returnType) {
        String initialisedValue = "";
        switch (returnType) {
            case "short":
                short randValue = (short) (rand.nextInt(Short.MAX_VALUE) + 1);
                initialisedValue = "(short) "+randValue;
                break;

            case "byte":
                initialisedValue="100";
                break;

            case "char":
                char randChar = (char) (rand.nextInt(26) + 97);
                initialisedValue = "'" + randChar + "'";
                break;

            case "int":
                int randInt = rand.nextInt(configuration.getIntMaxValue() + 1);
                initialisedValue = randInt+"";
                break;

            case "long":
                long range = 1234567L;
                long randLong = (long)(rand.nextDouble()*range);
                initialisedValue = randLong+"";
                break;

            case "float":
                float randFloat = rand.nextFloat();
                initialisedValue = "(float) "+randFloat;
                break;

            case "double":
                double randDouble = rand.nextDouble();
                initialisedValue = ""+randDouble;
                break;

            case "String":
                initialisedValue = "\"" + new IdentifierGenerator().getVariableName() + "\"";
                break;

            case "Object":
                initialisedValue = "new Object()";
                break;
        }
        return initialisedValue;
    }
}
