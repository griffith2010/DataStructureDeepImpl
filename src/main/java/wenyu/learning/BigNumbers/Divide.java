package wenyu.learning.BigNumbers;

import java.util.Arrays;

public class Divide {

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
	
	private static char[] divide(char[] number1, char[] number2, boolean minus) {
		char[] result = new char[number1.length];
		
		char[] tmpNum1 = new char[] {'0'};
		for(int i=0;i<number1.length;i++) {
			char[] tmpNew = new char[] {number1[i]};
			tmpNum1 = Add.addInterface(tmpNum1, tmpNew);
			
			char[] tmp = new char[] {'1'};
			char[] tmpPreResult = null;
			char[] tmpResult = Multiply.multiplyInterface(number2.clone(), tmp);
			tmpResult = Subtract.subtractInterface(tmpNum1.clone(), tmpResult.clone());
			while(tmpResult.length>0&&tmpResult[0]!='-') {
				tmp[0]++;
				tmpPreResult = tmpResult;
				tmpResult = Multiply.multiplyInterface(number2.clone(), tmp);
				tmpResult = Subtract.subtractInterface(tmpNum1.clone(), tmpResult);
			}
			result[i] = String.valueOf((tmp[0]-'0')-1).toCharArray()[0];
			if(tmpPreResult != null) {
				tmpNum1 = tmpPreResult;
			}
			tmpNum1 = Multiply.multiplyInterface(tmpNum1, "10".toCharArray());
		}
		
		return removeFrontZero(result, minus);
	}
	
	public static char[] divideInterface(char[] number1, char[] number2) throws Exception {
		boolean minus = false;
		if(number2.length==0 || (number2.length==1&&number2[0]=='0')) {
			throw new Exception("Number2 cannot be 0");
		}
		
		if(number1.length<=number2.length) {
			// To make sure number1 is bigger than number2;
			int i=0;
			while(number2.length==number1.length&&i<number1.length&&number2[i]==number1[i]) {i++;}
			if(number2.length>number1.length || (i<number1.length&&number2[i]>number1[i])) {
				return new char[] {'0'};
			}
		}
		
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
		
		char[] result = divide(number1, number2, minus);
		return result;
	}
	
	public static void main(String[] args) throws Exception {
		String number1 = "-1234325235636453";
		String number2 = "554322543543253";
		char[] result = divideInterface(number1.toCharArray(), number2.toCharArray());
		System.out.println("Divide of " + number1 + " and " + number2 + " is " + String.valueOf(result));
	}
}
