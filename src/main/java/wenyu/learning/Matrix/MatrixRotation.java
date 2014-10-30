package wenyu.learning.Matrix;

public class MatrixRotation {

	public static void rotateClockwise(int[][] matrix) {
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


		//Print rotated matrix
		System.out.println("Clock-wise rotated matrix:");
		for(int i=0;i<colLen; i++) {
			for(int j=0;j<rowLen;j++) {
				System.out.print(output[i][j]+"  ");
			}
			System.out.println();
		}
	}

	public static void rotateAntiClockwise(int[][] matrix) {
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


		//Print rotated matrix
		System.out.println("Anti-Clock-wise rotated matrix:");
		for(int i=0;i<colLen; i++) {
			for(int j=0;j<rowLen;j++) {
				System.out.print(output[i][j]+"  ");
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		int[][] arr = {{1,-2,-7,0},{-6,2,9,2},{-4,-2,-1,4}};
		
		rotateClockwise(arr);
		System.out.println("=========================================");
		rotateAntiClockwise(arr);

	}

}
