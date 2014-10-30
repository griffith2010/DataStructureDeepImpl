package wenyu.learning.Strings;

import java.util.Scanner;

/*
 * Transfer string-format number to actual number
 * Number could be like:
 * 1.  123
 * 2.  -123
 * 3.  123.123
 * 4.  -123.123
 * 6.  123e123
 * 7.  123E123
 * 8.  -123e123
 * 9.  -123e123
 * 10. 123.123e123
 * 11. -123.123e123
 * 12. 123.123E123
 * 13. -123.123E123
 * 13. 123e-123
 * 15. 123E-123
 */

public class StringToNumber {
	
	private static double[] scanDigits(String input, int idx, int sign) throws Exception {
		//Scan Digits
		double[] returnVal = new double[2];
		double tmpNumber = 0;
		if(input.charAt(idx) == 'e' || input.charAt(idx) == 'E') {
			throw new Exception("first digit cannot be e/E...");
		}
		
		for(;idx<input.length();idx++) {
			if(input.charAt(idx) == '.' || input.charAt(idx) == 'e' || input.charAt(idx) == 'E') {
				tmpNumber = tmpNumber/Math.pow(10, input.length()-idx);
				returnVal[0] = tmpNumber;
				returnVal[1] = idx;
				return returnVal;
			} else {
				int figure = Integer.valueOf(String.valueOf(input.charAt(idx)));
				// test if the number is bigger than max value of long
				if(sign>0 && Double.MAX_VALUE-figure * Math.pow(10, input.length()-1-idx)<tmpNumber) {
					throw new Exception("number overflow...");
				}
				// test if the number is bigger than max value of long
				if(sign<0 && Double.MAX_VALUE-figure * Math.pow(10, input.length()-1-idx)<tmpNumber+1) {
					throw new Exception("number overflow...");
				}
				tmpNumber += figure*Math.pow(10, input.length()-1-idx);
			}
		}
		
		returnVal[0] = tmpNumber;
		returnVal[1] = idx;
		return returnVal; 
	}
	
	private static double[] scanDigital(String input, int idx, int sign) throws Exception {
		double[] returnVal = new double[2];
		double digital = 0;
		idx++;
		
		double[] tmp = scanDigits(input, idx, sign);
		digital = tmp[0];
		int nextIdx = (int) tmp[1];
		if(nextIdx<input.length() && input.charAt(nextIdx) == '.') {
			throw new Exception("False Format (Has two points)...");
		}
		
		digital = digital*Math.pow(10, -1*(nextIdx-idx));
		returnVal[0] = digital;
		returnVal[1] = nextIdx;
		return returnVal;
	}
	
	private static double[] scanExponential(String input, int idx) throws Exception {
		double[] returnVal = new double[2];
		double exp = 0;
		int expSign = 1;
		idx++;
		
		if(input.charAt(idx) == '+') {
			idx++;
		} else if(input.charAt(idx) == '-') {
			idx++;
			expSign=-1;
		}
		
		double[] tmp = scanDigits(input, idx, expSign);
		exp = tmp[0];
		idx = (int) tmp[1];
		if(idx<input.length() && (input.charAt(idx) == '.' || input.charAt(idx) == 'e' || input.charAt(idx) == 'E')) {
			throw new Exception("False Format (Exponential cannot having other " + input.charAt(idx) + ")...");
		}
		
		exp = Math.pow(10, exp*expSign);
		returnVal[0] = exp;
		returnVal[1] = idx;
		return returnVal;
	}
	
	public static void transfer(String input) throws Exception {
		if(input==null || input.trim().length() == 0) {
			// Test if input is null or empty string
			return;
		}
		
		double number = 0;
		int idx = 0;
		int sign = 1;              		// Sign of integer
		if(input.charAt(0) == '+') {
			idx++;
		} else if(input.charAt(0) == '-') {
			idx++;
			sign = -1;
		}
		
		double[] tmpResult = scanDigits(input, idx, sign);
		number = tmpResult[0];
		idx = (int) tmpResult[1];
		
		if(idx<input.length() && input.charAt(idx) == '.') {
			tmpResult = scanDigital(input, idx, sign);
			number += tmpResult[0];
			idx = (int) tmpResult[1];
		}
		
		if(idx<input.length() && (input.charAt(idx) == 'e' || input.charAt(idx) == 'E')) {
			tmpResult = scanExponential(input, idx);
			number *= tmpResult[0];
			idx = (int) tmpResult[1];
		}
		
		if(idx<input.length()) {
			throw new Exception("False Format...");
		}
		
		number *= sign;
		System.out.println(number);
	}
	
	public static void main(String[] args) throws Exception {
		while(true) {
			System.out.print("Input a integer: ");
			Scanner scanner = new Scanner(System.in);
			String input = scanner.nextLine();
			transfer(input);
		}
	}

}
