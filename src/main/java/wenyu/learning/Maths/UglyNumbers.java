package wenyu.learning.Maths;

import java.util.Arrays;

/**
 * If a number only has factors 2, 3, and 5, it is an ugly number. 
 * 
 * For example, 6 and 10 are two ugly numbers, but 14 is not because 
 * it has a factor of 7. Usually 1 is considered to be the first ugly 
 * number. What is the arbitrary Kth ugly number?
 *	
 * Logic:
 *  This solution only spends time on ugly numbers. it will keep an sorted array only contains ugly numbers
 *  
 *  The key to this solution is how to keep the array sorted. Suppose that there are already some ugly
	numbers in the array and the greatest one is M. Let’s analyze how to get the next ugly number.
	The next ugly number should be the multiplication result of an existing ugly number by 2, 3, or 5.
	First, it multiplies all existing numbers by 2. Some multiplication results may be less than or equal to M,
	and they should be already in the array, so they are discarded. There may be several multiplication
	results greater than M, but only the first one is important and others will be recalculated later. The first
	multiplication result by 2 greater than M is defined as M2.
	Similarly, it also multiplies all existing ugly number by 3 and 5 and defines the first numbers greater
	than M as M3 and M5 respectively. The next ugly number is the minimum one among M2, M3, and M5.
	Is it necessary to multiple all existing ugly numbers by 2, 3, and 5? Actually, it is not. Since the
	existing ugly numbers are sorted, there is a special ugly number T2 in the array. The multiplication
	results of numbers before T2 by 2 are less than M. It stores the index of T2 and updates the index when a
	new ugly number is found. Similarly, there are also T3 and T5 for factors 3 and 5. In each round, it starts
	from T2, T3, and T5 to find the next ugly number.
 */
public class UglyNumbers {
	public static boolean verifyUgly(long num) {
		if(num <= 0) {
			return false;
		}
		
		while(num%2 == 0) {num /= 2;}
		while(num%3 == 0) {num /= 3;}
		while(num%5 == 0) {num /= 5;}
		
		return num==1;
	}
	
	public static long findKthBruteForce(int k) {
		// Straight way. Brute force
		int currUglyNum = 1;
		int i = 1;
		for(;currUglyNum<=k;i++) {
			if(verifyUgly(i)) {
				currUglyNum++;
			}
		}
		
		return i-1;
	}
	
	public static long findKthGraceful(int k) {
		long[] uglyNums = findKUglyNums(k);
		return uglyNums[k-1];
	}
	
	public static long[] findKUglyNums(int k) {
		int idx2 = 0; //store the last index whose value multipy 2 is bigger than the biggest ugly number in array
		int idx3 = 0; //store the last index whose value multipy 3 is bigger than the biggest ugly number in array
		int idx5 = 0; //store the last index whose value multipy 5 is bigger than the biggest ugly number in array
		long[] uglyNums = new long[k];
		int currIdx = 0;
		uglyNums[0] = 1;
		
		while(currIdx+1 < k) {
			long currMin = (uglyNums[idx2]*2 < uglyNums[idx3]*3)? uglyNums[idx2]*2:uglyNums[idx3]*3;
			currMin = (currMin<uglyNums[idx5]*5)?currMin:uglyNums[idx5]*5;
			uglyNums[++currIdx] = currMin;
			
			while(uglyNums[idx2]*2<=currMin) {idx2++;}
			while(uglyNums[idx3]*3<=currMin) {idx3++;}
			while(uglyNums[idx5]*5<=currMin) {idx5++;}
		}
		
		// System.out.println(Arrays.toString(uglyNums));
		return uglyNums;
	}
	
	public static void main(String[] args) {
		int k = 70;
		System.out.println("The " + k + "th ugly number is " + findKthBruteForce(k));
		System.out.println("The " + k + "th ugly number is " + findKthGraceful(k));
	}
}
