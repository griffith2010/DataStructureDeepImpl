package wenyu.learning.Arrays;

import java.util.Arrays;
import java.util.Random;

import wenyu.learning.Arrays.ArrayUtils;

/*
 * Find the rotate count of a sorted array
 * For example: init: 1,2,3,4,5,6 --> rotated: 5,6,1,2,3,4: count:2;
 * 
 * Solution1: O(n)
 * 		find the index sequence scan the array
 * 		if array[i]>array[i+1] then i is the rotate index
 * Solution2: O(logn), worst:O(n)
 * 		1. Two pointers P1 and P2. According to the rotation rule, 
 * 		   the first element should be greater than or equal to the last one.
 * 		2. If array[mid]>=array[P1], the middle number is in the first 
 * 		   increasingly sorted sub-array. then it moves P1 to the middle
 * 		3. if array[mid]<=array[P2], the middle number is in the second sub-array
 * 		   then, so it moves P2 to the middle.
 * 		4. It stops searching when P1 points to the last number of 
 *         the first sub-array and P2 points to the first number of 
 *         the second sub-array, which is also the minimum of the array.
 *      5. Special case: the algorithm cannot determine if the middle 
 *                       element belongs to the first or second subarray 
 *                       when the middle element and the two numbers pointed 
 *                       to by P1 and P2 are equal, and it cannot move pointers 
 *                       to narrow the search range. It has to search sequentially 
 *                       in such a scenario.
 */

public class RotatedArraySearch {
	public static final int runtime = 1;
	private static final int ArrayLen = 10;
	private static final int MaxItem = 100;

	private static int[] generateRotatedArray() {
		
		int[] tmpArr = ArrayUtils.generateSortedIntegerArray(ArrayLen, MaxItem);
		int rotateIdx = new Random().nextInt(ArrayLen - 1);		
		
		int[] tmp = new int[rotateIdx+1];
		int i = 0;
		for(;i<=rotateIdx;i++) {
			tmp[i] = tmpArr[i];
		}
		for(i=0;i+rotateIdx+1<tmpArr.length;i++) {
			tmpArr[i] = tmpArr[i+rotateIdx+1];
		}
		for(int j=0;i<tmpArr.length;i++,j++) {
			tmpArr[i] = tmp[j];
		}
		return tmpArr;
	}
	
	private static int[] generateRotatedArrayWithoutAuxSpace() {
		/*
		 * Logic:
		 * 	1. reverse whole array
		 * 	2. reverse first part of array before rotateIdx
		 *  3. reverse second part of array after rotateIdx
		 */
		int[] tmpArr = ArrayUtils.generateSortedIntegerArray(ArrayLen, MaxItem);
		int rotateIdx = new Random().nextInt(ArrayLen - 1);		
		
		// Step 1: reverse whole array
		int start = 0;
		int end = tmpArr.length-1;
		while(start<end) {
			int tmp = tmpArr[start];
			tmpArr[start] = tmpArr[end];
			tmpArr[end] = tmp;
			start++;
			end--;
		}
		
		// Step 2: reverse first part of array
		start = 0;
		end = rotateIdx-1;
		while(start < end) {
			int tmp = tmpArr[start];
			tmpArr[start] = tmpArr[end];
			tmpArr[end] = tmp;
			start++;
			end--;
		}
		
		// Step 3: reverse second part of array
		start = rotateIdx;
		end = tmpArr.length-1;
		while(start < end) {
			int tmp = tmpArr[start];
			tmpArr[start] = tmpArr[end];
			tmpArr[end] = tmp;
			start++;
			end--;
		}
		
		
		return tmpArr;
	}
	
	public static int sequentialSearch(int[] array) {
		for(int i=0;i<array.length-1;i++) {
			if(array[i] > array[i+1]) {
				System.out.println("Find such value. Index is " + i + ".");
				return i;
			}
		}
		System.out.println("No such value in the array.");
		return -1;
	}
	
	public static int binarySearch(int[] array) {
		int start = 0;
		int end = array.length-1;
		int mid;
		
		while(start<=end) {
			if((end-start)==1 && array[start]>=array[end]) {
				System.out.println("Find such value. Index is " + start + ".");
				return start;
			} else if(array[start]<array[end]) {
				System.out.println("No such value in the array.");
				return -1;
			}
			
			mid = (start+end)/2;
			
			/**
			 * Remember this case!!!
			 * ==========================================================
			 */
			// if numbers with indexes index1, index2, indexMid
			// are equal, search sequentially
			if(array[start] == array[end] && array[mid] == array[start]) {
				System.out.println("Having same values...");
				for(int i=start;i<=end;i++) {
					if(array[i] > array[i+1]) {
						System.out.println("Find such value. Index is " + i + ".");
						return i;
					}
				}
			}
			/**
			 * ==========================================================
			 */
			
			if(array[mid]>=array[start]) {
				start = mid;
			} else if(array[mid]<=array[end]) {
				end = mid;
			} else {
				start = mid;
			}
		}
		System.out.println("No such value in the array.");
		return -1;
	}
	
	public static void findItemInRotatedSortedArray(int[] array, int k) {
		int offset = sequentialSearch(array);
		offset++;
		
		if(array==null || array.length==0) {
			System.out.println("Cannot Find "+k);
			return;
		}
		
		int start = 0;
		int end = array.length-1;
		
		while(start <= end) {
			// Attention: This is wrong way
			// it fails if the sum of low and high 
			// is greater than the maximum positive 
			// int value
			// int mid = (start + end)/2; 
			
			// Right way is:
			int mid = start + (end-start)/2;
			int midIdx = (mid+offset)%array.length;
			if(array[midIdx] == k) {
				System.out.println("Find "+k+" at index "+midIdx);
				return;
			} else if(array[midIdx] > k) {
				end = mid - 1;
			} else if(array[midIdx] < k) {
				start = mid + 1;
			}
		}
		
		System.out.println("Cannot Find "+k);
		return;
	}
	
	public static void main(String[] args) throws Exception {
		//int[] array = generateRotatedArray();
		int[] array = generateRotatedArrayWithoutAuxSpace();
		
		sequentialSearch(array);
		System.out.println();
		binarySearch(array);
		
		findItemInRotatedSortedArray(array, 80);
	}


}
