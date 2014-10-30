package wenyu.learning.Maths;

/*
 * Generate the Fibonacci sequence (P81)
 * Find fibonacci number of given position
 * Logic:
 * 	Solution1: (O(2^n))
 * 		using recursion to calculate F(n)=F(n-1)+F(n-2)
 *  Solution2: O(n)
 *  	using iteration (non-recursion) 
 *  Solution3: O(logn)
 *  	using matrix (Math way...) 
 *  	find it from the coding interview (P81)
 */
public class Fibonacci {

	public static long fibonacci_solution1(int n) {
		if (n <= 0)
			return 0;
		if (n == 1)
			return 1;
		return fibonacci_solution1(n - 1) + fibonacci_solution1(n - 2);
	}

	public static long fibonacci_solution2(int n) {
		int result[] = new int[] { 0, 1 };
		long fibNMinusOne = 1;
		long fibNMinusTwo = 0;
		long fibN = 0;
		int i;
		if (n < 2)
			return result[n];
		for (i = 2; i <= n; ++i) {
			fibN = fibNMinusOne + fibNMinusTwo;
			fibNMinusTwo = fibNMinusOne;
			fibNMinusOne = fibN;
		}
		return fibN;
	}

	private static class Matrix2By2 {
		long m_00;
		long m_01;
		long m_10;
		long m_11;

		public Matrix2By2() {
		}

		public Matrix2By2(int a, int b, int c, int d) {
			m_00 = a;
			m_01 = b;
			m_10 = c;
			m_11 = d;
		}
	};

	private static Matrix2By2 MatrixMultiply(Matrix2By2 matrix1,
			Matrix2By2 matrix2) {
		Matrix2By2 result = new Matrix2By2();
		result.m_00 = matrix1.m_00 * matrix2.m_00 + matrix1.m_01 * matrix2.m_10;
		result.m_01 = matrix1.m_00 * matrix2.m_01 + matrix1.m_01 * matrix2.m_11;
		result.m_10 = matrix1.m_10 * matrix2.m_00 + matrix1.m_11 * matrix2.m_10;
		result.m_11 = matrix1.m_10 * matrix2.m_01 + matrix1.m_11 * matrix2.m_11;
		return result;
	}

	private static Matrix2By2 MatrixPower(int n) {
		Matrix2By2 result = new Matrix2By2();
		Matrix2By2 unit = new Matrix2By2(1, 1, 1, 0);
		assert (n > 0);
		if (n == 1) {
			result = unit;
		} else if (n % 2 == 0) {
			result = MatrixPower(n / 2);
			result = MatrixMultiply(result, result);
		} else if (n % 2 == 1) {
			result = MatrixPower((n - 1) / 2);
			result = MatrixMultiply(result, result);
			result = MatrixMultiply(result, unit);
		}
		return result;
	}

	public static long fibonacci_solution3(int n) {
		Matrix2By2 PowerNMinus2;
		int[] result = new int[] { 0, 1 };
		if (n < 2)
			return result[n];
		PowerNMinus2 = MatrixPower(n - 1);
		return PowerNMinus2.m_00;
	}

	public static void main(String[] args) {
		System.out.println(fibonacci_solution1(20));
		System.out.println(fibonacci_solution2(20));
		System.out.println(fibonacci_solution3(20));
	}

}
