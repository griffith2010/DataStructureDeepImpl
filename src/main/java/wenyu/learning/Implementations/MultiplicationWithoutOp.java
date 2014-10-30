package wenyu.learning.Implementations;

public class MultiplicationWithoutOp {
	public static int multiply(int num1, int num2) {
		boolean minus = false;
		if ((num1 < 0 && num2 > 0) || (num1 > 0 && num2 < 0))
			minus = true;
		if (num1 < 0)
			num1 = AddWithoutOp.add(~num1, 1);
		if (num2 < 0)
			num2 = AddWithoutOp.add(~num2, 1);
		int result = 0;
		while (num1 > 0) {
			if ((num1 & 0x1) != 0) {
				result = AddWithoutOp.add(result, num2);
			}
			num2 = num2 << 1;
			num1 = num1 >> 1;
		}
		if (minus)
			result = AddWithoutOp.add(~result, 1);
		return result;
	}

	public static void main(String[] args) {
		int result = multiply(10,20);
		System.out.println(result);
	}

}
