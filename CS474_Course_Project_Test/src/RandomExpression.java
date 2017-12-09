import java.util.*;

/*Steps
 * 
 * Generate Random Number for main template
 * Create a Linked List for that production rule
 * While that node is <expression> generate another random number
 * if <number> or <var> or +-/* then skip node
 * 
 * 
 * 
 */


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


public class RandomExpression {
	static boolean TEST = true;
	
	public static LinkedList<String> productionR1(){
		if(TEST){
			System.out.println("Production Rule 1 called");
		}
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
		if(TEST){
			System.out.println("Production Rule 2 called");
		}
		LinkedList<String> eq = new LinkedList<String>();
		eq.add("E");
		eq.add("-");
		eq.add("E");

		return eq;
		
	}
	
	public static LinkedList<String> productionR3(){
		if(TEST){
			System.out.println("Production Rule 3 called");
		}
		LinkedList<String> eq = new LinkedList<String>();
		eq.add("E");
		eq.add("*");
		eq.add("E");

		return eq;
		
	}
	
	public static LinkedList<String> productionR4(){
		if(TEST){
			System.out.println("Production Rule 4 called");
		}
		LinkedList<String> eq = new LinkedList<String>();
		eq.add("(");
		eq.add("E");
		eq.add(")");

		return eq;
		
	}
	
	//Unsure which to add "<number> | <var>" so i randomly chose 1
	public static LinkedList<String> productionR5(){
		if(TEST){
			System.out.println("Production Rule 5 called");
		}
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
	public static LinkedList<String> productionR6(){
		LinkedList<String> eq = new LinkedList<String>();
		eq.add("N"); //temp will change to values accordingly
		
		
		return eq;
	}
	
	//<var>        ::= [_a-zA-Z][_a-zA-Z0-9]{1,255} //production rule 7
	public static LinkedList<String> productionR7(){
		LinkedList<String> eq = new LinkedList<String>();
		eq.add("V"); //temp will change to values accordingly
		
		
		return eq;
	}
	

	public static void main(String[] args){
		Random rand = new Random();
		int rand_expression = rand.nextInt(5) + 1; //5 is max, 1 is min  //used to choose production rule, 1-5
		
		if(TEST){ //Testing bounds for production rule 1-5
			System.out.println("Testing bounds, if nothing printed then it is within bounds");
			for(int i = 0; i < 100; i++){
				rand_expression = rand.nextInt(5) + 1; //5 is max, 1 is min
				if(rand_expression < 1 || rand_expression > 5){
					System.out.println("Rand_Expression is out of bounds"); //just testing bounds
				}
			}
			System.out.println("Testing bounds is done");
		}
		

		
		System.out.println("Testing Switch ***************");
		
		int expression_count = 0; //while loop until this is 0
		LinkedList expression = new LinkedList<String>(); //an "accumulator" for our production rules
		//this switch statement is to get the main template for expression.
		//Could be implemented below but too lazy
		switch(rand_expression){
		case 1:
			expression = productionR1();
			break;
		case 2:
			expression = productionR2();
			break;
		case 3:
			expression = productionR3();
			break;
		case 4:
			expression = productionR4();
			break;
		case 5:
			expression = productionR5();
			break;
			
		}
		
		for(int i = 0; i < expression.size(); i++){
			System.out.print(expression.get(i));
		}
		System.out.println();
		
		//need to replace current <expression> with new <expression> rules
		int e_index;
		
		int while_counter = 0;
		do{
			while_counter++;
			e_index = expression.indexOf("E");
			if(e_index != -1){ //has an expression in equation
				rand_expression = rand.nextInt(5) + 1; //5 is max, 1 is min
				switch(rand_expression){
					case 1:
						LinkedList prod_r1 = productionR1();
						expression.remove(e_index);
						expression.addAll(e_index, prod_r1);
						break;
					case 2:
						LinkedList prod_r2 = productionR2();
						expression.remove(e_index);
						expression.addAll(e_index, prod_r2);
						break;
					case 3:
						LinkedList prod_r3 = productionR3();
						expression.remove(e_index);
						expression.addAll(e_index, prod_r3);
						break;
					case 4:
						LinkedList prod_r4 = productionR4();
						expression.remove(e_index);
						expression.addAll(e_index, prod_r4);

						break;
					case 5:
						LinkedList prod_r5 = productionR5();
						expression.remove(e_index);
						expression.addAll(e_index, prod_r5);
						break;
					default:
						//do nothing, it should be 1-5
				} //end switch
			}
			else{ //no "E" in linked List so break out of while loop
				break;
			}
			
//			if(TEST){
//				System.out.println("Expression Counter: " + expression_count);
//			}
			
			for(int i = 0; i < expression.size(); i++){
				System.out.print(expression.get(i));
			}
			System.out.println();
		
		}
		while(while_counter < 10); //testing do-while with a limited run
		//while(expression_count != 0); //end do-while
		//while(true); //gets broken by the if/else for no index of "E"
		
//		if(TEST){
//			System.out.println("Do-While Count: " + while_counter);
//		}

		System.out.print("Combined Linked List for accumulated expression ");
		for(int i = 0; i < expression.size(); i++){
			System.out.print(expression.get(i));
		}
		System.out.println();
		
		//Production and Expressions done, need to change while loop back to true
		
		//next step is to convert <var> | <numbers> to their values
		int randVN;
		LinkedList<String> prod;
		for(int i = 0; i < expression.size();i++){
			String node = (String) expression.get(i);
			if( node.equals("E") ){ //too lazy to convert to <number> | <var> so converting right away (might change later)
				randVN = rand.nextInt(7) + 6; // random 6 or 7
				if(randVN == 6){
					prod = productionR6(); //<number>
				}else{
					prod = productionR7(); //<var>
				}
				expression.remove(i);
				expression.addAll(i, prod);
			}
			else if( node.equals("N") ){
				LinkedList prod_r6 = productionR6();
				expression.remove(i);
				expression.addAll(i, prod_r6);
			
			}
			else if( node.equals("V") ){
				LinkedList prod_r7 = productionR7();
				expression.remove(i);
				expression.addAll(i, prod_r7);
				
			}
			
		}
		
		System.out.print("Combined Linked List for accumulated expression Converted to N or V: ");
		for(int i = 0; i < expression.size(); i++){
			System.out.print(expression.get(i));
		}
		System.out.println();
		
		
		
		
	} //end of main
} //end of class
