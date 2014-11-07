package wenyu.learning.Arrays;

import java.util.Arrays;

/*
 * How do you find the majority element in an array when it exists? The majority
 * is an element that occurs for more than half of the size of the array.
 * Logic:
 * 	Solution1: O(nlogn)
 * 	  1. sort the array
 * 	  2. find the mid value in the array
 * 	  3. scan all the array to see if it is the majority.
 *  Solution2: O(n) [need to change the initial array]
 *    1. Using partition function from the quick sort.
 *    2. loop until the index after partition is the mid of the array.
 *  Solution3: O(n) [no need to change the initial array].
 *    1. According to the definition of majority
 *    2. Just scan the array.
 */
public class MajorityFinding {

	private static boolean checkMajorityExistence(int[] numbers, int result) {
		int times = 0;
		for (int i = 0; i < numbers.length; ++i) {
			if (numbers[i] == result)
				times++;
		}
		return (times * 2 > numbers.length);
	}

	public static void findMajority_solution1(int[] numbers) {
		// Simplest way. sort the array and find the mid number;
		// Need to alter the array
		// O(nlogn)
		Arrays.sort(numbers);

		int midValue = numbers[numbers.length/2];
		if (checkMajorityExistence(numbers, midValue)) {
			System.out.println("Majority number is " + midValue);
		} else {
			System.out.println("Numbers have no majority.");
		}
	}

	private static int partition(int[] numbers, int start, int end) {
		int i = start;
		int j = end;
		int pivotV = numbers[i];
		
		while(i<j) {
			while(i<j && numbers[j]>=pivotV) {j--;}
			if(i<j) {
				numbers[i] = numbers[j];
				i++;
			}
			while(i<j && numbers[i]<pivotV) {i++;}
			if(i<j) {
				numbers[j] = numbers[i];
				j--;
			}
		}
		
		numbers[i] = pivotV;
		return i;
	}
	
	public static void findMajority_solution2(int[] numbers) {
		// Using partition method
		// Also need to alter the array
		// O(n)
		int mid = numbers.length/2;
		int index = partition(numbers, 0, numbers.length-1);

		int start = 0;
		int end = numbers.length-1;
		while(index != mid) {
			if(index > mid) {
				end = index-1;
			} else {
				start = index+1;
			}
			index = partition(numbers, start, end);
		}
		
		int result = numbers[index];
		if (checkMajorityExistence(numbers, result)) {
			System.out.println("Majority number is " + result);
		} else {
			System.out.println("Numbers have no majority.");
		}
	}

	public static void findMajority_solution3(int[] numbers) {
		// Using definition
		// No need to alter the array
		// O(n)
		int result = numbers[0];
		int times = 1;
		for (int i = 1; i < numbers.length; ++i) {
			if (times == 0) {
				result = numbers[i];
				times = 1;
			} else if (numbers[i] == result)
				times++;
			else
				times--;
		}
		
		if (checkMajorityExistence(numbers, result)) {
			System.out.println("Majority number is " + result);
		} else {
			System.out.println("Numbers have no majority.");
		}
	}

	public static void main(String[] args) {
		int[] numbers = new int[] {1, 2, 3, 2, 2, 2, 5, 4, 2};
		int[] numbers1 = new int[] {1, 2, 3, 3, 3, 3, 8, 3, 2};
		findMajority_solution1(numbers1);
		findMajority_solution2(numbers1);
		findMajority_solution3(numbers1);
	}

}
