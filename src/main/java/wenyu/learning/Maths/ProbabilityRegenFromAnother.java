package wenyu.learning.Maths;

import java.util.Random;

/*
 * 
 * Given a function func1 in which 0 occurs with probability 
 * 0.4 and 1 occurs with probability 0.6. Using function func1 
 * deduce a new function func2 such that both 0 and 1 occurs 
 * with probability 0.5
 * 
 * Logic:
 * 	Run the function f() two times. The possible outcomes are
 * 		00 with probability 0.4*0.4
 * 		11 with probability 0.6*0.6
 * 		01 with probability 0.4*0.6
 * 		10 with probability 0.6*0.4
 * 	Notice that the probabilities for 01 and 10 are the same. 
 *  So create a new function f1() such that
 */

public class ProbabilityRegenFromAnother {

	private static int func1() {
		return (new Random().nextInt(10)<4)?0:1;
	}
	
	public static int func2() {
		int first = func1();
		int second = func1();
		
		while(true) {
			if(first==0 && second==1) {
				return 1;
			} else if(first==1 && second==0) {
				return 0;
			}
		}
	}
}
