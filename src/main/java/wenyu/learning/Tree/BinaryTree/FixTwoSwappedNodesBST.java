package wenyu.learning.Tree.BinaryTree;

import java.util.Stack;

/*
 *  Two of the nodes of a Binary Search Tree (BST) are swapped. Fix (or correct) the BST.
	Input Tree:
	         10
	        /  \
	       5    8
	      / \
	     2   20
	
	In the above tree, nodes 20 and 8 must be swapped to fix the tree.  
	Following is the output tree
	         10
	        /  \
	       5    20
	      / \
	     2   8
	     
 * Logic:
 * 1. Prepare two node pointer, N1/N2
 * 2. The in-order traversal of the given tree. {3 '25' 7 8 10 15 20 '5'}
 *    If finding current node is smaller than the previous visited node. [25>7]
 *    a. if N1 is null, then set previous visited node to N1. [25]
 *    b. if N1 is not null, skip. (Since such case will happened twice, 25>7 & 20>5)
 *    c. set current node to N2. (Only mark the last smalle node. It above example, first time assign to 7 and then to 5).
 * 3. if two swapped nodes are adjancent, above logic will also work.
 */

public class FixTwoSwappedNodesBST {

	public static <E extends Comparable<E>> void fix(BinaryTreeNode<E> root) {
		if(root == null) {
			return;
		}
		BinaryTreeNode<E> first = null;			// N1
		BinaryTreeNode<E> second = null;		// N2
		
		
		Stack<BinaryTreeNode<E>> stack = new Stack<BinaryTreeNode<E>>();
		BinaryTreeNode<E> pre = null;
		stack.push(root);
		while(!stack.isEmpty()) {
			BinaryTreeNode<E> node = stack.peek();
			while(node!=null) {
				stack.push(node.left);
				node = node.left;
			}
			
			stack.pop(); //Pop out null peek value
			if(!stack.isEmpty()) {
				node = stack.pop();
				if(pre!=null && pre.value.compareTo(node.value) > 0) {
					first = (first==null) ? pre : first;
					second = node;
				}
				stack.push(node.right);
				pre = node;
			}
		}
		
		if(first!=null && second!=null) {
			E tmp = first.value;
			first.value = second.value;
			second.value = tmp;
		}
	}
	 
	public static void main(String[] args) {
		Integer[] arr = {1,2,3,6,5,7,8,9,10};
		BinaryTreeNode<Integer> root = BSTUtils.genFromSortedArr(arr, 0, arr.length-1);
		TraversalBFS.traversalWithLine(root);
		fix(root);
		System.out.println();
		TraversalBFS.traversalWithLine(root);
	}

}
