package wenyu.learning.Strings;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class DuplicateFindingProblems {

	/*
	 * Implement a function to find the first character in a 
	 * string that only appears once. For example, the output 
	 * is the character ‘l’ when the input is “google”.
	 */
	public static void findDuplicateInString(String str) {
		Map<Character, Integer> map = new LinkedHashMap<Character, Integer>();
		for(int i=0;i<str.length();i++) {
			if(map.containsKey(str.charAt(i))) {
				map.put(str.charAt(i), map.get(str.charAt(i))+1);
			} else {
				map.put(str.charAt(i), 1);
			}
		}
		
		Iterator<Entry<Character, Integer>> it = map.entrySet().iterator();
		while(it.hasNext()) {
			Entry<Character, Integer> entry = it.next();
			if(entry.getValue()==1) {
				System.out.println("The first character appears once is " + entry.getKey());
				return ;
			}
		}
	}
	
	/*
	 * Implement a function to find the first character in a 
	 * stream that only appears once at any time while 
	 * reading the stream. (Characters are read one by one 
	 * from a stream.)
	 * 
	 * For example, when the first two characters “go” are read 
	 * from a stream, the first character which appears once is 
	 * the character ‘g’. When the first six characters “google” 
	 * are read, the first character appearing only once is ‘l’.
	 */
	private static Map<Character, Integer> map = new LinkedHashMap<Character, Integer>();
	public static void insertCharToStream(char ch) {
		if(map.containsKey(ch)) {
			map.put(ch, map.get(ch)+1);
		} else {
			map.put(ch, 1);
		}
	}
	public static void findDuplicateInStream() {
		Iterator<Entry<Character, Integer>> it = map.entrySet().iterator();
		while(it.hasNext()) {
			Entry<Character, Integer> entry = it.next();
			if(entry.getValue()==1) {
				System.out.println("The first character appears once is " + entry.getKey());
				return ;
			}
		}
	}
	
	public static void removeDuplicates(String str) {
		Set<Character> set = new HashSet<Character>();
		StringBuilder strBuilder = new StringBuilder();
		for(int i=0;i<str.length();i++) {
			if(!set.contains(str.charAt(i))) {
				strBuilder.append(str.charAt(i));
				set.add(str.charAt(i));
			}
		}
		System.out.println("The string without duplicates is " + strBuilder.toString());
	}
	
	public static void main(String[] args) {
		System.out.println("Find Duplicates in String");
		findDuplicateInString("gloogle");
		
		//==========================================================
		System.out.println("\nFind Duplicates in Stream");
		insertCharToStream('g');
		findDuplicateInStream();
		insertCharToStream('o');
		findDuplicateInStream();
		insertCharToStream('o');
		findDuplicateInStream();
		insertCharToStream('g');
		findDuplicateInStream();
		insertCharToStream('l');
		findDuplicateInStream();
		insertCharToStream('e');
		findDuplicateInStream();
		

		//==========================================================
		System.out.println("\nString without Duplicates");
		removeDuplicates("google");
	}

}
