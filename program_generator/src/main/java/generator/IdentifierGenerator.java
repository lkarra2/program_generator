package generator;

import java.util.Random;

/**
 * Create identifiers based on the rule [a-zA-z]{5}
 */

public class IdentifierGenerator {

    String variableName = "";
    private String myName;

    public IdentifierGenerator() {
        int a = 97;
        Random random = new Random(); //values from 0 to 25
        int temp = random.nextInt(26);
        char l = (char)(temp + a);
        variableName += "" + l;
        for(int i = 0; i<5; i++) {
            int check = random.nextInt(2) + 1;
            if(check == 1) { //Upper Case
                int A = 65;
                temp = random.nextInt(26); //values from 0 to 25
                l = (char)(temp + A);
                variableName += "" + l;
            } else { //Lower Case
                a = 97;
                temp = random.nextInt(26); //values from 0 to 25
                l = (char) (temp + a);
                variableName += "" + l;
            }
        }
    }

    //Returns a single character for conditional loop identifiers
    public String getSingleChar(){
        int a = 97;
        Random random = new Random(); //values from 0 to 25
        int temp = random.nextInt(26);
        char l = (char)(temp + a);
        variableName = "" + l;
        return variableName;
    }

    //Returns the randomly generates varible name
    public String getVariableName() {
        return variableName;
    }

    //Returns name for a class or interface (identifier beginning with [A-Z])
    public String getClassOrInterfaceName() {
        myName = variableName;
        myName = myName.substring(0,1).toUpperCase() + myName.substring(1);
        return myName; }
}
