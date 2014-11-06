package wenyu.learning.Maths;

/*
 * Problem 1: Given a positive number n, count all distinct Non-Negative pairs (x, y) that satisfy the inequality x*x + y*y < n.
 * Examples:
 *    Input:  n = 5
 *    Output: 6
 *    The pairs are (0, 0), (0, 1), (1, 0), (1, 1), (2, 0), (0, 2)
 */
public class NonNegativePairsWithRequirements {
	
	public static int problem1(int N, boolean print) {
		int count = 0;
		for(int i=0; i<Math.sqrt(N); i++) {
			count += (int)Math.sqrt(N-i*i-0.0001)+1; // since question is less than, so minus 0.0001 to make sure that.
		}
		if(print) System.out.println("Count of distinct Non-Negative pairs is " + count);
		return count;
	}
	
	public static void main(String[] args) {
		problem1(6, true);
	}

}
