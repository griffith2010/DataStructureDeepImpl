package wenyu.learning.Arrays;

/*
 * Find the bigest sum of the sub-array in given array
 * Logic:
 * 	1. scan array one by one
 * 	2. if current sum is less than 0, reset sum to 0. Else, add this item to sum
 * 	3. if current sum is bigger than current max, reset max to current sum.
 */
public class SubArrayBigestSum {

	public static int getGreatestSumOfSubArray(int[] numbers) {
		int curSum = 0;
		int greatestSum = Integer.MIN_VALUE;
		for (int i = 0; i < numbers.length; ++i) {
			if (curSum <= 0) {
				curSum = numbers[i];
			} else {
				curSum += numbers[i];
			}
			
			if (curSum > greatestSum) {
				greatestSum = curSum;
			}
		}
		return greatestSum;
	}

	public static void main(String[] args) {
		System.out.println(getGreatestSumOfSubArray(new int[] {1, -2, 3, 10, -4, 7, 2, -5}));
	}

}
