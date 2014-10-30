package wenyu.learning.Maths.PermutationAndCombination;

import java.util.ArrayList;
import java.util.Stack;

public class PermutationsOfNArrays {

	public static void permute(ArrayList<int[]> arrays) {
		Stack<Integer> permutation = new Stack<Integer>();
		permuteCore(arrays, permutation);
	}

	private static void permuteCore(ArrayList<int[]> arrays, Stack<Integer> permutation) {
		if (permutation.size() == arrays.size()) {
			System.out.println(permutation);
			return;
		}
		int[] array = arrays.get(permutation.size());
		for (int i = 0; i < array.length; ++i) {
			permutation.push(array[i]);
			permuteCore(arrays, permutation);
			permutation.pop();
		}
	}
	
	public static void main(String[] args) {
		int[] array1 = { 1, 2, 3 };
		int[] array2 = { 4, 5, 6 };
		int[] array3 = { 7, 8, 9 };
		
		ArrayList<int[]> arrayList = new ArrayList<int[]>();
		arrayList.add(array1);
		arrayList.add(array2);
		arrayList.add(array3);
		permute(arrayList);
	}

}
