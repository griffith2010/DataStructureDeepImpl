package wenyu.learning.Tree.BinaryTree;

import java.util.LinkedList;
import java.util.Queue;


public class HeightCalculator {
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
	
	public static <E> int calculateTreeHeightWithoutRec(BinaryTreeNode<E> root) {
		if(root == null) {
			return 0;
		}
		int height = 0;
		int currentLineNodeCount = 1;
		int nextLineNodeCount = 0;
		Queue<BinaryTreeNode<E>> queue = new LinkedList<BinaryTreeNode<E>>();
		queue.offer(root);
		
		while(!queue.isEmpty()) {
			BinaryTreeNode<E> node = queue.poll();
			if(node.left != null) {
				queue.offer(node.left);
				nextLineNodeCount++;
			}
			if(node.right != null) {
				queue.offer(node.right);
				nextLineNodeCount++;
			}

			currentLineNodeCount--;
			if(currentLineNodeCount==0) {
				height++;
				currentLineNodeCount = nextLineNodeCount;
				nextLineNodeCount = 0;
			}
		}
		
		return height;
	}
	
	public static void main(String[] args) {
		BinaryTreeNode<Integer> root = BSTUtils.genIntegerBST(10);
		TraversalBFS.traversalWithLine(root, null);
		System.out.println("======================================================================");
		System.out.println("Tree height: " + calculateTreeHeight(root));
		System.out.println("Tree height: " + calculateTreeHeightWithoutRec(root));
	}

}
