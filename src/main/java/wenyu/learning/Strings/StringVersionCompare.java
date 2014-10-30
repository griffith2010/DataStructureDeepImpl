package wenyu.learning.Strings;

import java.util.Arrays;
import java.util.Comparator;

/*
 * Find the latest version of released software. For e.g1. 2 and 2.2.. latest is 2.2. 
 * eg2: 3.1 and 3.1.3... latest version is 3.1.3... version is passed as string in above format.
 */
public class StringVersionCompare {

	public static void sortVersions(String[] versions) {
		Comparator<String> comparator = new Comparator<String>() {
			public int compare(String o1, String o2) {
				String[] strSplite1 = o1.split("\\.");
				String[] strSplite2 = o2.split("\\.");
				
				int i = 0;
				int j = 0;
				
				while(i<strSplite1.length && j<strSplite2.length) {
					int ver1 = Integer.parseInt(strSplite1[i]);
					int ver2 = Integer.parseInt(strSplite2[i]);
					
					if(ver1 > ver2) {
						return 1;
					} else if(ver1 < ver2) {
						return -1;
					}
					
					i++;
					j++;
				}
				
				if(i<strSplite1.length) {
					return 1;
				}
				if(j<strSplite2.length) {
					return -1;
				}
				
				return 0;
			}
		};
		
		Arrays.sort(versions, comparator);
		
		for(int i=0;i<versions.length;i++) {
			System.out.print(versions[i] + " ");
		}
	}
	
	public static void main(String[] args) {
		String[] versions = {"1","2.1","1.2","3.1.3","3.2","3.1.9"};
		sortVersions(versions);
	}

}