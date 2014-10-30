package wenyu.learning.Maths;

import java.util.Scanner;

/*
 * implement a function to check if a given number is the power of 2
 * like 1,2,4,8,16...
 * Logic: 
 * 	 (n!=0) && (((n-1)&n)==0)
 */

public class MultiplesOfTwoCheck {

	public static void check(int n) {
		boolean result = (n&1)==0;
		if(result) {
			System.out.println(n + " is multiples of 2.");
		} else {
			System.out.println(n + " is not multiples of 2.");
		}
	}
	
	public static void main(String[] args) {
		while(true) {
			System.out.print("Please input a number: ");
			Scanner scanner = new Scanner(System.in);
			String input = scanner.nextLine();
			check(Integer.parseInt(input));
		}
	}

}
