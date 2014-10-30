package wenyu.learning.Maths.Prime;

/*
 * Check if a given number is prime number or not
 * Logic:
 * 	Solution1: simplest way, test number one by one
 * 	Solution2: only test N/2 numbers
 * 	Solution3: test 2 and then only test odd number before N/2
 *  Solution4: test square root of N numbers
 */

public class IsPrimeProblem {

	public static boolean isPrime_solution1(int num) {
		// Simplest way, scan from 2 to num-1
		for(int i=2;i<num;i++) {
			if(num%i == 0) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean isPrime_solution2(int num) {
		// Another way, scan from 2 to num/2
		// Since if num has factor, the factor must be smaller than num/2, 
		// or num/2 * 2 will be bigger than num itself
		for(int i=2;i<num/2;i++) {
			if(num%i == 0) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean isPrime_solution3(int num) {
		// Another way, scan from 2 to num/2
		// Since if num has factor, the factor must be smaller than num/2, 
		// or num/2 * 2 will be bigger than num itself
		
		// And except 2, all the even number can be ignore,
		// since even number also has the factor of 2
		if(num%2 == 0) {
			return false;
		}
		
		for(int i=3;i<num/2;i+=2) {
			if(num%i == 0) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean isPrime_solution4(int num) {
		// Another way, scan from 2 to Square root of num
		for(int i=2;i<Math.sqrt(num);i++) {
			if(num%i == 0) {
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		int number = 957997099;
		number = 1299709;
		if(isPrime_solution1(number)) {
			System.out.println(number + " is prime.");
		} else {
			System.out.println(number + " is not prime.");
		}
		
		if(isPrime_solution2(number)) {
			System.out.println(number + " is prime.");
		} else {
			System.out.println(number + " is not prime.");
		}
		
		if(isPrime_solution3(number)) {
			System.out.println(number + " is prime.");
		} else {
			System.out.println(number + " is not prime.");
		}
		
		if(isPrime_solution4(number)) {
			System.out.println(number + " is prime.");
		} else {
			System.out.println(number + " is not prime.");
		}
	}

}
