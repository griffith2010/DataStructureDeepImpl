package wenyu.learning.Arrays;

/*
 * Find min and max element of an unsorted integer array
 * 
 * Solution1:
 * 	compare 2n times
 * Solution2:
 * 	Compare 3n/2 times 
 */
public class GetMaxMinFromUnsortedArr {

	public static void find_solution1(int[] arr) {
		if(arr==null || arr.length==0) {
			return;
		}
		
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for(int i=0;i<arr.length;i++) {
			if(arr[i]>max) { // one compare
				max = arr[i];
			} else if(arr[i]<min) { // one compare
				min = arr[i];
			}
		}
		
		System.out.println("Min is" + min + ", Max is " + max);
	}
	
	public static void find_solution2(int[] arr) {
		if(arr==null || arr.length==0) {
			return;
		}
		
		int min;
		int max;
		int i;
		if(arr.length%2==1) {
			min = arr[0];
			max = arr[0];
			i = 1;
		} else {
			min = (arr[0]<=arr[1])?arr[0]:arr[1];
			max = (arr[0]>arr[1])?arr[0]:arr[1];
			i = 2;
		}
		
		for(;i<arr.length;i+=2) {
			if(arr[i]<arr[i+1]) { // one compare
				if(arr[i]<min) { // compare
					min = arr[i];
				}
				if(arr[i+1]>max) { // compare
					max = arr[i+1];
				}
			} else {
				if(arr[i+1]<min) {
					min = arr[i+1];
				}
				if(arr[i]>max) {
					max = arr[i];
				}
			}
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
