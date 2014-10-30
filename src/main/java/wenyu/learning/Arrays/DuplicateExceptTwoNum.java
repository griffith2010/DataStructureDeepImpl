package wenyu.learning.Arrays;

/*
 * Given an array, containing duplicated numbers except two.
 * Implement a function to find these two numbers
 * Logic:
 * 	1. First to XOR all the numbers;
 *  2. Find the first bit which is 1 in the result above
 *  3. Divide all the numbers to two parts, one is having the above 1 bit and another is not having
 *  4. XOR these two parts, and find the results
 */
public class DuplicateExceptTwoNum {

	public static void getOnce(int numbers[]){
		if (numbers.length < 2) {
			return;
		}
		int resultExclusiveOR = 0;
		for (int i = 0; i < numbers.length; ++ i) {
			resultExclusiveOR ^= numbers[i];
		}
		
		int indexOf1 = findFirstBitIs1(resultExclusiveOR);
		int num1 = 0;
		int num2 = 0;
		for (int j = 0; j < numbers.length; ++ j) {
			if(isBit1(numbers[j], indexOf1)) {
				num1 ^= numbers[j];
			} else {
				num2 ^= numbers[j];
			}
		}
		
		System.out.println("These two numbers are " + num1 + " and " + num2);
	}
	
	// The first 1 bit from the rightmost
	private static int findFirstBitIs1(int num){
		int indexBit = 0;
		while (((num & 1) == 0) && (indexBit < 32)) {
			num = num >> 1;
			++ indexBit;
		}
		return indexBit;
	}
	
	// check whether the bit with index indexBit is 1
	private static boolean isBit1(int num, int indexBit) {
		num = num >> indexBit;
		return (num & 1) == 1;
	}
	
	public static void getOnce_solution1(int[] arr) {
		if(arr==null || arr.length<2) {
			return;
		}
		
		int firstStepXORResult = 0;
		for(int num : arr) {
			firstStepXORResult ^= num;
		}
		
		// find first 1 bit
		String binaryStringForFirstXORResult = Integer.toBinaryString(firstStepXORResult);
		int i=binaryStringForFirstXORResult.length()-1;
		for(; i>=0; i--) {
			if(binaryStringForFirstXORResult.charAt(i) == '1') {
				break;
			}
		}
		int posFromEnd = binaryStringForFirstXORResult.length()-1-i;
		
		// divide arrays
		int xorResultWithOneBit = 0;
		int xorResultWithoutOneBit = 0;
		for(int num: arr) {
			if(Integer.toBinaryString(num).charAt(Integer.toBinaryString(num).length()-1-posFromEnd) == '1') {
				xorResultWithOneBit ^= num;
			} else {
				xorResultWithoutOneBit ^= num;
			}
		}
		
		System.out.println("These two numbers are " + xorResultWithOneBit + " and " + xorResultWithoutOneBit);
	}
	
	public static void main(String[] args) {
		getOnce(new int[] {2, 4, 3, 6, 3, 2, 5, 5});
	}

}
