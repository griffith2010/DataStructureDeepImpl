package wenyu.learning.Strings;

/*
 * Problem 1: Rotate the given string by k times
 * 			  For example: input:  k=3; string="abcdefg"
 * 			               output: efgabcd
 * Problem 2: Assume you have a method isSubstring which checks 
 * 			  if one word is a substring of another. Given two strings, 
 * 			  s1 and s2, write code to check if s2 is a rotation of s1 
 * 			  using only one call to isSubstring (i.e., “waterbottle” 
 * 			  is a rotation of “erbottlewat”).
 * 
 */

class RotateKSteps {
	/*
	 * Problem 1:
	 * Logic:
	 * 	1. reverse all the string. (gfedcba)
	 * 	2. reverse first k chars. (efgdcba)
	 *  3. reverse the last chars. (efgabcd)
	 */
	public static String rightRotateString(String string, int rotateCount) {
		rotateCount = rotateCount%string.length();
		
		char[] str = StringsReverseProblems.reverseWholeString(string).toCharArray();
		StringsReverseProblems.reverseCharArr(str, 0, rotateCount-1);
		StringsReverseProblems.reverseCharArr(str, rotateCount, str.length-1);
		
		return String.copyValueOf(str);
	}
}

class CheckRotationBySubstring {
	/*
	 * Problem 2:
	 * Logic:
	 * 1. Check if length(s1) == length(s2). If not, return false.
	 * 2. Else, concatenate s1 with itself and see whether s2 is substring of the result.
	 *    input: s1 = apple, s2 = pleap ==> apple is a substring of pleappleap
	 *    input: s1 = apple, s2 = ppale ==> apple is not a substring of ppaleppale
	 */
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
}

public class RotatedStringProblem {
	public static void main(String[] args) {
		String str = "abc";
		System.out.println(RotateKSteps.rightRotateString(str, 43));
		
		String str1 = "waterbottle";
		String str2 = "erbottlewat";
		CheckRotationBySubstring.checkIfRotation(str1, str2);
	}

}
