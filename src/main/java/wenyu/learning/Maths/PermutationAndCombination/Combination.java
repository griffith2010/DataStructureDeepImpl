package wenyu.learning.Maths.PermutationAndCombination;

import java.util.ArrayList;
import java.util.Stack;

import wenyu.learning.Maths.RearrangeArrayFindNextBig;

/*
 * Combination:
 * 1. selectRecursion
 * 2. selectNoRecursion
 * 3. selectBasedOnBitwise (no recursion)
 * 5. getAllCombinationsBasedOnBitwise
 */
public class Combination {
	public final int count = 10;
	private static int resultCount=0;

	public static ArrayList<String> selectRecursion(char[] arr, int start, int num, Stack<Character> stack, ArrayList<String> arrayList, boolean print) {
		if(start == arr.length) {
			return arrayList;
		}
		
		if(arrayList == null) {
			arrayList = new ArrayList<String>();
		}
		for(int i=start;i<arr.length;i++) {
			stack.add(arr[i]);
			if(stack.size() == num) {
				resultCount++;
				Object[] list = stack.toArray();
				if(print)
					System.out.print(resultCount + ": ");
				StringBuilder builder = new StringBuilder();
				for(Object ch : list) {
					if(print)
						System.out.print(ch.toString());
					builder.append(ch.toString());
				}
				arrayList.add(builder.toString());
				if(print)
					System.out.println();
			} else {
				selectRecursion(arr, i+1, num, stack, arrayList, print);
			}
			stack.pop();
		}
		return arrayList;
	}
	
	public static ArrayList<String> selectBasedOnBitwise(char[] list, int k, boolean print) {
		ArrayList<String> arrayList = new ArrayList<String>();
		
		// Step 1: form a bit array which contains k's 1. And this bit array is the smallest array with k's 1
		// 		   For example: listCount=5, k=3 ==> [0, 0, 1, 1, 1]
		Character[] bitArr = new Character[list.length];
		for(int i=0; i<bitArr.length; i++) {
			if(i<list.length-k) {
				bitArr[i] = '0';
			} else {
				bitArr[i] = '1';
			}
		}
		
		// Step 2: Using find next function to find next bigger big array
		int result = 0;
		while(result >= 0) {
			StringBuilder builder = new StringBuilder();
			for (int j=0; j<bitArr.length; j++) {
				if (bitArr[j] == '1') {
					int idx = list.length - bitArr.length + j;
					builder.append(list[idx]);
					if(print)
						System.out.print(list[idx]);
				}
			}
			arrayList.add(builder.toString());
			System.out.println();
			result = RearrangeArrayFindNextBig.find(bitArr);
		}
		
		return arrayList;
	}
	
	public static ArrayList<String> selectNoRecursion(char arr[], int num, boolean print) {
		ArrayList<String> arrayList = new ArrayList<String>();
		int resultCount=0;
		int index = 0;
		int arrLen = arr.length;
		if (arrLen < num) {
			return arrayList;
		}
		int[] tmp = new int[num];
		tmp[index] = 0;
		while (true) {
			if (tmp[index] >= arrLen) {
				if (index == 0) {
					break;
				}
				index--;
				tmp[index]++;
			} else if (index == num - 1) {
				String tmpStr = "";
				for (int i = 0; i < num; i++) {
					tmpStr += arr[tmp[i]];
				}
				resultCount++;
				if(print) {
					System.out.println(resultCount+": "+tmpStr);
				}
				arrayList.add(tmpStr);
				tmp[index]++;
			} else {
				index++;
				tmp[index] = tmp[index - 1] + 1;
			}
		}
		return arrayList;
	}

	public static void getAllCombinationsBasedOnBitwise(char[] list) {
		if (list.length > 32) {
			return;
		}
		double max = Math.pow(2, list.length);
		for (int i = 0; i < max; i++) {
			String binary = Integer.toBinaryString(i);
			for (int j = 0; j < binary.length(); j++) {
				if (binary.charAt(j) == '1') {
					int idx = list.length - binary.length() + j;
					System.out.print(list[idx]);
				}
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		char[] list = new char[] { 'a', 'b', 'c', 'd' };
		
		System.out.println("Non-recursion:");
		//Select without recursion
		selectNoRecursion(list, 3, true);
		
		System.out.println("Recursion:");
		//select using recursion
		selectRecursion(list, 0, 3, new Stack<Character>(), null, true);

		// Get all possible combination
		selectBasedOnBitwise(list, 2, true);
	}
}
