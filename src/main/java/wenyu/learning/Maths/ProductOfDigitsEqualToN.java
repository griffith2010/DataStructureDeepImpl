package wenyu.learning.Maths;

import java.util.Scanner;

/*
 * Given a number N, now find the smallest number K 
 * such that product of digits of K is equal to N. 
 * If there is no such K then return -1. 
 * 
 * Suppose N = 100, then K = 455 
 * 		   N = 26,  then K = -1
 * 
 * Logic:
 * 	start to divide from the 9-1;
 */
public class ProductOfDigitsEqualToN {
	
	public static int generate(int N) {
		StringBuilder resultStr = new StringBuilder();
		for(int factor = 9; factor > 1; factor--) {
			while(N % factor == 0) {
				N /= factor;
				resultStr.insert(0, factor);
			}
		}
		if(N != 1) {
			return -1;
		} else {
			return Integer.parseInt(resultStr.toString());
		}
	}
	
	public static void main(String[] args) {
		while(true) {
			System.out.print("Please input N: ");
			Scanner scanner = new Scanner(System.in);
			String NStr = scanner.nextLine();
			int N = Integer.parseInt(NStr);
			System.out.println(N + ": " + generate(N));
		}
	}

}
