package wenyu.learning.Implementations;

public class SubtractionWithoutOp {
	
	public static int subtract(int num1, int num2) {
		num2 = AddWithoutOp.add(~num2, 1);
		return AddWithoutOp.add(num1, num2);
	}
	
	public static void main(String[] args) {
		int result = subtract(20,10);
		System.out.println(result);
	}

}
