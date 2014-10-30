package wenyu.learning.Maths;

/*
 * Eight Queen problem
 * Logic:
 * 	1. Permutation from an array which has eight integer (0-7)
 * 	2. check if there are two queens on the same diagonal, 
 *     it returns 0, otherwise it returns 1.
 */

public class EightQueenProblems {

	public static void calEightQueen() {
		int[] columnIndex = { 0, 1, 2, 3, 4, 5, 6, 7 };
		Permutation(columnIndex, 8, 0);
	}

	private static void Permutation(int columnIndex[], int length, int index) {
		int i, temp;
		if (index == length) {
			if (Check(columnIndex, length) != 0) {
				for (int j = 0; j < columnIndex.length; j++) {
					System.out.print(columnIndex[j] + " ");
				}
				System.out.println();
			}
		} else {
			for (i=index;i<length;++i) {
				temp = columnIndex[i];
				columnIndex[i] = columnIndex[index];
				columnIndex[index] = temp;
				Permutation(columnIndex, length, index + 1);
				temp = columnIndex[index];
				columnIndex[index] = columnIndex[i];
				columnIndex[i] = temp;
			}
		}
	}

	/*
	 * If there are two queens on the same diagonal, it returns 0, otherwise it
	 * returns 1.
	 */
	private static int Check(int columnIndex[], int length) {
		int i, j;
		for (i = 0; i < length; ++i) {
			for (j = i + 1; j < length; ++j) {
				if ((i - j == columnIndex[i] - columnIndex[j])
						|| (j - i == columnIndex[i] - columnIndex[j]))
					return 0;
			}
		}
		return 1;
	}

	public static void main(String[] args) {
		calEightQueen();
	}

}
