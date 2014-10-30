package wenyu.learning.Strings;

/*
 * Find the longest common string (Continue chars)
 * Logic: Time:O(n^2)/Space:O(n^2) [solution1]
 * 	1. Form a matrix with all 0 in it;
 *  2. Scan two strings with two loop
 *  3. if(str1[i]!=str2[j])
 *  	  then set matrix[i][j] to 0
 *  4. else
 *  	  then set matrix[i][j] to matrix[i-1][j-1]+1
 *  	  in case if i=0 or j=0 which means 
 *  	  the first common item then set matrix[i][j] to 1
 *  5. The longest common string will end with the max matrix[i][j].
 *  For example:
 *  	str1: abch
 *  	str2: xbc
 *      matix:   a  b  c  h
 *      	  x	 0  0  0  0
 *      	  b  0  1  0  0
 *      	  c  0  0  2  0
 *      So the longest length will be 2 and common string is "bc"
 */
public class LongestCommonString {
	
	public static void LCString_solution1(char[] str1, char[] str2) {
		int len1 = str1.length;
		int len2 = str2.length;
		int[][] matrix = new int[len1][len2];
		int maxLen = 0;
		
		for(int i=0;i<len1;i++) {
			for(int j=0;j<len2;j++) {
				if(str1[i] == str2[j]) {
					if(i==0 || j==0) {
						matrix[i][j] = 1;
					} else {
						matrix[i][j] = matrix[i-1][j-1]+1;
					}
					if(matrix[i][j] > maxLen) {
						maxLen = matrix[i][j];
					}
				} else {
					matrix[i][j]=0;
				}
			}
		}
		
		System.out.println("Max length is " + maxLen);
		for(int i=len1-1;i>=0;i--) {
			for(int j=len2-1;j>=0;j--) {
				if(matrix[i][j]>1 && ((i==len1-1)||(j==len2-1)||matrix[i+1][j+1]==0)) {
					int len = matrix[i][j];
					int row = i;
					System.out.println("Length: " + len);
					while(len-->0) {
						System.out.print(str1[row-len]);
					}
					System.out.println();
				}
			}
		}
		
	}
	
	public static void LCString_solution2(char[] str1, char[] str2) {
		int len1 = str1.length;
		int len2 = str2.length;
		int maxLen = len1>len2?len1:len2;
		int[] max = new int[maxLen];
		int[] maxIndex = new int[maxLen];
		int[] c = new int[maxLen];

		for(int i=0;i<len2;i++) {
			for(int j=len1-1;j>=0;j--) {
				if(str2[i] == str1[j]) {
					if ((i == 0) || (j == 0)) {
						c[j] = 1;
					} else {
						c[j] = c[j - 1] + 1;
					}
				} else {
					c[j] = 0;
				}

				if (c[j] > max[0]) {
					max[0] = c[j];
					maxIndex[0] = j;

					for (int k=1;k<maxLen;k++) {
						max[k] = 0;
						maxIndex[k] = 0;
					}
				} else if (c[j] == max[0]) {
					for (int k=1;k<maxLen;k++) {
						if (max[k] == 0) {
							max[k] = c[j];
							maxIndex[k] = j;
							break;
						}
					}
				}
			}
		}

		for (int j=0;j<maxLen;j++) {
			if (max[j] > 0) {
				System.out.print((j + 1) + ":");
				for (int i = maxIndex[j] - max[j] + 1; i <= maxIndex[j]; i++)
					System.out.print(str1[i]);
				System.out.println();
			}
		}
	}

	public static void main(String[] args) {
		String str1 = new String("1234savxfacbagegsd");
        String str2 = new String("acdbacacbadefbf1234savxfdstyuiopvfdz");
        LCString_solution1(str1.toCharArray(),str2.toCharArray());
        LCString_solution2(str1.toCharArray(),str2.toCharArray());
	}

}
