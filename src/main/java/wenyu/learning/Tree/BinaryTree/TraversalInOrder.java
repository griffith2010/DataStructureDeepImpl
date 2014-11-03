package wenyu.learning.Tree.BinaryTree;

import java.util.Stack;

public class TraversalInOrder {

	public static <E> void traversalWithRecursion(BinaryTreeNode<E> node, TraversalOperation op) {
		if(node == null) {
			return;
		}
		
		if(node.left!=null) {
			traversalWithRecursion(node.left, op);
		}
		if(op == null) {
			System.out.print(node.value + " ");
		} else {
			op.operate(node);
		}
		if(node.right!=null) {
			traversalWithRecursion(node.right, op);
		}
	}
	
	public static <E> void traversalWithoutRecursion(BinaryTreeNode<E> root, TraversalOperation op) {
		if(root == null) {
			return;
		}
		
		Stack<BinaryTreeNode<E>> stack = new Stack<BinaryTreeNode<E>>();
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
				if(op == null) {
					System.out.print(node.value + " ");
				} else {
					op.operate(node);
				}
				stack.push(node.right);
			}
		}
	}
	
	public static <E> void MorrisInOrder(BinaryTreeNode<E> root) {
		/*
		 * Time: O(n), Space: O(1)
		 * 1. 如果当前节点的左孩子为空，则输出当前节点并将其右孩子作为当前节点。
		 * 2. 如果当前节点的左孩子不为空，在当前节点的左子树中找到当前节点在中序遍历下的前驱节点。
		 *    a) 如果前驱节点的右孩子为空，将它的右孩子设置为当前节点。当前节点更新为当前节点的左孩子。
		 *    b) 如果前驱节点的右孩子为当前节点，将它的右孩子重新设为空（恢复树的形状）。输出当前节点。当前节点更新为当前节点的右孩子。
		 * 3. 重复以上1、2直到当前节点为空。
		 * 
		 * Reference:
		 * http://www.cnblogs.com/AnnieKim/archive/2013/06/15/MorrisTraversal.html
		 */
		if(root == null) {
			return;
		}
		
		BinaryTreeNode<E> currNode = root;
		while(currNode!=null) {
			
			if(currNode.left == null) {
				System.out.print(currNode.value + " ");
				currNode = currNode.right;
			} else {
				BinaryTreeNode<E> pre = currNode.left;
				while(pre.right!=null && pre.right!=currNode) {
					pre = pre.right;
				}
				
				if(pre.right == null) {  // 2.a
					pre.right = currNode;
					currNode = currNode.left;
				} else if(pre.right == currNode) {				// 2.b
					System.out.print(currNode.value + " ");
					pre.right = null;
					currNode = currNode.right;
				}
			}
		}
	}
	
	private void demoEntry() {
		BinaryTreeNode<Integer> root = BSTUtils.genIntegerBST(8);
		TraversalBFS.traversalWithLine(root, null);
		
		System.out.println("========================");
		traversalWithRecursion(root, null);
		System.out.println();
		System.out.println("========================");
		traversalWithoutRecursion(root, null);
		System.out.println();
		System.out.println("========================");
		MorrisInOrder(root);
	}
	
	public static void main(String[] args) {
		new TraversalInOrder().demoEntry();
	}

}
