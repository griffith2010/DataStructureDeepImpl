package wenyu.learning.Strings;

import java.util.ArrayList;
import java.util.HashMap;

/*
 * Given two strings string1 and string2, find the smallest substring in string1 containing all characters of string2 efficiently.
 * For Example:
 * Input string1: “this is a test string”
 * Input string2: “tist”
 * Output string: “t stri”
 * 
 * Logic:
 * 	1. Build a count array count[] for string 2. The count array stores counts of characters.
 *  2. Scan the string1 from left to right until we find all the characters of string2. 
 *     To check if all the characters are there. 
 *     So we have substring “this is a t” containing all characters of string2. 
 *  3. Now move forward in string1 and keep adding characters to the substring “this is a t”. 
 *     Whenever a character is added, check if the added character matches the left most character 
 *     of substring. If matches, then add the new character to the right side of substring and remove 
 *     the leftmost character and all other extra characters after left most character. After removing the 
 *     extra characters, get the length of this substring and compare with min_len and update min_len 
 *     accordingly. 
 *     e.g. "this is a t" ==> "this is a te" ==> "this is a tes" ==> "this is a test" ==> "is a test"
 */

public class FindSmallestSubstrContainAnotherStr {

	public static String find(String string, String pattern) {
		HashMap<Character, Integer> patternConcurrence = new HashMap<Character, Integer>(); 
		HashMap<Character, Integer> currentConcurrence = new HashMap<Character, Integer>();
		Character[] smallest = null;
		
		// Step 1: Form pattern concurrence
		for(char ch : pattern.toCharArray()) {
			if(patternConcurrence.containsKey(ch)) {
				patternConcurrence.put(ch, patternConcurrence.get(ch)+1);
			} else {
				patternConcurrence.put(ch, 1);
			}
		}
		
		// Step 2: Form first version of substring
		ArrayList<Character> substring = new ArrayList<Character>();
		boolean startInsert = false;
		int matchCount = 0;
		int i=0;
		for(; i<string.length()&&matchCount<pattern.length(); i++) {
			char ch = string.charAt(i);
			if(!startInsert && patternConcurrence.containsKey(ch)) {
				substring.add(ch);
				currentConcurrence.put(ch, 1);
				startInsert = true;
				matchCount++;
			} else if(startInsert) {
				substring.add(ch);
				if(patternConcurrence.containsKey(ch)) {
					if(currentConcurrence.containsKey(ch)) {
						currentConcurrence.put(ch, currentConcurrence.get(ch)+1);
					} else {
						currentConcurrence.put(ch, 1);
					}
					if(patternConcurrence.get(ch) >= currentConcurrence.get(ch)) {
						matchCount ++;
					}
				}
			}
		}
		if(matchCount < pattern.length()) {
			return null;
		} else {
			Character[] tmp = new Character[substring.size()];
			tmp = substring.toArray(tmp);
			smallest = tmp;
		}
		
		// Step 3: add later characters and remove left most ones.
		for(; i<string.length(); i++) {
			char ch = string.charAt(i);
			if(ch == substring.get(0)) {
				substring.remove(0);
				while(substring.size() >= 0) {
					if(!currentConcurrence.containsKey(substring.get(0))) {
						// left-mode character is not in the pattern
						substring.remove(0);
					} else if(currentConcurrence.containsKey(substring.get(0)) 
							&& currentConcurrence.get(substring.get(0))>patternConcurrence.get(substring.get(0))) {
						// left-most character is in pattern, but the concurrence is bigger than in pattern
						currentConcurrence.put(substring.get(0), currentConcurrence.get(substring.get(0))-1);
						substring.remove(0);
					} else {
						break;
					}
				}
				substring.add(ch);
				if(substring.size() < smallest.length) {
					Character[] tmp = new Character[substring.size()];
					tmp = substring.toArray(tmp);
					smallest = tmp;
				}
			} else {
				substring.add(ch);
				if(patternConcurrence.containsKey(ch)) {
					currentConcurrence.put(ch, currentConcurrence.get(ch)+1);
				}
			}
		}
		
		// Step end: convert character array to string
		StringBuilder builder = new StringBuilder();
		for(char ch : smallest) {
			builder.append(ch);
		}
		return builder.toString();
	}
	
	public static void main(String[] args) {
		String substring = find("this is a test string", "tist");
		System.out.println(substring);
	}

}
