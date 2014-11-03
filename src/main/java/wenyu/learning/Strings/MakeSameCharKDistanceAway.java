package wenyu.learning.Strings;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

/*
 * Given a string and a positive integer d. Some characters may be repeated in the given string. 
 * Rearrange characters of the given string such that the same characters become d distance away 
 * from each other. Note that there can be many possible rearrangements, the output should be one 
 * of the possible rearrangements. If no such arrangement is possible, that should also be reported.
 * Expected time complexity is O(n) where n is length of input string.
 * 
 * Logic:
 * 1) Let the given string be str and size of string be n.
 * 2) Traverse str, store all characters and their frequencies in a Max Heap MH. 
 * 	  The value of frequency decides the order in MH, i.e., the most frequent character 
 *    is at the root of MH. 
 *    3) Make all characters of str as ‘\0′. 
 *    4) Do following while MH is not empty. 
 *    		a) Extract the Most frequent character. Let the extracted character be x and its frequency be f.
 *    		b) Find the first available position in str, i.e., find the first ‘\0′ in str. 
 *    		c) Let the first position be p. Fill x at p, p+d,.. p+(f-1)d
 */

public class MakeSameCharKDistanceAway {

	public static String transfer(String origin, int k) {
		if(origin==null || origin.length()==0) {
			return null;
		}
		
		final HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		ArrayList<Character> li = new ArrayList<Character>();
		for(char ch : origin.toCharArray()) {
			if(map.containsKey(ch)) {
				map.put(ch, map.get(ch)+1);
			} else {
				map.put(ch, 1);
				li.add(ch);
			}
		}
		
		Comparator<Character> comp = new Comparator<Character>() {
			public int compare(Character o1, Character o2) {
				return map.get(o2) - map.get(o1);
			}
			
		};
		Collections.sort(li, comp);
		
		char[] strChar = new char[origin.length()];
		int idx = 0;
		for(char ch : li) {
			int count = map.get(ch);
			for(int i=0; i<count; i++) {
				int nextIdx = idx+i*k;
				if(nextIdx >= strChar.length) {
					return null;
				} else {
					strChar[nextIdx] = ch;
				}
			}
			while(idx<strChar.length && strChar[idx] != '\0') {
				idx++;
			}
		}
		
		return String.valueOf(strChar);
	}
	
	public static void main(String[] args) {
		String transferred = transfer("abcabcdefabd", 3);
		System.out.println(transferred);
	}

}
