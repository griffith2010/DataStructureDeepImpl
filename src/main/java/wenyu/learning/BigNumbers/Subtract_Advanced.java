package wenyu.learning.BigNumbers;

import java.util.Arrays;

/*
 * Advanced logic of big number subtracting
 * Logic:
 * 	1. For integer: biggest positive number is 2^31 (10 digits, but first one is smaller than 2)
 * 	2. Big number can be expressed as [(9digits number), (9digits number), ...]
 */

public class Subtract_Advanced {
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
	
	public static int[] subtract(int[] number1, int[] number2) {
		int i=0;
		boolean minus = false;
		if(number1.length<=number2.length) {
			// To make sure number1 is bigger than number2;
			while(number2.length==number1.length&&i<number1.length&&number2[i]==number1[i]) {i++;}
			if(number2.length>number1.length || (i<number1.length&&number2[i]>number1[i])) {
				minus = true;
				int[] tmp = number1;
				number1 = number2;
				number2 = tmp;
			}
		}
		
		int num1Len = number1.length;
		int num2Len = number2.length;
		int[] result = (num1Len>num2Len)?new int[num1Len+1]:new int[num2Len+1];
		int carry = 0;
		for(i=1;i<=num1Len&&i<=num2Len;i++) {
			int currIdx1 = num1Len-i;
			int currIdx2 = num2Len-i;
			int currIdxResult = result.length-i;
			
			if(number1[currIdx1]-carry >= number2[currIdx2]) {
				result[currIdxResult] = number1[currIdx1] - carry - number2[currIdx2];
				carry = 0;
			} else {
				result[currIdxResult] = number1[currIdx1] - carry - number2[currIdx2] + maxSingleInt;
				carry = 1;
			}
		}
		
		while(i<=num1Len) {
			int currIdx1 = num1Len-i;
			int currIdxResult = result.length-i;
			
			result[currIdxResult] = number1[currIdx1] - carry;
			carry = 0;
			i++;
		}
		
		while(i<=num2Len) {
			int currIdx2 = num2Len-i;
			int currIdxResult = result.length-i;
			
			result[currIdxResult] = number2[currIdx2] - carry;
			carry = 0;
			i++;
		}
		
		for(i=0;i<result.length;i++) {
			if(result[i]!=0) {
				break;
			}
		}
		
		result = Arrays.copyOfRange(result, i, result.length);
		if(minus) {
			result[0] *= -1;
		}
		
		return result;
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
		int[] number1 = {9};
		int[] number2 = {987215422,653,999999910};
		System.out.println("Subtract: ");
		printNum(number1);
		System.out.println();
		printNum(number2);
		System.out.println();
		System.out.println("-------------------------------------------------------");
		printNum(subtract(number1, number2));
		
		System.out.println("\nValide: ");
		char[] sum = Subtract.subtractInterface(splitNum(number1), splitNum(number2));
		System.out.println(String.valueOf(sum));

	}

}
