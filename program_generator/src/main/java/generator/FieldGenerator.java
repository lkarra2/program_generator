package generator;

import config.Configuration;
import java.util.List;
import java.util.Random;

/*
    fieldGenerator creates a field declaration
 */

public class FieldGenerator {
    String Name;
    Configuration configuration;
    String fieldDeclaration;
    String arrayContent;
    Random rand = new Random();

    public FieldGenerator(String fieldName, Configuration configuration) {
        this.configuration=configuration;
        this.Name=fieldName;
    }

    //Generate a field with the given field name using a random type from list of allowed types
    String generate(){
        List<String> allowedTypes =  configuration.getAllowedTypes();
        int randomType = rand.nextInt(allowedTypes.size());
        fieldDeclaration = allowedTypes.get(randomType)+" "+Name;
        return fieldDeclaration;
    }


    public String generateArray() {
        List<String> allowedTypes =  configuration.getAllowedTypes();
        String allowArray = configuration.getAllowArray();
        int maxArraySize = configuration.getMaximumArraySize();
        int arraySize = rand.nextInt(maxArraySize);
        if (allowArray.equals("yes")) {
            int randomType = rand.nextInt(allowedTypes.size());
            String arrayType = allowedTypes.get(randomType);
            arrayContent = arrayType + "[] " + Name + "= new "+arrayType+ "["+arraySize+"]";
        }
        return arrayContent;
    }
}
