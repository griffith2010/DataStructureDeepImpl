package wenyu.learning.Implementations;

class AddClass {
	public static int sum = 0;
	private static int step = 1;
	public AddClass(int n) {
		sum += step++;
		if(step > n) {
			return;
		}
		AddClass bb = new AddClass(n);
	}
}

public class PlusToN {

	public static void main(String[] args) {
		AddClass bb = new AddClass(10);
		System.out.println(AddClass.sum);
	}

}
