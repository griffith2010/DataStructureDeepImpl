package wenyu.learning.Arrays;

/*
 * There are 2 sorted arrays A and B of size n each. 
 * Write an algorithm to find the median of the array 
 * obtained after merging the above 2 arrays(i.e. array of length 2n).
 * 
 * Logic (Remember size of A and B are same!!!)
 * 	Solution1: O(n)
 * 		1. merge two sorted array. O(n)
 * 		2. find median
 *  Solution2: O(logn) (*)
 *  	1) Calculate the medians m1 and m2 of the input arrays ar1[] and ar2[] respectively.
 *  	2) If m1 and m2 both are equal then we are done. return m1 (or m2)
 *  	3) If m1 is greater than m2, then median is present in one of the below two sub-arrays.
		    a)  From first element of ar1 to m1 (ar1[0...|_n/2_|])
		    b)  From m2 to last element of ar2  (ar2[|_n/2_|...n-1])
 *		4) If m2 is greater than m1, then median is present in one of the below two sub-arrays.
		   a)  From m1 to last element of ar1  (ar1[|_n/2_|...n-1])
		   b)  From first element of ar2 to m2 (ar2[0...|_n/2_|])
 * 		5) Repeat the above process until size of both the sub-arrays becomes 2.
 * 		6) If size of the two arrays is 2 then use below formula to get the median.
    		Median = (max(ar1[0], ar2[0]) + min(ar1[1], ar2[1]))/2
 */
public class FindMedianInTwoSortedArrays {

	public static double find_solution1(int[] arr1, int[] arr2) {
		// merge sorted arrays
		int[] merged = new int[arr1.length+arr2.length];
		int k = 0;
		int i = 0;
		int j = 0;
		
		while(i<arr1.length && j<arr2.length) {
			if(arr1[i]<=arr2[j]) {
				merged[k++] = arr1[i++];
			} else {
				merged[k++] = arr2[j++];
			}
		}
		while(i<arr1.length) {
			merged[k++] = arr1[i++];
		}
		while(j<arr2.length) {
			merged[k++] = arr2[j++];
		}
		
		if(merged.length%2==0) {
			return ((double)merged[merged.length/2]+merged[merged.length/2-1])/2;
		} else {
			return merged[merged.length/2];
		}
	}
	
	public static double find_solution3(int[] arr1, int[] arr2) {
		// merge sorted arrays
		int[] merged = new int[arr1.length+arr2.length];
		int k = 0;
		int i = 0;
		int j = 0;
		
		int smallerMid = -1;
		int biggerMid = -1;
		
		while(i<arr1.length && j<arr2.length) {
			if(arr1[i]<=arr2[j]) {
				merged[k++] = arr1[i++];
			} else {
				merged[k++] = arr2[j++];
			}
			
			if(k==merged.length/2) { 
				smallerMid = merged[k-1];
			}
			if(k==merged.length/2+1) {
				biggerMid = merged[k-1];
				return (double)(smallerMid + biggerMid)/2;
			}
		}
		return -1;
	}
	
	public static double find_solution2(int[] arr1, int s1, int e1, int[] arr2, int s2, int e2) {
		int len = e1-s1+1;
		if(len==2) {
			double v1 = (arr1[s1]>arr2[s2])?arr1[s1]:arr2[s2];
			double v2 = (arr1[e1]<arr2[e2])?arr1[e1]:arr2[e2];
			return (v1 + v2)/2;
		} else {
			double median1 = -1;
			double median2 = -1;
			if(len%2==0) {
				median1 = ((double)arr1[s1+len/2-1] + arr1[s1+len/2])/2;
				median2 = ((double)arr2[s2+len/2-1] + arr2[s2+len/2])/2;
				if(median1==median2) {
					return median1;
				} else if(median1<median2) {
					return find_solution2(arr1, s1+len/2-1, e1, arr2, s2, s2+len/2);
				} else {
					return find_solution2(arr1, s1, s1+len/2, arr2, s2+len/2-1, e2);
				}
			} else {
				median1 = arr1[s1+len/2];
				median2 = arr2[s2+len/2];
				if(median1==median2) {
					return median1;
				} else if(median1<median2) {
					return find_solution2(arr1, s1+len/2, e1, arr2, s2, s2+len/2);
				} else {
					return find_solution2(arr1, s1, s1+len/2, arr2, s2+len/2, e2);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		int[] numbers1 = {1,2,3,5,7,9,10,13};
		int[] numbers2 = {2,4,6,8,10,11,16,18};
		System.out.println("Median is " + find_solution1(numbers1, numbers2));
		
		System.out.println("Median is " + find_solution2(numbers1, 0, numbers1.length-1, numbers2, 0, numbers2.length-1));
		System.out.println("Median is " + find_solution3(numbers1, numbers2));
	}

}
