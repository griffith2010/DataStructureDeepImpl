package wenyu.learning.Sort;

/**
 * Best   Average    Worst   Worst (Space)
 * O(n)    O(n^2)   O(n^2)      O(1)
 */

public class InsertionSorting extends ParentClassBenchMark {
	public static final int runtime = 1;
	private static int[] array;
	
	private void insertionSorting() {
		if (array == null || array.length == 0) {
			return;
		}
		
		for(int i=1;i<array.length;i++) {
			int current = array[i];
			for(int j=i-1;j>=0&&array[j]>current;j--) {
				SortingUtils.swap(array, j, j+1);
			}
		}
	}
	
	@Override
	public void doExecut() {
		insertionSorting();
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
		demoEntry(InsertionSorting.class, runtime);
		SortingUtils.validateArray(array);
	}

}
