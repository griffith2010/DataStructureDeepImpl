package wenyu.learning.Matrix;

/*
 * Problem 1: Rotate matrix 90 degree, clockwise or anti-clockwise
 * Problem 2: Rotate matrix spirally.
 */

class ClockwiseRotate {
	/*
	 * Problem 1: Rotate matrix 90 degree, clockwise or anti-clockwise
	 */
	public static void rotateClockwise(int[][] matrix, boolean print) {
		/*
		 * 90 degree clockwise rotation of n*m array
		 * Logic:
		 * 	newMatrix[j][rowLen-1-i] = matrix[i][j];
		 */
		int rowLen = matrix.length;
		int colLen = matrix[0].length;
		int[][] output = new int[colLen][rowLen];

		for (int i = 0; i < rowLen; i++)
			for (int j = 0; j < colLen; j++)
				output[j][rowLen - 1 - i] = matrix[i][j];


		if(!print) return;
		//Print rotated matrix
		System.out.println("Clock-wise rotated matrix:");
		for(int i=0;i<colLen; i++) {
			for(int j=0;j<rowLen;j++) {
				System.out.print(output[i][j]+"  ");
			}
			System.out.println();
		}
	}

	public static void rotateAntiClockwise(int[][] matrix, boolean print) {
		/*
		 * 90 degree anti-clockwise rotation of n*m array
		 * Logic:
		 * 	newMatrix[colLen-1-i][i] = matrix[i][j];
		 */
		int rowLen = matrix.length;
		int colLen = matrix[0].length;
		int[][] output = new int[colLen][rowLen];

		for(int i=0;i<rowLen;i++)
			for(int j=0;j<colLen;j++)
				output[colLen-1-j][i] = matrix[i][j];

		if(!print) return;
		//Print rotated matrix
		System.out.println("Anti-Clock-wise rotated matrix:");
		for(int i=0;i<colLen; i++) {
			for(int j=0;j<rowLen;j++) {
				System.out.print(output[i][j]+"  ");
			}
			System.out.println();
		}
	}
}

class RotateSpirally {
	public static void rotate(int[][] matrix, boolean print) {
		int loopCount = (matrix.length<matrix[0].length)?matrix.length/2:matrix[0].length/2;
		
		for(int i=0;i<loopCount;i++) {
			int row = i;
			int col = i;
			int pre = matrix[row][col];
			
			col++;
			for(;col<matrix[0].length-i;col++) {
				int tmp = matrix[row][col];
				matrix[row][col] = pre;
				pre = tmp;
			}
			
			col--;
			row++;
			for(;row<matrix.length-i;row++) {
				int tmp = matrix[row][col];
				matrix[row][col] = pre;
				pre = tmp;
			}
			
			row--;
			col--;
			for(;col>=i;col--) {
				int tmp = matrix[row][col];
				matrix[row][col] = pre;
				pre = tmp;
			}
			
			col++;
			row--;
			for(;row>=i;row--) {
				int tmp = matrix[row][col];
				matrix[row][col] = pre;
				pre = tmp;
			}
		}
		
		if(!print) return;
		//Print rotated matrix
		System.out.println("Anti-Clock-wise rotated matrix:");
		for(int i=0;i<matrix.length; i++) {
			for(int j=0;j<matrix[0].length;j++) {
				System.out.print(matrix[i][j]+"  ");
			}
			System.out.println();
		}
	}
}

public class RotateMatrixProblems {
	public static void main(String[] args) {
		int[][] matrix = {{1,-2,-7,0},{-6,2,9,2},{-4,-2,-1,4}};
		ClockwiseRotate.rotateClockwise(matrix, true);
		System.out.println("=========================================");
		ClockwiseRotate.rotateAntiClockwise(matrix, true);
		System.out.println("=========================================");
		RotateSpirally.rotate(matrix, true);
	}

}
