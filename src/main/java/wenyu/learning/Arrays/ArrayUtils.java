package wenyu.learning.Arrays;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class ArrayUtils {
	public static int[] generateRandomDistinctSortedIntegerArray(int arrayLen, int maxItem) {
		int[] array = new int[arrayLen];
		Set<Integer> tmpSet = new HashSet<Integer>();
		Random random = new Random();
		
		for(int i=0;i<array.length;i++) {
			int value = random.nextInt(maxItem);
			while(tmpSet.contains(value)) {
				value = random.nextInt(maxItem);
			}
			tmpSet.add(value);
			array[i] = value;
		}
		Arrays.sort(array);
		return array;
	}
	
	public static int[] generateRandomIntegerArray(int arrayLen, int maxItem) {
		int[] array = new int[arrayLen];
		Random random = new Random();
		for(int i=0;i<array.length;i++) {
			int value = random.nextInt(maxItem);
			array[i] = value;
		}
		return array;
	}
	
	public static int[] generateSortedIntegerArray(int arrayLen, int maxItem) {
		int[] array = generateRandomIntegerArray(arrayLen, maxItem);
		Arrays.sort(array);
		
		return array;
	}
	
	public static void printArray(int[] array) {
		/*
		 * Same function as Arrays.toString()
		 */
		StringBuilder printStr = new StringBuilder("Array: [");
		for(int i=0;i<array.length;i++) {
			if(i == array.length-1) {
				printStr.append(array[i]);
			} else {
				printStr.append(array[i] + ",");
			}
			
		}
		printStr.append("]");
		System.out.println(printStr);
	}
}
