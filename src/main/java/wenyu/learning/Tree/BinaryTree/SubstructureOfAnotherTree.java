package wenyu.learning.Tree.BinaryTree;

/**
 * Given two binary trees, please check whether one is a substructure of the other
 */
public class SubstructureOfAnotherTree {
	private static <E> boolean checkHasSubBinaryTree(BinaryTreeNode<E> node1, BinaryTreeNode<E> node2) {
		boolean result = false;
		
		if(node1!=null && node2!=null) {
			if(node1.value == node2.value) {
				if(node2.left==null) {
					return true;
				} else {
					result = checkHasSubBinaryTree(node1.left, node2.left);
				}
				
				if(result) {
					if(node2.right==null) {
						return true;
					} else {
						result = checkHasSubBinaryTree(node1.right, node2.right);
					}
				}
			}
			
			if(!result) {
				result = checkHasSubBinaryTree(node1.left, node2);
			}
			
			if(!result) {
				result = checkHasSubBinaryTree(node1.right, node2);
			}
		}
	
		return result;
	}

	public static void main(String[] args) {
		BinaryTreeNode<Integer> root1 = new BinaryTreeNode<Integer>(8);
		root1.left = new BinaryTreeNode<Integer>(8);
		root1.right = new BinaryTreeNode<Integer>(7);
		root1.left.left = new BinaryTreeNode<Integer>(9);
		root1.left.right = new BinaryTreeNode<Integer>(2);
		root1.left.right.left = new BinaryTreeNode<Integer>(5);
		root1.left.right.right = new BinaryTreeNode<Integer>(7);
		
		BinaryTreeNode<Integer> root2 = new BinaryTreeNode<Integer>(8);
		root2.left = new BinaryTreeNode<Integer>(9);
		root2.right = new BinaryTreeNode<Integer>(2);
		root2.right.left = new BinaryTreeNode<Integer>(5);
		root2.right.right = new BinaryTreeNode<Integer>(7);
		
		if(checkHasSubBinaryTree(root1, root2)) {
			System.out.println("Root2 is the sub-tree of Root1");
		} else {
			System.out.println("Root2 is not the sub-tree of Root1");
		}
	}

}
