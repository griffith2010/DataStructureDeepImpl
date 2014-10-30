package wenyu.learning.Tree.BinaryTree;

import java.util.LinkedList;
import java.util.Queue;

/*
 * Given a binary tree, verify all the leaf node of that tree are all at same level
 * Solution1: Using BFS (without recursion)
 * Solution2: Calculate Height of left&right child (using recursion)
 */

public class VerifyLeafAtSameLevel {

	public static <E> boolean verifyRecursion(BinaryTreeNode<E> node) {
		int height = internalVerifyRecursion(node);
		if(height>=0) {
			System.out.println("(Recursion) All the leaves are at the same level.");
			return true;
		} else {
			System.out.println("(Resursion) All the leaves are not at the same level.");
			return false;
		}
	}
	private static <E> int internalVerifyRecursion(BinaryTreeNode<E> node) {
		if(node == null) {
			return 0;
		}
		
		int leftHeight = internalVerifyRecursion(node.left);
		if(leftHeight < 0) {
			return leftHeight;
		}
		int rightHeight = internalVerifyRecursion(node.right);
		if(rightHeight < 0) {
			return rightHeight;
		}
		
		if(leftHeight==0 && rightHeight==0) {
			return 1;
		} else if(leftHeight==0 || rightHeight==0) {
			return (leftHeight==0)? rightHeight+1 : leftHeight+1;
		} else if(leftHeight == rightHeight) {
			return leftHeight+1;
		} else {
			return -1;
		}
	}
	
	public static <E> boolean verifyByBFS(BinaryTreeNode<E> root) {
		if(root==null) {
			return true;
		}
		
		int currentLineNodeCount = 1;
		int nextLineNodeCount = 0;
		Queue<BinaryTreeNode<E>> queue = new LinkedList<BinaryTreeNode<E>>();
		queue.offer(root);
		boolean thisLevelHasLeaf = false;
		
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

			if(node.left==null && node.right==null) {
				thisLevelHasLeaf = true;
			}
			
			currentLineNodeCount--;
			if(currentLineNodeCount==0) {
				if(thisLevelHasLeaf && nextLineNodeCount>0) {
					System.out.println("All the leaves are not at the same level.");
					return false;
				}
				currentLineNodeCount = nextLineNodeCount;
				nextLineNodeCount = 0;
			}
			
		}
		System.out.println("All the leaves are at the same level.");
		return true;
	}
	
	public static void main(String[] args) {
		BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>();
		root.left = new BinaryTreeNode<Integer>();
		root.right = new BinaryTreeNode<Integer>();
		//root.right.right = new BinaryTreeNode<Integer>();
		
		verifyByBFS(root);
		verifyRecursion(root);
	}

}