package wenyu.learning.Tree.BalanceTree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class TraversalBFS {
	
	public static <E> void traversalBFS(BalancedTreeNode<E> root) {
		if(root == null) {
			return;
		}
		
		Queue<BalancedTreeNode<E>> queue  = new LinkedList<BalancedTreeNode<E>>();
		queue.offer(root);
		while(!queue.isEmpty()) {
			BalancedTreeNode<E> node = queue.poll();
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
	public static <E> void traversalWithLine(BalancedTreeNode<E> root) {
		if(root == null) {
			return;
		}
		int currentLineNodeCount = 1;
		int nextLineNodeCount = 0;
		Queue<BalancedTreeNode<E>> queue = new LinkedList<BalancedTreeNode<E>>();
		queue.offer(root);
		
		while(!queue.isEmpty()) {
			BalancedTreeNode<E> node = queue.poll();
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
	public static <E> void traversalWithLineZigZag(BalancedTreeNode<E> root) {
		if(root == null) {
			return;
		}
		int currentLineNodeCount = 1;
		int nextLineNodeCount = 0;
		int lineNo = 0;
		Stack<BalancedTreeNode<E>> stackEven = new Stack<BalancedTreeNode<E>>();
		Stack<BalancedTreeNode<E>> stackOdd = new Stack<BalancedTreeNode<E>>();
		stackEven.push(root);
		
		while(!stackEven.isEmpty() || !stackOdd.isEmpty()) {
			if(lineNo%2 == 0) {
				BalancedTreeNode<E> node = stackEven.pop();
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
				BalancedTreeNode<E> node = stackOdd.pop();
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
}
