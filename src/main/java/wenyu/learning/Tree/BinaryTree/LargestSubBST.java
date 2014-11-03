package wenyu.learning.Tree.BinaryTree;

/*
 * Given a Binary tree, find a largest BST in this tree.
 * Solution:
 * 		1. if current node is null, then it is BST
 * 		2. if current node's left sub-tree is BST and right sub-tree is BST and its value is within range, then current tree is BST
 * 		3. other case of above just return current largest sub-tree (either in left or right)
 */

public class LargestSubBST {
	public static int LargestBST(BinaryTreeNode<Integer> root) {
		int[] returnValue = new int[3];
		largestBST(root, returnValue);
		return returnValue[2];
	}
	
	private static boolean largestBST(BinaryTreeNode<Integer> node, int[] returnValue) {
		if(node == null) {
			returnValue[0] = Integer.MAX_VALUE;
			returnValue[1] = Integer.MIN_VALUE;
			returnValue[2] = 0;
			return true;
		}
		
		int[] leftReturnValue = new int[3];
		boolean leftIsBST = largestBST(node.left, leftReturnValue);
		int[] rightReturnValue = new int[3];
		boolean rightIsBST = largestBST(node.right, rightReturnValue);
		
		if(leftIsBST && rightIsBST && node.value>=leftReturnValue[1] && node.value<=rightReturnValue[0]) {
			returnValue[2] = leftReturnValue[2] + rightReturnValue[2] + 1;
			// returnValue[0] = (node.value < leftReturnValue[0]) ? node.value : leftReturnValue[0];
			returnValue[0] = leftReturnValue[0];
			// returnValue[1] = (node.value > rightReturnValue[1]) ? node.value : rightReturnValue[1];
			returnValue[1] = rightReturnValue[1];
			return true;
		} else
			returnValue[2] = (leftReturnValue[2] > rightReturnValue[2]) ? leftReturnValue[2] : rightReturnValue[2];
			return false;
		}
	
	public static void main(String[] args) {
		int treeNodeCount = 100;
		BinaryTreeNode<Integer> root = BSTUtils.genMostBalancedIntegerBST(treeNodeCount);
		TraversalBFS.traversalWithLine(root, null);
		
		System.out.println("Largest count is " + LargestBST(root));
	}

}
