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
	public static Production_Rules pr = new Production_Rules();

	public static void main(String[] args){
		Random rand = new Random();
		int rand_expression = rand.nextInt(5) + 1; //5 is max, 1 is min  //used to choose production rule, 1-5

		System.out.println("Testing Switch ***************");
		
		int expression_count = 0; //while loop until this is 0
		LinkedList expression = new LinkedList<String>(); //an "accumulator" for our production rules
		//this switch statement is to get the main template for expression.
		//Could be implemented below but too lazy
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
				randVN = rand.nextInt(1); // random 6 or 7
				if(randVN == 0){
					prod = pr.productionR6(); //<number>
				}else{
					prod = pr.productionR7(); //<var>
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
				LinkedList prod_r7 = pr.productionR7();
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
