package wenyu.learning.Arrays;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;

class Interval {
	int start;
	int end;
	public Interval(int start, int end) {
		if(end>=start) {
			this.start = start;
			this.end = end;
		} else {
			this.start = end;
			this.end = start;
		}
	}
}

public class IntervalProblems {
	public static boolean ifHasOverlap(Interval[] intervals) {
		/*
		 * You have N pairs of intervals, say integers. To find if has overlapping
		 * Logic: O(nlogn)
		 * 	1. sort interval array according to the start value. O(nlogn)
		 *  2. scan array to see if its start smaller than maxEnd. O(n)
		 */
		Interval[] tmpIntervals = intervals.clone();
		Arrays.sort(tmpIntervals, new Comparator<Interval>() {
			public int compare(Interval o1, Interval o2) {
				if(o1.start>o2.start) {
					return 1;
				} else if(o1.start < o2.start) {
					return -1;
				} else {
					return 0;
				}
			}
		});
		
		int maxEnd = tmpIntervals[0].end;
		for(int i=1;i<tmpIntervals.length;i++) {
			if(tmpIntervals[i].start<maxEnd) {
				return true; // find overlapping
			} 
			maxEnd = tmpIntervals[i].end;
		}
		return false;
	}
	
	public static Interval[] mergeInterval(Interval[] intervals) {
		/*
		 * Given a set of time intervals in any order, merge all overlapping 
		 * intervals into one and output the result which should have only 
		 * mutually exclusive intervals. Let the intervals be represented as 
		 * pairs of integers for simplicity. 
		 * 
		 * Logic:
		 * 	1. Sort the intervals based on increasing order of starting time.
		 *  2. Push the first interval on to a stack.
		 *  3. For each interval do the following
		 *  	a. If the current interval does not overlap with the stack top, push it.
		 *  	b. If the current interval overlaps with stack top and ending time of current interval is 
		 *  	   more than that of stack top, update stack top with the ending time of current interval.
		 *  4. At the end stack contains the merged intervals.
		 */
		
		Interval[] tmpIntervals = intervals.clone();
		Arrays.sort(tmpIntervals, new Comparator<Interval>() {
			public int compare(Interval o1, Interval o2) {
				if(o1.start>o2.start) {
					return 1;
				} else if(o1.start < o2.start) {
					return -1;
				} else {
					return 0;
				}
			}
		});
		
		Interval[] result = new Interval[intervals.length];
		int idx = 0;
		result[idx] = tmpIntervals[0];
		
		for(int i=0;i<tmpIntervals.length;i++) {
			if(tmpIntervals[i].start<=result[idx].end) {
				result[idx].end = (tmpIntervals[i].end>result[idx].end)?tmpIntervals[i].end:result[idx].end;
			} else {
				result[++idx] = tmpIntervals[i];
			}
		}
		
		result = Arrays.copyOf(result, idx+1);
		for(int i=0;i<result.length;i++) {
			System.out.print("["+result[i].start+","+result[i].end+"] ");
		}
		return result;
	}
	
	public static void main(String[] args) {
		Interval[] intervals = {new Interval(1,2),new Interval(3,5),new Interval(6,10),new Interval(10,12),new Interval(8,9)};
		if(ifHasOverlap(intervals)) {
			System.out.println("Find overlapping...");
		} else {
			System.out.println("No overlapping...");
		}
		
		mergeInterval(intervals);
	}

}
