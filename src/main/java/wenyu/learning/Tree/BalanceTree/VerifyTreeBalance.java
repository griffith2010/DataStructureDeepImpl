package wenyu.learning.Tree.BalanceTree;

import wenyu.learning.Tree.BinaryTree.BSTUtils;
import wenyu.learning.Tree.BinaryTree.BinaryTreeNode;

public class VerifyTreeBalance {
	
	public static <E> int treeHeight(BinaryTreeNode<E> node) {
		if(node == null) {
			return 0;
		}
		int leftHeight = treeHeight(node.left);
		if(leftHeight == -1) {
			return -1;
		}
		int rightHeight = treeHeight(node.right);
		if(rightHeight == -1) {
			return -1;
		}
		if(leftHeight-rightHeight>1 || leftHeight-rightHeight<-1) {
			return -1;
		} else {
			return (leftHeight > rightHeight) ? (leftHeight + 1) : (rightHeight + 1);
		}
	}

	public static void main(String[] args) {
		BinaryTreeNode<Integer> binaryTreeRoot = BSTUtils.genMostBalancedIntegerBST(10);
		int height = treeHeight(binaryTreeRoot);
		if(height == -1) {
			System.out.println("Tree is not balance...");
		} else {
			System.out.println("Tree is balance...");
		}
	}

}
