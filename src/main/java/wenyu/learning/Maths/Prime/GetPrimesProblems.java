package wenyu.learning.Maths.Prime;

import java.util.Arrays;

/*
 * Problems
 * 		1. get K primes
 * 		2. get primes smaller then K
 * 		3. get Kth prime
 * 		4. get Kth prime from N
 */

public class GetPrimesProblems {
	public static long[] getKPrimes(int k, boolean print) {
		long[] primes = new long[k];
		primes[0] = 2;
		int currPrimeCount = 1;
		for(int i=3;currPrimeCount<k;i+=2) { 							// even number cannot be prime
			boolean isPrime = true;
			long sqrt = (long) Math.sqrt(i); 
			for(int j=0; j<currPrimeCount && primes[j]<=sqrt; j++) {
				if (i % primes[j] == 0) {								// test the prime, to see if the prime is the factor of i
					isPrime = false;
					break;
				}
			}
			if (isPrime) {
				primes[currPrimeCount++] = i;
			}
		}

		if(print) {
			System.out.print("First " + k + "th primes are: ");
			System.out.print(Arrays.toString(primes));
			System.out.println();
		}
		
		return primes;
	}

	public static long[] getPrimeSmallerThanK(int k, boolean print) {
		long[] primes = new long[k];
		primes[0] = 2;
		int currPrimeCount = 1;
		for(int i=3;i<=k;i+=2) { 										// even number cannot be prime
			boolean isPrime = true;
			long sqrt = (long) Math.sqrt(i); 
			for(int j=0; j<currPrimeCount && primes[j]<=sqrt; j++) {
				if (i % primes[j] == 0) {								// test the prime, to see if the prime is the factor of i
					isPrime = false;
					break;
				}
			}
			if (isPrime) {
				primes[currPrimeCount++] = i;
			}
		}

		primes = Arrays.copyOf(primes, currPrimeCount);
		if(print) {
			System.out.print("First primes which are smaller than " + k + " are: ");
			System.out.print(Arrays.toString(primes));
			System.out.println();
		}
		return primes;
	}

	public static long getKthPrime(int k, boolean print) {
		long[] primes = getKPrimes(k, false);
		if(print) {
			System.out.println("The " + k + "th prime is: " + primes[k-1]);
		}
		
		return primes[k-1];
	}
	
	public static long[] getKPrimesFromN(int n, int k, boolean print) {
		long[] primes = new long[k+n];
		primes[0] = 2;
		int currPrimeCount = 1;
		int fromNIndex = k;
		int indexStartFrom = -1;
		for(int i=3;fromNIndex>0;i+=2) { // even number cannot be prime
			boolean isPrime = true;
			long sqrt = (long) Math.sqrt(i); 
			for(int j=0; j<currPrimeCount && primes[j]<=sqrt; j++) {
				// test the prime, to see if the prime is the factor of i
				if (i % primes[j] == 0) {
					isPrime = false;
					break;
				}
			}
			if (isPrime) {
				if(i>=n) {
					fromNIndex--;
					indexStartFrom = (indexStartFrom==-1) ? currPrimeCount : indexStartFrom;
				}
				primes[currPrimeCount++] = i;
			}
		}

		primes = Arrays.copyOfRange(primes, indexStartFrom, currPrimeCount);
		if(print) {
			System.out.print(k + "th primes from " + n + " are: ");
			System.out.print(Arrays.toString(primes));
			System.out.println();
		}
		
		return primes;
	}
	
	public static long[] getKPrimesFromNth(int n, int k, boolean print) {
		long[] primes = getKPrimes(k+n, false);

		primes = Arrays.copyOfRange(primes, n, k+n);
		if(print) {
			System.out.print(k + " primes from " + n + "th are: ");
			System.out.print(Arrays.toString(primes));
			System.out.println();
		}
		
		return primes;
	}

	public static void main(String[] args) {
		int k = 10;
		// getKPrimes(20, true);
		// getPrimeSmallerThanK(k, true);
		// getKthPrime(k);
		// getKPrimesFromN(10, k, true);
		getKPrimesFromNth(10, k, true);
	}
}
