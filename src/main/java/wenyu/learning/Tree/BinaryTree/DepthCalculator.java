package wenyu.learning.Tree.BinaryTree;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;

public class DepthCalculator {
	
	private static void printDepths(Map<BinaryTreeNode, Integer> depths) {
		
	}
	
	public static <E> int calculateDepth(BinaryTreeNode<E> root, BinaryTreeNode<E> node) {
		if(root==null || node==null) {
			return -1;
		}
		if(root == node) {
			return 0;
		}
		
		int depth = 0;
		int currentLineNodeCount = 1;
		int nextLineNodeCount = 0;
		Queue<BinaryTreeNode<E>> queue = new LinkedList<BinaryTreeNode<E>>();
		queue.offer(root);
		
		while(!queue.isEmpty()) {
			BinaryTreeNode<E> currNode = queue.poll();
			if(currNode.left != null) {
				if(currNode.left == node) {
					return depth+1;
				}
				queue.offer(currNode.left);
				nextLineNodeCount++;
			}
			if(currNode.right != null) {
				if(currNode.right == node) {
					return depth+1;
				}
				queue.offer(currNode.right);
				nextLineNodeCount++;
			}

			currentLineNodeCount--;
			if(currentLineNodeCount==0) {
				depth++;
				currentLineNodeCount = nextLineNodeCount;
				nextLineNodeCount = 0;
			}
		}
		
		return -1;
	}
	
	public static <E> Map<BinaryTreeNode<E>, Integer> calculateDepthForAllNodes(BinaryTreeNode<E> root) {
		if(root == null) {
			return null;
		}
		
		Map<BinaryTreeNode<E>, Integer> depths = new LinkedHashMap<BinaryTreeNode<E>, Integer>();
		int height = 0;
		int currentLineNodeCount = 1;
		int nextLineNodeCount = 0;
		Queue<BinaryTreeNode<E>> queue = new LinkedList<BinaryTreeNode<E>>();
		queue.offer(root);
		
		while(!queue.isEmpty()) {
			BinaryTreeNode<E> node = queue.poll();
			depths.put(node, height);
			
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
		
		return depths;
	}
	
	public static void main(String[] args) {
		BinaryTreeNode<Integer> root = new BinaryTreeNode<Integer>(1);
		BinaryTreeNode<Integer> child1 = new BinaryTreeNode<Integer>(3);
		BinaryTreeNode<Integer> child2 = new BinaryTreeNode<Integer>(8);
		BinaryTreeNode<Integer> child3 = new BinaryTreeNode<Integer>(9);
		root.left = new BinaryTreeNode<Integer>(2);
		root.left.right = child1;
		root.right = new BinaryTreeNode<Integer>(5);
		root.right.left = child2;
		root.right.right = new BinaryTreeNode<Integer>(6);
		root.right.right.left = child3;
		TraversalBFS.traversalWithLine(root, null);
		System.out.println("===================================================================");
		Map<BinaryTreeNode<Integer>, Integer> depths = calculateDepthForAllNodes(root);
		Iterator<Entry<BinaryTreeNode<Integer>, Integer>> it = depths.entrySet().iterator();
		while(it.hasNext()) {
			Entry<BinaryTreeNode<Integer>, Integer> entry = it.next();
			System.out.println("Node ("+entry.getKey().value+"): " + entry.getValue());
			// System.out.println("Node ("+entry.getKey().value+"): " + calculateDepth(root, entry.getKey()));
		}
	}

}
