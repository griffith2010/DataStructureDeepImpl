package wenyu.learning.Strings;

/*
 * In a given string some of the characters are replaced by question mark, 
 * and you can replace question mark with any character. Given such a string 
 * find total number of palindrome that can created. String contains only 
 * [a-z] characters and question marks can also be only replaced by [a-z].
 * Example:
 *    Input String: String str=”a??a”
 *    Output: 26
 *    
 * Logic:
 * 	  1. Two points: first start from 0; another from str.length-1
 * 	  2. if two points' character are same
 * 		 a. if such character is '?', then total*26
 * 		 b. if not, continue;
 *    3. if two points' character are not sam
 *    	 a. if one of them is '?', then continue
 *    	 b. if not, just return 0. Since there cannot be any palindrome.
 */

public class ReplaceMarkToFormPalindrome {

	public static int count(String str, char mark) {
		if(str==null) {
			return 0;
		} else if(str.length()==0) {
			return 1;
		}
		
		int total = 1;
		int start = 0;
		int end = str.length()-1;
		
		while(start <= end) {
			if(str.charAt(start) == str.charAt(end)) {
				if(str.charAt(start) == mark) {
					total *= 26;
				}
			} else if(str.charAt(start)!=mark && str.charAt(end)!=mark){
				return 0;
			}
			
			start++;
			end--;
		}
		
		return total;
	}
	
	public static void main(String[] args) {
		int total = count("ab??a", '?');
		System.out.println("Total count is " + total);
	}

}
