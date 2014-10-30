package wenyu.learning.Maths;

public class LongestIncreaseSubsequence {

	public static int lisCount_solution1(int[] seq) {
		/**
		 *  When a number in the input sequence is scanned, it is compared with the greatest number in the
		 *	auxiliary array. If the newly scanned number is greater, it is inserted into the array directly. Otherwise, it
		 *	replaces the least number of those greater than it in the array in order to allow a wider range of values to
		 *	be added after it. In order to find the greatest number in the array as well as to find the first number
		 *	greater than a target value efficiently, the array keeps sorted.
		 */
		int len = seq.length;
		int[] lookup = new int[len];
		lookup[0] = seq[0];
		int longestLength = 1;
		for (int i = 1; i < len; ++i) {
			if (seq[i] > lookup[longestLength - 1]) {
				longestLength++;
				lookup[longestLength - 1] = seq[i];
			} else {
				int low = 0;
				int high = longestLength - 1;
				while (low != high) {
					int mid = (low + high) / 2;
					if (lookup[mid] < seq[i]) {
						low = mid + 1;
					} else {
						high = mid;
					}
				}
				lookup[low] = seq[i];
			}
		}
		return longestLength;
	}

	public static int lisCount_solution2(int[] array) {
		Integer[] lis = new Integer[array.length];
		for (int i = 0; i < array.length; i++) {
			lis[i] = 1;
			for (int j = 0; j < i; j++) {
				if (array[j] < array[i] && (lis[j] + 1 > lis[i]))
					lis[i] = lis[j] + 1;
			}
		}
		int max = 0;
		for (int k = 0; k < lis.length; k++) {
			if (lis[k] > max)
				max = lis[k];
		}
		return max;
	}

	public static void getLIS(int[] arr) {
		int[] aux = new int[arr.length];
		aux[0] = 1;
		for(int i=0; i<arr.length; i++) {
			int k = 0;
			for(int j=0; j<i; j++) {
				if(arr[j]<=arr[i] && k<aux[j]) {
					k = aux[j];
				}
			}
			aux[i] = k + 1;
		}

		int max = aux[0];
		int k = 0;
		for (int i = 0; i < aux.length; i++) {
			if (max < aux[i]) {
				max = aux[i];
				k = i;
			}
		}
		print(k, arr, aux);
	}

	public static void print(int k, int[] arr, int[] b) {
		for (int i = k - 1; i >= 0; i--) {
			if (b[k] == b[i] + 1 && arr[i] <= arr[k]) {
				print(i, arr, b);
				break;
			}
		}
		System.out.print(arr[k] + " ");
	}

	public static void main(String[] args) {
		int[] arr = new int[] { 1, 9, 3, 8, 11, 4, 5, 6, 4, 1, 9, 10, 1, 7 };
		int count = lisCount_solution2(arr);
		System.out.println("Longest Increase Subsequence's count is " + count);
		System.out.print("Longest Increase Subsequence is ");
		getLIS(arr);
	}

}
