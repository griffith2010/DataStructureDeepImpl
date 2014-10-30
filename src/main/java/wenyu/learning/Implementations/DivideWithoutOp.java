package wenyu.learning.Implementations;

public class DivideWithoutOp {

	public static int divide(int num1, int num2) {
		if (num2 == 0)
			throw new ArithmeticException("num2 is zero.");
		boolean minus = false;
		if ((num1 < 0 && num2 > 0) || (num1 > 0 && num2 < 0))
			minus = true;
		if (num1 < 0)
			num1 = AddWithoutOp.add(~num1, 1);
		if (num2 < 0)
			num2 = AddWithoutOp.add(~num2, 1);
		int result = 0;
		for (int i = 0; i < 32; i = AddWithoutOp.add(i, 1)) {
			result = result << 1;
			if ((num1 >> (31 - i)) >= num2) {
				num1 = SubtractionWithoutOp.subtract(num1, num2 << (31 - i));
				result = AddWithoutOp.add(result, 1);
			}
		}
		if (minus)
			result = AddWithoutOp.add(~result, 1);
		return result;
	}

	public static void main(String[] args) {
		int result = divide(20,10);
		System.out.println(result);
	}

}
