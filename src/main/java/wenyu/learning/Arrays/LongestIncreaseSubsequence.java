package wenyu.learning.Arrays;

import java.util.Arrays;
import java.util.Stack;

public class LongestIncreaseSubsequence {

	public static int LISCount(int[] seq) {
		/*
		 * Calculate the count of longest increase subsequence
		 * Logic:
		 *  When a number in the input sequence is scanned, it is compared with the greatest number in the
			auxiliary array. If the newly scanned number is greater, it is inserted into the array directly. Otherwise, it
			replaces the least number of those greater than it in the array in order to allow a wider range of values to
			be added after it. In order to find the greatest number in the array as well as to find the first number
			greater than a target value efficiently, the array keeps sorted.
		 */
		int len = seq.length;
		int[] lookup = new int[len];
		lookup[0] = seq[0];
		int longestLength = 1;
		for (int i = 1; i < len; ++i) {
			if (seq[i] > lookup[longestLength - 1]) {
				longestLength++;
				lookup[longestLength - 1] = seq[i];
			} else {
				int low = 0;
				int high = longestLength - 1;
				while (low != high) {
					int mid = (low + high) / 2;
					if (lookup[mid] < seq[i]) {
						low = mid + 1;
					} else {
						high = mid;
					}
				}
				lookup[low] = seq[i];
			}
		}
		return longestLength;
	}

	
	/*
	 * Get the longest increase sub-sequence
	 * Logic:
	 * 	1. First step:
	 * 		a) prepare the b array
	 * 		b) values in b means the increase order position(index) of current position's value in array.
	 * 		   For example:
	 * 				arr: {1,8,3,10,5,6}
	 * 				aux: {1,2,2,3,3,4}
	 * 				like 8: {1, 8}/like 3: {1,3}/like 6:{1,3,5,6}
	 *		c) First step can also calculate the count of longest increase sub-sequence
	 *  2. Second step:
	 *  	a) according to the b. find the last one of the sub-sequence. (For example: 6 which in b is 4)
	 *  	b) from the end of array find the second last item in array print it.
	 *  	c) loop...
	 */
	public static void getLIS(int[] arr) {
		// First Step: form aux array
		int[] aux = new int[arr.length];
		aux[0] = 1;
		for(int i=1; i<arr.length; i++) {
			int j = 0;
			int max = 0;
			for(j=i-1; j>=0; j--) {
				if(arr[j]<=arr[i] && aux[j]>max) {
					max = aux[j];
				}
			}
			aux[i] = max + 1;
		}
		System.out.println(Arrays.toString(aux)); // print out aux array
		
		// Second Step: find the longest sequence count
		int max = Integer.MIN_VALUE;
		int maxIdx = -1;
		for(int i=0; i<aux.length; i++) {
			if(aux[i] > max) {
				max = aux[i];
				maxIdx = i;
			}
		}
		System.out.println("Longest Count is " + max);// print the longest sequence count

		//Third Step: reverse the array, and print out the increased sequence according to aux array
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(arr[maxIdx]);
		int next = maxIdx;
		for(int i=maxIdx-1; i>=0; i--) {
			if(aux[i]==aux[next]-1 && arr[i]<=arr[next]) {
				stack.push(arr[i]);
				next = i;
			}
		}
		while(!stack.isEmpty()) {
			System.out.print(stack.pop() + ", ");
		}
	}

	
	public static void main(String[] args) {
		int[] arr = new int[] { 1, 9, 3, 8, 11, 4, 5, 6, 4, 1, 9, 10, 1, 7 };
		arr = new int[] {9, 1,8,3,10,5,6};
		int count = LISCount(arr);
		System.out.println("Longest Increase Subsequence's count is " + count);
		System.out.print("Longest Increase Subsequence is ");
		getLIS(arr);
	}

}
