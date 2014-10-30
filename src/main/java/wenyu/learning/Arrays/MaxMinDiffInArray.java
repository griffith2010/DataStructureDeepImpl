package wenyu.learning.Arrays;

import java.util.Arrays;

/*
 * Find the max or min difference of two items in array
 * Max Logic: 
 * 	just find the minimum item and maximum item in array
 * 	difference is the substrance of them
 * 
 * Min Logic:
 * 	
 */

public class MaxMinDiffInArray {

	public static void findMaxDiff(int[] array) {
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		int minIdx = -1;
		int maxIdx = -1;
		
		for(int i=0;i<array.length;i++) {
			if(array[i]<min) {
				min = array[i];
				minIdx = i;
			}
			
			if(array[i]>max) {
				max = array[i];
				maxIdx = i;
			}
		}
		
		System.out.println("The max difference in array is " + (max-min));
		System.out.println("These two items are " + array[minIdx] + " and " + array[maxIdx]);
	}
	
	public static void findMinDiff(int[] array) {
		Arrays.sort(array);
		int min = Integer.MAX_VALUE;
		int minIdx = -1;
		
		for(int i=1;i<array.length;i++) {
			int diff = Math.abs(array[i]-array[i-1]);
			if(diff<min) {
				minIdx = i;
				min = diff;
			}
		}
		System.out.println("The min difference in array is " + min);
		System.out.println("These two items are " + array[minIdx-1] + " and " + array[minIdx]);
	}
	
	public static void main(String[] args) {
		int[] array = new int[] {-20,-3916237,-357920,-3620601,7374819,-7330761,30,6246457,-6461594,266854,-520,-470};
		findMaxDiff(array);

		findMinDiff(array);
	}

}
