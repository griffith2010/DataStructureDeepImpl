package wenyu.learning.Matrix;

/*
 * Given a boolean 2D array, where each row is sorted. 
 * Find the row with the maximum number of 1s.
 * 
 * Example
 * Input matrix
 * 0 1 1 1
 * 0 0 1 1
 * 1 1 1 1  // this row has maximum 1s
 * 0 0 0 0
 * 
 * Output: 2
 * 
 * Logic:
 * 	Solution 1: Do a row wise traversal of the matrix O(m*n).
 *  Solution 2: Since rows are sorted, use binary search find first 1 in each row. (m*logn)
 */

public class FindRowContainMostOne {
	
	private static int findFirstOne(int[][] matrix, int row, int start, int end) {
		while(start <= end) {
			int mid = start + (end-start)/2;
			if(matrix[row][mid]==1 && mid>0 && matrix[row][mid-1]==1) {
				return findFirstOne(matrix, row, start, mid-1);
			} else if(matrix[row][mid]==1 && (mid==0 || matrix[row][mid-1]<1)) {
				return mid;
			} else if(matrix[row][mid] > 1) {
				return findFirstOne(matrix, row, start, mid-1);
			} else if(matrix[row][mid] < 1) {
				return findFirstOne(matrix, row, mid+1, start);
			}
		}
		return matrix.length;
	}
		
	public static int find_solution2(int[][] matrix) {
		int maxRow = -1;
		int maxOnes = -1;
		
		for(int i=0; i<matrix.length; i++) {
			int first = findFirstOne(matrix, i, 0, matrix[i].length-1);
			int oneCount = matrix[i].length - first;
			if(oneCount > maxOnes) {
				maxOnes = oneCount;
				maxRow = i;
			}
		}
		
		return maxRow;
	}
	
	
	public static void main(String[] args) {
		int[][] matrix = {{0,1,1,1}, {0,0,1,1}, {0,1,1,1}, {0,0,0,0}};
		int row = find_solution2(matrix);
		System.out.println("Max row: " + row);
	}

}
