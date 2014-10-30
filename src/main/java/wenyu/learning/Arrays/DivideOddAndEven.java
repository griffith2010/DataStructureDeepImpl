package wenyu.learning.Arrays;

public class DivideOddAndEven {

	private static void print(int[] numbers) {
		for(int i=0;i<numbers.length;i++) {
			System.out.print(numbers[i] + " ");
		}
		System.out.println();
	}
	
	
	
	public static void divideOddAndEven(int[] arr) {
		if(arr==null || arr.length<=1) {
			return;
		}
		
		int start = 0;
		int end = arr.length-1;
		
		while(start<end) {
			while(start<end && arr[start]%2==0) {start++;}
			while(start<end && arr[end]%2==1) {end--;}
			
			if(start<end) {
				int tmp = arr[start];
				arr[start] = arr[end];
				arr[end] = tmp;
				start++;
				end--;
			}
		}
	}
	
	public static void main(String[] args) {
		int[] numbers = new int[] {1,2,3,4,5};
		int[] numbers1 = numbers.clone();
		divideOddAndEven(numbers);
		print(numbers);
	}

}
