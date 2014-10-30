package wenyu.learning.Tree.BinaryTree;


public class TreeHeightCalculator {
	/*
	 * The height of one node means this level count from the very bottom node to this node.
	 */
	
	public static <E> int calculateTreeHeight(BinaryTreeNode<E> node) {
		if(node==null) {
			return 0;
		}
		
		int leftHeight = calculateTreeHeight(node.left);
		int rightHeight = calculateTreeHeight(node.right);
		
		int currHeight = (leftHeight>=rightHeight)?leftHeight+1:rightHeight+1;
		return currHeight;
	}
	
	public static void main(String[] args) {
		BinaryTreeNode<Integer> root = BSTUtils.genIntegerBST(10);
		TraversalBFS.traversalWithLine(root);
		System.out.println("======================================================================");
		System.out.println("Tree height: " + calculateTreeHeight(root));
	}

}
