package wenyu.learning.Matrix;

/*
 * Write an algorithm such that if an element in an MxN matrix is 0, 
 * its entire row and column is set to 0.
 * Solution1:  Time Complexity: O(M*N)/Auxiliary Space: O(M + N)
 * 	1) Create two temporary arrays row[M] and col[N]. Initialize all values of row[] and col[] as 0.
 * 	2) Traverse the input matrix mat[M][N]. If you see an entry mat[i][j] as 0, then mark i in row[] and j in col[].
 * 	3) Traverse the row[] and col[]. If any of the row or column is marked, then mark entire row and col to 0 in mat[][]
 * 
 * Solution2: Time Complexity: O(M*N)/Auxiliary Space: O(1)
 * 	1) Scan the first row and set a variable rowFlag to indicate whether we need to set all 1s in first row or not.
 * 	2) Scan the first column and set a variable colFlag to indicate whether we need to set all 1s in first column or not.
 * 	3) Use first row and first column as the auxiliary arrays row[] and col[] respectively, consider the matrix as submatrix starting from second row and second column and apply method 1.
 * 	4) Finally, using rowFlag and colFlag, update first row and first column if needed.
 */
public class MatrixZeroSettingProblem {

	private static void printMatrix(int[][] matrix) {
		for(int i=0;i<matrix.length;i++) {
			for(int j=0;j<matrix[0].length;j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public static void solution1(int[][] matrix) {
		if(matrix == null) {
			return;
		}
		
		printMatrix(matrix);
		System.out.println();
		
		int[] row = new int[matrix.length];
		int rowCount = 0;
		int[] col = new int[matrix[0].length];
		int colCount = 0;
		for(int i=0;i<matrix.length;i++) {
			for(int j=0;j<matrix[0].length;j++) {
				if(matrix[i][j]==0) {
					row[rowCount++] = i;
					col[colCount++] = j;
				}
			}
		}
		
		for(int i=0;i<rowCount;i++) {
			int rowIdx = row[i];
			for(int j=0;j<matrix[0].length;j++) {
				matrix[rowIdx][j] = 0;
			}
		}
		
		for(int i=0;i<colCount;i++) {
			int colIdx = col[i];
			for(int j=0;j<matrix.length;j++) {
				matrix[j][colIdx] = 0;
			}
		}
		
		printMatrix(matrix);
	}
	
	public static void solution2(int[][] matrix) {
		if(matrix == null) {
			return;
		}
		
		printMatrix(matrix);
		System.out.println();
		
		boolean firstRowFlag = false;
		boolean firstColFlag = false;
		for(int i=0; i<matrix[0].length; i++) {
			if(matrix[0][i] == 0) {
				firstRowFlag = true;
				break;
			}
		}
		for(int i=0; i<matrix.length; i++) {
			if(matrix[i][0] == 0) {
				firstColFlag = true;
				break;
			}
		}
		
		for(int i=1; i<matrix.length; i++) {
			for(int j=1; j<matrix[0].length; j++) {
				if(matrix[i][j]==0) {
					matrix[i][0] = 0;
					matrix[0][j] = 0;
				}
			}
		}
		
		for(int i=1; i<matrix.length; i++) {
			if(matrix[i][0] == 0) {
				for(int j=1;j<matrix[i].length;j++) {
					matrix[i][j] = 0;
				}
			}
		}
		for(int i=1; i<matrix[0].length; i++) {
			if(matrix[0][i] == 0) {
				for(int j=1;j<matrix.length;j++) {
					matrix[j][i] = 0;
				}
			}
		}
		
		if(firstRowFlag) {
			for(int i=0; i<matrix[0].length; i++) {
				matrix[0][i] = 0;
			}
		}
		
		if(firstColFlag) {
			for(int i=0; i<matrix.length; i++) {
				matrix[i][0] = 0;
			}
		}
		
		printMatrix(matrix);
	}
	
	public static void main(String[] args) {
		int[][] matrix = {{0,2,3},{5,6,7},{9,10,11},{13,0,15}};
		solution1(matrix);
		System.out.println("=====================================");
		matrix = new int[][] {{0,2,3},{5,6,7},{9,10,11},{13,0,15}};
		solution2(matrix);
	}

}
