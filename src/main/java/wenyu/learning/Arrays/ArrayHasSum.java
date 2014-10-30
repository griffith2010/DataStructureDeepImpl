package wenyu.learning.Arrays;

import java.util.Arrays;
import java.util.Stack;

/*
 * To find sub-items in a array whose sum is given integer.
 * 		1. find two numbers in array with specified sum. (Allow sorting array)
 * 			Logic: first sort array, and two points.
 * 		2. find two numbers in array with specified sum. (Cannot change original array)
 * 			Logic: Loop array and use binary search to find the another number. like 3 and -3
 * 		3. find three numbers in array with specified sum.
 * 			Logic: Loop array and use above methods to find another two numbers
 */
public class ArrayHasSum {

	public static boolean findPairWithSumAllowSort(final int[] numbers, int sum, int excludeIndex) {
		/*
		 * If allowed to change the original array
		 * O(nlogn+n)
		 */
		
		if(numbers.length == 0) {
			return false;
		}
	
		Arrays.sort(numbers);
		boolean found = false;
		int ahead = numbers.length - 1;
		int behind = 0;
		while (ahead > behind) {
			if (ahead == excludeIndex)
				ahead--;
			if (behind == excludeIndex)
				behind++;
			int curSum = numbers[ahead] + numbers[behind];
			if (curSum == sum) {
				System.out.println("Find: " + numbers[ahead] + ", " + numbers[behind]);
				found = true;
				break;
			} else if (curSum > sum)
				ahead--;
			else
				behind++;
		}
		return found;
	}
	
	public static boolean findPairWithSumNoAllowSort(final int[] numbers, int sum, int excludeIndex) {
		/*
		 * If not allowed to change the original array
		 * O(nlogn)
		 */
		
		if(numbers.length == 0) {
			return false;
		}
		
		boolean found = false;
		for(int i=0; i<numbers.length; i++) {
			if(i == excludeIndex) {
				continue;
			}
			
			int rest = sum - numbers[i];
			int start = i+1;
			int end = numbers.length;
			int idx = Arrays.binarySearch(Arrays.copyOfRange(numbers, start, end), rest);
			if(idx >= 0) {
				System.out.println("Find: " + numbers[i] + ", " + numbers[idx]);
				found = true;
				break;
			}
		}
		return found;
	}
	
	public static boolean findTripleWithSum(final int numbers[], int sum) {
		boolean found = false;
		if (numbers.length < 3)
			return found;
		for (int i = 0; i < numbers.length; ++i) {
			int pairSum = -1*numbers[i] + sum;
			found = findPairWithSumNoAllowSort(numbers, pairSum, i);
			if (found)
				break;
		}
		return found;
	}

	public static boolean hasSubsetWithSum0(final int[] numbers) {
		double max = Math.pow(2, numbers.length);
		Stack<Integer> stack = new Stack<Integer>();
		for (int i=0;i<max;i++) {
			stack.removeAllElements();
			String binary = Integer.toBinaryString(i);
			int sum = 0;
			boolean ifAdd = false;
			for (int j=0;j<binary.length();j++) {
				if (binary.charAt(j) == '1') {
					ifAdd = true;
					int idx = numbers.length-binary.length()+j;
					sum += numbers[idx];
					stack.add(numbers[idx]);
				}
			}
			if(ifAdd && sum == 0) {
				while(!stack.isEmpty()) {
					System.out.print(stack.pop() + " ");
				}
				System.out.println();
				return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		int[] numbers = new int[] {1,2,-3,4,5,6,-3,3,2,2,1,5,-8,23,1,4,6,21,2,3,1,3,6,765,2,3,41};
		if(findPairWithSumAllowSort(numbers.clone(), 10, -1)) {
			System.out.println("Has pair items in array with this sum...");
		} else {
			System.out.println("Hasn't pair items in array with this sum...");
		}
		
		if(findPairWithSumNoAllowSort(numbers.clone(), 10, -1)) {
			System.out.println("Has pair items in array with this sum...");
		} else {
			System.out.println("Hasn't pair items in array with this sum...");
		}
		
		if(findTripleWithSum(numbers.clone(), 10)) {
			System.out.println("Has triple items in array with this sum is 10...");
		} else {
			System.out.println("Hasn't pair items in array with this sum is 10...");
		}
		
		if(hasSubsetWithSum0(numbers)) {
			System.out.println("Has triple items in array with this sum is 0...");
		} else {
			System.out.println("Hasn't pair items in array with this sum is 0...");
		}
	}

}
