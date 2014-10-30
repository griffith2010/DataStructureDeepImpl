package wenyu.learning.Arrays;

import java.util.Random;

/**
 * Given a positive value s, print all sequences with continuous numbers (with
 * two numbers at least) whose sum is s.
 * 
 * Take the input s=15 as an example. Because 1+2+3+4+5=4+5+6=7+8=15, three
 * continuous sequences should be printed: 1~5, 4~6, and 7~8.
 */
public class ContinuousSequenceWithSum {

	private static void printSeq(int small, int big) {
		StringBuilder str = new StringBuilder("Find: [");
		for (int i = small; i <= big; ++i) {
			str.append(String.valueOf(i) + " ");
		}
		System.out.println(str.substring(0, str.length()-1) + "]");
	}

	public static void findContinuousSequence(int sum) {
		if (sum <= 0) {
			System.out.println("No such sequence with sum is " + sum);
			return;
		}
		boolean ifHasSeq = false;
		
		int small = 0;
		int big = 1;
		int tmpSum = small + big;

		while (small < big && small <= sum / 2) {
			if (tmpSum == sum) {
				ifHasSeq = true;
				printSeq(small, big);
				tmpSum -= small;
				small++;
			}

			if (tmpSum < sum) {
				big++;
				tmpSum += big;
			} else if (tmpSum > sum) {
				tmpSum -= small;
				small++;
			}
		}
		
		if(!ifHasSeq) {
			System.out.println("No such sequence with sum is " + sum);
			return;
		}
	}

	public static void findContinuousSequence_solution1(int sum) {
		if(sum<=0) {
			System.out.println("No such sequence with sum is " + sum);
			return;
		} else if(sum == 1) {
			System.out.println("Find: [1]");
			return;
		}
		
		int first = 1;
		int second = 2;
		
		while(first<second && first<=sum/2) {
			int currSum = (first+second)*(second-first+1)/2;
			if(currSum == sum) {
				printSeq(first, second);
				first++;
			} else if(currSum < sum) {
				second++;
			} else {
				first++;
			}
		}
	}
	
	public static void main(String[] args) {
		int retryCount = 1;
		while(retryCount-- > 0) {
			int sum = new Random().nextInt(1000);
			findContinuousSequence(sum);
			System.out.println("##############################################");
			findContinuousSequence_solution1(sum);
		}
	}

}
