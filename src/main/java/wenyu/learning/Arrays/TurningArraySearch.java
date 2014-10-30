package wenyu.learning.Arrays;

import java.util.Random;

import wenyu.learning.Arrays.ArrayUtils;

/*
 * Find the turning index of a array
 * For example: arr: 1,2,3,5,2,1 --> turning index is 3
 * 
 * Logic:
 * 	Solution1: sequence scan
 *  Solution2: O(logn)
 *  	1. find the mid of array,
 *  	2. if array[mid]>=array[mid-1] and array[mid]<=array[mid+1]
 *  	   means mid is in the increase sub-array
 *         then start=mid
 *      3. reverse...
 */
public class TurningArraySearch {
	public static final int runtime = 1;
	private static final int ArrayLen = 10;
	private static final int MaxItem = 1000;

	private static int[] generateTunningArray() {
		int[] tmpArr = ArrayUtils.generateSortedIntegerArray(ArrayLen, MaxItem);
		int turningIdx = new Random().nextInt(ArrayLen - 1);		
		
		int start = 0;
		int end = tmpArr.length-1;
		
		while(start<end) {
			int tmp = tmpArr[start];
			tmpArr[start] = tmpArr[end];
			tmpArr[end] = tmp;
			start++;
			end--;
		}
		
		start = 0;
		end = turningIdx;
		
		while(start<end) {
			int tmp = tmpArr[start];
			tmpArr[start] = tmpArr[end];
			tmpArr[end] = tmp;
			start++;
			end--;
		}
		
		return tmpArr;
	}
	
	public static void sequentialSearch(int[] array) {
		for(int i=0;i<array.length-1;i++) {
			if(array[i] > array[i+1]) {
				System.out.println("Find such value. Index is " + i + ".");
				return;
			}
		}
		System.out.println("No such value in the array.");
	}
	
	public static void binarySearch(int[] array) {
		if (array.length <= 2)
			System.out.println("No such value in the array.");
		int left = 0;
		int right = array.length - 1;
		while (right > left + 1) {
			int middle = (left + right) / 2;
			if (middle == 0 || middle == array.length - 1) {
				System.out.println("No such value in the array.");
			
			}
			if (array[middle] >= array[middle - 1] && array[middle] > array[middle + 1]) {
				System.out.println("Find such value. Index is " + middle + ".");
				return;
			} else if (array[middle] >= array[middle - 1] && array[middle] <= array[middle + 1]) {
				left = middle;
			} else {
				right = middle;
			}
		}
		System.out.println("No such value in the array.");
	}
	
	public static void main(String[] args) throws Exception {
		int[] array = generateTunningArray();
		
		binarySearch(array);
		sequentialSearch(array);
	}


}
