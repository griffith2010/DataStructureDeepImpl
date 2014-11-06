package wenyu.learning.Arrays;

import java.util.Arrays;

/*
 * Find the min difference of the items in two arrays
 * choose each on in each array
 * 
 * Solution1: using sorting O(nlogn)
 * 	1. first, sorting two arrays
 * 	2. from top of these arrays.
 * 	   two points, if(|arr1[p1]-arr2[p2]|<minDiff)
 * 				   then track this two point
 * 				   if current diff is 0, just return, since 0 is the smallest difference
 * 				   else then
 * 					if (a[ia] < b[ib]) {
 *						ia++;
 *					} else {
 *						ib++;
 *					}
 *
 * Solution2: without sorting O(n^2)
 * 	1. two level loop
 */

public class MinDiffInTwoArrays {
	public static void findWithSort(int[] arr1, int[] arr2) {
		Arrays.sort(arr1);
		Arrays.sort(arr2);
		
		int idx1 = 0;
		int idx2 = 0;
		int minDiff = Integer.MAX_VALUE;
		int currDiff = -1;
		int minDiff1 = -1;
		int minDiff2 = -1;
		
		while(idx1<arr1.length && idx2<arr2.length) {
			currDiff = Math.abs(arr1[idx1] - arr2[idx2]);
			if(currDiff < minDiff) {
				minDiff = currDiff;
				minDiff1 = idx1;
				minDiff2 = idx2;
			}
			
			if(idx1<idx2) {
				idx1++;
			} else {
				idx2++;
			}
		}
		
		System.out.println("The min difference is " + minDiff + " (" + arr1[minDiff1] + " and " + arr2[minDiff2] + ").");
	}

	public static void main(String[] args) {
		int[] arr1 = new int[] {2,4,6,8,10};
		int[] arr2 = new int[] {1,5,6,8,9,20,11};
		findWithSort(arr1, arr2);
		
	}
}
