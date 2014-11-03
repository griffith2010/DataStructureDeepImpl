package wenyu.learning.Arrays;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

/*
 * Given two arrays sort the first array in the order of numbers given in the second array. 
 * For the numbers that do not appear in the second array, sort according to face value. 
 */
public class SortByAnotherArr {

	public static <E extends Comparable<E>> void sort(E[] arr, E[] base) {
		final HashMap<E, Integer> map = new HashMap<E, Integer>();
		for(int i=0; i<base.length; i++) {
			if(!map.containsKey(base[i])) {
				map.put(base[i], i);
			}
		}
		
		Comparator<E> comp = new Comparator<E>() {
			public int compare(E o1, E o2) {
				if(map.containsKey(o1) && map.containsKey(o2)) {
					return map.get(o1) - map.get(o2);
				} else {
					return o1.compareTo(o2);
				}
			}
		};
		
		Arrays.sort(arr, comp);
	}
	
	public static void main(String[] args) {
		int[] arr = ArrayUtils.generateRandomIntegerArray(9, 10);
		Integer[] arrInteger = new Integer[arr.length];
		for(int i=0; i<arr.length; i++) {
			arrInteger[i] = arr[i];
		}
		Integer[] base = {2,0,6,8,3,9};
		sort(arrInteger, base);
		System.out.println(Arrays.toString(arrInteger));
	}

}
