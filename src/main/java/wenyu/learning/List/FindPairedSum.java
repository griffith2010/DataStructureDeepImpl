package wenyu.learning.List;

import java.util.HashSet;
import java.util.Set;

/*
 * Find the pair of numbers that sums to an integer k from an linked list.
 * Solution1: O(n^2)
 * 		scan link and then scan all other nodes that sum is the given sum
 * Solution2: O(n)
 * 		using hashmap to store the read node first
 */
public class FindPairedSum {

	public static boolean find_solution1(MyListNode<Integer> root, int sum) {
		MyListNode<Integer> node = root;
		while(node != null) {
			Integer currVal = node.getValue();
			Integer restVal = sum - currVal;
			
			MyListNode<Integer> anoNode = node.next;
			while(anoNode != null) {
				if(anoNode.getValue().equals(restVal)) {
					System.out.println("Paired node is " + node.getValue() + " and " + anoNode.getValue());
					return true;
				}
				anoNode = anoNode.next;
			}
			
			node = node.next;
		}
		
		return false;
	}
	
	public static boolean find_solution2(MyListNode<Integer> root, int sum) {
		Set<Integer> set = new HashSet<Integer>();
		
		MyListNode<Integer> node = root;
		while(node!=null) {
			if(set.contains(sum-node.getValue())) {
				System.out.println("Paired node is " + node.getValue() + " and " + (sum-node.getValue()));
				return true;
			} else {
				set.add(node.getValue());
			}
			node = node.next;
		}
		
		return false;
	}
	
	public static void main(String[] args) {
		MyListNode<Integer> root = new MyListNode<Integer>(1);
		root.next = new MyListNode<Integer>(3);
		root.next.next = new MyListNode<Integer>(5);
		root.next.next.next = new MyListNode<Integer>(6);
		
		find_solution1(root, 8);
		find_solution2(root, 8);
	}
}
