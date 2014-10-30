package wenyu.learning.BigNumbers;

import java.util.Arrays;

public class Subtract {
	
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
	
	private static char[] subtract(char[] number1, char[] number2) {
		boolean minus = false;
		int i = 0;
		if(number1.length<=number2.length) {
			// To make sure number1 is bigger than number2;
			while(number2.length==number1.length&&i<number1.length&&number2[i]==number1[i]) {i++;}
			if(number2.length>number1.length || (i<number1.length&&number2[i]>number1[i])) {
				minus = true;
				char[] tmp = number1;
				number1 = number2;
				number2 = tmp;
			}
		}
		
		char[] result = new char[number1.length];
		char[] reversedNumber1 = reverse(number1);
		char[] reversedNumber2 = reverse(number2);
		
		int carry = 0;
		for(i=0;i<number2.length;i++) {
			int num1 = reversedNumber1[i]-'0';
			int num2 = reversedNumber2[i]-'0';
			
			num1 -= carry;
			if(num1<num2) {
				carry = 1;
				num1 += 10;
			} else {
				carry = 0;
			}
			int sub = num1-num2;
			result[i] = Integer.toString(sub).charAt(0);
		}
		
		while(i<number1.length) {
			int num1 = reversedNumber1[i]-'0';
			num1 -= carry;
			if(num1<0) {
				carry = 1;
				num1 += 10;
			} else {
				carry = 0;
			}
			result[i++] = Integer.toString(num1).charAt(0);
		}
		
		result = reverse(result);
		result = removeFrontZero(result, minus);
		return result;
	}
	
	public static char[] subtractInterface(char[] number1, char[] number2) {
		number1 = (number1.length==0)?new char[] {'0'}:number1;
		number2 = (number2.length==0)?new char[] {'0'}:number2;
		
		if(number1[0]!='-' && number2[0]!='-') {
			return subtract(number1, number2);
		} else if(number1[0]=='-' && number2[0]!='-') {
			number1 = Arrays.copyOfRange(number1, 1, number1.length);
			char[] tmpSum = Add.addInterface(number1, number2);
			char[] sum = new char[tmpSum.length+1];
			sum[0] = '-';
			for(int i=1;i<sum.length;i++) {
				sum[i] = tmpSum[i-1];
			}
			return sum;
		} else if(number1[0]!='-' && number2[0]=='-') {
			number2 = Arrays.copyOfRange(number2, 1, number2.length);
			char[] sum = Add.addInterface(number1, number2);
			return sum;
		} else if(number1[0]=='-' && number2[0]=='-') {
			number1 = Arrays.copyOfRange(number1, 1, number1.length);
			number2 = Arrays.copyOfRange(number2, 1, number2.length);
			return subtract(number2, number1);
		}
		return null;
	}
	
	public static void main(String[] args) {
		String number1 = "280";
		String number2 = "280";
		
		char[] result = subtractInterface(number1.toCharArray(), number2.toCharArray());
		System.out.println("Subtract of " + number1 + " and " + number2 + " is " + String.valueOf(result));
	}

}
