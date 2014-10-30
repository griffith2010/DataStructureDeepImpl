package wenyu.learning.Maths;

import java.util.Scanner;

/*
 * implement a function to check if a given number is the power of 2
 * like 1,2,4,8,16...
 * Logic: 
 * 	 (n!=0) && (((n-1)&n)==0)
 */

public class PowerOfTwoCheck {

	public static void isPower2(int n) {
		boolean result = (n!=0) && (((n-1)&n)==0);
		if(result) {
			System.out.println(n + " is power of 2.");
		} else {
			System.out.println(n + " is not power of 2.");
		}
	}
	
	public static void main(String[] args) {
		while(true) {
			System.out.print("Please input a number: ");
			Scanner scanner = new Scanner(System.in);
			String input = scanner.nextLine();
			isPower2(Integer.parseInt(input));
		}
	}

}
