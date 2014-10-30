package wenyu.learning.Maths.PermutationAndCombination;

import java.util.Iterator;
import java.util.Stack;

/*
 * Implement an algorithm to print all valid (e.g., properly opened and closed) combinations of n-pairs of parentheses.
 * 
 * EXAMPLE:
 * input: 3 (e.g., 3 pairs of parentheses)
 * output: ()()(), ()(()), (())(), ((())), (()())
 * 
 * LOGIC:
 * 	For every time, there are two choice: 
 * 		1. add another ( if not finish
 * 		2. add ) if there is ( left before
 */
public class ParenthesesPermutation {

	public static void printParentheses(int count, int leftCount, Stack<Character> stack) {
		if(count==0 && leftCount==0) {
			Iterator<Character> it = stack.iterator();
			while(it.hasNext()) {
				System.out.print(it.next());
			}
			System.out.println();
		}
		
		// Case 1: add another ( if not finish
		if(count>0) {
			stack.push('(');
			printParentheses(count-1, leftCount+1, stack);
			stack.pop();
		}
		
		// Case 2: add ) if there is ( left before
		if(leftCount>0) {
			stack.push(')');
			printParentheses(count, leftCount-1, stack);
			stack.pop();
		}
	}
	
	
	
	public static void main(String[] args) {
		printParentheses(3, 0, new Stack<Character>());
	}
}
