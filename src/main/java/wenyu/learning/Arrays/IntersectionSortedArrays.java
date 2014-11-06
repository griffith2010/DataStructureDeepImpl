package wenyu.learning.Arrays;

/*
 * Find the intersection of two sorted array
 */
public class IntersectionSortedArrays {

	public static void find_solution1(int[] numbers1, int[] numbers2) {
		// Scan two array
		// O(m+n)
		
		int i=0;
		int j=0;
		while(i<numbers1.length && j<numbers2.length) {
			if(numbers1[i] == numbers2[j]) {
				System.out.print(numbers1[i] + " ");
				i++;
				j++;
			} else if(numbers1[i] < numbers2[j]) {
				i++;
			} else {
				j++;
			}
		}
	}
	
	public static int[] find_solution2(int[] numbers1, int[] numbers2) {
		// Using binary search (if one length of the array is very very big)
		// O(nlogm)
		if(numbers1.length>=numbers2.length) {
			for(int i=0;i<numbers2.length;i++) {
				// boolean find = binarySearch(number1, number2[i]);
				// if(find) {
				//	print number2[i];
				// } 
			}
		} else {
			// ...
		}
		return null;
	}
	
	public static void main(String[] args) {
		int[] arr1 = new int[] {1,2,3,4,5,6,7,8};
		int[] arr2 = new int[] {3,5,6,7,9};
		find_solution1(arr1, arr2);
	}

}
