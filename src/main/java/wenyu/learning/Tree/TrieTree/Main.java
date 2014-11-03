package wenyu.learning.Tree.TrieTree;

import java.util.Scanner;

public class Main {

	public static void TrieStrTreeDemo() {
		TrieTreeAPI<Character> api = new TrieTreeAPI<Character>() {
			@Override
			public Character[] toArray(Object s) {
				String str = s.toString();
				Character[] cArray = new Character[str.length()];
				for(int i = 0; i < cArray.length; i++) {
					cArray[i] = str.charAt(i);
				}
				return cArray;
			}
		};
		
		while(true) {
			System.out.println("Help: <commond> <item>");
			System.out.println("Commond: [insert, search, delete]");
			Scanner scanner = new Scanner(System.in);
			String[] input = scanner.nextLine().trim().split(" ");
			if(input.length == 2) {
				String commond = input[0].toLowerCase().trim();
				String value = input[1];
				
				boolean result = false;
				if("insert".equals(commond)) {
					result = api.insert(value);
				} else if("search".equals(commond)) {
					result = api.search(value);
				} else if("delete".equals(commond)) {
					result = api.delete(value);
				}
				String resultStr = (result)? "Success" : "Failure";
				System.out.println("Result: " + resultStr);
			} else if(input.length==1 && "exit".equals(input[0].toLowerCase().trim())) {
				break;
			} else {
				System.out.println("Wrong commond.");
			}
		}
	}
	
	public static void TrieIntTreeDemo() {
		TrieTreeAPI<Integer> api = new TrieTreeAPI<Integer>() {
			@Override
			public Integer[] toArray(Object integer) {
				String valueStr = integer.toString();
				char[] valueChars = valueStr.toCharArray();
				Integer[] value = new Integer[valueChars.length];
				for(int i=0;i<value.length;i++) {
					value[i] = valueChars[i] - '0';
				}
				return value;
			}
		};
		
		while(true) {
			System.out.println("Help: <commond> <item>");
			System.out.println("Commond: [insert, search, delete]");
			Scanner scanner = new Scanner(System.in);
			String[] input = scanner.nextLine().trim().split(" ");
			if(input.length == 2) {
				String commond = input[0].toLowerCase().trim();
				Integer value = Integer.parseInt(input[1]);
				
				boolean result = false;
				if("insert".equals(commond)) {
					result = api.insert(value);
				} else if("search".equals(commond)) {
					result = api.search(value);
				} else if("delete".equals(commond)) {
					result = api.delete(value);
				}
				String resultStr = (result)? "Success" : "Failure";
				System.out.println("Result: " + resultStr);
			} else if(input.length==1 && "exit".equals(input[0].toLowerCase().trim())) {
				break;
			} else {
				System.out.println("Wrong commond.");
			}
		}
	}
	
	public static void main(String[] args) {
		TrieStrTreeDemo();
		
//		TrieIntTreeDemo();

	}

}
