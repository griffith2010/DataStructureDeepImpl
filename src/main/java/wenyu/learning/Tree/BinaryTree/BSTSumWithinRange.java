package wenyu.learning.Tree.BinaryTree;

/*
 * Given a BST, find the sum of nodes whose value is within a given range.
 * Time Complexity: O(logn)
 * Solution1: Brute force to scan all the nodes in BST, if current node's value is within given range, add to sum. else next one;
 * Solution2: Pre-order scan BST.
 * 			  if current node's value within given range, add to sum.
 * 			  if current node's value greater than minimum value of range, scan left tree
 * 			  if current node's value smaller than maximum value of range, scan right tree
 */

public class BSTSumWithinRange {

	private static int getSumWithinRange(BinaryTreeNode<Integer> node, int min, int max, int sum) {
		if(node==null || min>max) {
			return sum;
		}
		
		if(node.value>=min && node.value<=max) {
			sum += node.value;
		}
		
		if(node.value>=min) {
			sum = getSumWithinRange(node.left, min, max, sum);
		}
		
		if(node.value<=max) {
			sum = getSumWithinRange(node.right, min, max, sum);
		}
		
		return sum;
	}
	
	public static void main(String[] args) {
		BinaryTreeNode<Integer> root = BSTUtils.genMostBalancedIntegerBST(5);
		TraversalBFS.traversalWithLine(root);
		int min = 33;
		int max = 66;
		int sum = 0;
		sum = getSumWithinRange(root, min, max, sum);
		
		System.out.println("Sum within " + min + " and " + max + " is " + sum);
	}

}
