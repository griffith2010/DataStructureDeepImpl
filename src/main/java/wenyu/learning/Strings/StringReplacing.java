package wenyu.learning.Strings;

/*
 * Find target in given string, and replace it with given replacement.
 */
public class StringReplacing {

	public static String solution1(String orgStr, char target, String replacement, boolean print) {
		String result = "";
		for(int i=0;i<orgStr.length();i++) {
			if(orgStr.charAt(i) == ' ') {
				result += "%20";
			} else {
				result += orgStr.charAt(i);
			}
		}
		
		if(print) {
			System.out.println(result);
		}
		return result;
	}

	public static String solution2(String orgStr, char target, String replacement, boolean print) {
		char[] org = orgStr.toCharArray();
		int findCount = 0;
		for(int i=0;i<org.length;i++) {
			if(org[i] == target) {
				findCount++;
			}
		}
		
		char[] result = new char[orgStr.length()+findCount*replacement.length()-findCount];		
		int i = org.length-1;
		int j = result.length-1;
		for(;i>=0;i--,j--) {
			if(org[i] == target) {
				for(int k=replacement.length()-1;k>=0;k--) {
					result[j--] = replacement.toCharArray()[k];
				}
				j++;
			} else {
				result[j] = org[i];
			}
		}
		String resultStr = String.valueOf(result);
		if(print) {
			System.out.println(resultStr);
		}
		return resultStr;
	}
	
	public static void main(String[] args) {
		String orgContent = "My name is Wenyu Chang";
		char target = ' ';
		String replacement = "%20";
		
		solution1(orgContent, target, replacement, true);
		solution2(orgContent, target, replacement, true);
	}
}
