package wenyu.learning.Maths;

/*
 * Implement a function to check if a integer is palindrome (eg. 12321)
 * Solution1: Transfer the integer to string and check string
 * Solution2: Form a reverse integer according the given one. 
 * 			  And then check if these two integer is the same one.
 */

public class PalindromeCheck {

	public static boolean solution1(int number) {
		//Converting a Number into a String
		String numStr = String.valueOf(number);
		int i = 0;
		int j = numStr.length()-1; 
		
		while(i<j){
			if(numStr.charAt(i) != numStr.charAt(j)) {
				System.out.println(number + " is not palindrom...");
				return false;
			}
			i++;
			j--;
		}
		System.out.println(number + " is palindrom...");
		return true;
	}
	
	public static boolean solution2(int number, boolean print) {
		int reverseNum = 0;
		int tmpNum = number;
		while(tmpNum>0) {
			int tmp = tmpNum%10;
			reverseNum = reverseNum*10+tmp;
			tmpNum /= 10;
		}
		
		if(reverseNum == number) {
			if(print) {
				System.out.println(number + " is palindrom...");
			}
			return true;
		} else {
			if(print) {
				System.out.println(number + " is not palindrom...");
			}
			return false;
		}
	}
	
	public static void main(String[] args) {
		int number = 12300321;
		solution1(number);
		System.out.println("=========================================");
		solution2(number, true);
	}

}
