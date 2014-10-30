package wenyu.learning.Maths;

import java.util.Scanner;

/*
 * How many times does the digit 1 occur in the integers from 1 to n (p200)
 * Logic:
 * 	Solution1: 
 * 		Scan numbers from 0-n one by one	
 *  Solution2: For example: 2s from 0-n: f(513) = 5*f(99)+f(13)+100
 * 		1. The last digit will be repeated every 10 numbers, the last two digits will 
 * 		   be repeated every 10^2 numbers, the last 3 digits will be repeated every 
 *         10^3 numbers, etc.
 *      2. So, if there are X 2s between 0 and 99, then we know there are 2x twos between 
 *         0 and 199. Between 0 and 299, we have 3x twos from the last two digits, and
 *         another 100 2s from the first digit.
 */
public class DigitCountInN {
	private static void printResult(long[] result) {
		for(int i=1;i<result.length;i++) {
			System.out.print("[" + i + ":" + result[i] + "] ");
		}
		System.out.println();
	}
	
	public static long[] solution1(double n) {
		// Straightforward but Inefficient
		long[] result = new long[10];
		for(int i=0;i<=n;++i) {
			int currN = i;
			while(currN>0) {
				result[currN % 10]++;
				currN = currN/10;
			}
		}
		
		return result;
	}
	
	public static long[] solution2(long number) {
		long[] result = new long[10];
		
		// base case
		if(number==0) return result;

		int power = 1;
		//result[0] = 1;
		while(10*power <= number) {
			power *= 10;
			//result[0] += number/power;
		}
//		if(power<number) {
//			result[0] += number/power;
//		}
		
		long first = number/power;
		long remainder = number%power;

		//count Ks from first digit
		for(int i=1;i<result.length;i++) {
			if(first>i) result[i] += power;
			else if(first == i) result[i] += remainder+1;
		}

		//Count Ks from all other digits
		long[] tmpPowerResult = solution2(power-1);
		for(int i=1;i<tmpPowerResult.length;i++) {
			tmpPowerResult[i] *= first;
		}
		long[] tmpRemainderResult = solution2(remainder);
		for(int i=1;i<result.length;i++) {
			result[i] += tmpPowerResult[i] + tmpRemainderResult[i];
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		while(true) {
			System.out.println("Input a number:");
			Scanner scanner = new Scanner(System.in);
			String input = scanner.nextLine();
			long number = Long.parseLong(input);
			printResult(solution1(number));
			printResult(solution2(number));
		}
	}

}
