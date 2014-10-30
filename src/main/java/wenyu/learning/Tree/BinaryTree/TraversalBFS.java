package wenyu.learning.Tree.BinaryTree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class TraversalBFS {
	
	public static <E> void traversalBFS(BinaryTreeNode<E> root) {
		if(root == null) {
			return;
		}
		
		Queue<BinaryTreeNode<E>> queue  = new LinkedList<BinaryTreeNode<E>>();
		queue.offer(root);
		while(!queue.isEmpty()) {
			BinaryTreeNode<E> node = queue.poll();
			System.out.print(node.value + " ");
			
			if(node.left != null) {
				queue.offer(node.left);
			}
			if(node.right != null) {
				queue.offer(node.right);
			}
		}
		System.out.println("");
	}
	
	// Print BFS with lines
	public static <E> void traversalWithLine(BinaryTreeNode<E> root) {
		if(root == null) {
			return;
		}
		int currentLineNodeCount = 1;
		int nextLineNodeCount = 0;
		Queue<BinaryTreeNode<E>> queue = new LinkedList<BinaryTreeNode<E>>();
		queue.offer(root);
		
		while(!queue.isEmpty()) {
			BinaryTreeNode<E> node = queue.poll();
			System.out.print(node.value + " ");
			
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
				System.out.println();
				currentLineNodeCount = nextLineNodeCount;
				nextLineNodeCount = 0;
			}
		}
	}
	
	// Print BFS with lines
	public static <E> void traversalWithLineZigZag(BinaryTreeNode<E> root) {
		if(root == null) {
			return;
		}
		int currentLineNodeCount = 1;
		int nextLineNodeCount = 0;
		int lineNo = 0;
		Stack<BinaryTreeNode<E>> stackEven = new Stack<BinaryTreeNode<E>>();
		Stack<BinaryTreeNode<E>> stackOdd = new Stack<BinaryTreeNode<E>>();
		stackEven.push(root);
		
		while(!stackEven.isEmpty() || !stackOdd.isEmpty()) {
			if(lineNo%2 == 0) {
				BinaryTreeNode<E> node = stackEven.pop();
				System.out.print(node.value + " ");
				if(node.left != null) {
					stackOdd.push(node.left);
					nextLineNodeCount++;
				}
				if(node.right != null) {
					stackOdd.push(node.right);
					nextLineNodeCount++;
				}
			} else {
				BinaryTreeNode<E> node = stackOdd.pop();
				System.out.print(node.value + " ");
				if(node.right != null) {
					stackEven.push(node.right);
					nextLineNodeCount++;
				}
				if(node.left != null) {
					stackEven.push(node.left);
					nextLineNodeCount++;
				}
			}

			currentLineNodeCount--;
			if(currentLineNodeCount==0) {
				System.out.println();
				currentLineNodeCount = nextLineNodeCount;
				nextLineNodeCount = 0;
				lineNo++;
			}
		}
	}
	
	public static void main(String[] args) {
		BinaryTreeNode<Integer> root = BSTUtils.genIntegerBST(10);
		traversalWithLine(root);
		System.out.println("=============================================");
		traversalBFS(root);
		System.out.println("=============================================");
		traversalWithLineZigZag(root);
	}
}
