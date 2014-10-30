package wenyu.learning.BigNumbers;

import java.util.Arrays;

public class Multiply {
	
	private static char[] removeFrontZero(char[] number, boolean minus) {
		int i=0;
		for(;i<number.length;i++) {
			if(number[i]!='0' && number[i]!='\u0000') {
				break;
			}
		}
		
		if(i==number.length) {
			return "0".toCharArray();
		} 
		
		number = Arrays.copyOfRange(number, i, number.length);
		if(minus){
			number = Arrays.copyOf(number, number.length+1);
			for(int j=number.length-2;j>=0;j--) {
				number[j+1] = number[j];
			}
			number[0] = '-';
		}
		
		return number;
	}
	
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
	
	private static char[] multiply(char[] number1, char[] number2, boolean minus) {
		char[] result = new char[] {'0'};
		char[] reversedNumber1 = reverse(number1);
		char[] reversedNumber2 = reverse(number2);
		
		for(int i=0;i<reversedNumber2.length;i++) {
			int num2 = reversedNumber2[i] - '0';
			int carry = 0;
			char[] tmpMultiResult = new char[reversedNumber1.length+1+i];
			int j = 0;
			while(j<i) {
				tmpMultiResult[j++] = '0';
			}
			for(j=0;j<reversedNumber1.length;j++) {
				int num1 = reversedNumber1[j] - '0';
				int tmpResult = num1*num2+carry;
				if(tmpResult>=10) {
					carry = tmpResult/10;
					tmpResult = tmpResult%10;
				} else {
					carry = 0;
				}
				tmpMultiResult[j+i] = Integer.toString(tmpResult).charAt(0);
			}
			if(carry > 0) {
				tmpMultiResult[j+i] = Integer.toString(carry).charAt(0);;
			}
			
			result = Add.addInterface(result, reverse(tmpMultiResult));
		}
		
		if(result.length==1&&result[0]=='0') {
			return result;
		}
		
		return removeFrontZero(result, minus);
	}
	
	public static char[] multiplyInterface(char[] number1, char[] number2) {
		number1 = (number1.length==0)?new char[] {'0'}:number1;
		number2 = (number2.length==0)?new char[] {'0'}:number2;
		
		boolean minus = false;
		if(number1[0]=='-' && number2[0]!='-') {
			number1 = Arrays.copyOfRange(number1, 1, number1.length);
			minus = true;
		} else if(number1[0]!='-' && number2[0]=='-') {
			number2 = Arrays.copyOfRange(number2, 1, number2.length);
			minus = true;
		} else if(number1[0]=='-' && number2[0]=='-') {
			number1 = Arrays.copyOfRange(number1, 1, number1.length);
			number2 = Arrays.copyOfRange(number2, 1, number2.length);
		}
		
		char[] result = multiply(number1, number2, minus);
		return result;
	}
	
	public static void main(String[] args) {
		String number1 = "33";
		String number2 = "3216434321";
		char[] result = multiplyInterface(number1.toCharArray(), number2.toCharArray());
		System.out.println("Mulitiplication of " + number1 + " and " + number2 + " is " + String.valueOf(result));
	}
}
