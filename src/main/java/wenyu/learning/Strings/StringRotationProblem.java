package wenyu.learning.Strings;

/*
 * Rotate the given string by k times
 * For example: k=3; string="abcdefg"
 * 				output: efgabcd
 * Logic:
 * 	1. reverse all the string. (gfedcba)
 * 	2. reverse first k chars. (efgdcba)
 *  3. reverse the last chars. (efgabcd)
 */

public class StringRotationProblem {

	public static String rightRotateString(String string, int rotateCount) {
		rotateCount = rotateCount%string.length();
		
		char[] str = StringsReverseProblems.reverseWholeString(string).toCharArray();
		StringsReverseProblems.reverseCharArr(str, 0, rotateCount-1);
		StringsReverseProblems.reverseCharArr(str, rotateCount, str.length-1);
		
		return String.copyValueOf(str);
	}
	
	public static void main(String[] args) {
		String str = "abc";
		System.out.println(rightRotateString(str, 43));
	}

}
