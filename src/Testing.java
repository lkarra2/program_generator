//Class used to test simple code before implementation
import java.util.*;

public class Testing {
	public static void main(String[] args){
		
		//Testing Linked List
		
		LinkedList<String> l1 = new LinkedList<String>(); //prod_r1
		LinkedList<String> l2 = new LinkedList<String>(); //expression
		
		l1.add("H");
		l1.add("e");
		
		l2.add("l");
		l2.add("l");
		l2.add("o");
		
		l1.addAll(l2); //adds l2 to the end of l1
		l2 = l1;
		System.out.print("Combined Linked List for L1 and L2, printing through L1 ");
		for(int i = 0; i < l1.size(); i++){
			System.out.print(l1.get(i));
		}
		System.out.println();
		
		System.out.print("Combined Linked List for L1 and L2, printing through L2 ");
		for(int i = 0; i < l2.size(); i++){
			System.out.print(l2.get(i));
		}
		System.out.println();
		
		
		LinkedList<String> L1 = new LinkedList<String>();
		L1.add("");
		
		if(L1.getFirst().equals("E")){ //if L1 was empty then error is given
			
			System.out.println("Equals");
		}
		else{
			System.out.println("Not equal");
		}
		
		System.out.println("Testing index and addat index\n");
		
		LinkedList<String> L2 = new LinkedList<String>();
		L2.add("A");
		L2.add("B");
		L2.add("C");
		L2.add("D");
		L2.add("E");
		L2.add("F");
		
		LinkedList<String> L3 = new LinkedList<String>();
		L3.add("b");
		L3.add("c");
		
		int index = L2.indexOf("B");
		System.out.println("L2 B Index: " + index);
		
		L2.remove(index);
		L2.addAll(index, L3);
		
		System.out.print("L2 List: ");
		for(int i = 0; i < L2.size(); i++){
			System.out.print(L2.get(i));
		}
		
		System.out.println();
		Random rand = new Random();
		int rand_expression = rand.nextInt(5) + 1; //5 is max, 1 is min  //used to choose production rule, 1-5
		//Testing bounds for production rule 1-5
		System.out.println("Testing bounds, if nothing printed then it is within bounds");
		for(int i = 0; i < 100; i++){
			rand_expression = rand.nextInt(5) + 1; //5 is max, 1 is min
			if(rand_expression < 1 || rand_expression > 5){
				System.out.println("Rand_Expression is out of bounds"); //just testing bounds
			}
		}
		System.out.println("Testing bounds is done");

		System.out.println("Testing letters");
		int b = 65;
		char a = (char)b;

		System.out.println(a);
		int start = 65;
		for(int i = 0; i < 26; i ++){
			char l = (char)(start + i);
			System.out.print( l + " ");

		}


		
		
	}

}
