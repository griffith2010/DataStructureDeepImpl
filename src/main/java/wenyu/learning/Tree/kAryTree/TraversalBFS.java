package wenyu.learning.Tree.kAryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class TraversalBFS {
	// Print BFS only in one line
	public static <E> void BFS(KAryTreeNode<E> root) {
		if(root == null) {
			return;
		}

		Queue<KAryTreeNode<E>> queue = new LinkedList<KAryTreeNode<E>>();
		queue.offer(root);

		while(!queue.isEmpty()) {
			KAryTreeNode<E> node = queue.poll();
			System.out.print(node.value + ",");

			if(node.children != null) {
				for(int i=0;i<node.children.length;i++) {
					if(node.children[i] != null) {
						queue.offer(node.children[i]);
					}
				}
			}
		}
		System.out.println();
	}

	// Pring BFS with lines
	public static <E> void BFSWithLine(KAryTreeNode<E> root) {
		if(root == null) {
			return;
		}
		int currentLineNodeCount = 1;
		int nextLineNodeCount = 0;
		Queue<KAryTreeNode<E>> queue = new LinkedList<KAryTreeNode<E>>();
		queue.offer(root);

		while(!queue.isEmpty()) {
			KAryTreeNode<E> node = queue.poll();
			System.out.print(node.value + " ");

			if(node.children != null) {
				for(int i=0;i<node.children.length;i++) {
					if(node.children[i] != null) {
						queue.offer(node.children[i]);
						nextLineNodeCount++;
					}
				}
			}
			currentLineNodeCount--;
			if(currentLineNodeCount==0) {
				System.out.println();
				currentLineNodeCount = nextLineNodeCount;
				nextLineNodeCount = 0;
			}
		}
	}

	// Pring BFS with lines and ZigZag
	public static <E> void BFSWithZigZag(KAryTreeNode<E> root) {
		if(root == null) {
			return;
		}
		Stack<KAryTreeNode<E>> stackOdd = new Stack<KAryTreeNode<E>>();
		Stack<KAryTreeNode<E>> stackEven = new Stack<KAryTreeNode<E>>();

		int currentLineNodeCount = 1;
		int nextLineNodeCount = 0;
		int currentLine = 1;
		stackOdd.push(root);

		while(!stackOdd.isEmpty()||!stackEven.isEmpty()) {
			if(currentLine%2 == 1) {
				KAryTreeNode<E> node = stackOdd.pop();
				System.out.print(node.value + " ");

				if(node.children != null) {
					for(int i=0;i<node.children.length;i++) {
						if(node.children[i] != null) {
							stackEven.push(node.children[i]);
							nextLineNodeCount++;
						}
					}
				}
			} else {
				KAryTreeNode<E> node = stackEven.pop();
				System.out.print(node.value + " ");

				if(node.children != null) {
					for(int i=node.children.length-1;i>=0;i--) {
						if(node.children[i] != null) {
							stackOdd.push(node.children[i]);
							nextLineNodeCount++;
						}
					}
				}
			}

			currentLineNodeCount--;
			if(currentLineNodeCount==0) {
				System.out.println();
				currentLine++;
				currentLineNodeCount = nextLineNodeCount;
				nextLineNodeCount = 0;
			}
		}
	}

	//Print reversed
	public static <E> void reversedBFSWithLine(KAryTreeNode<E> root) {
		if(root == null) {
			return;
		}

		ArrayList<Integer> lineNodeCount = new ArrayList<Integer>();
		Queue<KAryTreeNode<E>> queue = new LinkedList<KAryTreeNode<E>>();
		Stack<KAryTreeNode<E>> stack = new Stack<KAryTreeNode<E>>();
		queue.add(root);
		lineNodeCount.add(1);
		lineNodeCount.add(0);
		int nextLine = 1;
		int currLineNode = 0;

		while(!queue.isEmpty()) {
			KAryTreeNode<E> node = queue.poll();
			stack.push(node);
			currLineNode++;

			for(int i=node.children.length-1;i>=0;i--) {
				if(node.children[i] != null) {
					queue.offer(node.children[i]);
					lineNodeCount.set(nextLine, lineNodeCount.get(nextLine)+1);
				}
			}

			if(currLineNode == lineNodeCount.get(nextLine-1)) {
				nextLine++;
				currLineNode = 0;
				lineNodeCount.add(0);
			}
		}

		int height = lineNodeCount.size()-3; // because there will be two 0s at the tail of line node count list
		while(!stack.isEmpty()) {
			KAryTreeNode<E> node = stack.pop();
			System.out.print(node.value + " ");
			lineNodeCount.set(height, lineNodeCount.get(height)-1);
			if(lineNodeCount.get(height)==0) {
				System.out.println();
				height--;
			}
		}
	}

	public static void main(String[] args) {
		while(true) {// Generate k ary tree 
		KAryTreeNode<Integer> root = KAryTreeUtils.genRandomIntegerKAryTree(8, 20, false);

		// Normal traversal, print all items in one line
		BFS(root);
		System.out.println("=====================");

		// print all items in multi-lines
		BFSWithLine(root);

		// print all items in multi-lines also ZigZag
		System.out.println("=====================");
		BFSWithZigZag(root);

		// print all items reversed
		System.out.println("=====================");
		reversedBFSWithLine(root);
	}
	}
}
