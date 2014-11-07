package wenyu.learning.Arrays;

import java.util.Arrays;

/*
 * Given an array and a value, how do you implement a function to remove 
 * all instances of that value in place and return the new length of the array?
 * 
 * Solution 1: O(n)/O(n)
 * 		Use extra space to store elements without the given one. 
 * Solution 2: O(n^2)/O(1)
 * 		Remove given one and then rearrange array. 
 * Solution 3: O(n)/O(1)
 * 		1. Divide array to two parts, one contains given element and the other doesnt contain.
 * 		2. Copy range 
 */

public class RemoveGivenElement {

	public static <E> E[] remove(E[] arr, E ele, boolean print) {
		if(arr==null || ele==null || arr.length==0) {
			return null;
		}

		// Divide array to two parts
		int start=0;
		int end = arr.length-1;
		while(start<end) {
			while(start<arr.length && !arr[start].equals(ele)) {start++;}
			while(end>=0 && arr[end].equals(ele)) {end--;}
			
			// Swap these two
			if(start<arr.length && end>=0 && start<end) {
				arr[start] = arr[end];
				arr[end] = ele;
			}
		}
		
		// Remove elements
		E[] newArr = Arrays.copyOfRange(arr, 0, end+1);
		if(print) System.out.println(Arrays.toString(newArr));
		return newArr;
	}
	
	public static void main(String[] args) {
		Integer[] arr = {1,2,3,1,3,4,1,1,4,4,1,4,4,5};
		remove(arr, new Integer(-1), true);
	}

}
