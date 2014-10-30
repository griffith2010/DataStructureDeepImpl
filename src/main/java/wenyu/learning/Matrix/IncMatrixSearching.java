package wenyu.learning.Matrix;

/**
 * In a 2-D matrix, every row is increasingly sorted from left to right, and the
 * last number in each row is not greater than the first number of the next row.
 * A sample matrix follows. Please implement a function to check whether a
 * number is in such a matrix or not. It returns true if it tries to find the
 * number 7 in the sample matrix, but it returns false if it tries to find the
 * number 12. 
 * 
 * 1 3 5 
 * 7 9 11 
 * 13 15 17
 */
public class IncMatrixSearching {

	public static void find(int[][] matrix, int k) {
		int rows = matrix.length;
		int cols = matrix[0].length;
		int start = 0;
		int end = rows * cols - 1;
		
		while (start <= end) {
			int mid = start + (end - start) / 2;
			int row = mid / cols;
			int col = mid % cols;
			
			int currMidValue = matrix[row][col];
			if (currMidValue == k) {
				System.out.println("Find " + k+ " at matrix[" + row + "][" + col + "].");
				return;
			} else if (currMidValue > k) {
				end = mid - 1;
			} else {
				start = mid + 1;
			}
		}
		
		System.out.println("Cannot Find " + k);
	}

	public static void main(String[] args) {
		int[][] matrix  = {{1,3,5},{7,9,11},{13,15,17}};
		
		find(matrix, 11);
	}

}
