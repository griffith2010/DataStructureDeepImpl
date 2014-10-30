package wenyu.learning.Maths.Prime;

import java.util.Random;

/*
 * Print all pairs(sets) of prime numbers (p,q) 
 * such that p*q <= n, where n is given number
 * 
 * Logic:
 * 	1. first find all the primes within n/2;
 *  2. find pairs
 */
public class PairPrimeMultipyLessThanN {

	public static void printPairs(int n) {
		long[] primes = GetPrimesProblems.getPrimeSmallerThanK(n/2, false);
		
		for(int i=0;i<primes.length;i++) {
			for(int j=i+1;j<primes.length;j++) {
				if(primes[i]*primes[j]<n) {
					System.out.print("("+primes[i]+"*"+primes[j]+"),");
				}
			}
		}
	}
	
	public static void main(String[] args) {
		int testCount = 1;
		Random random = new Random();
		while(testCount-- > 0) {
			int num = random.nextInt(9999);
			printPairs(num);
			System.out.println();
		}
	}

}
