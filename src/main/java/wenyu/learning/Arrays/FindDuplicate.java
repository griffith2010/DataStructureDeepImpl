package wenyu.learning.Arrays;

import java.util.Arrays;

/*
 * Given an array where all numbers but one occurs in pairs, 
 * suggest all ways to find the unique number. 
 * What if the array was sorted? (Code)
 */
public class FindDuplicate {

	public static void findFromUnsortedArr(int[] arr) {
		if(arr.length%2==0) {
			return;
		}
		
		/*
		 * using XOR to find the unique number in array
		 * O(n)
		 */
		int unique = arr[0];
		for(int i=1;i<arr.length;i++) {
			unique ^= arr[i];
		}
		
		System.out.println("Unique number is " + unique);
	}
	
	public static void findFromSortedArr(int[] arr) {
		if(arr.length%2==0) {
			return;
		}
		Arrays.sort(arr); // sort	
		
		int front = 0;
		int end = arr.length-1;
		
		while(front<=end) {
			int mid = front + (end-front)/2;
			
			if(mid%2==1) {
				if(mid>0 && arr[mid] == arr[mid-1]) {
					front = mid+1;
				} else if(mid<arr.length-1 && arr[mid] == arr[mid+1]) {
					end = mid-1;
				} else {
					System.out.println("Unique number is " + arr[mid]);
					return;
				}
			} else {
				if(mid<arr.length-1 && arr[mid] == arr[mid+1]) {
					front = mid+2;
				} else if(mid>0 && arr[mid] == arr[mid-1]) {
					end = mid-2;
				} else {
					System.out.println("Unique number is " + arr[mid]);
					return;
				}
			}
			
		}
		
		//System.out.println("Unique number is " + unique);
	}
	
	public static void main(String[] args) {
		int[] array = {1,2,8,2,8,6,6,5,10,1,10};
		findFromUnsortedArr(array);
		findFromSortedArr(array);
	}
}
