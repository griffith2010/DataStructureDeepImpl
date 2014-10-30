package wenyu.learning.Sort;

import java.util.Arrays;

/**
 * Best        Average        Worst     Worst (Space)
 * O(nlogn)    O(nlogn)      O(nlogn)       O(n)
 */

public class MergeSorting extends ParentClassBenchMark {
	public static final int runtime = 1;
	private static int[] array;
	
	private void mergeArray(int start, int mid, int end) {
		if(mid<start || end<mid+1) {
			return;
		}
		int[] tmp = new int[end-start+1];
		int tmpIdx=0;
		int i = 0;
		int j = 0;
		
		while(i<mid-start+1&&j<end-mid) {
			if(array[start+i]<array[mid+1+j]) {
				tmp[tmpIdx++] = array[start+i];
				i++;
			} else {
				tmp[tmpIdx++] = array[mid+1+j];
				j++;
			}
		}
		while(i<mid-start+1) {
			tmp[tmpIdx++] = array[start+i++];
		}
		while(j<end-mid) {
			tmp[tmpIdx++] = array[mid+1+j++];
		}
		
		for(tmpIdx=0;tmpIdx<end-start+1;tmpIdx++) {
			array[start+tmpIdx] = tmp[tmpIdx];
		}
	}
	
	// Entry
	private void mergeSorting(int start, int end) {
		if(start<end) {
			int mid = (start+end)/2;
			mergeSorting(start, mid);
			mergeSorting(mid+1, end);
			mergeArray(start,mid,end);
		}
	}
	
	private void mergeSortingWithoutRecursion() {
		for(int i=1;i<array.length;i=i*2) {
			for(int j=0;j<array.length-i;j+=2*i) {
				int start = j;
				int mid = j+i-1;
				int end = ((j+2*i-1)>=array.length)?array.length-1:(j+2*i-1);
				
				mergeArray(start,mid,end);
			}
		}
	}
	
	@Override
	public void doExecut() {
		mergeSorting(0, array.length-1);
		
		mergeSortingWithoutRecursion();
	}
	
	public static void main(String[] args) {
		array = SortingUtils.generateRandomArray();
		demoEntry(MergeSorting.class, runtime);
		SortingUtils.validateArray(array);
	}

}
