package wenyu.learning.Tree.BinaryTree;

import java.lang.reflect.Array;
import java.util.HashSet;
import java.util.Set;

public class LowestCommonAncestor {
	// For binary tree, find two nodes' lowest common ancestor
	public static <E> BinaryTreeNode<E> LCA(BinaryTreeNode<E> root, BinaryTreeNode<E> child1, BinaryTreeNode<E> child2) {
		if(child1 == child2) {
			return child1;
		}
		boolean[] findChildren = new boolean[2];
		findChildren[0] = false;
		findChildren[1] = false;
		BinaryTreeNode<E> possibleCommonAncestor = LCA(root, child1, child2, findChildren);
		if(findChildren[0] && findChildren[1]) {
			return possibleCommonAncestor;
		} else {
			return null;
		}
	}
	private static <E> BinaryTreeNode<E> LCA(BinaryTreeNode<E> node, BinaryTreeNode<E> child1, BinaryTreeNode<E> child2, boolean[] findChildren) {
		if (node==null) {
			return null;
		}

		BinaryTreeNode<E> leftRoot = LCA(node.left, child1, child2, findChildren);
		BinaryTreeNode<E> rightRoot = LCA(node.right, child1, child2, findChildren);
		
		if(node==child1) {
			findChildren[0] = true;
			return child1;
		} else if(node==child2) {
			findChildren[1] = true;
			return child2;
		} else if(leftRoot!=null && rightRoot!=null) {
			// if child1 and child2 are on both sides
			return node; 
		} else {
			// either one of child1 or child2 is on one side 
			// OR child1 or child2 is not in L&R subtrees
			return (leftRoot != null) ? leftRoot : rightRoot;
		}
	}
	
	// For binary tree, find several nodes' lowest common ancestor
	public static <E> BinaryTreeNode<E> LCA(BinaryTreeNode<E> root, BinaryTreeNode<E>[] nodes) {
		// remove duplicate nodes
		Set<BinaryTreeNode<E>> set = new HashSet<BinaryTreeNode<E>>();
		for(BinaryTreeNode<E> node : nodes) {
			set.add(node);
		}
		@SuppressWarnings("unchecked")
		BinaryTreeNode<E>[] tmpNodes = (BinaryTreeNode<E>[]) Array.newInstance(BinaryTreeNode.class, set.size());
		nodes = set.toArray(tmpNodes);
		int[] findChildCount = new int[1];
		findChildCount[0] = 0;
		BinaryTreeNode<E> possibleCommonAncestor = LCA(root, nodes, findChildCount);
		if(findChildCount[0] == nodes.length) {
			return possibleCommonAncestor;
		} else {
			return null;
		}
	}
	private static <E> BinaryTreeNode<E> LCA(BinaryTreeNode<E> node, BinaryTreeNode<E>[] nodes, int[] findChildCount) {
		if (node == null) {
			return null;
		}

		BinaryTreeNode<E> leftRoot = LCA(node.left, nodes, findChildCount);
		BinaryTreeNode<E> rightRoot = LCA(node.right, nodes, findChildCount);
		
		for(int i=0;i<nodes.length;i++) {
			if (node == nodes[i]) {
				findChildCount[0]++;
				return node;
			}
		}
		
		if (leftRoot != null && rightRoot != null) {
			// if child1 and child2 are on both sides
			return node; 
		} else { 		
			// either one of child1 or child2 is on one side 
			// OR child1 or child2 is not in L&R subtrees
			return (leftRoot != null) ? leftRoot : rightRoot;
		}
	}

	public static void main(String[] args) {
		BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(1);
		BinaryTreeNode<Integer> child1 = new BinaryTreeNode<Integer>(3);
		BinaryTreeNode<Integer> child2 = new BinaryTreeNode<Integer>(8);
		BinaryTreeNode<Integer> child3 = new BinaryTreeNode<Integer>(9);
//		root.left = new TreeNode<Integer>(2);
//		root.left.right = child1;
//		root.right = new TreeNode<Integer>(5);
//		root.right.left = child2;
//		root.right.right = new TreeNode<Integer>(6);
//		root.right.right.left = child3;
		root.left = new BinaryTreeNode<Integer>(2);
		root.right = child1;
		root.right.left = child2;
		root.right.right = new BinaryTreeNode<Integer>(6);
		root.right.right.left = child3;
		
		//find two nodes' lowest common ancestor
		BinaryTreeNode<Integer> commonAncestor = LCA(root, child2, child3);
		if(commonAncestor!=null) {
			System.out.println("Lowest Common Ancestor is " + commonAncestor.value);
		} else {
			System.out.println("These two nodes has no lowest common ancestor.");
		}
		
		
		@SuppressWarnings("unchecked")
		BinaryTreeNode<Integer>[] nodes = (BinaryTreeNode<Integer>[]) Array.newInstance(BinaryTreeNode.class, 3);
		nodes[0] = child2;
		nodes[1] = child2;
		nodes[2] = child3;
		//find several nodes' lowest common ancestor
		commonAncestor = LCA(root, nodes);
		if(commonAncestor!=null) {
			System.out.println("Lowest Common Ancestor is " + commonAncestor.value);
		} else {
			System.out.println("These nodes have no lowest common ancestor.");
		}
	}
}
