package wenyu.learning.Strings;

/*
 * Find the longest common sub-sequence (not continue chars)
 * For example:
 * 	str1: 1a2b3cd4
 *  str2: arbrcid9
 *  result: abcd
 *  
 * Logic: O(n^2)
 * 	First part to form the matrix
 * 	1. Form a matrix with all 0 in it;
 *  2. Scan two strings with two loop from the end
 *  3. if(str1[i]!=str2[j])
 *  	  then set matrix[i][j] to max(matrix[i+1][j], matrix[i][j+1])
 *  	  if i==len1 or j==len2 then matrix[i][j] to 0
 *  4. else
 *  	  then set matrix[i][j] to matrix[i+1][j+1]+1
 *  
 *  Second part to read from the matrix and get the result
 *  1. scan two strings from [0] and [0]
 *  2. if str1[i]==str2[j]
 *  	 then print current char
 *  		  and i++ ; j++
 *  3. else
 *  	next step is choose the max of matrix[i+1][j] and matrix[i][j+1]
 *  	if matrix[i+1][j] is bigger, then i++;
 *      if matrix[i][j+1] is bigger, then j++;
 */

public class LongestCommonSubsequence {

	public static String getLCSubseq(String str1, String str2) {
        int substringLength1 = str1.length();
        int substringLength2 = str2.length();

        int[][] opt = new int[substringLength1+1][substringLength2+1];
        for (int i = substringLength1-1; i >= 0; i--) {
            for (int j = substringLength2-1; j >= 0; j--) {
                if (str1.charAt(i) == str2.charAt(j))
                    opt[i][j] = opt[i + 1][j + 1] + 1;
                else
                    opt[i][j] = Math.max(opt[i + 1][j], opt[i][j + 1]);
            }
        }

        int i = 0, j = 0;
        String result = "";
        while (i < substringLength1 && j < substringLength2) {
            if (str1.charAt(i) == str2.charAt(j)) {
                result += str1.charAt(i);
                i++;
                j++;
            } else if (opt[i + 1][j] >= opt[i][j + 1]) {
            	i++;
            } else {
                j++;
            }
        }
        return result;
	}

	public static void main(String[] args) {
		String str1 = new String("adbbagfdsfdsaffdsatrewtrewdsgc");
        String str2 = new String("acadhdrwetfgdsrewtfdssdgbc");
        String result = getLCSubseq(str1,str2);
        System.out.print("LCS:" + result);
	}

}
