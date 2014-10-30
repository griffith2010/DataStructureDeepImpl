package wenyu.learning.Arrays;

/*
 * Stock prices are stored in an array in the order of date. How do you get the
 * most profit from a sequence of stock prices?
 * For example, the most profit to be gained from the sequence of ordered stock
 * prices {9, 11, 5, 7, 16, 1, 4, 2} is 11, bought when the price was 5 and sold
 * when the price was 16.
 * Logic:
 *   Solution1: O(n^2)
 *    1. loop every item in the array
 *    2. every item, find the max difference item after it.
 *    
 *   Solution2: O(n)
 *    1. to see maxDiff_solution2
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
