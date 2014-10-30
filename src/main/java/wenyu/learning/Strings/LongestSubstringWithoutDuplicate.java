package wenyu.learning.Strings;

import java.util.HashMap;
import java.util.Map;

/*
 * Given s string, Find max size of a sub-string, in which no duplicate chars present.
 * Logic:
 * 	1. scan each char in string
 *  2. using hashmap to store each character's index
 *  3. using longestSoFar to store current longest sub-string
 *  4. using longestSubstringResult to store current max longest sub-string
 *  5. if(current char didn't in longestSoFar) then add it to longestSoFar, and add it to hashmap too
 *  6. else find the previous index from hashmap, and reset longestSoFar to index+1 to current index.
 *     and also need to update the new index of that character.
 *  7. if longestSoFar is longer than longestSubstringResult, then update longestSubstringResult.
 */

public class LongestSubstringWithoutDuplicate {

	public static String longestSubstringUnrepeatedChar(String inputString) {
		StringBuilder longestSoFar = new StringBuilder();
		String longestSubstringResult = "";
		if (inputString.isEmpty()) {
			return "";
		} else if (inputString.length() == 1) {
			return inputString;
		}
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (int i = 0; i < inputString.length(); i++) {
			String currentCharacter = String.valueOf(inputString.charAt(i));
			int idx = longestSoFar.indexOf(currentCharacter);
			if (idx == -1) {
				if (!map.containsKey(currentCharacter)) {
					map.put(currentCharacter, i);
				}
			} else {
				longestSoFar.delete(0, idx+1);
				map.put(currentCharacter, i);
			}
			longestSoFar.append(currentCharacter);
			if (longestSoFar.length() > longestSubstringResult.length()) {
				longestSubstringResult = longestSoFar.toString();
			}
		}
		return longestSubstringResult;
	}

	public static void main(String[] args) {
		String str = longestSubstringUnrepeatedChar("changawenyu");
		System.out.println(str);
	}

}
