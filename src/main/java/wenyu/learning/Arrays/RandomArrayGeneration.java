package wenyu.learning.Arrays;

import java.util.Random;

/**
 * @author wenychan
 *
 */
public class RandomArrayGeneration {
	private static final int arraySize = 10;
	private static int[] array;
	public static final String N1 = "Range N Problem";
	public static final String N2 = "Range N-1 Problem";
	
	private static void print(int[] array) {
		System.out.print("Array:");
		for(int i=0;i<arraySize;i++) {
			System.out.print(" " + array[i]);
		}
		System.out.println();
	}
	
	private static void shuffle() {
		Random rgen = new Random();
		for (int i=0; i < array.length; i++) {
			int randomPosition = rgen.nextInt(array.length);
			int temp = array[i];
			array[i] = array[randomPosition];
			array[randomPosition] = temp;
		}
	}
	
	public static int[] getArray(String problem) {
		if(array == null) {
			array = new int[arraySize];	
		}
		
		if(problem == N1) {
			for(int i=0;i<arraySize;i++) {
				array[i] = i;
			}
			int dupNum = new Random().nextInt(arraySize);
			int missNum = new Random().nextInt(arraySize);
			while(missNum==dupNum) {
				missNum = new Random().nextInt(arraySize);
			}
			array[missNum] = dupNum;
			shuffle();
		} else if(problem == N2) {
			for(int i=0;i<arraySize-1;i++) {
				array[i] = i;
			}
			int dupNum = new Random().nextInt(arraySize-1);
			array[arraySize-1] = dupNum;
			shuffle();
		}
		
		print(array);
		return array;
	}
}
