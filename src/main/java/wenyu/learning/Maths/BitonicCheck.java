package wenyu.learning.Maths;

/*
 * 1. A bitonic sequence is composed of two subsequences, one monotonically decreasing and the other monotonically increasing.
 * 2. any rotation of a bitonic sequence is a bitonic sequence, or if you prefer, one of the subsequences can wrap 
 *    around the end of the bitonic sequence. 
 * 3. a sorted sequence is itself a bitonic sequence: one of the subsequences is empty.
 * 
 * Logic:
 * Obviously if the direction changes more than 
 * two times we cannot have a bitonic sequence. 
 * If the direction changes less than two times,
 * we must have a bitonic sequence. 
 */

public class BitonicCheck {

	public static boolean bitonic(int[] array) {
		if (array == null) {
			System.out.println("array is not bitonic.");
			return false;
		} else if (array.length < 4) {
			System.out.println("array is bitonic.");
			return true;
		}
		
		int switchTimes = 0;
		Boolean currDirection = null; 				// false is decreasing, true is increasing
		
		for(int i=1; i<array.length; i++) {
			if(array[i] == array[i-1]) {
				
				continue;
			}
			
			if(currDirection==null) {
				currDirection = array[i]>array[i-1];
			} else if(currDirection != array[i]>array[i-1]){
				switchTimes++;
				currDirection = array[i]>array[i-1];
			}
		}
		
		if(switchTimes < 2) {
			System.out.println("array is bitonic.");
		} else {
			System.out.println("array is not bitonic.");
		}
		return switchTimes < 2;
	}

	public static void main(String[] args) {
		int[] arr = {0, 8, 12,10,10,10,10,10, 6, 1};
		bitonic(arr);
	}

}
