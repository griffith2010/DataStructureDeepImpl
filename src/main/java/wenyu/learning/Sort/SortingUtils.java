package wenyu.learning.Sort;

import java.util.Random;

public class SortingUtils {
	private static final int arrayLen = 10;
	
	public static void validateArray(int[] array) {
		for(int i=1;i<array.length;i++) {
			if(array[i]<array[i-1]) {
				System.out.println("Sorting Failed...");
				return;
			}
		}
		System.out.println("Sorting Success...");
	}
	
	public static void printArray(int[] array) {
		if(array == null) {
			return;
		}
		System.out.print("[");
		for(int i=0;i<array.length-1;i++) {
			System.out.print(array[i] + ",");
		}
		System.out.println(array[array.length-1] + "]");
	}
	
	public static void swap(int[] array, int i, int j) {
		int tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
	}
	
	public static void swap1(int[] array, int i, int j) {
		array[i] = array[i] + array[j];
		array[j] = array[i] - array[j];
		array[i] = array[i] - array[j];
	}
	
	public static int[] generateRandomArray() {
		int[] array = new int[arrayLen];
		Random random = new Random();
		for(int i=0;i<arrayLen;i++) {
			array[i] = random.nextInt(100);
		}
		System.out.println("Success to generate array whose length is " + arrayLen);
		return array;
	}
}
 