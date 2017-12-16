package generator;

import java.util.Random;

public class AccessModifierGenerator {

    AccessModifierGenerator(){

    }

    public String getAccessModifier(){
        String accessModifier = "";
        Random rand = new Random();
        int accessType = rand.nextInt(3)+1;
        switch (accessType){
            case 1: accessModifier = "private";
                    break;
            case 2: accessModifier = "protected";
                    break;
            case 3: accessModifier = "public";
                    break;
            default: break;
        }

        return accessModifier;
    }
}
