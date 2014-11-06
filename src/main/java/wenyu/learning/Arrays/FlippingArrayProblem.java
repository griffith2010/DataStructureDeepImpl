package wenyu.learning.Arrays;

/*
	You are given a binary array with N elements: d[0], d[1], ... d[N - 1].
	You can perform AT MOST one move on the array: choose any two integers [L, R], and flip all the elements 
	between (and including) the L-th and R-th bits. L and R represent the left-most and right-most index of the 
	bits marking the boundaries of the segment which you have decided to flip.
	
	What is the maximum number of '1'-bits (indicated by S) which you can obtain in the final bit-string?
	
	'Flipping' a bit means, that a 0 is transformed to a 1 and a 1 is transformed to a 0 (0->1,1->0).
	Input Format
	An integer N
	Next line contains the N bits, separated by spaces: d[0] d[1] ... d[N - 1]
	
	Output:
	S
	
	Constraints:
	1 <= N <= 100000
	d[i] can only be 0 or 1f
	0 <= L <= R < n
	
	Sample Input:
	8
	1 0 0 1 0 0 1 0
	
	Sample Output:
	6
	
	Explanation:
	
	We can get a maximum of 6 ones in the given binary array by performing either of the following operations:
	Flip [1, 5] ==> 1 1 1 0 1 1 1 0
*/


import java.util.Scanner;

public class FlippingArrayProblem {
	public static int calculate(int[] arr) {
		boolean hasZero = false;
		
		int maxDiff = 0;
        int startIdx = 0;
        int endIdx = 0;
        int flipCount = 0;
        int totalOnes = 0;
		
		
        int currDiff = 0;
        int currStart = 0;
        int currFlipCount = 0;
        
        
        for(int i=0; i<arr.length; i++) {
            if(arr[i] == 1) {
                currDiff ++;
                currFlipCount ++;
                totalOnes ++;
            } else {
            	hasZero = true;
                currDiff --;
            }
            
            if(currDiff < maxDiff) {
                maxDiff = currDiff;
                startIdx = currStart;
                endIdx = i;
                flipCount = currFlipCount;
            } else if(currDiff > 0) {
                currDiff = 0;
                currStart = i+1;
                currFlipCount = 0;
            }
        }
        
        int result = endIdx - startIdx + 1 - flipCount + totalOnes - flipCount;
        if(!hasZero) {
        	result --;
        }
        return result;
        
	}
	
	
	public static void main(String args[]){
		/* Enter your code here. Read input from STDIN. Print output to STDOUT */
		while(true) {
			System.out.println("Enter the 1/0 string, split with blanket (For example: 1 0 0 1 0 1)");
	        Scanner scanner = new Scanner(System.in);
	        String input = scanner.nextLine();
	        String[] numArr = input.split(" ");
	    
	        int[] arr = new int[numArr.length];
	        for(int i=0;i<numArr.length; i++) {
	            try {
	                arr[i] = Integer.parseInt(numArr[i]);
	            } catch(Exception ex) {
	                return;
	            }
	        }
	        
	        int result = calculate(arr);
	        System.out.println(result); 
		}
	}
}
