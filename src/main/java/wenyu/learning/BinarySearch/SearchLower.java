package wenyu.learning.BinarySearch;

import java.util.Random;

import wenyu.learning.Arrays.ArrayUtils;

public class SearchLower extends ParentClassBenchMark {
	public static final int runtime = 1;
	private static final int ArrayLen = 6;
	private static final int MaxItem = 100;

	public void SEQSmallerThanSearch(int[] array, int k) {
		if (array == null || array.length == 0) {
			return;
		}

		for (int i = array.length - 1; i >= 0; i--) {
			if (array[i] < k) {
				System.out.println("Result is " + array[i]);
				return;
			}
		}
		System.out.println("No Result");
	}
	
	public void BSSmallerThanSearch_solution1(int[] array, int key) {
		if(key<array[0]) {
			System.out.println("No Result");
			return;
		}
		if(key>array[array.length-1]) {
			System.out.println("Result is " + array[array.length-1]);
			return;
		}
		
		int start = 0;
		int end = array.length-1;
		int mid;
		
		while(start<end-1) {
			mid = start + (end-start)/2;
			if(array[mid] < key) {
				start = mid;
			} else if(array[mid] >= key) {
				end = mid-1;
			}
		}
		
		if(array[end] >= key) {
			System.out.println("Result is " + array[start]);
		} else {
			System.out.println("Result is " + array[end]);
		}
	}
	
	public static void main(String[] args) throws Exception {
		int count = 10;
		while(count-- > 0) {
			int[] array = ArrayUtils.generateSortedIntegerArray(ArrayLen, MaxItem);
			ArrayUtils.printArray(array);
			int k = new Random().nextInt(MaxItem - 1);
			System.out.println("k=" + k);
	
	//		demoEntry(new SearchLower(), runtime, SearchLower.class.getMethod("SEQSmallerThanSearch", int[].class, int.class), array, k);
	//		demoEntry(new SearchLower(), runtime, SearchLower.class.getMethod("BSSmallerThanSearch_solution1", int[].class, int.class), array, k);
			
			new SearchLower().SEQSmallerThanSearch(array, k);
			new SearchLower().BSSmallerThanSearch_solution1(array, k);
			System.out.println();
		}
	}
}
