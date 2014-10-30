package wenyu.learning.Sort;

import java.util.Random;

/**
 * Best        Average      Worst     Worst (Space)
 * O(nlogn)    O(nlogn)     O(n^2)        O(n)
 */

public class QuickSorting extends ParentClassBenchMark {
	public static final int runtime = 1;
	private static int[] array;
	
	private int partition(int start, int end) {
		SortingUtils.swap(array, start, new Random().nextInt(end-start+1)+start);
		int i = start;
		int j = end;
		int pivotV = array[i];
		
		while(i<j) {
			while(i<j && array[j]>=pivotV) {j--;}
			if(i<j) {
				array[i++] = array[j];
			}
			while(i<j && array[i]<pivotV) {i++;}
			if(i<j) {
				array[j--] = array[i];
			}
		}
		array[i] = pivotV;
		return i;
	}
	
	private void quickSorting(int start, int end) {
		if(start<end) {
			int idx = partition(start, end);
			quickSorting(start, idx-1);
			quickSorting(idx+1, end);
		}
	}
	
	@Override
	public void doExecut() {
		if (array == null || array.length == 0) {
			return;
		}
		quickSorting(0, array.length-1);
	}

	
	public static void validateArray() {
		for(int i=1;i<array.length;i++) {
			if(array[i]<array[i-1]) {
				System.out.println("Sorting Failed...");
				return;
			}
		}
		System.out.println("Sorting Success...");
	}
	public static void main(String[] args) {
		array = SortingUtils.generateRandomArray();
		demoEntry(QuickSorting.class, runtime);
		SortingUtils.validateArray(array);
	}

}
