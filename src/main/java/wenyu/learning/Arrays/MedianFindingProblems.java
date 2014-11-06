package wenyu.learning.Arrays;

import wenyu.learning.Heap.MyBinaryHeap;

/*
 * Problem 1: Find median in array
 * Problem 2: Find median in stream
 * Problem 3: Find median in two sorted arrays with n element each
 */

class MedianInOneArray {
	/*
	 * Problem 1: Find median in array:
	 * Logic: O(n)
	 * 	1. determine the length of array. Even or Odd?
	 *  2. Odd: partition function to find the mid value in array;
	 *  3. Even: partition function to find the mid and mid-1 value in array;
	 */
	private static int partition(int[] numbers, int start, int end) {
		int i = start;
		int j = end;
		int pivotV = numbers[i];
		while (i<j) {
			while(i<j && numbers[j]>=pivotV) {j--;}
			if (i<j) {numbers[i++] = numbers[j];}
			while(i<j && numbers[i]<=pivotV) {i++;}
			if (i<j) {numbers[j--] = numbers[i];}
		}
		numbers[i] = pivotV;
		return i;
	}

	public static float find(int[] numbers, boolean print) {
		int len = numbers.length;
		if ((len & 1) == 0) {
			// len is even number
			boolean findMid = false;
			boolean findMidPlus1 = false;
			float midV = 0;
			float midMinus1V = 0;
			
			int mid = len / 2;
			int start = 0;
			int end = numbers.length - 1;

			int idx = partition(numbers, start, end);
			while (!findMid || !findMidPlus1) {
				if(idx == mid) {
					findMid = true;
					midV = numbers[idx];
					end = idx = 1;
				} else if(idx == mid-1) {
					findMidPlus1 = true;
					midMinus1V = numbers[idx];
					start = idx+1;
				} else if (idx > mid) {
					end = idx - 1;
				} else {
					start = idx + 1;
				}
				idx = partition(numbers, start, end);
			}
			if(print) System.out.println("Median in array: " + (midV+midMinus1V)/2);
			return (midV+midMinus1V)/2;
		} else {
			// len is odd number
			int mid = len / 2;
			int start = 0;
			int end = numbers.length - 1;

			int idx = partition(numbers, start, end);
			while (idx != mid) {
				if (idx > mid) {
					end = idx - 1;
				} else {
					start = idx + 1;
				}
				idx = partition(numbers, start, end);
			}
			if(print) System.out.println("Median in array: " + numbers[len/2]);
			return numbers[len/2];
		}
	}
}

class MedianInStream {
	/*
	 * Problem 2: Find median in stream:
	 * Logic: O(logn) to insert and O(1) to find the median
	 *  1. Implement a new data structure, which has a max heap and min heap.
	 *  2. Suppose that the count of existing numbers is even; a new number will be
	 *     inserted into the min heap. If the new number is less than some numbers
	 *     in the max heap, it violates our rule that all numbers in the min heap
	 *     should be greater than numbers in the max heap.
	 */
	private static final int maxEleCount = 100;
	private static final MyBinaryHeap<Integer> minHeap = new MyBinaryHeap<Integer>(maxEleCount, MyBinaryHeap.MIN_HEAP);;
	private static final MyBinaryHeap<Integer> maxHeap = new MyBinaryHeap<Integer>(maxEleCount, MyBinaryHeap.MAX_HEAP);;

	public static void insert(int num) {
		if (((minHeap.size() + maxHeap.size()) & 1) == 0) {
			// if total size is even, a new number will be inserted into the min heap.
			if (maxHeap.size() > 0 && num < maxHeap.peek()) {
				// If the new number is less than some numbers in the max heap,
				// it violates our rule that all numbers in the min heap should
				// be greater than numbers in the max heap.
				maxHeap.insert(num);
				num = maxHeap.pop();
			}
			minHeap.insert(num);
		} else {
			// Same as the logic above, just reverse
			if (minHeap.size() > 0 && minHeap.peek() < num) {
				minHeap.insert(num);
				num = minHeap.pop();
			}
			maxHeap.insert(num);
		}
	}

	public static float getMedian(boolean print) {
		int size = minHeap.size() + maxHeap.size();
		if (size == 0) {
			if(print) System.out.println("No numbers are available");
			return -1;
		}
		float median = 0;
		if ((size&1) == 1) {
			median = minHeap.peek();
		} else {
			median = ((float)minHeap.peek() + (float)maxHeap.peek())/2;
		}
		
		if(print && median>0) System.out.println("Current median is " + median);
		else if(print) System.out.println("No numbers are available");
		return median;
	}
	
	public static void demo(int[] numbers) {
		for(int i=0;i<numbers.length;i++) {
			System.out.println(numbers[i] + " is inserted. ");
			MedianInStream.insert(numbers[i]);
			MedianInStream.getMedian(true);
		}
	}
}

class MedianInTwoSortedArrays {
	/*
	 * Problem 3: Find median in two sorted arrays with n element each
	 * Logic (Remember size of A and B are same!!!)
	 * 	Solution1: O(n)
	 * 		1. merge two sorted array. O(n)
	 * 		2. find median
	 *  Solution2: O(n) Advanced version of solution1
	 *  	1. merge two sorted array. O(n)
	 *  	2. when index match merged.length/2 & merged.length/2+1. Then just return to save time.
	 *  Solution3: O(logn) (*)
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
	public static double find_solution1(int[] arr1, int[] arr2, boolean print) {
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
			if(print) System.out.println("Median is " + ((double)merged[merged.length/2]+merged[merged.length/2-1])/2);
			return ((double)merged[merged.length/2]+merged[merged.length/2-1])/2;
		} else {
			if(print) System.out.println("Median is " + merged[merged.length/2]);
			return merged[merged.length/2];
		}
	}
	
	public static double find_solution2(int[] arr1, int[] arr2, boolean print) {
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
				if(print) System.out.println("Median is " + (double)(smallerMid + biggerMid)/2);
				return (double)(smallerMid + biggerMid)/2;
			}
		}
		return -1;
	}
	
	public static double find_solution3(int[] arr1, int s1, int e1, int[] arr2, int s2, int e2) {
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
					return find_solution3(arr1, s1+len/2-1, e1, arr2, s2, s2+len/2);
				} else {
					return find_solution3(arr1, s1, s1+len/2, arr2, s2+len/2-1, e2);
				}
			} else {
				median1 = arr1[s1+len/2];
				median2 = arr2[s2+len/2];
				if(median1==median2) {
					return median1;
				} else if(median1<median2) {
					return find_solution3(arr1, s1+len/2, e1, arr2, s2, s2+len/2);
				} else {
					return find_solution3(arr1, s1, s1+len/2, arr2, s2+len/2, e2);
				}
			}
		}
	}
}

public class MedianFindingProblems {
	public static void main(String[] args) {
		int[] numbers = new int[] { 1, 2, 3, 8, 9, 9, 3, 6 };
		//MedianInOneArray.find(numbers, true);
		
		MedianInStream.demo(numbers);
	}
}
