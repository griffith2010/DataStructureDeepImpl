package wenyu.learning.Arrays;

import java.util.Arrays;

/*
 * Arrange the numbers in an array in alternating order. 
 * 
 * For example if the array is [a1, a2, a3, a4.. ]arrange the array such that b1<=b2>=b3<=b4 and so on.
 * Sampe Input: 3 5 7 8 4 9 
 * Sample Output: 3 < 5 > 4 < 8 >7 < 9
 * 
 * Logic:
 * 	Solution1:
 * 		1. sort the array 
 * 		2. swap every two items
 *  Solution2:
 *  	1. scan the array one by one
 *  	2. if (i % 2 == 1 && array[i] < array[i - 1]) then swap
 *  	3. if (i % 2 == 1 && array[i] > array[i - 1]) then swap
 */
public class StrangeSort {

	public static void sort_solution1(int[] array) {
		Arrays.sort(array);

		for (int i = 1; i < array.length; i += 2) {
			int tmp = array[i - 1];
			array[i - 1] = array[i];
			array[i] = tmp;
		}

		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}

	public static void sort_solution2(int[] array) {
		for (int i = 1; i < array.length; i++) {
			if (i % 2 == 1 && array[i] < array[i - 1]) {
				int tmp = array[i - 1];
				array[i - 1] = array[i];
				array[i] = tmp;
			} else if (i % 2 == 0 && array[i] > array[i - 1]) {
				int tmp = array[i - 1];
				array[i - 1] = array[i];
				array[i] = tmp;
			}
		}

		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}	
		System.out.println();
	}

	public static void main(String[] args) {
		int[] array = {100, 7,5,8,4,9};
		int[] array1 = array.clone();
		sort_solution1(array);
		
		sort_solution2(array1);

	}

}
