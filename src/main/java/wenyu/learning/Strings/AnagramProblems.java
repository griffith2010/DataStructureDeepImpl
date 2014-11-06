package wenyu.learning.Strings;

import java.util.Arrays;

/*
 * Anagram: strings contain the all the same characters
 * Solution 1:
 * 		1) Sort both strings
 * 		2) Compare the sorted strings
 * Solution 2:
 * 		1) Create count arrays of size 256 for both strings. 
 *         Initialize all values in count arrays as 0.
 * 		2) Iterate through every character of both strings and 
 *    	   increment the count of character in the corresponding count arrays.
 * 		3) Compare count arrays. If both count arrays are same, 
 * 	       then return true.
 */

public class AnagramProblems {

	public static void ifAnagram_solution1(String str1, String str2) {
		
		if(str1.length() != str2.length()) {
			System.out.println("Is not anagram...");
			return;
		}
		
		char[] tmp = str1.toCharArray();
		Arrays.sort(tmp);
		String sortedStr1 = String.valueOf(tmp);
		
		tmp = str2.toCharArray();
		Arrays.sort(tmp);
		String sortedStr2 = String.valueOf(tmp);
		
		if(sortedStr1.equals(sortedStr2)) {
			System.out.println("Is anagram...");
		} else {
			System.out.println("Is not anagram...");
		}
	}
	
	public static void ifAnagram_solution2(String str1, String str2) {
		if(str1.length() != str2.length()) {
			System.out.println("Is not anagram...");
			return;
		}
		
		int[] hash = new int[256];
		for(int i=0;i<str1.length();i++) {
			hash[str1.charAt(i)]++;
		}
		
		for(int i=0;i<str2.length();i++) {
			if(hash[str2.charAt(i)]>0) {
				hash[str2.charAt(i)]--;
			} else {
				System.out.println("Is not anagram...");
				return;
			}
		}
		
		for(int i=0;i<256;i++) {
			if(hash[i]>0) {
				System.out.println("Is not anagram...");
				return;
			}
		}
		
		System.out.println("Is anagram...");
	}
	
	public static void main(String[] args) {
		String str1 = "adbc";
		String str2 = "cabd";

		ifAnagram_solution1(str1, str2);
		ifAnagram_solution2(str1, str2);
	}

}
