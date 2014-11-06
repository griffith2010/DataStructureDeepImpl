package wenyu.learning.Arrays;

import java.util.Arrays;
import java.util.Comparator;

/*
 * You are given pairs of numbers. In a pair the first number is smaller to the second number. 
 * Suppose you have two sets (a, b) and (c, d), 
 * the second set can follow the first set if b<c.
 * So you can form a long chain in the similar 
 * fashion. 
 * Find the longest chain which can be formed.
 * 
 * Logic:
 * 	1) Sort the pairs according to their second element --> O(nlogn)  
 *  2) Select the first pair from the sorted array and print it. --> O(1) 
 *  3) Do following for remaining pairs in the sorted array. --> O(n) 
 */
public class LongestPairedChain {

	public static class pair {
		public int x;
		public int y;
		public pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void find(pair[] pairs) {
		Comparator<pair> comparator = new Comparator<pair>() {

			public int compare(pair o1, pair o2) {
				if(o1.y<o2.y) {
					return -1;
				} else {
					return 1;
				}
			}
		};
		
		Arrays.sort(pairs, comparator);
		int longestCount = 0;
		pair currPair = pairs[0];
		for(int i=1;i<pairs.length;i++) {
			if(currPair.y < pairs[i].x) {
				System.out.print("("+currPair.x+","+currPair.y+"),");
				longestCount++;
				currPair = pairs[i];
			}
		}
		System.out.print("("+currPair.x+","+currPair.y+"),");
		System.out.println("\nTotal Length is " + ++longestCount);
	}
	
	public static void main(String[] args) {
		//pair[] pairs = {new pair(1,2), new pair(1,5), new pair(3,4), new pair(7,8), new pair(9,10)};
		pair[] pairs = {new pair(10,11), new pair(1,12), new pair(13,14), new pair(15,16)};
		find(pairs);
	}

}
