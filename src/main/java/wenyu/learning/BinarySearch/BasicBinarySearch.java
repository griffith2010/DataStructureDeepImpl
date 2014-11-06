package wenyu.learning.BinarySearch;

import java.util.Random;

import wenyu.learning.Arrays.UtilsForArray;

public class BasicBinarySearch {
	
	public static int sequentialSearch(int[] array, int k) {
		if(array==null || array.length==0) {
			return -1;
		}
		
		for(int i=0;i<array.length;i++) {
			if(array[i] == k) {
				return i;
			}
		}
		
		return -1;
	}
	
	/**
	 * Basic Binary Search Method without recursion
	 */
	public static int binarySearch(int[] array, int k) {
		if(array==null || array.length==0) {
			return -1;
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
			
			if(array[mid] == k) {
				return mid;
			} else if(array[mid] > k) {
				end = mid - 1;
			} else if(array[mid] < k) {
				start = mid + 1;
			}
		}
		
		return -1;
	}
	
	public static int binarySearchWithRec(int[] array, int start, int end, int k) {
		if(start>end) {
			return -1;
		}
		int mid = start + (end-start)/2;
		if(array[mid] == k) {
			return mid;
		} else if(array[mid] > k) {
			end = mid-1;
			return binarySearchWithRec(array, start, end, k);
		} else {
			start = mid+1;
			return binarySearchWithRec(array, start, end, k);
		}
	}
	
	public static void main(String[] args) {
		int count = 10;
		while(count-- > 0) {
			int[] array = UtilsForArray.generateSortedIntegerArray(10, 1000);
			UtilsForArray.printArray(array);
			int k = new Random().nextInt(10-1);
			System.out.println("Trying to find " + array[k] + ". It's index is " + k);
			
			System.out.println(sequentialSearch(array, array[k]));
			System.out.println(binarySearch(array, array[k]));
			System.out.println(binarySearchWithRec(array, 0, array.length-1, array[k]));
			System.out.println();
			System.out.println();
			System.out.println();
		}
	}
}
