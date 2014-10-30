package wenyu.learning.Strings;

/*
 * Problem 1. Reverse string
 * Logic: Two points from the start and end. And then swap.
 * 	
 * Problem 2. Reverse string but keep word's itself order(This is sun --> sun is This)
 * Logic: Reverse all the string, and then split the string by blank space and then reverse each word.
 */

public class StringsReverseProblems {

	public static String reverseWholeString(String str) {
		char[] charArr = str.toCharArray();
		reverseCharArr(charArr, 0, str.length()-1);
		return String.valueOf(charArr);
	}
	
	protected static void reverseCharArr(char[] str, int start, int end) {
		while(start < end) {
			char tmp = str[start];
			str[start] = str[end];
			str[end] = tmp;
			start++;
			end--;
		}
	}
	
	public static String reverseStringByWords(String string) {
		char[] str = reverseWholeString(string).toCharArray();
		
		int pre = 0;
		for(int i=0;i<str.length;i++) {
			if(str[i]==' ') {
				reverseCharArr(str, pre, i-1);
				pre = i+1;
			} else if(i==str.length-1) {
				reverseCharArr(str, pre, i);
			}
		}
		
		return String.copyValueOf(str);
	}
	
	public static void main(String[] args) {
		String str = "wenyuchang";
		System.out.println(reverseWholeString(str));
		
		str = "this is a sentence";
		System.out.println(reverseStringByWords(str));
	}

}
