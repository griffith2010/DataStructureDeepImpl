package wenyu.learning.Queue;

import java.util.Arrays;

/*
 * Problem:
 * Given an array of numbers and a sliding window size, how do you get the maximum numbers in all sliding windows?
 * For example, if the input array is {2, 3, 4, 2, 6, 2, 5, 1} and the size of the sliding windows is 3, the output of 
 * maximums are {4, 4, 6, 6, 6, 5}.
 * 
 * Logic: Using Queue with Max value data structure
 */
public class MaxmumNumInSlidingWindow {

	public static int[] find(int[] arr, int windowSize, boolean print) {
		if(arr==null || arr.length==0) {
			return new int[]{};
		}
			
		int[] result = new int[arr.length];
		int idx = 0;
		MyQueueWithMaxMin<Integer> queue = new MyQueueWithMaxMin<Integer>(arr.length);
		int i=0;
		queue.offer(arr[i++]);
		if(i<arr.length) queue.offer(arr[i++]);
		if(i<arr.length) queue.offer(arr[i++]);
		result[idx++] = queue.max();
		for(; i<arr.length; i++) {
			queue.offer(arr[i]);
			queue.poll();
			result[idx++] = queue.max();
		}

		result = Arrays.copyOfRange(result, 0, idx);
		if(print) System.out.println(Arrays.toString(result));
		return result;	
	}
	
	public static void main(String[] args) {
		int[] arr = {2,3,4,2,6,2,5,1};
		find(arr, 1, true);
	}

}
