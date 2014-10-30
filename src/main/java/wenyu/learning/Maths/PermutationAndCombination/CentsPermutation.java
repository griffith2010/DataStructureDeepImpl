package wenyu.learning.Maths.PermutationAndCombination;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/*
 * Given an infinite number of quarters (25 cents), 
 * dimes (10 cents), nickels (5 cents) and pennies (1 cent), 
 * write code to calculate the number of ways of 
 * representing n cents.
 * 
 * Logic:
 * 	*** pay attention what level below means
 */
public class CentsPermutation {
	public static int count = 0;
	public static void printPermutation(int total, Map<String, Integer> centTypes, int level) {
		if(total == 0) {
			System.out.print(++count + " [");
			for(Entry<String, Integer> entry : centTypes.entrySet()) {
				System.out.print(entry.getKey() + ":" + entry.getValue() + " / ");
			}
			System.out.println("]");
		}
		
		if(total>=25 && level>=25) {
			// if level is smaller than 25, it means that 25 cents 
			// should not be take into consideration in this recursion
			// because if taking into consideration, there will be 
			// duplicate permutation
			if(centTypes.containsKey("quarters(25)")) {
				centTypes.put("quarters(25)", centTypes.get("quarters(25)")+1);
			} else {
				centTypes.put("quarters(25)", 1);
			}
			printPermutation(total-25, centTypes, 25);
			centTypes.put("quarters(25)", centTypes.get("quarters(25)")-1);
		}
		
		if(total>=10 && level>=10) {
			if(centTypes.containsKey("dimes(10)")) {
				centTypes.put("dimes(10)", centTypes.get("dimes(10)")+1);
			} else {
				centTypes.put("dimes(10)", 1);
			}
			printPermutation(total-10, centTypes, 10);
			centTypes.put("dimes(10)", centTypes.get("dimes(10)")-1);
		}
		
		if(total>=5 && level>=5) {
			if(centTypes.containsKey("nickels(5)")) {
				centTypes.put("nickels(5)", centTypes.get("nickels(5)")+1);
			} else {
				centTypes.put("nickels(5)", 1);
			}
			printPermutation(total-5, centTypes, 5);
			centTypes.put("nickels(5)", centTypes.get("nickels(5)")-1);
		}
		
		if(total>=1 && level>=1) {
			if(centTypes.containsKey("pennies(1)")) {
				centTypes.put("pennies(1)", centTypes.get("pennies(1)")+1);
			} else {
				centTypes.put("pennies(1)", 1);
			}
			printPermutation(total-1, centTypes, 1);
			centTypes.put("pennies(1)", centTypes.get("pennies(1)")-1);
		}
	}
	
	public static void main(String[] args) {
		printPermutation(100, new HashMap<String, Integer>(), 30);
	}

}
