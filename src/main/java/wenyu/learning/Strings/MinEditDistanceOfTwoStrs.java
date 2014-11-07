package wenyu.learning.Strings;

/*
 * Implement a function that finds the edit distance of two input strings. There are three types of
 * edit operations: insertion, deletion, and substitution. Edit distance is the minimal number of edit operations to
 * modify a string from one state to the other.
 * 
 * For example, the edit distance between “Saturday” and “Sunday” is 3 since the following three edit operations are 
 * required to modify one into another:
 * (1) Saturday → Sturday (deletion of ‘a’)
 * (2) Sturday → Surday (deletion of ‘t’)
 * (3) Surday → Sunday (substitution of ‘n’ for ‘r’)
 * There is no way to achieve it with fewer than three operations.
 * 
 * 
 * Logic:
 * 1. If a function f(i,j) is defined to indicate the edit distance between the substring of the first string ending
 *    with the jth character and the substring of the second string ending with the ith character.
 * 2. Insert the ith character of the second string into the first string. In this case, f(i,j)=f(i-1,j)+1.
 * 3. Delete the jth character of the first string. In this case, f(i,j)=f(i,j-1)+1.
 * 4. Replace the jth character of the first string with the ith character of the second string. In this case, f(i,j)=f(i-1,j-1)+1.
 * 
 * f(i, j) = i    									[j=0]
 * 			 j    									[i=0]
 * 			 f(i-1, j-1)    						[string[i]=string[j]]
 * 			 min{f(i-1,j), f(i,j-1), f(i-1,j-1)}   	[string[i]!=string[j]]
 * 
 * For example f(i, j): 
 *     S a t u r d a y
 *   0 1 2 3 4 5 6 7 8
 * S 1 0 1 2 3 4 5 6 7
 * u 2 1 1 2 2 3 4 5 6
 * n 3 2 2 2 3 3 4 5 6
 * d 4 3 3 3 3 4 3 4 5
 * a 5 4 3 4 4 4 4 3 4
 * y 6 5 4 4 5 5 5 4 3
 */
public class MinEditDistanceOfTwoStrs {

	public static <E> int getMinDistance(E[] arr1, E[] arr2, boolean print) {
		int[][] F = new int[arr2.length+1][arr1.length+1];
		for(int i=0; i<F.length; i++) {
			F[i][0] = i;
		}
		for(int j=0; j<F[0].length; j++) {
			F[0][j] = j;
		}
		
		for (int i=1; i<F.length; i++) {
			for (int j=1; j<F[0].length; j++) {
				if (arr1[j-1].equals(arr2[i-1])) {
					F[i][j] = F[i-1][j-1];
				} else {
					int deletion = F[i][j-1] + 1;
					int insertion = F[i-1][j] + 1;
					int substitution = F[i-1][j-1] + 1;

					int min = deletion<insertion? deletion : insertion;
					min = min<substitution? min : substitution;
					F[i][j] = min;
				}
			}
		}
		if(print) System.out.println("Minimum Distance is " + F[arr2.length][arr1.length]);
		return F[arr2.length][arr1.length];
	}
	
	public static void main(String[] args) {
		Character[] arr1 = {'S','u','n','d','a','y'};
		Character[] arr2 = {'S','a','t','u','r','d','a','y'};
		getMinDistance(arr1, arr2, true);

	}

}
