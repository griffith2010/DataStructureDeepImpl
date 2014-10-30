package wenyu.learning.Maths;

import java.util.Random;

/*
 * Write a method to generate a random number between 1 
 * and 7, given a method that generates a random number 
 * between 1 and 5 (i.e., implement rand7() using rand5()).
 * 
 * Logic:
 * 	1. rand5()-1 ==> [0,1,2,3,4]
 *  2. 5*(rand5()-1) ==> [0,5,10,15,20]
 *  3. (rand5() - 1) ==> [0,1,2,3,4]
 *  4. 5 * (rand5() - 1) + (rand5() - 1) ==> [0 ~ 24]
 *  5. if(num<21) ==> [0 ~ 20] (count:21)
 *  
 *    A           B              num
 *	rand5()-1   rand5()-1      5 * A + B        num % 7 + 1
 *	---------   ---------      ---------        -----------
 *	0           0              0                1
 *	1           0              5                6
 *	2           0              10               4
 *	3           0              15               2
 *	4           0              20               7
 *	0           1              1                2
 *	1           1              6                7
 *	2           1              11               5
 *	3           1              16               3
 *	4           1              21               reject
 *	0           2              2                3
 *	1           2              7                1
 *	2           2              12               6
 *	3           2              17               4
 *	4           2              22               reject
 *	0           3              3                4
 *	1           3              8                2
 *	2           3              13               7
 *	3           3              18               5
 *	4           3              23               reject
 *	0           4              4                5
 *	1           4              9                3
 *	2           4              14               1
 *	3           4              19               6
 *	4           4              24               reject
 */
public class Rand7FromRand5 {

	private static int rand5() {
		return new Random().nextInt(5)+1;
	}
	
	public static int rand7() {
		while (true) {
			int num = 5 * (rand5() - 1) + (rand5() - 1);
			if (num < 21)
				return (num % 7 + 1);
		}
	}

	public static void main(String[] args) {
		while(true) {
			System.out.println(rand7());
		}
	}

}
