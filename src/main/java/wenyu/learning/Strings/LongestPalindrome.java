package wenyu.learning.Strings;

/*
 * Given a string S, find the longest palindromic substring in S.
 * 
 * Logic:
 * 	Solution1: Optimism way with O(n^2)
 *  Solution2: DP with O(n^2) time and O(n^2) space
 */

public class LongestPalindrome {

	public static String expandAroundCenter(String s, int l, int r) {
		int n = s.length();
		while (l >= 0 && r <= n - 1 && s.charAt(l) == s.charAt(r)) {
			l--;
			r++;
		}
		return s.substring(l + 1, r);
	}

	public static String longestPalindrome_solution1(String s) {
		int n = s.length();
		if (n == 0)
			return "";
		String longest = s.substring(0, 1);
		for (int i = 0; i < n; i++) {
			String p1 = expandAroundCenter(s, i, i);
			if (p1.length() > longest.length())
				longest = p1;

			String p2 = expandAroundCenter(s, i, i + 1);
			if (p2.length() > longest.length())
				longest = p2;
		}
		return longest;
	}

	public static String longestPalindrome_solution2(String s) {
		int n = s.length();
		int longestBegin = 0;
		int maxLen = 1;
		boolean[][] table = new boolean[s.length()][s.length()];
		for (int i = 0; i < n; i++) {
			table[i][i] = true;
		}
		for (int i = 0; i < n - 1; i++) {
			if (s.charAt(i) == s.charAt(i + 1)) {
				table[i][i + 1] = true;
				longestBegin = i;
				maxLen = 2;
			}
		}
		for (int len = 3; len <= n; len++) {
			for (int i = 0; i < n - len + 1; i++) {
				int j = i + len - 1;
				if (s.charAt(i) == s.charAt(j) && table[i + 1][j - 1]) {
					table[i][j] = true;
					longestBegin = i;
					maxLen = len;
				}
			}
		}
		return s.substring(longestBegin, maxLen);
	}

	public static void main(String[] args) {
		String str = "ecabbacdcabbac";

		String result = longestPalindrome_solution1(str);
		System.out.println(result);
		
		System.out.println("======================================");
		
		result = longestPalindrome_solution1(str);
		System.out.println(result);
	}

}
