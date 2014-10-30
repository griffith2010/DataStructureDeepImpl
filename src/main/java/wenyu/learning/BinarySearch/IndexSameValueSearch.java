package wenyu.learning.BinarySearch;

import java.util.Arrays;
import java.util.Random;

import wenyu.learning.Arrays.ArrayUtils;

/*
 * Array value from 0 to n;
 * find the first one whose value is not the same as its index
 */

public class IndexSameValueSearch extends ParentClassBenchMark {
	public static final int runtime = 1;
	private static final int ArrayLen = 10;
	private static final int MaxItem = 100;
	
	public static int[] generateSuchArray() {
		int[] arr = new int[ArrayLen];
		
		int randomPoint = new Random().nextInt(ArrayLen);
		int i=0;
		for(; i<=randomPoint; i++) {
			arr[i] = i;
		}
		
		int nextInt = new Random().nextInt(1)+randomPoint;
		for(; i<arr.length; i++) {
			arr[i] = nextInt++;
		}
		
		return arr;
	}
	
	public void SEQIndexEqualSearch(int[] array) {
		if(array == null || array.length == 0) {
			return;
		}
		
		for(int i=0;i<array.length;i++) {
			if(array[i] != i) {
				System.out.println("Result is " + i);
				return;
			}
		}
		System.out.println("No Result");
	}
	
	public void BSIndexEqualSearch(int[] array) {
		if(array == null || array.length == 0) {
			return;
		}
		
		int start = 0;
		int end = array.length-1;
		
		while(start < end) {
			int mid = start + (end-start)/2;
			
			if(array[mid] == mid) {
				start = mid+1;
			} else {
				end = mid;
			}
		}
		
		if(array[end] == end) {
			System.out.println("No Result");
		} else {
			System.out.println("Result is " + end);
		}
	}
	
	public static void main(String[] args) throws Exception {
		int count = 10;
		
		while(count-- > 0) {
			int[] array = generateSuchArray();
			System.out.println(Arrays.toString(array));
			
			demoEntry(new IndexSameValueSearch(), runtime, IndexSameValueSearch.class.getMethod("SEQIndexEqualSearch", int[].class), array);
			demoEntry(new IndexSameValueSearch(), runtime, IndexSameValueSearch.class.getMethod("BSIndexEqualSearch", int[].class), array);
			System.out.println();
		}
	}
}
