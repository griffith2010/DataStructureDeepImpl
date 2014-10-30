package wenyu.learning.Others;


/**
 * There is a large file( 1TB) containinig braces. 
 * Question is to check for their balance. 
 */
public class BalanceBraces {

	public static boolean check(String string) {
		int counter = 0;
		for(int i=0;i<string.length();i++) {
			if(string.charAt(i) == '{') {
				counter++;
			} else if(string.charAt(i) == '}') {
				counter--;
			}
			
			if(counter<0) {
				return false;
			}
		}
		
		return counter==0;
	}
	
	/**
	 *  As the question said, the string could be very very large
	 *  String need to be checked parallelly
	 * 
	 *  We should check the same two conditions: 
		1. The total sum of the brackets is 0 
		2. We never go negative at any point of time. 
	 */
	public static boolean checkParallel(String string) {
		int chunkSize = 10;
		int counter = 0;
		
		int i=0;
		for(;i+chunkSize<string.length();i+=chunkSize) {
			String subStr = new String(string.substring(i, i+chunkSize));
			int[] result = checkSubString(subStr);
			
			if(counter+result[1]<0 || counter+result[0]<0) {
				return false;
			} else {
				counter += result[0];
			}
		}
		
		if(i<string.length()) {
			String subStr = new String(string.substring(i, string.length()));
			int[] result = checkSubString(subStr);
			
			if(counter+result[1]<0 || counter+result[0]<0) {
				return false;
			} else {
				counter += result[0];
			}
		}
		
		return counter==0;
	}
	
	private static int[] checkSubString(String string) {
		/**
		 *  This can easily be done with one chunk, if we will do it on multiple chunks, 
		 *  then there is two numbers that we need to report per chunk: 
			1. Sum of all brackets. 
			2. Lowest negative number that was found during processing this chunk. 
		 */
		int[] result = new int[2];
		int preRightBrace = 0;
		int leftBrace = 0;
		int rightBrace = 0;
		
		for(int i=0;i<string.length();i++) {
			if(string.charAt(i) == '{') {
				leftBrace++;
			} else if(string.charAt(i) == '}') {
				if(leftBrace==0) {
					preRightBrace++;
				}
				rightBrace++;
			}
		}
		
		result[0] = leftBrace-rightBrace;
		result[1] = preRightBrace;
		return result;
	}
	
	public static void main(String[] args) {
		String string = "fdasrhflafkjldajgoi[tgi;anvijfdshr{FdarqrEWQ}EWQRqwr{EWQREQWR{EWQREWQFR}EWQREWQ{REWQREWQREWQ}REWQREQW}REQWRrewqrewq.";
		if(check(string)) {
			System.out.println("String is balance...");
		} else {
			System.out.println("String is not balance...");
		}

		
		if(checkParallel(string)) {
			System.out.println("String is balance...");
		} else {
			System.out.println("String is not balance...");
		}
	}

}
