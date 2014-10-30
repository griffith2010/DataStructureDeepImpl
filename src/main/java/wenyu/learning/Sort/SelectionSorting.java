package wenyu.learning.Sort;


/**
 * Best   Average    Worst   Worst (Space)
 * O(n)    O(n^2)   O(n^2)      O(1)
 */

public class SelectionSorting extends ParentClassBenchMark {
	public static final int runtime = 1;
	private static int[] array;
	
	private void selectionSorting() {
		if (array == null || array.length == 0) {
			return;
		}
		
		for(int i=0;i<array.length;i++) {
			int min = array[i];
			int minIdx = i;
			for(int j=i+1;j<array.length;j++) {
				if(array[j]<=min) {
					min = array[j];
					minIdx = j;
				}
			}
			SortingUtils.swap(array, i, minIdx);
		}
	}
	
	@Override
	public void doExecut() {
		selectionSorting();
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
		demoEntry(SelectionSorting.class, runtime);
		SortingUtils.validateArray(array);
	}

}
