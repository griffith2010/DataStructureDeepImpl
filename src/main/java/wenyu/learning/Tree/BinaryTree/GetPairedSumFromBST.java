package wenyu.learning.Tree.BinaryTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

/*
 * Given a binary search tree of n nodes, find all the 
 * pair of  nodes whose sum is equal to a given number k.
 * 
 * Logic:
 * 	Solution1: Brute Force O(n^2), Space:O(1)
 * 	Solution2: Binary Search O(nlogn), Space:O(1) 
 * 			   for each node find if exists another 
 *             item value equal to k-node.value
 *  Solution3: Convert to sorted array O(n), Space: O(n)
 *  		   Using in-order traversal
 *  Solution4: Convert to single link. O(n), Space: O(logn) for recursion [need to change tree]
 *  Solution5: in-order traversal and reversed in-order traversal
 *  		   O(n), Space: O(logn) [no need to change tree]
 */
public class GetPairedSumFromBST {
	public static void find_solution5(BinaryTreeNode<Integer> root, int sum) {
		Queue<BinaryTreeNode<Integer>> inOrdQueue = new LinkedList<BinaryTreeNode<Integer>>();
		Queue<BinaryTreeNode<Integer>> reInOrdQueue = new LinkedList<BinaryTreeNode<Integer>>();
		Map<Integer, Boolean> visitedByFirstQueue = new HashMap<Integer, Boolean>();

		boolean hasSuchPair = false;
		boolean next = true;
		boolean reversedNext = true;
		Integer front = null;
		Integer end = null;
		
		// Prepare stacks
		Stack<BinaryTreeNode<Integer>> tmpStack = new Stack<BinaryTreeNode<Integer>>();
		tmpStack.push(root);
		while(!tmpStack.isEmpty()) {
			BinaryTreeNode<Integer> node = tmpStack.peek();
			while(node!=null) {
				tmpStack.push(node.left);
				node = node.left;
			}
			
			tmpStack.pop(); //Pop out null peek value
			if(!tmpStack.isEmpty()) {
				BinaryTreeNode<Integer> tmpNode = tmpStack.pop();
				inOrdQueue.offer(tmpNode);
				tmpStack.push(tmpNode.right);
			}
		}
		
		tmpStack.push(root);
		while(!tmpStack.isEmpty()) {
			BinaryTreeNode<Integer> node = tmpStack.peek();
			while(node!=null) {
				tmpStack.push(node.right);
				node = node.right;
			}
			
			tmpStack.pop(); //Pop out null peek value
			if(!tmpStack.isEmpty()) {
				BinaryTreeNode<Integer> tmpNode = tmpStack.pop();
				reInOrdQueue.offer(tmpNode);
				tmpStack.push(tmpNode.left);
			}
		}
		
		while(!inOrdQueue.isEmpty() && !reInOrdQueue.isEmpty()) {
			front = next? inOrdQueue.poll().value : front;
			visitedByFirstQueue.put(front, true);
			end = reversedNext? reInOrdQueue.poll().value : end;
			if(front!=null && end!=null && front!=end && !visitedByFirstQueue.containsKey(end)) {
				if(front+end == sum) {
					System.out.println("[" + front + "," + end + "]");
					next = true;
					reversedNext = true;
					hasSuchPair = true;
				} else if(front+end < sum) {
					next = true;
					reversedNext = false;
				} else {
					next = false;
					reversedNext = true;
				}
			}
		}
		
		if(!hasSuchPair) {
			System.out.println("Don't has such pair nodes");
		}
	}
	
	public static void main(String[] args) {
		BinaryTreeNode<Integer> root = BSTUtils.genMostBalancedIntegerBST(10);
		TraversalBFS.traversalWithLine(root);
		
		while(true) {
			System.out.println("Input a sum: ");
			Scanner scanner = new Scanner(System.in);
			String input = scanner.nextLine();
			int sum = Integer.parseInt(input);
			find_solution5(root, sum);
		}
	}

}
