package wenyu.learning.Maths;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Big numbers can be formed if numbers in an array are concatenated together.
 * How do you print the minimum concatenated number of a given array? For
 * example, if the input array is {3, 32, 321}, there are six concatenated
 * numbers and the minimum one is 321323 (P203)
 */
public class ConcatenatedMinimumNumber {

	private static Comparator<String> numericComparator = new Comparator<String>() {
		/**
		 * Numbers mn and nm are concatenated from two numbers m and n. 
		 * (In this section, a number mn stands for a concatenated number 
		 * of m and n, rather than m×n.) If mn < nm, the output is mn, 
		 * in which m is placed ahead of n
		 */
		public int compare(String num1, String num2) {
			String str1 = num1 + num2;
			String str2 = num2 + num1;
			return str1.compareTo(str2);
		}
	};

	public static void concateNumbers(String[] numbers) {
		String strNumbers[] = new String[numbers.length];
		for (int i = 0; i < numbers.length; ++i) {
			strNumbers[i] = String.valueOf(numbers[i]);
		}
		Arrays.sort(strNumbers, numericComparator);
		for (int i = 0; i < numbers.length; ++i)
			System.out.print(strNumbers[i]);
		System.out.print("\n");
	}

	public static void main(String[] args) {
		String[] numbers = { "123", "321", "3", "56", "8", "21", "65" };
		concateNumbers(numbers);
	}

}
