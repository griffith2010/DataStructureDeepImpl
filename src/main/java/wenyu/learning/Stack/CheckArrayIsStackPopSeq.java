package wenyu.learning.Stack;

import java.util.Stack;

/**
 * You are given two integer arrays, 
 * one of which is a sequence of numbers 
 * pushed into a stack (supposing all 
 * numbers are unique). Please check 
 * whether the other array is a 
 * corresponding sequence popped from the stack. 
 * 
 * For example, if the pushing sequence is 
 * {1, 2, 3, 4, 5}, the sequence 
 * {4, 5, 3, 2, 1} is a corresponding popping 
 * sequence, but {4, 3, 5, 1, 2} is not.
 */
public class CheckArrayIsStackPopSeq {

	public static boolean check(int[] numbers1, int[] numbers2) {
		if(numbers1.length != numbers2.length) {
			return false;
		}
		
		Stack<Integer> stack = new Stack<Integer>();
		int num2Idx = 0;
		
		for(int i=0;i<numbers1.length;i++) {
			stack.push(numbers1[i]);
			
			while(!stack.isEmpty() && stack.peek() == numbers2[num2Idx]) {
				stack.pop();
				num2Idx++;
			}
		}
		
		if(stack.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
	
	public static void main(String[] args) {
		int[] numbers1 = new int[] {1,2,3,4,5};
		int[] numbers2 = new int[] {4,5,3,2,1};
		//numbers2 = new int[] {4, 3, 5, 1, 2};
		numbers2 = new int[] {1, 5, 4, 3, 6};
		
		if(check(numbers1, numbers2)) {
			System.out.println("Numbers2 is the pop sequence of numbers1");
		} else {
			System.out.println("Numbers2 is not the pop sequence of numbers1");
		}
	}

}
