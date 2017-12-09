import java.util.*;

/* Production Rules
 *
 *<expression> ::= <expression> + <expression>	//production rule 1
 *<expression> ::= <expression> - <expression>	//production rule 2
 *<expression> ::= <expression> * <expression>	//production rule 3
 *<expression> ::= ( <expression> )				//production rule 4
 *<expression> ::= <number> | <var>				//production rule 5
 *<number> 	   ::= 0 | [1-9][0-9]*				//production rule 6
 *<var>        ::= [_a-zA-Z][_a-zA-Z0-9]{1,255} //production rule 7
 */

public class Production_Rules {
    static Random rand = new Random();

    public Production_Rules(){

    }
    public static LinkedList<String> productionR1(){
        System.out.println("Production Rule 1 called");
        LinkedList<String> eq = new LinkedList<String>();
        eq.add("E");
        eq.add("+");
        eq.add("E");

		/*
		if(TEST){ //print out linked list
			System.out.print("ProductionR1 Linked List: ");
			for(int i = 0; i < eq.size(); i++){
				System.out.print(eq.get(i));
			}
			System.out.println();
		}
		*/

        return eq;

    }

    public static LinkedList<String> productionR2(){
        System.out.println("Production Rule 2 called");
        LinkedList<String> eq = new LinkedList<String>();
        eq.add("E");
        eq.add("-");
        eq.add("E");

        return eq;

    }

    public static LinkedList<String> productionR3(){
        System.out.println("Production Rule 3 called");
        LinkedList<String> eq = new LinkedList<String>();
        eq.add("E");
        eq.add("*");
        eq.add("E");

        return eq;

    }

    public static LinkedList<String> productionR4(){
        System.out.println("Production Rule 4 called");
        LinkedList<String> eq = new LinkedList<String>();
        eq.add("(");
        eq.add("E");
        eq.add(")");

        return eq;

    }

    //Unsure which to add "<number> | <var>" so i randomly chose 1
    public static LinkedList<String> productionR5(){
        System.out.println("Production Rule 5 called");
        Random rand = new Random();
        int choose = rand.nextInt(2) + 1; //max 2, min 1

        LinkedList<String> eq = new LinkedList<String>();
        if(choose == 1){
            eq.add("N");
        }
        else{
            eq.add("V");
        }

        return eq;

    }

    //<number> 	   ::= 0 | [1-9][0-9]*				//production rule 6
    //if( [1-9][0-9]* ) cap at 5 digits with 50% of continuing until 5
    public static LinkedList<String> productionR6(){
        System.out.println("Production Rule 6 is called");
        LinkedList<String> eq = new LinkedList<String>();
        //eq.add("N"); //temp will change to values accordingly

        int random = rand.nextInt(2) + 1; // value of 1 or 2 for 50%
        if( random == 1){
            eq.add("0");
        }
        else {
            random = rand.nextInt(9) + 1; // values 1-9
            eq.add("" + random);

            //50% chance to add another value
            for (int i = 0; i < 4; i++) { //capped at adding 5 values so the returned list is not too long
                random = rand.nextInt(2) + 1;
                if (random == 1) { //add another value
                    random = rand.nextInt(9) + 0; //generate another value to add
                    eq.add("" + random);
                } else {
                    break; //break from for loop
                }
            }
        }

        return eq;
    }

    //<var>        ::= [_a-zA-Z][_a-zA-Z0-9]{1,255} //production rule 7
    //[_a-zA-Z] = starts with "_" followed by a-z or A-Z
    public static LinkedList<String> productionR7(){
        System.out.println("Production Rule 7 is called");
        LinkedList<String> eq = new LinkedList<String>();
        eq.add("V"); //temp will change to values accordingly

        /*
        eq.add("_");
        int random = rand.nextInt(2) + 1;

        if(random == 1){ //upper case
            //int start = 65;
            random = rand.nextInt(90) + 65;
            char l = (char)(random);
            eq.add("" + l);
        }
        else{ //lower case
            //int start = 65;
            random = rand.nextInt(122) + 97;
            char l = (char)(random);
            eq.add("" + l);
        }

        eq.add("_");

        random = rand.nextInt(3) + 1;
        if(random == 1){ //upper case
            //int start = 65;
            random = rand.nextInt(90) + 65;
            char l = (char)(random);
            eq.add("" + l);
        }
        else if(random == 2){ //lower case
            //int start = 65;
            random = rand.nextInt(122) + 97;
            char l = (char)(random);
            eq.add("" + l);
        }
        else{
            random = rand.nextInt(9) + 0;
            eq.add(""+random);
        }

        random = rand.nextInt(255) + 1; //not sure about this one since its {1-255}
        eq.add(""+random);
        */

        return eq;
    }




}
