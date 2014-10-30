package wenyu.learning.Tree.BinaryTree;

/*
 * Find the two npackage wenyu.learning.Tree.BinaryTree;

/*
 * Find the two nodes which have the longest distance in tree
 * Logic:
 * 	1. calculate by the almost the same logic of calculating node's height
 */
public class LongestDistance {
	
	public static <E> void findLongestDistance(BinaryTreeNode<E> root) {
		// Object array to store result
		// result[0] is max distance value
		// result[1] is left node of max distance nodes pair
		// result[2] is right node of max distance nodes pair
		Object[] result = new Object[3];
		result[0] = Integer.MIN_VALUE;
		result[1] = null;
		result[2] = null;
		findLongestDistance(root, result);
		
		if(result[1]!=null && result[2]!=null) {
			System.out.println("Longest Distance two nodes are " + ((BinaryTreeNode<E>)result[1]).value + " and " + ((BinaryTreeNode<E>)result[2]).value);
		}
	}
	
	private static <E> Object[] findLongestDistance(BinaryTreeNode<E> node, Object[] result) {
		if(node==null) {
			return new Object[] {null, 0};
		}
		
		Object[] leftTmpResult = findLongestDistance(node.left, result);
		int leftTmpMaxHeight = (Integer) leftTmpResult[1];
		BinaryTreeNode<E> leftTmpNode = (leftTmpMaxHeight==0)?node:(BinaryTreeNode<E>) leftTmpResult[0];
		
		Object[] rightTmpResult = findLongestDistance(node.right, result);
		int rightTmpMaxHeight = (Integer) rightTmpResult[1];
		BinaryTreeNode<E> rightTmpNode = (rightTmpMaxHeight==0)?node:(BinaryTreeNode<E>) rightTmpResult[0];
		
		if(leftTmpMaxHeight+rightTmpMaxHeight > (Integer) result[0]) {
			result[1] = leftTmpNode;
			result[2] = rightTmpNode;
			result[0] = leftTmpMaxHeight+rightTmpMaxHeight;
		}
		
		int currHeight = (leftTmpMaxHeight>=rightTmpMaxHeight) ? leftTmpMaxHeight+1 : rightTmpMaxHeight+1;
		BinaryTreeNode<E> returnNode = null;
		if(currHeight==1) {
			returnNode = node;
		} else {
			returnNode = (leftTmpMaxHeight>=rightTmpMaxHeight) ? leftTmpNode : rightTmpNode;
		}
		
		Object[] currTmpResult = {returnNode, currHeight};
		return currTmpResult;
	}
	
	public static void main(String[] args) {
		BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(1);
		BinaryTreeNode<Integer> child1 = new BinaryTreeNode<Integer>(3);
		BinaryTreeNode<Integer> child2 = new BinaryTreeNode<Integer>(8);
		BinaryTreeNode<Integer> child3 = new BinaryTreeNode<Integer>(9);
		
		root.left = new BinaryTreeNode<Integer>(2);
		root.left.right = child1;
		root.right = new BinaryTreeNode<Integer>(5);
		root.right.left = child2;
		root.right.right = new BinaryTreeNode<Integer>(6);
		root.right.right.left = child3;
		
//		root.left = new TreeNode(2);
//		root.right = child1;
//		root.right.left = child2;
//		root.right.right = new TreeNode(6);
//		root.right.right.left = child3;
		
		findLongestDistance(root);
	}

}