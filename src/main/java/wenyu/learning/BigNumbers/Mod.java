package wenyu.learning.BigNumbers;


public class Mod {

	private static char[] mod(char[] number1, char[] number2) throws Exception {
		char[] divide = Divide.divideInterface(number1, number2);
		char[] result = Multiply.multiplyInterface(number2, divide);
		result = Subtract.subtractInterface(number1, result);
		
		return result;
	}
	
	public static char[] modInterface(char[] number1, char[] number2) throws Exception {
		number1 = (number1.length==0)?new char[] {'0'}:number1;
		number2 = (number2.length==0)?new char[] {'0'}:number2;
		
		if(number1[0]!='-' && number2[0]!='-') {
			if(number1.length<=number2.length) {
				int i=0;
				while(number2.length==number1.length&&i<number1.length&&number2[i]==number1[i]) {i++;}
				if(number2.length>number1.length || (i<number1.length&&number2[i]>number1[i])) {
					throw new Exception("Number2 is bigger than number1.");
				}
			}
			return mod(number1, number2);
		} else {
			throw new Exception("Mod cannot have negative numbers.");
		}
	}
	
	public static void main(String[] args) throws Exception {
		String number1 = "432142159523543267645";
		String number2 = "1";
		
		char[] result = modInterface(number1.toCharArray(), number2.toCharArray());
		System.out.println("Mod of " + number1 + " and " + number2 + " is " + String.valueOf(result));
	}

}
