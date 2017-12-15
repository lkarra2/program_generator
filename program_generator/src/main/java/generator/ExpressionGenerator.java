package generator;

import productionrules.ProductionRules;
import java.util.LinkedList;
import java.util.Random;
import java.util.ArrayList;

public class ExpressionGenerator {
    public static ProductionRules pr = new ProductionRules();
    Random rand = new Random();


    //public LinkedList<String> generateExpression() {
    public String generateExpression(){
        //Choosing production rule, 1-5
        int rand_expression = rand.nextInt(5) + 1; //5 is max, 1 is min
        LinkedList<String> expression = new LinkedList<String>(); //An "accumulator" for our production rules

        ArrayList<String> variableArr = new ArrayList<String>();

        //This switch statement is to get the main template for expression
        switch(rand_expression){
            case 1:
                expression = pr.productionR1();
                break;
            case 2:
                expression = pr.productionR2();
                break;
            case 3:
                expression = pr.productionR3();
                break;
            case 4:
                expression = pr.productionR4();
                break;
            case 5:
                expression = pr.productionR5();
                break;
        }
        //Need to replace current <expression> with new <expression> rules
        int e_index;
        int while_counter = 0;
        do{
            while_counter++;
            e_index = expression.indexOf("E");
            if(e_index != -1){ //has an expression in equation
                rand_expression = rand.nextInt(5) + 1; //5 is max, 1 is min
                switch(rand_expression){
                    case 1:
                        LinkedList prod_r1 = pr.productionR1();
                        expression.remove(e_index);
                        expression.addAll(e_index, prod_r1);
                        break;
                    case 2:
                        LinkedList prod_r2 = pr.productionR2();
                        expression.remove(e_index);
                        expression.addAll(e_index, prod_r2);
                        break;
                    case 3:
                        LinkedList prod_r3 = pr.productionR3();
                        expression.remove(e_index);
                        expression.addAll(e_index, prod_r3);
                        break;
                    case 4:
                        LinkedList prod_r4 = pr.productionR4();
                        expression.remove(e_index);
                        expression.addAll(e_index, prod_r4);

                        break;
                    case 5:
                        LinkedList prod_r5 = pr.productionR5();
                        expression.remove(e_index);
                        expression.addAll(e_index, prod_r5);
                        break;
                    default:
                        //Do nothing, it should be 1-5
                } //End switch
            }
            else{ //No "E" in linked List so break out of while loop
                break;
            }
        }
        while(while_counter < 10); //testing do-while with a limited run

        //Production and Expressions done, need to change while loop back to true

        System.out.println("Printing Expression Before Converting V and N: ");
        for(String s : expression){
            System.out.print(s.toString());
        }
        System.out.println();

        //Next step is to convert <var> | <numbers> to their values
        int randVN;
        LinkedList<String> prod;
        for(int i = 0; i < expression.size(); i++){
            String node = (String) expression.get(i);
            if( node.equals("E") ){ //Too lazy to convert to <number> | <var> so converting right away (might change later)
                randVN = rand.nextInt(2) + 1; // random 6 or 7
                if(randVN == 1){
                    prod = pr.productionR6(); //<number>
                }else{
                    prod = pr.productionR7(); //<var>

                    //Convert LinkedList to String
                    String[] expString = prod.toArray(new String[0]);

                    StringBuilder strAtoS = new StringBuilder();
                    for(String s : expString){
                        strAtoS.append(s);
                    }
                    variableArr.add(strAtoS.toString());
                    //converted and added the "V" to a string into variableArr
                    System.out.println("E to V: Variable converted to String: " + strAtoS.toString());

                }
                expression.remove(i);
                expression.addAll(i, prod);
            }
            else if( node.equals("N") ){
                LinkedList prod_r6 = pr.productionR6();
                expression.remove(i);
                expression.addAll(i, prod_r6);
            }
            else if( node.equals("V") ){
                LinkedList<String> prod_r7 = pr.productionR7();

                //Convert LinkedList to String
                String[] expString = prod_r7.toArray(new String[0]);

                StringBuilder strAtoS = new StringBuilder();
                for(String s : expString){
                    strAtoS.append(s);
                }
                variableArr.add(strAtoS.toString());
                //converted and added the "V" to a string into variableArr
                System.out.println("V: Variable converted to String: " + strAtoS.toString());


                expression.remove(i);
                expression.addAll(i, prod_r7);
            }
        }

        System.out.println("Printing Expression: ");
        for(String s : expression){
            System.out.print(s.toString());
        }

        //Converting to string with initalized variables and expression
        System.out.println("\nExpression Generator");

        //Convert LinkedList<String> to a String
        String[] temp = expression.toArray(new String[0]); //Correct start from 0 - expString.length
        StringBuilder expString = new StringBuilder();
        for(String s : temp ){
            expString.append(s);
        }
        String expressionString = expString.toString(); //A string format of the expression generated
        //Done - LinkedList to String

        //Build the return String
        StringBuilder returnString = new StringBuilder();

        for(int i = 0; i < variableArr.size(); i++){
            int value = rand.nextInt(999); //need to randomly generate 3 digits
            returnString.append("int " + variableArr.get(i).toString() + " = " + Integer.toString(value) + ";\n");

        }

        //create a variable name for the expression
        LinkedList<String> var = pr.productionR7();
        StringBuilder varName = new StringBuilder();
        for(String s : var){
            varName.append(s);
        }

        returnString.append("\n");

        //add expression to returnString
        returnString.append("int " + varName.toString() + " = " + expressionString);


        return returnString.toString();
        //return expression;

    }

    public static void main(String[] args){

        ExpressionGenerator g = new ExpressionGenerator();
        String exp = g.generateExpression();


        System.out.println("Testing Expression Generator in class");
        System.out.println("Expression: " + exp);

    }
}
