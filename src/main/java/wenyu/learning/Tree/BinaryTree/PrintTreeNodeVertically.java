package wenyu.learning.Tree.BinaryTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.TreeMap;

/*
 * Given a binary tree. I need to print the nodes in vertical line. 
 * For example:
               5
            /     \
           3       7
          / \     / \
		 1   4   6   8
	        /     \   \
		   2	   9  10
 * Answer would be – 1 2 3 5 4 6 9 7 8 10
 * 
 * Logic:
 * 	Each node is on a vertical line and can be assigned a number. 
 *  The line corresponding to the root being zero. If a node has 
 *  the vertical line number v, then its left child has number 
 *  v-1, and right child has v+1. So we can do a breadth first 
 *  search, collecting all nodes with the same vertical line in 
 *  a list. 
 */
public class PrintTreeNodeVertically {
	
	public static <E> void printVertically(BinaryTreeNode<E> root) {
		if(root == null) {
			return;
		}
		
		Map<Integer, ArrayList<BinaryTreeNode<E>>> indexMap = new TreeMap<Integer, ArrayList<BinaryTreeNode<E>>>();
		Map<BinaryTreeNode<E>, Integer> nodeMap = new HashMap<BinaryTreeNode<E>, Integer>();
		
		// Using BFS to initial index of each node
		Queue<BinaryTreeNode<E>> queue = new LinkedList<BinaryTreeNode<E>>();
		queue.add(root);
		ArrayList<BinaryTreeNode<E>> list = new ArrayList<BinaryTreeNode<E>>();
		list.add(root);
		indexMap.put(0, list);
		nodeMap.put(root, 0);
		
		while(!queue.isEmpty()) {
			BinaryTreeNode<E> node = queue.poll();
			int vLevel = nodeMap.get(node);
			if(node.left!=null) {
				queue.offer(node.left);
				if(indexMap.containsKey(vLevel-1)) {
					indexMap.get(vLevel-1).add(node.left);
				} else {
					list = new ArrayList<BinaryTreeNode<E>>();
					list.add(node.left);
					indexMap.put(vLevel-1, list);
				}
				nodeMap.put(node.left, vLevel-1);
			}
			
			if(node.right!=null) {
				queue.offer(node.right);
				if(indexMap.containsKey(vLevel+1)) {
					indexMap.get(vLevel+1).add(node.right);
				} else {
					list = new ArrayList<BinaryTreeNode<E>>();
					list.add(node.right);
					indexMap.put(vLevel+1, list);
				}
				nodeMap.put(node.right, vLevel+1);
			}
		}
		
		// print according to the vertical level index
		Iterator<Entry<Integer, ArrayList<BinaryTreeNode<E>>>> it = indexMap.entrySet().iterator();
		while(it.hasNext()) {
			Entry<Integer, ArrayList<BinaryTreeNode<E>>> entry = it.next();
			ArrayList<BinaryTreeNode<E>> nodesOnSameVLevel = entry.getValue();
			for(BinaryTreeNode<E> node : nodesOnSameVLevel) {
				System.out.print(node.value + " ");
			}
		}
	}
	
	public static void main(String[] args) {
		BinaryTreeNode<Integer> root = BSTUtils.genMostBalancedIntegerBST(8);
		TraversalBFS.traversalWithLine(root);
		
		System.out.println("===========================");
		printVertically(root);
	}

}
