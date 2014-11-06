package wenyu.learning.Arrays;

/*
 * Given array of N integers ranging from 0 to N-1. 
 * Output maximum repeating integer. 
 * 
 * Logic:
 * 	Solution1: Time: O(n), Space: O(n)
 * 	Solution2: Time: O(n), Space: O(1)
 * 		1. put all arr[i] back to its same index (For example: arr[1]=2, but 2 back to arr[2])
 * 		2. if(arr[i] != i) add back to correct index; set it to a N+1 (invalid value)
 *      3. Go through the arr find the most big index;
 *  Solution3: arr[arr[i]%N] += N; (*)
 */
public class MaxRepeatElementFinding {

	public static void find_solution1(int[] arr) {
		int N = arr.length;
		int[] aux = new int[N];
		
		for(int i=0;i<arr.length;i++) {
			aux[arr[i]]++;
		}
		
		int max = -1;
		int maxInt = -1;
		for(int i=0;i<aux.length;i++) {
			if(aux[i]>max) {
				max = aux[i];
				maxInt = i;
			}
			
		}
		
		System.out.println("Maximum repeating integer is " + maxInt + "(" + max + " times)");
	}
	
	public static void find_solution2(int[] arr) {
		int N = arr.length;
		for(int i=0;i<arr.length;i++) {
			if(arr[i] == i || arr[i]<0) {
				continue;
			} else if(arr[arr[i]] < 0) {
				arr[arr[i]]--;
				arr[i] = N+1;
			} else if(arr[arr[i]] != arr[i]) {
				int idx = arr[i];
				arr[i] = arr[idx];
				arr[idx] = idx;
			} else if(arr[arr[i]] == arr[i]) {
				arr[arr[i]] = -2;
				arr[i] = N+1;
			} 
		}
		
		for(int i=0;i<arr.length;i++) {
			if(arr[i]==i) {
				arr[i] = -1;
			} else if(arr[i]<0 || arr[i]==N+1) {
				continue;
			} else if(arr[arr[i]] == arr[i]) {
				arr[arr[i]] = -2;
				arr[i] = N+1;
			} else if(arr[arr[i]] < 0){
				arr[arr[i]]--;
				arr[i] = N+1;
			} else if(arr[arr[i]] == N+1) {
				arr[arr[i]] = -1;
				arr[i] = N+1;
			}
		}
		
		int max = -1;
		int maxInt = -1;
		for(int i=0;i<arr.length;i++) {
			if(arr[i]<0) {
				if(max<arr[i]*-1) {
					max = arr[i]*-1;
					maxInt = i;
				}
			}
		}
		System.out.println("Maximum repeating integer is " + maxInt + "(" + max + " times)");
	}
	
	public static void find_solution3(int[] arr) {
		int N = arr.length;
		
		for(int i=0;i<arr.length;i++) {
			arr[arr[i]%N] += N;
		}
		
		int max = -1;
		int maxInt = -1;
		for(int i=0;i<arr.length;i++) {
			if(max<arr[i]) {
				max = arr[i];
				maxInt = i;
			}
		}
		System.out.println("Maximum repeating integer is " + maxInt + "(" + max/N + " times)");
	}
	
	public static void main(String[] args) {
		int[] arr = {1,3,3,3,2,1,5,6,2,8,2,2,9};
		find_solution1(arr.clone());
		find_solution2(arr.clone());
		find_solution3(arr.clone());
	}

}
