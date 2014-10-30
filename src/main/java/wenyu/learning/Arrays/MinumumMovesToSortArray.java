package wenyu.learning.Arrays;

public class MinumumMovesToSortArray {
	/*
	 * In order to sort a sequence with minimum number 
	 * of moves, we first find the longest increasing 
	 * subsequence, and then it is only necessary to 
	 * insert numbers out of the longest increasing 
	 * subsequence into the appropriate places.
	 */
	public static int minMoveCount(int[] seq) {
		return seq.length - LongestIncreaseSubsequence.LISCount(seq);
	}
	
	public static void main(String[] args) {
		int[] numbers = {1, 2, 5, 3, 4, 7, 6};
		System.out.println("The minimum moves to sort array is " + minMoveCount(numbers));
	}

}
