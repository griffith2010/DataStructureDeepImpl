package wenyu.learning.Maths;

import java.util.Random;
import java.util.Scanner;

/*
 * How many times does the given digit occur in the integers from 1 to n (p200)
 * Logic:
 * 	Solution1: 
 * 		Scan numbers from 0-n one by one
 *  Solution2: for example: 21345
 * 		1. 21345 can be divided to 0-1345 and 01346-21345
 * 		2. 01345-21345:
 * 			a) First to calculate most signification digit in numbers from 10000 to 19999
 * 			b) Then, permutation other digit
 * 		3. recursion for 0-1345			
 *  Solution3: For example: 1s from 0-n: f(513) = 5*f(99)+f(13)+100
 * 										 f(113) = 2*f(99)+f(13)+13+1
 * 		1. The last digit will be repeated every 10 numbers, the last two digits will 
 * 		   be repeated every 10^2 numbers, the last 3 digits will be repeated every 
 *         10^3 numbers, etc.
 *      2. So, if there are X 1s between 0 and 99, then we know there are 2X 1s between 
 *         0 and 199. Between 0 and 299, we have 3x twos from the last two digits, and
 *         another 100 1s from the first digit.
 */
public class DigitCountFromZeroToN {
	public static int solution1(double n, int digit) {
		// Straightforward but Inefficient
		int count = 0;
		for(int i=0;i<=n;++i) {
			int currN = i;
			while(currN>0) {
				if(currN % 10 == digit) {
					count++;
				}
				currN = currN/10;
			}
		}
		return count;
	}

	public static long solution2(String strN, int digit) {
		// example: 21345
		if(strN==null || strN.length()==0) {
			return 0;
		}
		
		int first = strN.charAt(0)-'0';
		int length = strN.length();
		if(length==1 && first>=digit) {
			return 1;
		} else if(length == 1) {
			return 0;
		}
		
		// If strN is 21345, numFirstDigit is the number of digit 1
		// in the most signification digit in numbers from 10000 to 19999
		double firstDigitKCount = 0;
		if(first > digit) {
			// 10000 to 19999 is Pow(10, 4)
			firstDigitKCount = 1;
			for(int i=0;i<length-1;++i)
				firstDigitKCount *= 10;
		} else if(first == digit) {
			String tmpStr = strN.substring(1);
			double tmpN = Integer.parseInt(tmpStr);
			firstDigitKCount = tmpN + 1;
		}
		
		// numOtherDigits is the number of digit k in digits except
		// the most significant digit in numbers from 01346 to 21345
		// permutation. One of the digits is 1, and the other 
		// three digits can be any digit from 0 to 9. So is 10^3
		// As a result: C(1,4)*C(1,10)*C(1,10)*C(1,10)*firstDigit
		double otherDigitsKCount = first*(length-1);
		for(int i=0;i<length-2;++i)
			otherDigitsKCount *= 10;
		
		// numRecursive is the number of digit 1 in numbers from 1 to 1345
		double recursiveKCount = solution2(strN.substring(1), digit);
		return (long)(firstDigitKCount + otherDigitsKCount + recursiveKCount);
	}
	
	public static long solution3(long number, int digit) {
		if(digit == 0) {
			System.out.println("This solution cannot solve k=0 problems");
			return -1;
		}
		
		// base case
		if(number == 0) return 0;
		
		// 513 into 5 * 100 + 13. [Power = 100; First = 5; Remainder = 13]
		int power = 1;
		while(10*power <= number) {
			power *= 10;
		}
		long first = number/power;
		long remainder = number%power;
		
		//count Ks from first digit
		int nKsFirst = 0;
		if(first > digit) nKsFirst += power;
		else if(first == digit) nKsFirst += remainder+1;
		
		//Count Ks from all other digits
		long nKsOther = first*solution3(power-1, digit) + solution3(remainder, digit);
		return nKsOther + nKsFirst;
	}
	
	public static void main(String[] args) {
		while(true) {
			System.out.print("Input a number:");
			Scanner scanner = new Scanner(System.in);
			String input = scanner.nextLine();
			int digit = new Random().nextInt(9)+1;
			System.out.println("Solution1 Count of " + digit + ": " + solution1(Double.parseDouble(input), digit));
			System.out.println("Solution2 Count of " + digit + ": " + solution2(input, digit));
			System.out.println("Solution3 Count of " + digit + ": " + solution3(Integer.parseInt(input), digit));
		}
	}

}
