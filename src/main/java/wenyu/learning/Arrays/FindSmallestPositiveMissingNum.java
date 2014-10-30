package wenyu.learning.Arrays;

import java.util.Arrays;
import java.util.Random;

/*
 * Find the smallest non-negative number 
 * missing from an sorted/unsorted array which contains distinct value
 */
public class FindSmallestPositiveMissingNum {
	
	public static void findFromSortedArrWithoutNegativeNumber(int[] arr, int offset) {
		/* 
		 * Logic (Without negative numbers):
		 * 	Solution1: O(n): Scan one by one
		 * 	Solution2: O(logn)
		 */
		
		int front = offset;
		int end = arr.length-1;
		
		while(front<end) {
			int mid = front + (end-front)/2;
			if(arr[mid] == mid-offset) {
				front = mid+1;
			} else {
				end = mid;
			}
		}
		
		if(arr[front] != front-offset) {
			System.out.println("Missing integer is " + (front-offset));
		} else {
			System.out.println("Don't have missing integer");
		}
	}
	
	public static void findFromSortedArr(int[] arr) {
		/*
		 * Logic (logn):
		 * 	   1. first find the offset which contains the first non-negative number
		 * 	   2. use the above function to find the missing number
		 */
		
		int start = 0;
		int end = arr.length-1;
		
		while(start<end) {
			int mid = start + (end-start)/2;
			if(arr[mid] < 0) {
				start = mid+1;
			} else if(arr[mid] >= 0) {
				end = mid;
			}
		}
		int offset = start;
		findFromSortedArrWithoutNegativeNumber(arr, offset);
		
	}
	
	public static void findFromUnsortedArr(int[] arr) {
		/*
		 * Logic:
		 * 	Solution1: O(n^2): scan from 0 and then scan all array to find it exists
		 * 	Solution2: O(nlogn): sort array first and then binary find 
		 *  Solution3: Using hash to store and scan using O(n) time and O(n) space
		 *  Solution4: We traverse the array containing all positive numbers and to 
		 *  		   mark presence of an element x, we change the sign of value at 
		 *  		   index x to negative. We traverse the array again and print the 
		 *  		   first index which has positive value;
		 */
		int len = arr.length;
		for(int i=0;i<arr.length;i++) {
			if(arr[i] < 0) {
				arr[i] = 0;
			}
		}
		for(int i=0;i<arr.length;i++) {
			int tmp = Math.abs(arr[i]);
			if(tmp<len && tmp>0 && arr[tmp-1]>0) {
				arr[tmp-1] *= -1;
			}
		}
		
		for(int i=0;i<arr.length;i++) {
			if(arr[i] > 0) {
				System.out.println("Missing integer is " + (i+1));
				return;
			}
		}
	}
	
	private static int[] generateArr(int count) {
		int[] arr = new int[count];
		int missing = new Random().nextInt(count);
		for(int i=0; i<count; i++) {
			arr[i] = (i>=missing)? i+1:i;
		}
		System.out.println(Arrays.toString(arr));
		System.out.println("Missing: " + missing);
		return arr;
	}
	
	public static void main(String[] args) {
		int[] arr = generateArr(10);
		findFromSortedArrWithoutNegativeNumber(arr, 0);
		
		arr = new int[] {-2,-1,0,1,2,3,5,6,7,8,9};
		findFromSortedArr(arr);
//		
//		int[] arr1 = {1, 1, 0, -1, -2};
//		findFromUnsortedArr(arr1);
	}

}
