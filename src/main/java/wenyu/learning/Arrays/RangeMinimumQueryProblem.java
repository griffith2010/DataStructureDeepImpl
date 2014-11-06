package wenyu.learning.Arrays;

/*
 * RMQ (Range Minimum Query)
 * Given an array A[0, N-1] find the position of  the element with the minimum value between two given indices. 
 * For example: A = [2,4,5,2,6,7,8,9,1,7]
 * 				RMQ(2,7) = 3
 * 
 * Logic: Using Sparse Table (ST) algorithm: 
 * 		  1. preprocess RMQ for sub arrays of length 2k using dynamic programming. 
 *           We will keep an array M[0, N-1][0, logN] where M[i][j] is the index of the 
 *           minimum value in the sub array starting at i having length 2^j. O(nlogn)
 *           For example(above): M[1][0]=1, M[1][1]=1, M[1][2]=3;
 *           * M[i][j]=(A[M[i][j-1]<=A[M[i+2^(j-1)-1][j-1]]]) ? M[i][j-1] : M[i+2^(j-1)-1][j-1]
 *           * Logic: M[i][j] can be divide to two part, M[i][j-1] and M[i+2^(j-1)-1][j-1], and then find the minimum of these two parts.
 *        2. select two blocks that entirely cover the interval [i..j] and find the minimum between them. Let k = [log(j - i + 1)]
 *           For example(above): RMQ(i,j) = min(M[i][k], M[j-2^k-1][k]); O(1)
 */
public class RangeMinimumQueryProblem {

	public static int usingST(int[] arr, int start, int end, boolean print) {
		if(arr==null || arr.length==0 || start<0 || end>=arr.length || start>end) {
			return -1;
		}
		
		int[][] M = new int[arr.length][arr.length];
		
		// Preprocess M
		for(int i=0; i<arr.length; i++) {
			M[i][0] = i;
		}
		for(int j=1; Math.pow(2,j)<arr.length; j++) {  //Math.pow(2, j) == 1<<j
			for(int i=0; (i+Math.pow(2,j)-1)<arr.length; i++) {
				M[i][j] = (arr[M[i][j-1]]<arr[M[i+(int)Math.pow(2,j-1)][j-1]]) ? M[i][j-1] : M[i+(int)Math.pow(2,j-1)][j-1];
			}
		}
		
		
		// Select two blocks and find the RMQ
		int k = (int) (Math.log(end-start+1)/Math.log(2));
		int result = (arr[M[start][k]]<arr[M[end-(int)Math.pow(2,k)+1][k]]) ? M[start][k] : M[end-(int)Math.pow(2,k)+1][k];
		
		if(print) {
			System.out.println("RMQ(" + start + "," + end + ") is " + result);
		}
		return result;
	}
	
	public static void main(String[] args) {
		int[] arr = {2,4,5,2,6,7,8,9,1,7};
		usingST(arr, 0, 2, true);
	}

}
