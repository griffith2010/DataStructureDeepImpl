package wenyu.learning.Matrix;

import wenyu.learning.Heap.MyIndexMinHeap;

/**
 *  In a 2-D matrix, every row is increasingly sorted from left to right, and every column is
	increasingly sorted from top to bottom. Please implement a function to check whether a number is in such a
	matrix or not. For example, all rows and columns are increasingly sorted in the following matrix. It returns true if it
	tries to find number 7, but it returns false if it tries to find number 5.
	1 2 8 9
	2 4 9 12
	4 7 10 13
	6 8 11 15
 */

public class DiagonalIncMatrixSearching {
	public static void find(int[][] matrix, int k) {
		int currRow = 0;
		int currCol = matrix[0].length-1;
		
		while(currRow<matrix.length && currCol>=0) {
			int currValue = matrix[currRow][currCol];
			
			if(currValue == k) {
				System.out.println("Find " + k + " at matrix[" + currRow + "][" + currCol + "].");
				return;
			} else if(currValue < k) {
				currRow++;
			} else {
				currCol--;
			}
		}
		System.out.println("Cannot Find " + k);
	}
	
	public static void printAsceding(int[][] matrix) {
		MyIndexMinHeap<Integer> heap = new MyIndexMinHeap<Integer>(matrix.length);
		int[] tmpIdx = new int[matrix.length];
		for(int i=0;i<matrix.length;i++) {
			heap.insert(i, matrix[i][tmpIdx[i]]);
		}
		
		while(!heap.isEmpty()) {
			int minIndex = heap.minIndex();
			int minKey = heap.minKey();
			if(tmpIdx[minIndex]+1<matrix[0].length) {
				tmpIdx[minIndex]++;
				heap.increaseKey(minIndex, matrix[minIndex][tmpIdx[minIndex]]);
				System.out.print(minKey + " ");
			} else {
				System.out.print(minKey + " ");
				heap.delMin();
			}
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		int[][] matrix = {{1,2,8,9},{2,4,9,12},{4,7,10,13},{6,8,11,15}};
		
		find(matrix, 15);
		
		printAsceding(matrix);
	}
}
