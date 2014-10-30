package wenyu.learning.Arrays;

public class MergeSortedArray {
	@SuppressWarnings("unchecked")
	public <T extends Comparable<T>> T[] merge(T[] array1, T[] array2) {
		T[] result = (T[]) new Comparable[array1.length+array2.length];
		int resultIdx = 0;
		int i = 0;
		int j = 0;
		for(;i<array1.length&&j<array2.length;) {
			if(array1[i].compareTo(array2[j])<=0) {
				result[resultIdx++] = array1[i++];
			} else {
				result[resultIdx++] = array2[j++];
			}
		}
		
		for(;i<array1.length;) {
			result[resultIdx++] = array1[i++];
		}
		for(;j<array2.length;) {
			result[resultIdx++] = array2[j++];
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		Integer[] numbers1 = {1,2,3,5,7,9,10};
		Integer[] numbers2 = {2,4,6,8,10,11};
		
		MergeSortedArray ins = new MergeSortedArray();
		Comparable<Integer>[] merged = ins.merge(numbers1, numbers2);
		
		for(int i=0;i<merged.length;i++) {
			System.out.print(merged[i] + " ");
		}
	}
}
