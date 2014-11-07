package wenyu.learning.Arrays;

/*
 * If an element at the left side is greater than another element at the right side, they form a
 * reversed pair in an array. How do you get a count of reversed pairs?
 * Note: Numbers in pairs must keep the order of origin array.
 * 
 * For example, there are five reversed pairs in the array {7, 5, 6, 4}, which are (7, 5), (7, 6), (7, 4), (5, 4), and (6, 4).
 * (6, 5) is not, since 5 appears before 6 in origin array
 * 
 * Solution 1: Brute force O(n^2)
 * Solution 2: It recursively splits an array into two sub-arrays. 
 * 			   1. It counts reversed pairs inside a sub-array;
 * 			   2. Merging adjacent sub-arrays and counts reversed pairs while merging them. 
 * 			   Therefore, the solution can be implemented based on the merge sort algorithm.
 * 			   
 *			   Logic of step 2: 
 *			    Two pointers (P1 and P2) are initialized to the end of two sub-arrays. If the number referenced by P1
 *				is greater than the number referenced by P2, there are some reversed pairs in such cases. As shown in
 *				Figure 7-3(a) and Figure 7-3(c), the number of reversed pairs is the same as the number of remaining
 *				numbers in the second sub-array. There are no reversed pairs if the number referenced by P1 is less than
 *				or equal to the number referenced by P2 (Figure 7-3(b)). Another pointer P3 is initialized to the end of the
 *				merged array. The greater number of the two referenced by P1 and P2 is copied to the location referenced
 *				by P3 in order to keep numbers in the merged array sorted. It moves these three pointers backward and
 *				continues to compare, count, and copy until one sub-array is empty. Then the remaining numbers in
 *				the other sub-array are copied to the merged array.
 */
public class ReversePairsInArray {

	public static int solution2(int[] numbers, boolean print) {
		int[] buffer = new int[numbers.length];
		int count = countReversedPairs(numbers, buffer, 0, numbers.length - 1);
		if(print) System.out.println("Result is " + count);
		return count;
	}

	private static int countReversedPairs(int[] numbers, int[] buffer, int start, int end) {
		if(start >= end)
			return 0;
		int middle = start + (end - start) / 2;
		int left = countReversedPairs(numbers, buffer, start, middle);
		int right = countReversedPairs(numbers, buffer, middle + 1, end);
		int between = merge(numbers, buffer, start, middle, end);
		return left + right + between;
	}

	private static int merge(int[] numbers, int[] buffer, int start, int middle, int end) {
		int i = middle; // the end of the first sub-array
		int j = end; // the end of the second sub-array
		int k = end; // the end of the merged array
		int count = 0;
		while(i >= start && j >= middle + 1) {
			if(numbers[i] > numbers[j]) {
				buffer[k--] = numbers[i--];
				count += (j - middle);
			}
			else {
				buffer[k--] = numbers[j--];
			}
		}
		while(i >= start) {
			buffer[k--] = numbers[i--];
		}
		while(j >= middle + 1) {
			buffer[k--] = numbers[j--];
		}
		// copy elements from buffer[] to numbers[]
		for(i = start; i <= end; ++i) {
			numbers[i] = buffer[i];
		}
		return count;
	}

	public static void main(String[] args) {
		int[] numbers = {7, 5, 6, 4};
		solution2(numbers, true);
	}
}
