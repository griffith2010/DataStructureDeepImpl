package wenyu.learning.Others;

/*
 * Write a method which finds the maximum of two numbers. 
 * You should not use if-else or any other comparison operator.
 * EXAMPLE
 * Input: 5, 10
 * Output: 10
 */
public class NumCompareWithoutCompOp {

	public static int compareBigger(int num1, int num2) {
		int num = num1-num2;
		// if first bit of num is 1 => num<0 => num1<num2
		
		int k = (num>>31)&1;
		
		int result = num1 - k * (num1-num2);
		
		return result;
	}
	
	public static void main(String[] args) {
		System.out.println(compareBigger(5,3));
	}

}
