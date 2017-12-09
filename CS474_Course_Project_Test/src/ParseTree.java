import java.util.*;

/*
 * Works correctly for a given pre-ordered equation
 * So enter "* + + 4 5 * 6 7 8" means (((4+5)+(6*7))*8) = 408
 * 
 * 
 * So for CS 474 course project
 * get the equation into a pre-ordered equation?
 * need to adjust the eval and toString methods with updated expressions
 * 
 * Recursively build equation based on random number
 * after equation is done, convert to pre-ordered equation like listed above
 */

public class ParseTree {
    private String s;
    private ParseTree left, right;
   
    // create the parse tree from standard input
    public ParseTree() {
        //s = StdIn.readString();
    	Scanner sc = new Scanner(System.in);
    	s = sc.next();

        if (s.equals("+") || s.equals("*")) {
            left  = new ParseTree();
            right = new ParseTree();
        }
//    	if(s.equals("\n")){
//    		return;
//    		
//    	}
    	
    }

    // evaluate the parse tree
    public int eval() {
        if      (s.equals("+")) return left.eval() + right.eval();
        else if (s.equals("*")) return left.eval() * right.eval();
        else                    return Integer.parseInt(s);
    }

    // preorder traversal
    public String toString() {
        if (s.equals("+") || s.equals("*"))
            return s + " " + left + " " + right;
        else
            return s;
    }

    // test out the parse tree
    public static void main(String[] args) {
        ParseTree tree = new ParseTree();
        System.out.println(tree);
        System.out.println(tree.eval());
    }

}