package wenyu.learning.Arrays;

import wenyu.learning.Heap.MyBinaryHeap;

/*
 * Find median in array:
 * Logic: O(n)
 * 	1. determine the length of array. Even or Odd?
 *  2. Odd: partition function to find the mid value in array;
 *  3. Even: partition function to find the mid and mid-1 value in array;
 *  
 * Find median in stream:
 * Logic: O(logn) to insert and O(1) to find the median
 *  1. implement a new data structure, which has a max heap and min heap
 *  2. Suppose that the count of existing numbers is even; a new number will be
	   inserted into the min heap. If the new number is less than some numbers
	   in the max heap, it violates our rule that all numbers in the min heap
	   should be greater than numbers in the max heap.
 */

class StreamStructure {
	private MyBinaryHeap<Integer> minHeap;
	private MyBinaryHeap<Integer> maxHeap;

	public StreamStructure(int max) {
		minHeap = new MyBinaryHeap<Integer>(max, MyBinaryHeap.MIN_HEAP);
		maxHeap = new MyBinaryHeap<Integer>(max, MyBinaryHeap.MAX_HEAP);
	}

	public void insert(int num) {
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

	public float getMedian() {
		int size = minHeap.size() + maxHeap.size();
		if (size == 0) {
			System.out.println("No numbers are available");
			return -1;
		}
		float median = 0;
		if ((size&1) == 1) {
			median = minHeap.peek();
		} else {
			median = ((float)minHeap.peek() + (float)maxHeap.peek())/2;
		}
		return median;
	}
}

public class FindMedian {

	private static int partition(int[] numbers, int start, int end) {
		int i = start;
		int j = end;
		int pivotV = numbers[i];

		while (i < j) {
			while (i < j && numbers[j] >= pivotV) {
				j--;
			}
			if (i < j) {
				numbers[i] = numbers[j];
				i++;
			}
			while (i < j && numbers[i] <= pivotV) {
				i++;
			}
			if (i < j) {
				numbers[j] = numbers[i];
				j--;
			}
		}

		numbers[i] = pivotV;
		return i;
	}

	public static float findMedianInArray(int[] numbers) {
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
			return numbers[len / 2];
		}
		
	}

	public static int findMedianInStreamUsing(int[] numbers) {
		StreamStructure structure = new StreamStructure(numbers.length);
		
		for(int i=0;i<numbers.length;i++) {
			System.out.println(numbers[i] + " is inserted. ");
			structure.insert(numbers[i]);
			
			float currMedian = structure.getMedian();
			if(currMedian!=-1) {
				System.out.println("Current median is " + currMedian);
			}
			
			System.out.println();
		}
		

		return -1;
	}

	public static void main(String[] args) {
		int[] numbers = new int[] { 1, 2, 3, 8, 3, 6 };
		System.out.println("Median in array: " + findMedianInArray(numbers));
		
		findMedianInStreamUsing(numbers);
	}

}
