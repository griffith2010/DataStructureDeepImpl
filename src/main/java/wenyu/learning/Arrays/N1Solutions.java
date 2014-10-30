package wenyu.learning.Arrays;

import java.util.Arrays;

/*
 * An array contains n numbers ranging from 0 to n-1. 
 * There are some numbers duplicated in the array.
 */
public class N1Solutions {
	public static final int runtime = 1;
	public static int[] array;
	public static int arraySize;

	private void solution1() {
		// Sort
		Arrays.sort(array);
		for(int i=0;i<arraySize;i++) {
			if(array[i]==array[i+1]) {
				System.out.println("Duplicated number is " + array[i]);
				return;
			}
		}
	}
	
	private void solution2() {
		int[] tmp = new int[arraySize];
		for(int i=0;i<arraySize;i++) {
			tmp[i] = -1;
		}
		for(int i=0;i<arraySize;i++) {
			if(tmp[array[i]] != -1) {
				System.out.println("Duplicated number is " + array[i]);
			} else {
				tmp[array[i]] = array[i];
			}
		}
	}
	
	private void solution3() {
		for(int i=0;i<arraySize;i++) {
			if(array[i]==i) {
				continue;
			}
			
			if(array[array[i]]==array[i]) {
				System.out.println("Duplicated number is " + array[i]);
				return;
			} else {
				int tmp = array[i];
				array[i] = array[tmp];
				array[tmp] = tmp;
				i--;
			}
		}
	}
}
