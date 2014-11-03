package wenyu.learning.Arrays;

import java.util.Arrays;

/*
 * Problem 1: Given an array of integers (unsigned integers > 0), 
 *            find three numbers in that array which form the maximum product.
 * Problem 2: Given an array of integers (signed integers), 
 * 			  find three numbers in that array which form the maximum product. 
 */

public class MaxProductFromThreeElemsInArr {

	public static int findUnsigned(int[] arr) {
		/*
		 * Logic: Find biggest three elements in array
		 */
		
		if(arr==null || arr.length<3) {
			return -1;
		}
		
		int largest = -1;
		int middle = -1;
		int smallest = -1;
		
		for(int i=0; i<arr.length; i++) {
			if(arr[i] < 0) {
				System.out.println("Value must be unsigned...");
				return -1;
			}
			
			if(arr[i] < smallest) {
				continue;
			} else if(arr[i] < middle) {
				smallest = arr[i];
			} else if(arr[i] < largest) {
				smallest = middle;
				middle = arr[i];
			} else {
				smallest = middle;
				middle = largest;
				largest = arr[i];
			}
		}
		
		if(smallest<0 || middle<0 || largest<0) {
			return -1;
		} else {
			return smallest*middle*largest;
		}
	}
	
	public static int findSigned_solution1(int[] arr) {
		/*
		 * Logic: Sort first. Time: O(nlogn)
		 */
		Arrays.sort(arr);
		
		if(arr[arr.length-1] > 0){
			// -3, -2, 0, 1, 2, 3 
			// or -7, -8, 0, 1, 2, 3
			return (arr[arr.length-1]*arr[arr.length-2]*arr[arr.length-3] > arr[arr.length-1]*arr[0]*arr[1])?arr[arr.length-1]*arr[arr.length-2]*arr[arr.length-3]:arr[arr.length-1]*arr[0]*arr[1];
		}else{
			// -3, -2, -1, 0
			return arr[arr.length-1]*arr[arr.length-2]*arr[arr.length-3];
		}
	}
	
	public static int findSigned_solution2(int[] arr) {
		/*
		 * Logic: 1. find three maximum numbers
		 * 		  2. find two minimum numbers
		 * 		  3. if maximum is negative, just return product of three maximums
		 * 		  4. if maximum is positive
		 * 			 a. case 1: largest*middle*smaller > largest*smallest*secondSmallest
		 * 			 b. case 2: largest*middle*smaller <= largest*smallest*secondSmallest
		 */
		
		if(arr==null || arr.length<3) {
			return -1;
		}
		
		int largest = Integer.MIN_VALUE;
		int middle = Integer.MIN_VALUE;
		int smaller = Integer.MIN_VALUE;
		
		int smallest = Integer.MAX_VALUE;
		int secSmallest = Integer.MAX_VALUE;
		
		for(int i=0; i<arr.length; i++) {
			if(arr[i] < smaller) {
				continue;
			} else if(arr[i] < middle) {
				smaller = arr[i];
			} else if(arr[i] < largest) {
				smaller = middle;
				middle = arr[i];
			} else {
				smaller = middle;
				middle = largest;
				largest = arr[i];
			}
			
			if(arr[i] < smallest) {
				secSmallest = smallest;
				smallest = arr[i];
			} else if(arr[i] < secSmallest) {
				secSmallest = arr[i];
			}
		}
		
		if(largest > 0) {
			return largest*middle*smaller>largest*smallest*secSmallest ? largest*middle*smaller : largest*smallest*secSmallest;
		} else {
			return largest*middle*smaller;
		}
	}
	
	public static void main(String[] args) {
		int[] arr = ArrayUtils.generateRandomIntegerArray(10, 10);
		ArrayUtils.printArray(arr);
		
		int product = findUnsigned(arr);
		System.out.println(product);
		
		arr = new int[] {-10,-8,-9,0,1,3,5,-6,10,12};
		product = findSigned_solution1(arr);
		System.out.println(product);

		product = findSigned_solution2(arr);
		System.out.println(product);
	}

}
