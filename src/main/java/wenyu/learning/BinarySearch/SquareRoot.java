package wenyu.learning.BinarySearch;

import java.util.Random;

/*
 * Find the square root of some number
 * Note: Special case is if the number is huge, normal type like int or long can not handle it when run num*num
 * Suggestion: Always use the BSSquareRootSearchLargeNum.
 */
public class SquareRoot extends ParentClassBenchMark {
	public void SEQSquareRootSearch(long k) {
		for(long i=1;i<=k;i++) {
			long square = i*i;
			if(square == k) {
				System.out.println("Result is " + i);
				return;
			} else if(square > k) {
				System.out.println("Result is " + (i-1));
				return;
			}
		}
	}
	
	public void BSSquareRootSearch(long k) {
		/*
		 * Attention: If key is not very large
		 */
		
		if(k < 0) {
			System.out.println("No Result");
		}
		
		long start = 0;
		long end = k;
		long mid;
		
		while(start<end-1) {
			mid = start + (end-start)/2;
			long square = mid*mid;
			if(square == k) {
				System.out.println("Result is " + mid);
				return;
			} else if(square > k) {
				end = mid-1;
			} else {
				start = mid;
			}
		}
		
		if(end*end<=k) {
			System.out.println("Result is " + end);
		} else {
			System.out.println("Result is " + start);
		}
	}
	
	public static void BSSquareRootSearchLargeNum(long k) {
		if(k<0) {
			System.out.println("No Result");
		}
		
		long start = 0;
		long end = k;
		long mid;
		
		while(start<end-1) {
			mid = start + (end-start)/2;
			long sub = k/mid;
			if(sub == k) {
				System.out.println("Result is " + mid);
				return;
			} else if(sub < mid) {
				end = mid-1;
			} else {
				start = mid;
			}
		}
		
		if(k/end >= end) {
			System.out.println("Result is " + end);
		} else {
			System.out.println("Result is " + start);
		}
	}
	
	public static void main(String[] args) throws Exception {
		long k = new Random().nextLong();
		if(k<0) {
			k *= -1;
		}
		System.out.println("Trying to find " + k + "'s square root. Result is " + (long) Math.sqrt(k));
		demoEntry(new SquareRoot(), 1, SquareRoot.class.getMethod("SEQSquareRootSearch", long.class), k);
		demoEntry(new SquareRoot(), 1, SquareRoot.class.getMethod("BSSquareRootSearch", long.class), k);
		demoEntry(new SquareRoot(), 1, SquareRoot.class.getMethod("BSSquareRootSearchLargeNum", long.class), k);
	}

}
