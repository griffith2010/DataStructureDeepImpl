package wenyu.learning.Matrix;

/*
 *  1 2 3 4
	5 6 7 8
	9 10 11 12
	13 14 15 16
	
	To 
	
	5 1 2 3
	9 10 6 4
	13 11 7 8
	14 15 16 12
 */
public class MatrixSpiralRotation {

	public static void transfer(int[][] matrix) {
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
	}
	
	private static void print(int[][] matrix) {
		for(int i=0;i<matrix.length;i++) {
			for(int j=0;j<matrix[0].length;j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		int[][] matrix = {{1,2,3},{5,6,7},{9,10,11},{13,14,15}};
		matrix = new int[][] {{1,2,3}};
		transfer(matrix);
		
		print(matrix);
	}

}
