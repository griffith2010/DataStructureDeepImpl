package wenyu.learning.Maths;

import java.util.Random;

/*
 * Rearrange the order of digits of given number, to find the next bigger number.
 * logic: O(n)
 * 		1. Scan digit from the end of array
 * 		2. Find first pair digits which left one is smaller then right one. Mark left one as L. (e.g 123598731, (5,9) is the first pair)
 * 		3. Find the digit right of the L which is the first greater digit. (e.g first greater is 7)
 * 		4. Switch current digit and above greater digit. (e.g 123 7 98 5 31)
 * 		5. Since rest digits is descending order, so use O(n) to reverse this order. (e.g 1237 13589)
 */
public class RearrangeArrayFindNextBig {
	public static void find(int number) {
		char[] numStr = String.valueOf(number).toCharArray();
		
		for(int i=numStr.length-2; i>=0; i--) {
			if(numStr[i] < numStr[i+1]) {
				// Find first digit greater than current digit
				for(int j=numStr.length-1; j>i; j--) {
					if(numStr[j] > numStr[i]) {
						char tmp = numStr[j];
						numStr[j] = numStr[i];
						numStr[i] = tmp;
						break;
					}
				}
				
				// reverse sort rest
				int start = i+1;
				int end = numStr.length-1;
				while(start < end) {
					char tmp = numStr[start];
					numStr[start] = numStr[end];
					numStr[end] = tmp;
					start++;
					end--;
				}
				
				System.out.println("Next bigger number is " + String.valueOf(numStr));
				return;
			}
		}
		System.out.println("No such number...");
	}
	
	public static int find(Character[] arr) {
		for(int i=arr.length-2; i>=0; i--) {
			if(arr[i] < arr[i+1]) {
				// Find first digit greater than current digit
				for(int j=arr.length-1; j>i; j--) {
					if(arr[j] > arr[i]) {
						char tmp = arr[j];
						arr[j] = arr[i];
						arr[i] = tmp;
						break;
					}
				}
				
				// reverse sort rest
				int start = i+1;
				int end = arr.length-1;
				while(start < end) {
					char tmp = arr[start];
					arr[start] = arr[end];
					arr[end] = tmp;
					start++;
					end--;
				}
				return 0;
			}
		}
		return -1;
	}
	
	public static void main(String[] args) {
		Random random = new Random();
		int testCount = 10;
		while(testCount-- > 0) {
			int num = random.nextInt(9999);
			System.out.println(num);
			find(num);
			System.out.println("=================================");
		}
	}

}
