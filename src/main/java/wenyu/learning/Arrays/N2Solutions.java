package wenyu.learning.Arrays;

/*
 * An array contains n numbers ranging from 0 to n-2. 
 * There is exactly one number duplicated in the array. 
 * How do you find the duplicated number?
 */

public class N2Solutions {
	public static final int runtime = 1;
	public static int[] array;
	public static int arraySize;
	
	private void solution1() {
		int sum1 = (0+arraySize-2)*(arraySize-1)/2;
		int sum2 = 0;
		for(int i=0;i<arraySize;i++) {
			sum2+=array[i];
		}
		System.out.println("Duplicated number is " + (sum2-sum1));
	}
	
	private void solution2() {
		int[] tmp = new int[arraySize];
		for(int i=0;i<arraySize;i++) {
			tmp[i] = -1;
		}
		for(int i=0;i<arraySize;i++) {
			if(tmp[array[i]] != -1) {
				System.out.println("Duplicated number is " + array[i]);
			} else {
				tmp[array[i]] = array[i];
			}
		}
	}
	
	private void solution3() {
		int dupNum = array[arraySize-1];
		for(int i=0;i<arraySize-1;i++) {
			dupNum = dupNum^i^array[i];
		}
		System.out.println("Duplicated number is " + dupNum);
	}
	
//	@Override
//	public void doExecut() {
//		//solution1();
//		//solution2();
//		solution3();
//	}
//	
//	public static void main(String[] args) {
//		array = RandomArrayGen.getArray(RandomArrayGen.N2);
//		arraySize = array.length;
//		demoEntry(N2Solutions.class, runtime);
//	}

}
