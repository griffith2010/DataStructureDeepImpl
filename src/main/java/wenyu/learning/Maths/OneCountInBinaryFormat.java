package wenyu.learning.Maths;

import java.util.Random;

/*
 * Given an integer, find the 1 count of 
 * the binary represent of that integer
 * 
 * Solution1: O(n)
 * 	(this solution's disadvange is be careful about negative integer or using >>>
 *   >> may add 1 to the left side when right rotate)
 * 	1. find the last bit of binary represent 
 *     of that integer by N&1 
 *     (which means N and 000...0001, this will return only last digit) 
 *  2. right rotate the number which means remove the last bit
 *  
 * Solution2: (suit for the problems which is not allowed to change original number)
 * 	Almost the same as the logic of solution1,
 *  Just left rotate the flat(which is 000...001) 
 *  rather than right rotate the integer like the logic above 
 *  
 * Solution3:
 * 	using: mathematics
 * 
 * Solution4:
 * 	using: divide to get one count
 */

public class OneCountInBinaryFormat {
	public static void solution1(int n) {
		int count = 0;
		while(n != 0) {
			int result = n&1;
			if(result!=0)
				count++;
			n = n >>> 1;
		}
		System.out.println("Count: " + count);
	}

	public static void solution2(int n) {
		int count = 0;
		int flag = 1;
		while(flag != 0) {
			int result = n&flag;
			if(result!=0)
				count ++;
			flag = flag << 1;
		}
		System.out.println("Count: " + count);
	}
	
	public static void solution3(int n) {
		int count = 0;
		while (n != 0) {
			++ count;
			n = (n - 1) & n;
		}
		System.out.println("Count: " + count);
	}
	
	public static void solution4(int n) {
		int count=0;
		while(n > 0) {
			int digit = n%2;
			if(digit == 1) {
				count++;
			}
			n = n/2;
		}
		System.out.println("Count: " + count);
	}
	
	public static void main(String[] args) {
		int testCount = 5;
		while(testCount-- > 0) {
			Random random = new Random();
			int number = random.nextInt(1000);
			solution1(number);
			solution2(number);
			solution3(number);
			solution4(number);
			System.out.println("===================================");
		}
	}
}
