package wenyu.learning.Maths;

/*
 * The reflected binary code, also known as Gray code after Frank Gray, 
 * is a binary numeral system where two successive values differ in only 
 * one bit (binary digit). The reflected binary code was originally designed 
 * to prevent spurious output from electromechanical switches.
 * Example: 001, 011, 010, ...
 * 
 * Problem 1: Given two hexadecimal numbers find if they can be consecutive in gray code
 * 			  Logic: xor them, and verify if the result is the power of 2
 */
public class VerifyGrayCode {

	public static boolean verify(long num1, long num2) {
		long n = num1 ^ num2;
		boolean result = (n!=0) && (((n-1)&n)==0);
		return result;
	}
	
//	public static int binaryToGray(int num)
//	{
//		return (num >> 1) ^ num;
//	}
	 
//	public static int grayToBinary(int num)
//	{
//	    int mask;
//	    for (mask = num >> 1; mask != 0; mask = mask >> 1) {
//	        num = num ^ mask;
//	    }
//	    return num;
//	}
	
	public static void main(String[] args) {
		long num1 = 1l;
		long num2 = 10l;
		
		if(verify(num1, num2)) {
			System.out.println(num1 + " and " + num2 + " can be consecutive in gray code.");
		} else {
			System.out.println(num1 + " and " + num2 + " cannot be consecutive in gray code.");
		}
	}
}
