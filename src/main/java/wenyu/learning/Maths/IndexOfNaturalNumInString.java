package wenyu.learning.Maths;

/*
 * You have a single string which contains all 
 * the positive numbers upto N concatenated together. 
 * If you are given an input number then how would 
 * you find the index position of the number in the 
 * string. 
 * Eg: String str = "12345678910111213141516171819202122232425......up to 10000"; 
 * input = 20 should return the index of 20 in the string which is 29
 * The example string is up to 10000. The actual string can be up to any number N.
 * 
 * Logic:
 * 		1-9: 9*1
 * 		10-99: 90*2
 * 		100-999: 900*3
 * 		10^(n-1)-10^n-1: 9*10^(n-1)*n
 * 	1. Find how many digit the number is and use above formula to calculate index.
 * 	   formula:	idx + 9*10^(n-1)*n
 *  2. After loop, idx has already point to the the first number of bit (n-1) [divide]
 *  3. Find the number count before first number of bit (n-1) and given number.
 *  4. Remaining index count could be calculated by: number_count*n
 *  5. and index + 1 will be the result
 */
public class IndexOfNaturalNumInString {

	public static double find(int number) {
		int bkNum = number;
		double idx = 0;
		double bitCount=1;
		int divide = 1;
		while(bkNum/divide>=10) {
			idx = idx+9*Math.pow(10, bitCount-1)*bitCount;
			divide *= 10;
			bitCount++;
		}
		
		idx = idx+(bkNum-divide)*bitCount+1;
		return idx;
	}
	
	private static void verify(int number, double idx) {
		StringBuilder builder = new StringBuilder();
		for(int i=1;i<number;i++) {
			String str = String.valueOf(i);
			builder.append(str);
		}
		if(builder.length()+1 == idx) {
			System.out.println("Verify: Correct...");
		} else {
			System.out.println("Verify: Wrong...");
		}
	}
	
	public static void main(String[] args) {
		int number = 2000;
		double idx = find(number);
		System.out.println(number + "'s index in natural number string is " + (long)idx);
		verify(number, idx);
	}
}
