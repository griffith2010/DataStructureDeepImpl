package wenyu.learning.Matrix;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

import wenyu.learning.Heap.MyIndexMinHeap;

/*
 *  In a 2-D matrix, every row is increasingly sorted from left to right, and every column is
 *	increasingly sorted from top to bottom. Please implement a function to check whether a number is in such a
 *	matrix or not. For example, all rows and columns are increasingly sorted in the following matrix. It returns true if it
 *	tries to find number 7, but it returns false if it tries to find number 5.
 *	1 2 8 9
 *	2 4 9 12
 *	4 7 10 13
 *	6 8 11 15
 *
 * Problem 1: Search one item in such matrix
 * Problem 2: Sort items in such matrix;	
 */

public class DiagonalIncMatrixProblems {
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
		/*
		 * Time Complexity: O(N2LogN)
		 */
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
	
	public static void printAscedingWithoutIndexHeap(int[][] matrix) {
		/*
		 * Time Complexity: O(N2LogN)
		 */
		if(matrix==null || matrix.length==0 || matrix[0].length==0) {
			return;
		}
		
		class Structure {
			int value;
			int rowIdx;
			int colIdx;
			public Structure(int value, int ridx, int cidx) {
				this.value= value;
				this.rowIdx = ridx;
				this.colIdx = cidx;
			}
		}
		Comparator<Structure> comp = new Comparator<Structure>() {
			public int compare(Structure s1, Structure s2) {
				return s1.value - s2.value;
			}
		};
		PriorityQueue<Structure> pq = new PriorityQueue<Structure>(matrix.length*matrix[0].length, comp);
		for(int i=0; i<matrix.length; i++) {
			pq.add(new Structure(matrix[i][0], i, 0));
		}
		
		while(!pq.isEmpty()) {
			Structure currMin = pq.poll();
			int currValue = currMin.value;
			int currRowIdx = currMin.rowIdx;
			int currColIdx = currMin.colIdx;
			System.out.print(currValue + " ");
			if(currColIdx+1 < matrix[currRowIdx].length) {
				pq.add(new Structure(matrix[currRowIdx][currColIdx+1], currRowIdx, currColIdx+1));
			}
		}
	}
	
	public static void main(String[] args) {
		int[][] matrix = {{1,2,8,9},{2,4,9,12},{4,7,10,13},{6,8,11,15}};
		
		find(matrix, 15);
		
		printAsceding(matrix);
		
		printAscedingWithoutIndexHeap(matrix);
	}
}
