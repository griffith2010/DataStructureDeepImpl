package wenyu.learning.Tree.BinaryTree;

import java.util.Stack;

import wenyu.learning.Stack.BuildinStack;

/**
 * Given a binary tree and an integer value, 
 * please print all paths where the sum of 
 * node values equals the given integer. 
 * All nodes from the root node to a node 
 * compose a path. (P168)
 */

public class GetPathWithSum {
	public static void getPathWithSum(BinaryTreeNode<Integer> root, int sum) {
		getPathWithSum(root, sum, 0, new Stack<BinaryTreeNode<Integer>>());
	}
	
	private static void getPathWithSum(BinaryTreeNode<Integer> node, int sum, int currSum, Stack<BinaryTreeNode<Integer>> stack) {
		if(node == null) {
			return;
		}

		int tmpSum = node.value+currSum;
		if(tmpSum == sum) {
			stack.push(node);
			BuildinStack.printStack(stack);
			stack.pop();
		} else if(tmpSum < sum) {
			currSum += node.value;
			stack.push(node);
			getPathWithSum(node.left, sum, currSum, stack);
			getPathWithSum(node.right, sum, currSum, stack);
			stack.pop();
			currSum -= node.value;
		}
	}
	
	public static void main(String[] args) {
		BinaryTreeNode<Integer> root1 = new BinaryTreeNode<Integer>(9);
		root1.left = new BinaryTreeNode<Integer>(5);
		root1.right = new BinaryTreeNode<Integer>(12);
		root1.left.left = new BinaryTreeNode<Integer>(4);
		root1.left.right = new BinaryTreeNode<Integer>(7);
		TraversalBFS.traversalWithLine(root1, null);
		System.out.println("=====================================");
		
		getPathWithSum(root1, 30);
	}
}
