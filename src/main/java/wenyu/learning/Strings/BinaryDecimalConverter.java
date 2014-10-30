package wenyu.learning.Strings;

import java.util.ArrayDeque;
import java.util.Deque;

/*
 * Given a (decimal - e.g. 3.72) number that is passed in as a string, 
 * print the binary representation. If the number can not be represented 
 * accurately in binary, print “ERROR”
 * 
 * Logic:
 * 	Binary to Decimal:
 * 		1101.0111=>1*2^3+1*2^2+0*2^1+1*2^0+0*2^(-1)+1*2^(-2)+1*2^(-3)+1*2^(-4) 
 *  Decimal to Binary
 *  	123.456 => 123=>1111011
 *  			   0.456=> 0.456*2=0.912  0
 *  			   		   0.912*2=1.824  1
 *                         0.824*2=1.648  1
 *                         0.648*2=1.296  1
 *                         ...
 */
public class BinaryDecimalConverter {

	public static void DecimalToBinary(Double number) {
		String numStr = String.valueOf(number);
		boolean sign = false;
		if(numStr.charAt(0) == '-') {
			//Negative number
			sign = true;
			numStr = numStr.substring(1);
		}
		
		int numInt = Integer.parseInt(numStr.split("\\.")[0]);
		double numFlo = 0;
		if(numStr.split("\\.").length>1) {
			numFlo = Double.parseDouble("0."+numStr.split("\\.")[1]);
		}
		
		Deque<Integer> stackInt = new ArrayDeque<Integer>();
		while(numInt>0) {
			int bit = numInt%2;
			numInt /= 2;
			stackInt.addFirst(bit);
		}
		
		Deque<Integer> stackFlo = new ArrayDeque<Integer>();
		while(numFlo>0) {
			numFlo *= 2;
			if(numFlo >= 1) {
				stackFlo.addLast(1);
				numFlo -= 1;
			} else {
				stackFlo.add(0);
			}
		}
		
		// Print result
		while(!stackInt.isEmpty()) {
			System.out.print(stackInt.pollFirst());
		}
		if(!stackFlo.isEmpty()) {
			System.out.print(".");
		}
		while(!stackFlo.isEmpty()) {
			System.out.print(stackFlo.pollFirst());
		}
	}
	
	public static void BinaryToDecimal(String binStr) {
		String numInt = binStr.split("\\.")[0];
		String numFlo = "";
		if(binStr.split("\\.").length>1) {
			numFlo = binStr.split("\\.")[1];
		}
		
		double result = 0;
		int i = 0;
		while(i<numInt.length()) {
			char bit = numInt.charAt(numInt.length()-i-1);
			result += (bit-'0') * Math.pow(2, i++);
		}
		
		if(numFlo.length()>0) {
			i = numFlo.length();
			while(i>0) {
				char bit = numFlo.charAt(i-1);
				result += (bit-'0') * Math.pow(2, -1*i--);
			}
		}
		
		System.out.println(result);
	}
	
	public static void main(String[] args) {
		Double number = 22.1234;
		DecimalToBinary(number);
		
		System.out.println();
		
		String binStr = "10110.00011111100101110010010001110100010100111000111011110011";
		BinaryToDecimal(binStr);
	}

}
