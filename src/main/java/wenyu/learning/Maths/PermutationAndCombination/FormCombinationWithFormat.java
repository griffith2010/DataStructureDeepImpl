package wenyu.learning.Maths.PermutationAndCombination;

import java.util.Arrays;

public class FormCombinationWithFormat {

	public static void problem1(int d, int N) {
		/*
		 * Given a number d and size of array N. Print all combination of 
		 * element in the array such that first element of array is d and 
		 * next element in the array can be +1 or -1 the previous element 
		 * in the array. Code was required.
		 * 
		 * E.g. Input: d=4 N=3
		 * Output:
		 *   4 3 2
		 *   4 3 4
		 *   4 5 4
		 *   4 5 6 
		 */
		
		if(N <= 0) {
			return;
		}
		
		int[] arr = new int[N];
		arr[0] = d;
		problem1_core(arr, 0);
	}
	
	private static void problem1_core(int[] arr, int currIdx) {
		if(currIdx == arr.length-1) {
			System.out.println(Arrays.toString(arr));
			return;
		}
		
		arr[currIdx+1] = arr[currIdx]+1;
		problem1_core(arr, currIdx+1);
		
		arr[currIdx+1] = arr[currIdx]-1;
		problem1_core(arr, currIdx+1);
	}
	
	public static void main(String[] args) {
		problem1(3, 8);
	}

}
