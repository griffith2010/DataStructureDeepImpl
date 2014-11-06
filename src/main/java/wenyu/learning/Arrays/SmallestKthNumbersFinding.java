package wenyu.learning.Arrays;

/*
 * Please find the smallest k 
 * numbers (in value) out of n numbers.
 * 
 * !!! Using partition method
 */
public class SmallestKthNumbersFinding {

	private static int partition(int[] numbers, int start, int end) {
		int i = start;
		int j = end;
		int pivotV = numbers[i];
		
		while(i<j) {
			while(i<j && numbers[j]>=pivotV) {j--;}
			if(i<j) {
				numbers[i] = numbers[j];
				i++;
			}
			while(i<j && numbers[i]<=pivotV) {i++;}
			if(i<j) {
				numbers[j] = numbers[i];
				j--;
			}
		}
		
		numbers[i] = pivotV;
		return i;
	}
	
	// Find smallest k numbers in array
	public static void find(int[] numbers, int k) {
		int start = 0;
		int end = numbers.length-1;
		
		int idx = partition(numbers, start, end);
		while(idx != k) {
			if(idx > k) {
				end = idx-1;
			} else {
				start = idx+1;
			}
			idx = partition(numbers, start, end);
		}
		
		for(int i=0;i<k;i++) {
			System.out.print(numbers[i] + " ");
		}
		System.out.println();
	}
	
	// Find the smallest number in array
	public static void findSmallest(int[] numbers) {
		if(numbers==null && numbers.length<=0) {
			return;
		}
		
		int min = Integer.MAX_VALUE;
		for(int number : numbers) {
			if(number < min) {
				min = number;
			}
		}
		
		System.out.println("The smallest number is " + min);
	}
	
	// Find last 2 smallest numbers in array
	public static void findTwoSmallest(int[] numbers) {
		if(numbers==null && numbers.length<=1) {
			return;
		}
		
		int smallest = (numbers[0]<numbers[1])?numbers[0]:numbers[1];
		int secondSmallest = (numbers[0]>=numbers[1])?numbers[0]:numbers[1];
		
		int i=2;
		for(; i<numbers.length-1; i+=2) {
			if(numbers[i]<numbers[i+1]) {
				if(numbers[i]<smallest) {
					smallest = numbers[i];
					secondSmallest = smallest;
				}
				if(numbers[i+1]<secondSmallest) {
					secondSmallest = numbers[i+1];
				}
			} else {
				if(numbers[i+1]<smallest) {
					smallest = numbers[i+1];
					secondSmallest = smallest;
				}
				if(numbers[i]<secondSmallest) {
					secondSmallest = numbers[i];
				}
			}
		}
		
		if(i == numbers.length-1) {
			if(numbers[i]<smallest) {
				smallest = numbers[i];
				secondSmallest = smallest;
			} else if(numbers[i]<secondSmallest) {
				secondSmallest = numbers[i];
			}
		}
		
		System.out.println("The last 2 smallest number are " + smallest + ", " + secondSmallest);
	}
	
	public static void main(String[] args) {
		int[] numbers = new int[] {4, 5, 1, 6, 2, 7, -2, -1};
		findSmallest(numbers);
		find(numbers, 2);
		findTwoSmallest(numbers);
	}

}
