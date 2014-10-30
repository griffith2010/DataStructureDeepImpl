package wenyu.learning.BigNumbers;

import java.util.Arrays;

/*
 * Advanced logic of big number adding
 * Logic:
 * 	1. For integer: biggest positive number is 2^31 (10 digits, but first one is smaller than 2)
 * 	2. Big number can be expressed as [(9digits number), (9digits number), ...]
 */

public class Add_Advanced {
	private static final int maxSingleInt = (int)Math.pow(10, 9);
	
	private static void printNum(int[] number) {
		for(int i=0;i<number.length;i++) {
			if(i!=0 && String.valueOf(number[i]).length()<9) {
				System.out.printf("%09d", number[i]);
			} else {
				System.out.print(number[i]);
			}
		}
	}
	
	public static int[] add(int[] number1, int[] number2) {
		int num1Len = number1.length;
		int num2Len = number2.length;
		
		int[] result = (num1Len>num2Len)?new int[num1Len+1]:new int[num2Len+1];
		int carry = 0;
		int i=1;
		for(;i<=num1Len&&i<=num2Len;i++) {
			int currIdx1 = num1Len-i;
			int currIdx2 = num2Len-i;
			int currIdxResult = result.length-i;
			
			if(number1[currIdx1] + number2[currIdx2] + carry<maxSingleInt) {
				result[currIdxResult] = number1[currIdx1] + number2[currIdx2] + carry;
				carry = 0;
			} else {
				int tmp = number1[currIdx1] + number2[currIdx2] + carry;
				result[currIdxResult] = tmp % maxSingleInt;
				carry = tmp / maxSingleInt;
			}
		}
		
		while(i<=num1Len) {
			int currIdx1 = num1Len-i;
			int currIdxResult = result.length-i;
			
			if(number1[currIdx1] + carry<maxSingleInt) {
				result[currIdxResult] = number1[currIdx1] + carry;
				carry = 0;
			} else {
				int tmp = number1[currIdx1] + carry;
				result[currIdxResult] = tmp % maxSingleInt;
				carry = tmp / maxSingleInt;
			}
			i++;
		}
		
		while(i<=num2Len) {
			int currIdx2 = num2Len-i;
			int currIdxResult = result.length-i;
			
			if(number2[currIdx2] + carry<maxSingleInt) {
				result[currIdxResult] = number2[currIdx2] + carry;
				carry = 0;
			} else {
				int tmp = number2[currIdx2] + carry;
				result[currIdxResult] = tmp % maxSingleInt;
				carry = tmp / maxSingleInt;
			}
			i++;
		}
		
		if(carry!=0) {
			result[result.length-i] = carry;
			i++;
		}
		return Arrays.copyOfRange(result, result.length-i+1, result.length);
	}
	
	private static char[] splitNum(int[] number) {
		StringBuilder builder = new StringBuilder();
		for(int i=0;i<number.length;i++) {
			if(i!=0 && String.valueOf(number[i]).length()<9) {
				String str = String.format("%09d", number[i]);
				builder.append(str);
			} else {
				builder.append(number[i]);
			}
		}
		 
		return builder.toString().toCharArray();
	}
	
	public static void main(String[] args) {
		int[] number1 = {923567899,1,1,2};
		int[] number2 = {987215422,653,999999910};

		System.out.println("Add: ");
		printNum(number1);
		System.out.println();
		printNum(number2);
		System.out.println();
		System.out.println("-------------------------------------------------------");
		printNum(add(number1, number2));
		
		System.out.println("\nValide: ");
		char[] sum = Add.addInterface(splitNum(number1), splitNum(number2));
		System.out.println(String.valueOf(sum));
	}

}
