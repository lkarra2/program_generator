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
		
		
	}

}
