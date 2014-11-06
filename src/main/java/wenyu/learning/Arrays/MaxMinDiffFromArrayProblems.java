package wenyu.learning.Arrays;

import java.util.Arrays;

/*
 * Problem 1: Find the max or min difference of two items in array
 * Problem 2: Find out the maximum difference between any two elements such that larger 
 *            element appears after the smaller number in arr[].
 */

class MaxMinDiffInArray {
	/*
	 * Problem 1: Find two elements in array which form max/min differnece
	 * Max Logic: 
	 * 	1. Find the minimum item and maximum item in array.
	 * 	2. difference is the substrance of them
	 * 
	 * Min Logic:
	 * 	1. Sort array
	 *  2. Scan one by one, to see the difference adjancent elements
	 */
	public static void maxDiff(int[] array) {
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		int minIdx = -1;
		int maxIdx = -1;
		
		for(int i=0;i<array.length;i++) {
			if(array[i]<min) {
				min = array[i];
				minIdx = i;
			}
			
			if(array[i]>max) {
				max = array[i];
				maxIdx = i;
			}
		}
		
		System.out.println("The max difference in array is " + (max-min));
		System.out.println("These two items are " + array[minIdx] + " and " + array[maxIdx]);
	}
	
	public static void minDiff(int[] array) {
		Arrays.sort(array);
		int min = Integer.MAX_VALUE;
		int minIdx = -1;
		
		for(int i=1;i<array.length;i++) {
			int diff = Math.abs(array[i]-array[i-1]);
			if(diff<min) {
				minIdx = i;
				min = diff;
			}
		}
		System.out.println("The min difference in array is " + min);
		System.out.println("These two items are " + array[minIdx-1] + " and " + array[minIdx]);
	}
}

class MaxDiffInArraySequence {
	/*
	 * Problem 2: Find max difference in array sequence
	 * Logic:
	 *   Solution1: O(n^2)
	 *    1. loop every item in the array
	 *    2. every item, find the max difference item after it.
	 *    
	 *   Solution2: O(n)
	 *    1. to see maxDiff_solution2
	 *    Logic:
	 *    In this method, instead of taking difference of the picked element with every other element, 
	 *    we take the difference with the minimum element found so far. So we need to keep track of 2 things:
	 *    1) Maximum difference found so far (max_diff).
	 *    2) Minimum number visited so far (min_element).
	 */
	
	public static void maxDiffSeq_solution1(int numbers[]) {
		int max = Integer.MIN_VALUE;

		for (int i = 0; i < numbers.length; i++) {
			for (int j = i + 1; j < numbers.length; j++) {
				if (numbers[j] - numbers[i] > max) {
					max = numbers[j] - numbers[i];
				}
			}
		}

		System.out.println("Max difference is " + max);
	}

	public static void maxDiffSeq_solution2(int numbers[]) {
		if (numbers == null || numbers.length < 2)
			return;

		int min = numbers[0];
		int maxDiff = numbers[1] - min;
		for (int i = 2; i < numbers.length; ++i) {
			if (numbers[i - 1] < min)
				min = numbers[i - 1];
			int currentDiff = numbers[i] - min;
			if (currentDiff > maxDiff)
				maxDiff = currentDiff;
		}

		System.out.println("Max difference is " + maxDiff);
	}
}

public class MaxMinDiffFromArrayProblems {
	public static void main(String[] args) {
		int[] array = new int[] {-20,-3916237,-357920,-3620601,7374819,-7330761,30,6246457,-6461594,266854,-520,-470};
		MaxMinDiffInArray.maxDiff(array);
		MaxMinDiffInArray.minDiff(array);
	}

}
