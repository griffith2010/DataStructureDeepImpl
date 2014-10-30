package wenyu.learning.Arrays;

import java.util.Stack;

/*
 * Given an array of integers. replace each number with 
 * next higher number on its right side , which is nearer.
 * (if not present than keep it as it is.)
 * for e.g. input – > 3 4 6 1
 *          output->4 6 6 1
 *          
 * Solution1: O(n^2) or O(nlogn) with binary search for bigger index
 * Solution2: O(n) with O(n) space. Using stack
 */
public class ReplaceWithNextGreater {

	public static void replace_solution1(int[] arr) {
		for(int i=0;i<arr.length;i++) {
			for(int j=i+1;j<arr.length;j++) {
				if(arr[j]>arr[i]) {
					arr[i] = arr[j];
					break;
				}
			}
		}
		
		for(int i=0;i<arr.length;i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
	
	public static void replace_solution2(int[] arr) {
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(0);
		
		for(int i=1;i<arr.length;i++) {
			while(!stack.isEmpty() && arr[i]>arr[stack.peek()]) {
				arr[stack.pop()] = arr[i];
			}
			
			stack.push(i);
		}
		
		
		for(int i=0;i<arr.length;i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		int[] array = {9,8,6,7,1,3,5,7,2,3,10};
		
		replace_solution1(array.clone());
		replace_solution2(array.clone());
	}

}
