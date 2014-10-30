package wenyu.learning.Matrix;

/*
 *  find the maximum areas(m*n) covered by 0.
 *  Problem1:   Find maximum area(m*n) covered by 0. 
 *     Logic:   See the comment in the code.
 *  
 *  Problem2:   Find maximum square(m*m) covered by 0.
 *     Logic:   Let the given binary matrix be M[R][C]. The idea of the algorithm 
 *  		    is to construct an auxiliary size matrix S[][] in which each entry S[i][j] 
 *  			represents size of the square sub-matrix with all 1s including M[i][j] where 
 *  			M[i][j] is the right-most and bottom-most entry in sub-matrix.
 *  			1) Construct a sum matrix S[R][C] for the given M[R][C].
 *				     a)	Copy first row and first columns as it is from M[][] to S[][], if 0 copy as 1. if 1 copy as 0
 *				     b)	For other entries, use following expressions to construct S[][]
 *				         If M[i][j] is 0 then
 *				            S[i][j] = min(S[i][j-1], S[i-1][j], S[i-1][j-1]) + 1
 *				         Else If M[i][j] is 1
 *				            S[i][j] = 0
 *				2) Find the maximum entry in S[R][C]
 * 				3) Using the value and coordinates of maximum entry in S[i], print sub-matrix of M[][]
 */
public class MatrixaxZeroCoverAreaProblems {

	public static void problem1(int[][] matrix) {
		// find the maximum rectangular area covered by 0
		int MaxX = matrix[0].length;
		int MaxY = matrix.length;
		int maxM = -1, maxN = -1, maxArea = -1;
		for(int i=0; i<MaxY; i++) {
			for(int j=0; j<MaxX; j++) {
				int lx, rx, uy, dy;
				if(matrix[i][j] == 0) {
					// grow horizontally first
					lx=j-1;
					rx=j+1;
					while(lx>=0 && matrix[i][lx]==0) lx--;  //if left is also 0
					lx++;
					while(rx<MaxX && matrix[i][rx]==0) rx++;  // if right is also 0
					rx--;
					
					// check vertically 
					uy=i-1;
					dy=i+1;
					// Go upward, see if horizontally covered with 0
					while(uy>=0 && matrix[uy][j]==0) {
						int minx2=j-1, maxx2=j+1;
						while(minx2>=0 && minx2>=lx && matrix[uy][minx2]==0) minx2--;
						minx2++;
						while(maxx2<MaxX && maxx2<=rx && matrix[uy][maxx2]==0) maxx2++;
						maxx2--;
						if(minx2>lx) lx = minx2;
						if(maxx2<rx) rx = maxx2;
						uy--;
					}
					uy++;
					// Go downward, see if horizontally covered with 0
					while(dy<MaxY && matrix[dy][j]==0) {
						int minx2=j-1, maxx2=j+1;
						while(minx2>=0 && minx2>=lx && matrix[dy][minx2]==0) minx2--;
						minx2++;
						while(maxx2<MaxX && maxx2<=rx && matrix[dy][maxx2]==0) maxx2++;
						maxx2--;
						if(minx2>lx) lx = minx2;
						if(maxx2<rx) rx = maxx2;
						dy++;
					}
					dy--;
					int areax = rx-lx+1;
					int areay = dy-uy+1;
					int area = areax*areay;

					System.out.println("("+j+","+i+")  area : (" + areax + "," + areay + ")");
					if(area>maxArea) {
						maxArea = area;
						maxM = areax;
						maxN = areay;
					}				
				}
			}
		}

		System.out.println("Max area (" + maxArea + ") : (" + maxM + "," + maxN + ")");
	}
	
	public static void problem2(int[][] matrix) {
		// Step1: Construct a sum matrix S[R][C] for the given M[R][C]
		int[][] aux = new int[matrix.length][matrix[0].length];
		for(int i=0; i<matrix[0].length; i++) {
			aux[0][i] = (matrix[0][i]==0)? 1 : 0;
		}
		for(int i=0; i<matrix.length; i++) {
			aux[i][0] = (matrix[i][0]==0)? 1 : 0;
		}
		
		for(int i=1; i<aux.length; i++) {
			for(int j=1; j<aux[0].length; j++) {
				if(matrix[i][j] == 0) {
					aux[i][j] = (aux[i-1][j]<aux[i][j-1]) ? aux[i-1][j] : aux[i][j-1];
					aux[i][j] = (aux[i][j]<aux[i-1][j-1]) ? aux[i][j] : aux[i-1][j-1];
					aux[i][j] += 1;
				} else {
					aux[i][j] = 0;
				}
			}
		}
		
		// int maxRow = 0;
		// int maxCol = 0;
		int max = Integer.MIN_VALUE;
		for(int i=1; i<aux.length; i++) {
			for(int j=1; j<aux[0].length; j++) {
				if(aux[i][j] > max) {
					// maxRow = i;
					// maxCol = j;
					max = aux[i][j];
				}
			}
		}
		System.out.println(max*max);
	}

	public static void main(String[] args) {
		int matrix[][] = {{ 0,0,0,1,1,1,1,1,1,0,0,0,1,1 }, 
						  { 1,1,1,0,0,0,1,0,1,1,1,1,1,0 },
						  { 1,1,0,0,0,1,1,1,1,1,1,1,0,0 },
						  { 0,0,1,1,1,0,0,0,0,0,1,1,1,1 },
						  { 0,0,1,1,0,0,0,0,1,1,0,0,0,0 },
						  { 1,1,0,0,0,0,0,0,0,0,0,1,1,0 }
						 };
		problem1(matrix);
		problem2(matrix);
	}

}
