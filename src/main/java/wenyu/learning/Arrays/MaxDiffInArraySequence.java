package wenyu.learning.Arrays;

/*
 * Given an array arr[] of integers, find out the difference between any two elements such that larger 
 * element appears after the smaller number in arr[].
 * Examples: If array is [2, 3, 10, 6, 4, 8, 1] then returned value should be 8 (Diff between 10 and 2). 
 * 			 If array is [7, 9, 5, 6, 3, 2 ] then returned value should be 2 (Diff between 7 and 9)
 * 
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
public class MaxDiffInArraySequence {

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

	public static void main(String[] args) {
		int[] numbers = new int[] { 9, 11, 5, 7, 16, 1, 4, 2 };
		maxDiffSeq_solution1(numbers);
		maxDiffSeq_solution2(numbers);

	}

}
