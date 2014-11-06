package wenyu.learning.Arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;

/*
 * Problem 1: Find the most items in an array which can form a contiguous elements array. 
 * 			  Note: element is not duplicated!
 * 			  Note: element could not be contiguous in origin array
 * 			  example: [15,12,11,20,10] ==> [10,11,12]
 * 			  Solution1: sort first and find the largest O(nlogn)/O(1)
 * 			  Solution2: Use extra space to store information of if having contiguous element of each element O(n)/O(n)
 * Problem 2: Given an array of distinct integers, find length of the longest subarray which contains numbers 
 * 			  that can be arranged in a continuous sequence.
 * 			  Note: element is not duplicated!
 * 			  Node: element must be contiguous in origin array
 * 			  Logic: If all elements are distinct, then a subarray has contiguous elements if and only if the difference 
 * 					 between maximum and minimum elements in subarray is equal to the difference between last and 
 *                   first indexes of subarray. O(n^2)
 * Problem 3: Given an array of distinct integers, find length of the longest subarray which contains numbers
 * 			  that can be arranged in a continuous sequence.
 * 			  Note: element can be duplicated! 
 * 					e.g. the array {12, 14, 12} follows the first property, but numbers in it are not contiguous elements.
 * 			  Node: element must be contiguous in origin array
 * 			  Logic: Same as above logic, but with another hash set for every subarray and if we find an element already in hash, 
 * 					 we don’t consider the current subarray. O(n^2)
 *
 */

public class ContiguousSequenceFindingProblems {

	public static int problem1_solution1(int[] arr, boolean print) {
		if(arr==null || arr.length==0) {
			return -1;
		} else if(arr.length==1) {
			return 1;
		}
		
		// Sort array
		Arrays.sort(arr);
		
		// Find largest sub-array elements
		int largestCount = 0;
		int currCount = 0;
		for(int i=0; i<arr.length-1; i++) {
			currCount++;
			if(arr[i] != arr[i+1]-1) {
				if(currCount > largestCount) {
					largestCount = currCount;
				}
				currCount = 0;
			}
		}
		if(print) {
			System.out.println("Largest sub-array elements count is " + largestCount);
		}
		return largestCount;
	}
	
	public static int problem1_solution2(int[] arr, boolean print) {
		HashMap<Integer, Boolean> hasBigger = new HashMap<Integer, Boolean>();
		HashMap<Integer, Boolean> hasSmaller = new HashMap<Integer, Boolean>();
		
		// Prepare hasBigger and hasSmaller
		for(int i=0; i<arr.length; i++) {
			hasBigger.put(arr[i], false);
			hasSmaller.put(arr[i], false);
			if(hasBigger.containsKey(arr[i]+1)) {
				hasSmaller.put(arr[i]+1, true);
				hasBigger.put(arr[i], true);
			}
			if(hasSmaller.containsKey(arr[i]-1)) {
				hasBigger.put(arr[i]-1, true);
				hasSmaller.put(arr[i], true);
			}
		}
		
		// Find largest sub-array elements
		int largestCount = 0;
		int currCount = 0;
		Iterator<Entry<Integer, Boolean>> it = hasBigger.entrySet().iterator();
		while(it.hasNext() && !hasSmaller.isEmpty()) {
			Entry<Integer, Boolean> entry = it.next();
			int element = entry.getKey();
			if(hasSmaller.containsKey(element)) {
				currCount = 1;
				int currEle = element;
				while(hasBigger.get(currEle)) {
					currEle++;
					currCount++;
					hasSmaller.remove(currEle);
				}
				
				currEle = element;
				boolean currEleHasSmaller = hasSmaller.get(currEle);  
				while(!hasSmaller.isEmpty() && currEleHasSmaller) {
					currEle--;
					currCount++;
					currEleHasSmaller = hasSmaller.get(currEle);
					hasSmaller.remove(currEle);
				}
				
				hasSmaller.remove(element);
				if(currCount > largestCount) {
					largestCount = currCount;
				}
			}
		}
		
		if(print) {
			System.out.println("Largest sub-array elements count is " + largestCount);
		}
		return largestCount;
	}
	
	public static int problem2(int[] arr, boolean print) {
		if(arr==null || arr.length==0) {
			return 0;
		} else if(arr.length==1) {
			return 1;
		}
		
		int largestCount = 1;
		for(int i=0; i<arr.length; i++) {
			int max = arr[i];
			int min = arr[i];
			for(int j=i+1; j<arr.length; j++) {
				if(arr[j] > max) {
					max = arr[j];
				}
				if(arr[j] < min) {
					min = arr[j];
				}
				
				if((max-min)==(j-i) && (max-min+1)>largestCount) {
					largestCount = max-min+1;
				}
			}
		}
		if(print) {
			System.out.println("Largest continual subarray elements count is " + largestCount);
		}
		return largestCount;
	}
	
	public static int problem3(int[] arr, boolean print) {
		if(arr==null || arr.length==0) {
			return 0;
		} else if(arr.length==1) {
			return 1;
		}
		
		HashSet<Integer> set = new HashSet<Integer>();
		int largestCount = 1;
		for(int i=0; i<arr.length; i++) {
			int max = arr[i];
			int min = arr[i];
			set.clear();
			set.add(arr[i]);
			for(int j=i+1; j<arr.length; j++) {
				if(set.contains(arr[j])) {
					break;
				}
				
				set.add(arr[j]);
				if(arr[j] > max) {
					max = arr[j];
				}
				if(arr[j] < min) {
					min = arr[j];
				}
				
				if((max-min)==(j-i) && (max-min+1)>largestCount) {
					largestCount = max-min+1;
				}
			}
		}
		if(print) {
			System.out.println("Largest continual subarray elements count is " + largestCount);
		}
		return largestCount;
	}
	
	public static void main(String[] args) {
		int[] arr = new int[] {15,12,11,6,3,4,5,20,10,21,13};
		problem1_solution1(arr, true);
		problem1_solution2(arr, true);
		
		arr = new int[]{14, 12, 11, 20};
		problem2(arr, true);
		problem3(arr, true);
	}

}
