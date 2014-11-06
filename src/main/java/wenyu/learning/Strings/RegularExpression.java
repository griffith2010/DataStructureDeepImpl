package wenyu.learning.Strings;

/*
 * How do you implement a function to match regular expressions with ‘.’ and ‘*’
 * in patterns? For example, the string “aaa” matches the pattern “a.a” and the
 * pattern “ab*ac*a”. However, it does not match the pattern “aa.a” nor “ab*a”.
 * (P55)
 */

public class RegularExpression {
	public static boolean match(String string, String pattern) {
		if (string == null || pattern == null) {
			return false;
		}

		return matchCore(string.toCharArray(), 0, pattern.toCharArray(), 0);
	}

	private static boolean matchCore(char[] string, int strStart, char[] pattern, int patternStart) {
		if(strStart==string.length && patternStart >= pattern.length) {
			return true;
		}
		if(strStart!=string.length && patternStart >= pattern.length) {
			return false;
		}
		
		if(patternStart<pattern.length-1 && pattern[patternStart+1] == '*') {
			if(strStart<string.length && (pattern[patternStart]==string[strStart] || pattern[patternStart]=='.')) {
				// move on the next state
				return matchCore(string, strStart+1, pattern, patternStart+2)
				// stay on the current state
				|| matchCore(string, strStart+1, pattern, patternStart)
				// ignore a '*'
				|| matchCore(string, strStart, pattern, patternStart+2);
			} else {
				// ignore a '*'
				return matchCore(string, strStart, pattern, patternStart+2);
			}
		}
		
		if(strStart<string.length && (pattern[patternStart]==string[strStart] || pattern[patternStart]=='.')) {
			return matchCore(string, strStart+1, pattern, patternStart+1);
		}
		
		return false;
	}

	public static void main(String[] args) {
		String str = "aaaaa";
		String pattern = "ab*ac*.*";
		
		if(match(str, pattern)) {
			System.out.println("Match...");
		} else {

			System.out.println("Not Match...");
		}
	}
}
