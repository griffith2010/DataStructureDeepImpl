package wenyu.learning.Tree.BinaryTree;

import java.util.HashMap;
import java.util.Stack;

/*
 * Clone Binary Tree with Random Pointer
 * Solution 1: clone tree with hash map
 * Solution 2: clone without aux-space
 * 		Logic: 1. Create new nodes in cloned tree and insert each new node in original tree between 
 * 				  the left pointer edge of corresponding node in the original tree.
 * 			   2. Set random pointer in cloned tree as per original tree.
 * 			   3. Restore left pointers correctly in both original and cloned tree.
 */

public class BinaryTreeClone {

	public static <E> BinaryTreeNode<E> cloneTreeWithHashMap(BinaryTreeNode<E> root) {
		if(root == null) {
			return null;
		}
		
		// Prepare node mapping from original node to clone node
		final HashMap<BinaryTreeNode<E>, BinaryTreeNode<E>> nodeMap = new HashMap<BinaryTreeNode<E>, BinaryTreeNode<E>>();	
		TraversalOperation<E> op1 = new TraversalOperation<E>() {
			public Object operate(BinaryTreeNode<E> node) {
				BinaryTreeNode<E> cloneNode = new BinaryTreeNode<E>(node.value);
				nodeMap.put(node, cloneNode);
				return null;
			}
		};
		TraversalPreOrder.traversalWithoutRecursion(root, op1);
		
		// Set points to clone node
		TraversalOperation<E> op2 = new TraversalOperation<E>() {
			public Object operate(BinaryTreeNode<E> node) {
				BinaryTreeNode<E> cloneNode = nodeMap.get(node);
				cloneNode.left = nodeMap.get(node.left);
				cloneNode.right = nodeMap.get(node.right);
				cloneNode.random = nodeMap.get(node.random);
				return null;
			}
		};
		
		TraversalPreOrder.traversalWithoutRecursion(root, op2);
		return nodeMap.get(root);
	}
	
	public static <E> BinaryTreeNode<E> cloneTreeWithoutAuxSpace(BinaryTreeNode<E> root) {
		if(root == null) {
			return null;
		}
		
		// Prepare node mapping from original node to clone node, and set clone node to original node's left
		Stack<BinaryTreeNode<E>> stack = new Stack<BinaryTreeNode<E>>();
		stack.push(root);
		while(!stack.isEmpty()) {
			BinaryTreeNode<E> node = stack.pop();
			BinaryTreeNode<E> cloneNode = new BinaryTreeNode<E>(node.value);
			cloneNode.left = node.left;
			node.left = cloneNode;
			
			if(node.right!=null) {
				stack.push(node.right);
			}
			if(node.left.left!=null) {
				stack.push(node.left.left);
			}
		}
		
		// Set clone node's right/random
		stack.clear();
		stack.push(root);
		while(!stack.isEmpty()) {
			BinaryTreeNode<E> node = stack.pop();
			BinaryTreeNode<E> cloneNode = node.left;
			if(node.right != null) {
				cloneNode.right = node.right.left;
			}
			if(node.random != null) {
				cloneNode.random = node.random.left;
			}
			
			if(node.right!=null) {
				stack.push(node.right);
			}
			if(node.left.left!=null) {
				stack.push(node.left.left);
			}
		}
		
		// Remove link between origin node and clone node
		BinaryTreeNode<E> cloneRoot = root.left;
		stack.clear();
		stack.push(root);
		while(!stack.isEmpty()) {
			BinaryTreeNode<E> node = stack.pop();
			BinaryTreeNode<E> cloneNode = node.left;
			BinaryTreeNode<E> nodeLeft = cloneNode.left;
			node.left = nodeLeft;
			cloneNode.left = (nodeLeft==null)? null : nodeLeft.left;
			
			
			if(node.right!=null) {
				stack.push(node.right);
			}
			if(nodeLeft!=null) {
				stack.push(nodeLeft);
			}
		}
		
		return cloneRoot;
	}
	
	public static void main(String[] args) {
		BinaryTreeNode<Integer> root = BSTUtils.genIntegerBST(30);
		TraversalBFS.traversalWithLine(root, null);
		
		System.out.print("=============================================");
		
		BinaryTreeNode<Integer> cloneRoot = cloneTreeWithHashMap(root);
		TraversalBFS.traversalWithLine(cloneRoot, null);
		
		System.out.println("=============================================");
		
		cloneRoot = cloneTreeWithoutAuxSpace(root);
		TraversalBFS.traversalWithLine(cloneRoot, null);
	}

}
