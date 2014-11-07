package wenyu.learning.Tree.BinaryTree;

/*
 * Problem 1:  Please check whether it is possible for an array to be the post-order traversal sequence of a 
 * 			   binary search tree. All numbers in the input array are unique.
 */

class VerifyIsPostOrder {
	/*
	 * For example, the array {5, 7, 6, 9, 11, 10, 8} is the post-order traversal sequence of the binary search tree in
	 * However, there is not a binary search tree whose post-order traversal sequence is {7, 4, 6, 5}.
	 * 				 8
	 * 			   /   \
	 * 			  6    10
	 * 			 / \   / \
	 *          5   7 9  11
	 */
	public static <E extends Comparable<E>> boolean verify(E[] arr, boolean print) {
		boolean result = verify_core(arr, 0, arr.length-1);
		if(print) System.out.println("Result is " + result);
		return result;
	}
	private static <E extends Comparable<E>> boolean verify_core(E[] arr, int start, int end) {
		if(start<0 || end>=arr.length) {
			return false;
		} else if(start>=end) {
			return true;
		}
		
		// Last element is root
		E root = arr[end];
		
		// Find left tree nodes
		int leftEnd = start;
		for(; leftEnd<end; leftEnd++) {
			if(arr[leftEnd].compareTo(root) > 0) {
				break;
			}
		}
		leftEnd--;
		
		// verify rest(right) tree nodes
		for(int i=leftEnd+1; i<end; i++) {
			if(arr[i].compareTo(root) < 0) {
				return false;
			}
		}
		
		// Verify left&right tree
		boolean left = false;
		if(leftEnd >= 0) {
			left = verify_core(arr, start, leftEnd);
		}
		boolean right = verify_core(arr, leftEnd+1, end-1);
		
		return left && right;
	}
}

class VerifyIsPreOrder {
	/*
	 * For example, the array {8, 6, 5, 7, 10, 9, 11} is the post-order traversal sequence of the binary search tree in
	 * However, there is not a binary search tree whose post-order traversal sequence is {7, 4, 6, 5}.
	 * 				 8
	 * 			   /   \
	 * 			  6    10
	 * 			 / \   / \
	 *          5   7 9  11
	 */
	
	public static <E extends Comparable<E>> boolean verify(E[] arr, boolean print) {
		boolean result = verify_core(arr, 0, arr.length-1);
		if(print) System.out.println("Result is " + result);
		return result;
	}
	private static <E extends Comparable<E>> boolean verify_core(E[] arr, int start, int end) {
		if(start<0 || end>=arr.length) {
			return false;
		} else if(start>=end) {
			return true;
		}
		
		// Last element is root
		E root = arr[start];
		
		// Find left tree nodes
		int leftEnd = start;
		for(; leftEnd<=end; leftEnd++) {
			if(arr[leftEnd].compareTo(root) > 0) {
				break;
			}
		}
		leftEnd--;
		
		// verify rest(right) tree nodes
		for(int i=leftEnd+1; i<=end; i++) {
			if(arr[i].compareTo(root) < 0) {
				return false;
			}
		}
		
		// Verify left&right tree
		boolean left = false;
		if(leftEnd >= 0) {
			left = verify_core(arr, start+1, leftEnd);
		}
		boolean right = verify_core(arr, leftEnd+1, end);
		
		return left && right;
	}
}

public class VerifyArrayIsOrderOFBST {
	public static void main(String[] args) {
		Integer[] arr = {5, 7, 6, 9, 11, 10, 8};
		VerifyIsPostOrder.verify(arr, true);
		
		arr = new Integer[] {8, 6, 5, 7, 10, 9, 11};
		VerifyIsPreOrder.verify(arr, true);
	}
}
