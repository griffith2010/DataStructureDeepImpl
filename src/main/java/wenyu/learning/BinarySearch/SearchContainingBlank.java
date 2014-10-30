package wenyu.learning.BinarySearch;

/*
 * Given a sorted array of strings which is interspersed with 
 * empty strings, write a method to find the location of a given string.
 * 
 * Example: find “ball” in [“at”, “”, “”, “”, “ball”, “”, “”, “car”, “”, “”, “dad”, “”, “”] will return 4
 * Example: find “ballcar” in [“at”, “”, “”, “”, “”, “ball”, “car”, “”, “”, “dad”, “”, “”] will return -1
 */
public class SearchContainingBlank {

	public static int search(String[] strs, int front, int end, String str) {
		if(front<=end) {
			int mid = front + (end-front)/2;
			if(strs[mid] == "") {
				int result = search(strs, front, mid-1, str);
				if(result == -1) {
					result = search(strs, mid+1, end, str);
				}
				return result;
			} else {
				if(strs[mid].equals(str)) {
					return mid;
				} else if(strs[mid].compareTo(str) > 0) {
					return search(strs, front, mid-1, str);
				} else {
					return search(strs, mid+1, end, str);
				}
			}
		}
		return -1;
	}
	
	public static void main(String[] args) {
		String[] strs = {"at", "", "", "", "ball", "", "", "car", "", "", "dad", "", ""};
		int idx = search(strs, 0, strs.length-1, "ballaa");
		System.out.println(idx);
	}

}
