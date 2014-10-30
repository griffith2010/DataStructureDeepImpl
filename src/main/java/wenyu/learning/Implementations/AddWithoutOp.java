package wenyu.learning.Implementations;

public class AddWithoutOp {

	public static int add(int num1, int num2) {
		int sum, carry;
		do {
			sum = num1 ^ num2;
			carry = (num1 & num2) << 1;
			num1 = sum;
			num2 = carry;
		} while (num2 != 0);
		return num1;
	}

	public static void main(String[] args) {
		int result = add(10,20);
		System.out.println(result);
	}

}
