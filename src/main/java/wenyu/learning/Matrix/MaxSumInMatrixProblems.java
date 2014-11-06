package wenyu.learning.Matrix;

/*
 * Brute force:
 * There are total O(n^2) row range and O(n^2) 
 * column range. So total O(n^4) sub matrix. 
 * To find the sum of any sub matrix we need to 
 * do a O(n^2) operation. So the brute force 
 * algorithm will have a complexity of O(n^6).
 * 
 * Better Solution:
 * We will create a same sized matrix to keep 
 * the vertical sum of the original matrix. 
 * For example verticalSum[i,j]=arr[0,j]+arr[1,j]+...+arr[i,j]. 
 * Now we will take the row range and move from left to right to 
 * find the maximum sum. There are O(n^2) row range possible. 
 * Row range will be a range from rowStart to rowEnd. 
 * Both of these variables can vary from 1 to n. 
 * So there are O(n^2) rowRange possible.  
 * For any starting row we start with one row and move from left to right. 
 * Then we take 2 rows and move from left to right. 
 * While doing so, we maintain an array sum which maintains the 
 * vertical sum of the selected row range. 
 * This sum array and vertical sum matrix will help us find the sum 
 * for a new sub matrix in O(1) time.  
 * So the total time complexity will be O(n^3). 
 * O(n^2) for row range, and we will have a O(column) operation 
 * inside that. So the total complexity will become O(n^3).
 * 
 * 一个子矩阵是由原矩阵中的行列组成，在求解的过程中只要找出其最大的和，
 * 在查找一维矩阵（即一维数组）时，求n个元素的和最大的子区间，我们采用的思想
 * 是扫描这一维矩阵，用max表示出现的和的最大值，sum表示从左至右的求和的值，
 * 我们要考虑sum值出现的情况，如果sum < 0这种求和是没有意义的，因为任何数加
 * 上一个小于0的负数其值必然减少，也就是这时是不可能出现最大的值，这样就可以
 * 采用将要加上的元素值赋给sum，以该点做为下一个求和的开始
 */
public class MaxSumInMatrixProblems {
	public static int findMaximumSumSubMatrix_BruteForce(int[][] arr) {
		int biggest = Integer.MIN_VALUE;
		for(int i=1; i<=arr[0].length; i++) {						// O(n^2) column range
			for(int j=0; j+i-1<arr[0].length; j++) {
				for(int k=1; k<=arr.length; k++) {					// O(n^2) row range
					for(int l=0; l+k-1<arr.length; l++) {
						if(i==2&&k==1) {
							System.out.print("");
						}
						int sum = 0;
						for(int m=j; m<j+i; m++) {					// O(n^2) operation to do sum				
							for(int n=l; n<l+k; n++) {
								sum += arr[n][m];
								if(sum > biggest) {
									biggest = sum;
								}
							}
						}
					}
				}
			}
		}
		
		return biggest;
	}
	
	
	private static int findMaximumSumSubMatrix(int[][] arr, int[] leftTopRightBottom) {
		leftTopRightBottom[0] = 0;
		leftTopRightBottom[1] = 0;
		leftTopRightBottom[2] = 0;
		leftTopRightBottom[3] = 0;
		int rows = arr.length;
		int cols = arr[0].length;
		int[] sum = new int[cols];
		int[] pos = new int[cols];
		int localMax;
		int maxSum = arr[0][0];
		int[][] verticalSum = new int[rows][cols];

		for (int jCol = 0; jCol < cols; jCol++) {
			for (int iRow = 0; iRow < rows; iRow++) {
				if (iRow == 0) {
					verticalSum[iRow][jCol] = arr[iRow][jCol];
				} else {
					verticalSum[iRow][jCol] = arr[iRow][jCol] + verticalSum[iRow - 1][jCol];
				}
			}
		}
		
		for (int iRow = 0; iRow < rows; iRow++) {
			for (int k = iRow; k < rows; k++) {
				for (int index = 0; index < cols; index++) {
					sum[index] = 0;
					pos[index] = 0;
				}
				localMax = 0;
				int tmp = 0;
				if (iRow > 0) {
					tmp = verticalSum[iRow - 1][0];
				}
				sum[0] = verticalSum[k][0] - tmp;
				for (int j = 1; j < cols; j++) {
					tmp = 0;
					if (iRow > 0) {
						tmp = verticalSum[iRow - 1][j];
					}
					if (sum[j - 1] > 0) {
						sum[j] = sum[j - 1] + verticalSum[k][j] - tmp;
						pos[j] = pos[j - 1];
					} else {
						sum[j] = verticalSum[k][j] - tmp;
						pos[j] = j;
					}
					if (sum[j] > sum[localMax]) {
						localMax = j;
					}
				}
				if (sum[localMax] > maxSum) {
					maxSum = sum[localMax];
					leftTopRightBottom[0] = pos[localMax];
					leftTopRightBottom[1] = localMax;
					leftTopRightBottom[2] = iRow;
					leftTopRightBottom[3] = k;
				}
			}
		}
		return maxSum;
	}
	
	public static int maxKKSubMatrixSum(int[][] mat, int k) {
		/* Find the max sum K*k sub-matrix in given matrix.
		 * O(n*m + (n-k)*(m-k)) ==> O(n^2)
		 * 
		 * Logic:
		 * 	1. first generate an aux matrix, containing the sum of all left and above cell.
		 * 	   eg. aux[1][2]=mat[0][0]+mat[0][1]+mat[0][2]+mat[1][0]+mat[1][1]+mat[1][2];
		 *  2. From the aux matrix
		 *     a) from mat[k-1][k-1] to mat[m][n]
		 *     b) if m>k then need to reduce aux[m-k-1][n-1]. reduce the above sum
		 *     c) if n>k then need to reduce aux[m-1][n-k-1]. reduce left sum
		 *     d) if m>k && n>k then need to add aux[m-k-1][n-k-1]. Since b) and c) reduce this twice
		 */
		
		int biggest = Integer.MIN_VALUE;
		// Step 1: prepare aux matrix
		int[][] num = new int[mat.length][mat[0].length];
		int sum = 0;
		for (int i = 0; i < mat.length; i++) {
			sum += mat[i][0];
			num[i][0] = sum;
		}
		sum = 0;
		for (int j = 0; j < mat[0].length; j++) {
			sum += mat[0][j];
			num[0][j] = sum;
		}
		for (int m = 1; m < mat.length; m++) {
			for (int n = 1; n < mat[0].length; n++) {
				num[m][n] = mat[m][n] + num[m - 1][n] + num[m][n - 1] - num[m - 1][n - 1];
			}
		}
		
		// Step 2: find max sub-matrix
		for (int m=k;m<=num.length;m++) {
			for (int n=k;n<=num[0].length;n++) {
				int tmp = num[m-1][n-1];
				if(m>k) {
					tmp -= num[m-k-1][n-1];
				}
				if(n>k) {
					tmp -= num[m-1][n-k-1];
				}
				if(m>k && n>k) {
					tmp += num[m-k-1][n-k-1];
				}
				
				if(biggest < tmp) {
					biggest = tmp;
				}
			}
		}
		return biggest;
	}

	public static void main(String[] args) {
		int[][] arr = {{1,-2,-7,0},{-6,2,9,2},{-4,-2,-1,4}};
		int k = 3;
		int result = maxKKSubMatrixSum(arr, k);
		System.out.println("Max "+k+"*"+k+" sum is " + result);

		int[] leftRightTopBottom = new int[4];
		int maxsum = findMaximumSumSubMatrix(arr, leftRightTopBottom);
		System.out.println("max sum: " + maxsum);
		System.out.println("indices left right top bottom");
		for (int index : leftRightTopBottom) {
			System.out.print(index + ",");
		}
		
		maxsum = findMaximumSumSubMatrix_BruteForce(arr);
		System.out.println("max1 sum: " + maxsum);
	}

}
