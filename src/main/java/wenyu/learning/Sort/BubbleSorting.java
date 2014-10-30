package wenyu.learning.Sort;

/**
 * Best   Average    Worst   Worst (Space)
 * O(n)    O(n^2)   O(n^2)      O(1)
 */

public class BubbleSorting extends ParentClassBenchMark {
	public static final int runtime = 1;
	private static int[] array;
	
	private void bubbleSorting() {
		if (array == null || array.length == 0) {
			return;
		}
		
		for(int i=0;i<array.length;i++) {
			for(int j=1;j<array.length-i;j++) {
				if(array[j-1]>array[j]) {
					SortingUtils.swap(array, j-1, j);
				}
			}
		}
	}
	
	@Override
	public void doExecut() {
		bubbleSorting();
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
		demoEntry(BubbleSorting.class, runtime);
		SortingUtils.validateArray(array);
	}

}
