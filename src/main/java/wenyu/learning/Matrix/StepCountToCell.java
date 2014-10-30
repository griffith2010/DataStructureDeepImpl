package wenyu.learning.Matrix;

/*
 * Given a MXN matrix. To find the number of ways to reach the mth 
 * row and nth column cell from 0,0 cell. 
 * 
 * Find the same if some of the cells are marked as not reachable.
 */
public class StepCountToCell {
	public static int findStepCount(int m, int n) {
		int step1 = 0;
		int step2 = 0;
		
		if(m==0&&n==0) {
			return 1;
		}
		if(m>0) {
			step1 = findStepCount(m-1, n);
		}
		if(n>0) {
			step2 = findStepCount(m, n-1);
		}
		
		return step1 + step2;
	}
	
	public static void main(String[] args) {
		int maxM = 10;
		int maxN = 20;
		System.out.println(findStepCount(maxM,maxN));
	}

}
