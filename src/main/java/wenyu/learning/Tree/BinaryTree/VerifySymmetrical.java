package wenyu.learning.Tree.BinaryTree;

/*
 * Verify if a binary tree is symmetrical whose right part is identical to the left part.
 */

public class VerifySymmetrical {
	public static <E> boolean verify(BinaryTreeNode<E> root) {
		return verify(root, root);
	}
	
	public static <E> boolean verify(BinaryTreeNode<E> node1, BinaryTreeNode<E> node2) {
		if(node1 == null && node2 == null) {
			return true;
		}
		if(node1 == null || node2 == null) {
			return false;
		}
		if(node1.value != node2.value) {
			return false;
		}
		
		return verify(node1.left, node2.right) && verify(node1.right, node2.left);
	}
}
