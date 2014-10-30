package wenyu.learning.Strings;

/*
 * Write a method that takes a string, 
 * in this format "aabbaadddc". 
 * Encode the string by counting the consecutive 
 * letters. Eg: "a2b2a2d3c1"
 */
public class CombineSameCharInString {
	
	public static String changeFormat(String str) {
		StringBuilder result = new StringBuilder();
		for(int i=0;i<str.length();) {
			char currChar = str.charAt(i);
			result.append(currChar);
			int counter = 1;
			while(++i<str.length() && str.charAt(i) == currChar) {
				counter++;
			}
			result.append(counter);
		}
		
		return result.toString();
	}
	
	
	public static void main(String[] args) {
		String result = changeFormat("aabbdddssseeeeeeffflfcngsl");
		System.out.println(result);
	}

}
