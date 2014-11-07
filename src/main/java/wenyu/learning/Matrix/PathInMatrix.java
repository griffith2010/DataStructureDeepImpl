package wenyu.learning.Matrix;

import wenyu.learning.Matrix.RobotMoveProblems.Restriction;

/*
 * Problem 1:
 * How do you implement a function to check whether there is a path for a 
 * string in a matrix of characters? It moves to left, right, up, and down 
 * in a matrix, and a cell for a step. And cell can be available.
 * 
 * For example, the matrix below with three rows and four columns has a path for the string “bcced” (as highlighted
 * in the matrix). It does not have a path for the string “abcb”, because the first “b” in the string occupies the “b” cell
 * in the matrix, and the second “b” in the string cannot enter the same cell again.
 *  a b c e
 *  s f c s
 *  a d e e
 *  
 * Problem 2: Same as above problem, except some cell are not available. 
 */
public class PathInMatrix {
	public static interface Restriction<E> {
		public boolean verify(E[][] matrix, int row, int col);
	}
	
	public static <E> boolean find(E[][] matrix, E[] arr, Restriction<E> restrict) {
		if(matrix==null || matrix.length==0 || matrix[0].length==0 || arr==null || arr.length==0) {
			return false;
		}
		
		for(int i=0; i<matrix.length; i++) {
			for(int j=0; j<matrix[0].length; j++) {
				boolean[][] visited = new boolean[matrix.length][matrix[0].length];
				if(find_core(matrix, arr, i, j, 0, restrict, visited)) return true;
			}
		}
		
		return false;
	}
	
	private static <E> boolean find_core(E[][] matrix, E[] arr, int row, int col, int currIdx, Restriction<E> restrict, boolean[][] visited) {
		if(currIdx == arr.length) {
			return true;
		} else if(row<0 || row>=matrix.length || col<0 || col>=matrix[0].length || visited[row][col] || !matrix[row][col].equals(arr[currIdx])) {
			return false;
		} else if(!restrict.verify(matrix, row, col)) {
			return false;
		}
		
		boolean hasPath = false;
		visited[row][col] = true;
		
		hasPath = find_core(matrix, arr, row, col-1, currIdx+1, restrict, visited);
		if(hasPath) return true;
		
		hasPath = find_core(matrix, arr, row-1, col, currIdx+1, restrict, visited);
		if(hasPath) return true;
		
		hasPath = find_core(matrix, arr, row+1, col, currIdx+1, restrict, visited);
		if(hasPath) return true;
		
		hasPath = find_core(matrix, arr, row, col+1, currIdx+1, restrict, visited);
		if(hasPath) return true;
		
		visited[row][col] = false;
		return false;
	}
	
	public static void main(String[] args) {
		Restriction<Character> restrict = new Restriction<Character>() {
			public boolean verify(Character[][] matrix, int row, int col) {
//				if(matrix[row][col] > 'c') return false;
//				else return true;
				return true;
			}
			
		};
		
		Character[][] matrix = {{'a','b','c','e'}, {'s','f','c','s'},{'a','d','e','e'}};
		boolean result = find(matrix, new Character[] {'b','c','c','e','d'}, restrict);
		System.out.println("Result is " + result);
	}
}
