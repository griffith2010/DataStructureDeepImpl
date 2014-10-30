package wenyu.learning.BigNumbers;

import java.util.Arrays;

/*
 * Add big numbers
 * Logic: Add by add each digit one by one
 */

public class Add {
	
	private static char[] reverse(char[] number) {
		int start = 0;
		int end = number.length-1;
		int empty = 0;
		
		while(start<end) {
			if(number[end] == '\u0000') {
				end--;
				empty++;
			}
			char tmp = number[start];
			number[start] = number[end];
			number[end] = tmp;
			
			start++;
			end--;
		}
		
		return Arrays.copyOf(number, number.length-empty);
	}
	
	private static char[] add(char[] number1, char[] number2) {
		int sumLen = (number1.length>number2.length)?number1.length+1:number2.length+1;
		
		char[] sum = new char[sumLen];
		char[] reversedNumber1 = reverse(number1);
		char[] reversedNumber2 = reverse(number2);
		
		int carry = 0;
		int i = 0;
		for(;i<reversedNumber1.length&&i<reversedNumber2.length;i++) {
			int num1 = reversedNumber1[i]-'0';
			int num2 = reversedNumber2[i]-'0';
			int tmpSum = num1+num2+carry;
			if(tmpSum>=10) {
				carry = 1;
				tmpSum = tmpSum%10;
			} else {
				carry = 0;
			}
			sum[i] = Integer.toString(tmpSum).charAt(0);
		}
		
		while(i<reversedNumber1.length) {
			int num1 = reversedNumber1[i]-'0';
			int tmpSum = num1+carry;
			if(tmpSum>=10) {
				carry = 1;
				tmpSum = tmpSum%10;
			} else {
				carry = 0;
			}
			sum[i++] = Integer.toString(tmpSum).charAt(0);
		}
		 
		while(i<reversedNumber2.length) {
			int num2 = reversedNumber2[i]-'0';
			int tmpSum = num2+carry;
			if(tmpSum>=10) {
				carry = 1;
				tmpSum = tmpSum%10;
			} else {
				carry = 0;
			}
			sum[i++] = Integer.toString(tmpSum).charAt(0);
		}
		
		if(carry > 0) {
			sum[i] = '1';
		}
		
		return reverse(sum);
	}
	
	public static char[] addInterface(char[] number1, char[] number2) {
		number1 = (number1.length==0)?new char[] {'0'}:number1;
		number2 = (number2.length==0)?new char[] {'0'}:number2;
		
		if(number1[0]!='-' && number2[0]!='-') {
			return add(number1, number2);
		} else if(number1[0]=='-' && number2[0]!='-') {
			number1 = Arrays.copyOfRange(number1, 1, number1.length);
			return Subtract.subtractInterface(number2, number1);
		} else if(number1[0]!='-' && number2[0]=='-') {
			number2 = Arrays.copyOfRange(number2, 1, number2.length);
			return Subtract.subtractInterface(number1, number2);
		} else if(number1[0]=='-' && number2[0]=='-') {
			number1 = Arrays.copyOfRange(number1, 1, number1.length);
			number2 = Arrays.copyOfRange(number2, 1, number2.length);
			char[] tmpSum = add(number1, number2);
			char[] sum = new char[tmpSum.length+1];
			sum[0] = '-';
			for(int i=1;i<sum.length;i++) {
				sum[i] = tmpSum[i-1];
			}
			return sum;
		}
		return null;
	}
	
	public static void main(String[] args) {
		String number1 = "-123567";
		String number2 = "-7654";
		char[] sum = addInterface(number1.toCharArray(), number2.toCharArray());
		System.out.println("Sum of " + number1 + " and " + number2 + " is " + String.valueOf(sum));
	}

}
