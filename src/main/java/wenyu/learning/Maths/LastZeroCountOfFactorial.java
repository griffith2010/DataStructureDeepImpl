package wenyu.learning.Maths;

/*
 *  5! = 5 * 4 * 3 * 2 * 1 = 120 (it contains 1 zero). 
	How many zeroes will be contained in 100!
	
	Logic: Every 5 will have a zero!
		   Every 25 will have two zeros!
		   Every 125 will have three zeros!
		   
	Which means: zero count = n/5+n/25+n/125+...n/pow(5,k)
	limit: n>pow(5,k)
 */
public class LastZeroCountOfFactorial {

	
	public static int countTrailingZerosOfFactorial(int n) {
	    int count = 0;
	    for (int powerOfFive = 5; n / powerOfFive > 0; powerOfFive *= 5) {
	        count += n / powerOfFive;
	    }
	    return count;
	}
	
	public static void main(String[] args) {
		int num = 100;
		System.out.println("Zero count is: " + countTrailingZerosOfFactorial(num));
	}

}
