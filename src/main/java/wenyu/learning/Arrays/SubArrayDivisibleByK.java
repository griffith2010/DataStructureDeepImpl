package wenyu.learning.Arrays;

/*
 * WAP a program to find a longest continuous subset whose sum is divisible by K. 
 * We are given a array of number (negative+positive).  
 * calculate the complexity of your algorithm
 * 
 * Logic:
 * 	1. using one aux array to store the mod of k:
 * 		s[i] = (a[1] + a[2] + ... + a[i]) % k
 *  2. And we can note that, if there is such [i, j] that we are looking for, then:
		s[j]-s[i] == (a[1]+...+a[j]) - (a[1]+...+a[i]) == (a[i]+...+a[j]) == 0
	3. So, for each s[i] == s[j], sum of sub-set [i, j] is divisible by k. 
	   Now we need to find the most appropriate range. 
 */
public class SubArrayDivisibleByK {

	public static void find(int[] arr, int k) {
		int[] aux = new int[arr.length];
		int sum = 0;
		for(int i=0;i<arr.length;i++) {
			sum += arr[i];
			aux[i] = sum%k;
			for(int j=0;j<i;j++) {
				if(aux[j] == aux[i]) {
					//Find subset whose sum is divisible by K
					if(aux[j]==0) {
						System.out.println("Find sub-set whose sum is divisible by " + k + ". (" + 0 + "," + i +")");
						System.out.println("Find sub-set whose sum is divisible by " + k + ". (" + (j+1) + "," + i +")");
					} else {
						System.out.println("Find sub-set whose sum is divisible by " + k + ". (" + (j+1) + "," + i +")");
					}
				}
			}
		}
	}
	
	public static void main(String[] args) {
		int[] arr = {7,0,2,5,7,8,13};
		int k = 7;
		find(arr, k);
	}

}
