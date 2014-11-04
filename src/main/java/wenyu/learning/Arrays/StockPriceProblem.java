package wenyu.learning.Arrays;

/*
 * Stock prices are stored in an array in the order of date. How do you get the
 * most profit from a sequence of stock prices?
 * For example, the most profit to be gained from the sequence of ordered stock
 * prices {9, 11, 5, 7, 16, 1, 4, 2} is 11, bought when the price was 5 and sold
 * when the price was 16.
 * 
 * Problem 1: Only allow to buy/sell once (Same as max min from unsorted array.)
 * Problem 2: Allow buy/sell multiply times. (Not implemented. http://www.geeksforgeeks.org/stock-buy-sell/)
 */

public class StockPriceProblem {
	public static void problem1(int numbers[]) {
		/*
		 * Problem 1
		 */
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
		problem1(numbers);

	}
}
