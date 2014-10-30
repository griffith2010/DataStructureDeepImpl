package wenyu.learning.Strings;

/*
 * Assume you have a method isSubstring which checks 
 * if one word is a substring of another. Given two strings, 
 * s1 and s2, write code to check if s2 is a rotation of s1 
 * using only one call to isSubstring (i.e., “waterbottle” 
 * is a rotation of “erbottlewat”).
 * 
 * Logic:
 * 1. Check if length(s1) == length(s2). If not, return false.
 * 2. Else, concatenate s1 with itself and see whether s2 is substring of the result.
 *    input: s1 = apple, s2 = pleap ==> apple is a substring of pleappleap
 *    input: s1 = apple, s2 = ppale ==> apple is not a substring of ppaleppale
 */
public class CheckRotationBySubstring {

	public static int checkIfRotation(String str1, String str2) {
		// Can only use substring once
		String tmp = str2+str2;
		int result = tmp.indexOf(str1);
		
		if(result<0) {
			System.out.println(str1 + " is not the rotation of " + str2);
		} else {
			System.out.println(str1 + " is the rotation of " + str2);
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		String str1 = "waterbottle";
		String str2 = "erbottlewat";

		checkIfRotation(str1, str2);
	}

}
