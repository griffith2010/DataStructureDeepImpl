package wenyu.learning.Matrix;

/*
 * A robot starts at cell (0, 0) of a grid with m rows and n columns. It can move to the left, right, 
 * up, and down, and moves one cell for a step. It cannot enter cells where the digit sum of the row index and
 * column index are greater than a given k.
 * 
 * For example, when k is 18, the robot can reach cell (35, 37) because 3+5+3+7=18. However, it cannot reach cell
 * (35, 38) because 3+5+3+8=19 and that is greater than k. How many cells can the robot reach?
 */



public class RobotMoveProblems {
	public static interface Restriction<E> {
		public boolean verify(E[][] matrix, int row, int col);
	}
	
	public static <E> int totalCellCount(E[][] matrix, Restriction<E> restrict, int startRow, int startCol, boolean print) {
		
		boolean[][] visited = new boolean[matrix.length][matrix[0].length];
		int count = totalCellCount_core(matrix, restrict, startRow, startCol, visited);
		if(print) System.out.println("Result is " + count);
		return count;
	}
	
	private static <E> int totalCellCount_core(E[][] matrix, Restriction<E> restrict, int row, int col, boolean[][] visited) {
		boolean flag = true;
		flag = flag && !(row<0 || col<0 || row>=matrix.length || col>=matrix[0].length);
		flag = flag && !visited[row][col];
		flag = flag && restrict.verify(matrix, row, col);
		if(!flag) return 0;
		
		
		visited[row][col] = true;
		int count = 1;
		count += totalCellCount_core(matrix, restrict, row, col-1, visited);
		count += totalCellCount_core(matrix, restrict, row-1, col, visited);
		count += totalCellCount_core(matrix, restrict, row, col+1, visited);
		count += totalCellCount_core(matrix, restrict, row+1, col, visited);
		return count;
	}
	
	public static void main(String[] args) {
		Restriction<Integer> restrict = new Restriction<Integer>() {
			public boolean verify(Integer[][] matrix, int row, int col) {
				if(matrix[row][col] > 10) return false;
				else return true;
			}
			
		};

		Integer[][] matrix = {{1,2,11}, {9,11,10}, {11,6,3}};
		totalCellCount(matrix, restrict, 0, 0, true);
	}

}
