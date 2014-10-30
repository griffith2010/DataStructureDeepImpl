package wenyu.learning.Matrix;

/**
 *  Please print a matrix in spiral order, clockwise from outer rings to inner rings. For example, the
	matrix below is printed in the sequence of 1, 2, 3, 4, 8, 12, 16, 15, 14, 13, 9, 5, 6, 7, 11, 10.
	1 2 3 4
	5 6 7 8
	9 10 11 12
	13 14 15 16
 */

public class MatrixSpiralPrinting {

	private static void printSingleSpiral(int[][] matrix, int level) {
		int row = level;
		int col = level;
		
		while(col<matrix[0].length-level) {
			System.out.print(matrix[row][col++] + " ");
		}
		
		row++;
		col = matrix[0].length-level-1;
		while(row<matrix.length-level) {
			System.out.print(matrix[row++][col] + " ");
		}
		
		col--;
		row = matrix.length-level-1;
		while(col>=level && row>level) {
			System.out.print(matrix[row][col--] + " ");
		}
		
		col = level;
		row--;
		while(row>level && col<matrix[0].length-level-1) {
			System.out.print(matrix[row--][col] + " ");
		}
	}
	
	public static void printMatrixSpiral(int[][] matrix) {
		int level = 0;
		
		while(level*2<matrix.length && level*2<matrix[0].length) {
			printSingleSpiral(matrix, level);
			level++;
		}
	}
	
	public static void main(String[] args) {
		//int[][] matrix = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
		//int[][] matrix = {{1,2,3,4},{5,6,7,8}};
		int[][] matrix = {{1,2,3},{5,6,7},{9,10,11},{13,14,15}};
		
		printMatrixSpiral(matrix);

	}

}
