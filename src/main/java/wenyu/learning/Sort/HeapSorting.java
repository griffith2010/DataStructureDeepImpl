package wenyu.learning.Sort;

/**
 * Best        Average        Worst     Worst (Space)
 * O(nlogn)    O(nlogn)      O(nlogn)       O(1)
 */

public class HeapSorting extends ParentClassBenchMark {
	public static final int runtime = 1;
	private static int[] array;

	private void buildHeap(int[] array) {
		for(int i=1;i<array.length;i++) {
			int hole = i+1;
			int insert = array[i];
			for(;hole>1&&insert>array[hole/2-1];hole=hole/2) {
				array[hole-1] = array[hole/2-1];
			}
			array[hole-1] = insert;
		}
	}
	
	private void sortHeap(int[] array) {
		if(array==null || array.length==0) {
			return;
		}

		for(int i=array.length-1;i>0;i--) {
			int biggest = array[0];
			array[0] = array[i];
			array[i] = biggest;
			
			// percolateDown
			int hole = 0+1;
			int child = 0;
			int current = array[hole-1];
			for(;hole*2<=i;hole=child) {
				child = hole*2;
				if(child!=i&&array[child]>array[child-1]) {
					child++;
				}
				if(current<array[child-1]) {
					array[hole-1] = array[child-1];
				} else {
					break;
				}
			}
			array[hole-1] = current;
		}
	}
	
	@Override
	public void doExecut() {
		buildHeap(array);
		
		sortHeap(array);
	}

	public static void main(String[] args) {
		array = SortingUtils.generateRandomArray();
		demoEntry(HeapSorting.class, runtime);
		SortingUtils.validateArray(array);
	}
}
