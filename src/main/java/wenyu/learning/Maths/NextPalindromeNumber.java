package wenyu.learning.Maths;

import java.util.Scanner;

import wenyu.learning.Strings.StringsReverseProblems;

/*
 * Output nearest number greater than given number such that output is palindrome
 * ex: 121:131
 *     900:909
 *     99:101
 *     
 * Logic:
 * 	Solution1: 
 * 		Scan number bigger than current number one by one
 *  Solution2:
 *  	1. if len of number is even
 *  		a) Find the decimal representation of the input number ("2133").
 *  		b) Split it into the left half and right half ("21", "33");
 *  		c) Compare the last digit in the left half and the first digit in the right half.
    			a. If the right is greater than the left, increment the left and stop. ("22")
    			   If there is carry after increment, result need to remove one 0 in the middle
    			   For example: 99+1 ==> 100|001 ==> 101
			    b. If the right is less than the left, stop.
			    c. If the right is equal to the left, repeat step 3 with the second-last digit 
			       in the left and the second digit in the right (and so on).
 *  		d) Take the left half and append the left half reversed. That's your next largest palindrome. ("2222")
 *  
 *  	2. if len of number is odd
 *  		a) Find the decimal representation of the input number ("21233").
 *  		b) Split it into the left half and right half and mid ("21", "33", "2");
 *  		c) Compare the last digit in the left half and the first digit in the right half.
    			a. If the right is greater than the left, increment the middle digit. ("21", "2", "12") ==> ("21", "3", "12")
    			   If mid == 9, need to carry one to left
			    b. If the right is less than the left, stop.
			    c. If the right is equal to the left, repeat step 3 with the second-last digit 
			       in the left and the second digit in the right (and so on).
 *  		d) Take the left half and append the left half reversed. That's your next largest palindrome. ("21312")
 */
public class NextPalindromeNumber {

	public static int solution1(int currNumber) {
		for(int i=currNumber+1;;i++) {
			if(PalindromeCheck.solution2(i, false)) {
				return i;
			}
		}
	}
	
	public static int solution2(int currNumber) {
		String numStr = String.valueOf(currNumber);
		int len = numStr.length();
		
		if(len%2 == 0) {
			int offset = 0;
			while((len/2-offset-1)>=0 && numStr.charAt(len/2-offset-1)==numStr.charAt(len/2+offset)) {
				offset++;
			}
			
			String leftStr = "";
			String rightStr = "";
			if((len/2-offset-1)>=0 && numStr.charAt(len/2-offset-1)>numStr.charAt(len/2+offset)) {
				leftStr = numStr.substring(0, len/2);
				rightStr = StringsReverseProblems.reverseStringByWords(leftStr);
			} else {
				String originalLeftStr = numStr.substring(0, len/2);
				leftStr = String.valueOf(Integer.parseInt(originalLeftStr)+1);
				rightStr = StringsReverseProblems.reverseStringByWords(leftStr);
				if(originalLeftStr.length() < rightStr.length()) {
					rightStr = rightStr.substring(1);  // If there is carry after increment, result need to remove one 0 in the middle
				}
			}
			int result = Integer.parseInt(leftStr+rightStr);
			return result;
		} else {
			int offset = 0;
			while((len/2-offset-1)>=0 && numStr.charAt(len/2-offset-1)==numStr.charAt(len/2+offset+1)) {
				offset++;
			}
			
			String leftStr = "";
			String rightStr = "";
			String midStr = String.valueOf(numStr.charAt(len/2));
			if((len/2-offset-1)>=0 && numStr.charAt(len/2-offset-1)>numStr.charAt(len/2+offset+1)) {
				leftStr = numStr.substring(0, len/2);
				rightStr = StringsReverseProblems.reverseStringByWords(leftStr);
				leftStr += midStr;
			} else {
				String originalLeftStr = numStr.substring(0, len/2);
				leftStr = String.valueOf(Integer.parseInt(originalLeftStr+midStr)+1);
				if(originalLeftStr.length()+1 < leftStr.length()) {
					rightStr = StringsReverseProblems.reverseStringByWords(leftStr.substring(0, leftStr.length()-2));
				} else {
					rightStr = StringsReverseProblems.reverseStringByWords(leftStr.substring(0, leftStr.length()-1));
				}
			}
			int result = Integer.parseInt(leftStr+rightStr);
			return result;
		}
	}
	
	public static void main(String[] args) {
		while(true) {
			System.out.println("Input a number:");
			Scanner scanner = new Scanner(System.in);
			String input = scanner.nextLine();
			int number = Integer.parseInt(input);
			
			System.out.println("Next parlindraom number is " + solution1(number));
			System.out.println("Next parlindraom number is " + solution2(number));
		}
	}
}
