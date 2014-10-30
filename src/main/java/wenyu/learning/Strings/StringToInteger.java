package wenyu.learning.Strings;
import java.util.Scanner;


public class StringToInteger {

	public static void transfer(String input) {
		if(input==null || input.trim().length() == 0) {
			// Test if input is null or empty string
			return;
		}

		long number = 0;
		input = input.trim();			// Trim the input
		
		int idx = 0;
		int sign = 1;              		// Sign of integer
		if(input.charAt(0) == '+') {
			idx++;
		} else if(input.charAt(0) == '-') {
			idx++;
			sign=-1;
		}
		
		for(;idx<input.length();idx++) {
			try {
				int figure = Integer.valueOf(String.valueOf(input.charAt(idx)));
				
				// test if the number is bigger than max value of long (difficult)
				if(sign>0 && Long.MAX_VALUE-figure * Math.pow(10, input.length()-1-idx)<number) {
					System.out.println("Number Overflow, bigger than max value of Long...");
					return;
				}
				
				// test if the number is bigger than max value of long (difficult)
				if(sign<0 && Long.MAX_VALUE-figure * Math.pow(10, input.length()-1-idx)<number+1) {
					System.out.println("Number Overflow, smaller than min value of Long...");
					return;
				}
				
				number += figure * Math.pow(10, input.length()-1-idx);
			} catch (NumberFormatException ex) {
				// if contains invalid characters
				System.out.println("Input contains invalid characters...");
				return;
			}
		}
		number *= sign;
		System.out.println(number);
	}
	
	public static void main(String[] args) {
		while(true) {
			System.out.print("Input a integer: ");
			Scanner scanner = new Scanner(System.in);
			String input = scanner.nextLine();
			transfer(input);
		}
	}
}
