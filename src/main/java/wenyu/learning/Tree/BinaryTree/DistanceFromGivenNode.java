package wenyu.learning.Tree.BinaryTree;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.Map.Entry;

/*
 * Get all the other nodes' distance from the given node
 */

/*
 * Solution1 Logic:
 * 		1. Calculate distance of nodes which are the given node's children.
 * 		2. Form parent stack which is the path from root to given node.
 * 		3. Calculate other nodes' distance by go through above parent nodes and calculate its sub-nodes' depth. Then add offset to these depths.
 */
class Solution1 {
	private static <E> Map<BinaryTreeNode<E>, Integer> calculateDepthForAllNodes(BinaryTreeNode<E> root, BinaryTreeNode<E> exceptNode, int offset) {
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
			currentLineNodeCount--;
			if(node != exceptNode) {
				depths.put(node, height+offset);
				
				if(node.left != null) {
					queue.offer(node.left);
					nextLineNodeCount++;
				}
				if(node.right != null) {
					queue.offer(node.right);
					nextLineNodeCount++;
				}
			}

			if(currentLineNodeCount==0) {
				height++;
				currentLineNodeCount = nextLineNodeCount;
				nextLineNodeCount = 0;
			}
		}
		
		return depths;
	}
	
	private static <E> boolean getParentStack(BinaryTreeNode<E> node, BinaryTreeNode<E> givenNode, Stack<BinaryTreeNode<E>> path) {
		if(node != null) {
			if(node == givenNode) {
				return true;
			} else {
				path.push(node);
				boolean finish = getParentStack(node.left, givenNode, path);
				if(!finish) {
					finish = getParentStack(node.right, givenNode, path);
				}
				if(!finish) {
					path.pop();
				}
				return finish;
			}
		}
		return false;
	}
	
	public static <E> Map<BinaryTreeNode<E>, Integer> calculateDistance(BinaryTreeNode<E> root, BinaryTreeNode<E> node) {
		// Step 1: calculate distance of nodes which are the given node's children
		Map<BinaryTreeNode<E>, Integer> subNodesDistance = calculateDepthForAllNodes(node, null, 0);
		
		// Step 2: Form parent stack which is the path from root to given node
		Stack<BinaryTreeNode<E>> path = new Stack<BinaryTreeNode<E>>();
		getParentStack(root, node, path);
		// BuildinStack.printStack(path);
		
		// Step 3: calculate other nodes' distance
		int offset = 0;
		BinaryTreeNode<E> except = node;
		while(!path.isEmpty()) {
			BinaryTreeNode<E> currParent = path.pop();
			offset++;
			Map<BinaryTreeNode<E>, Integer> tmpSubNodesDistance = calculateDepthForAllNodes(currParent, except, offset);
			subNodesDistance.putAll(tmpSubNodesDistance);
			except = currParent;
		}
		
		return subNodesDistance;
	}
}




/* Solution2 Logic:
 * 	1. Find all the node's parent
 *  2. Start from the given node as root, 
 *     and then breath first traversal to 
 *     calculate the height
 */
class Solution2 {
	private static <E> Map<BinaryTreeNode<E>, BinaryTreeNode<E>> getParents(BinaryTreeNode<E> root) {
		Map<BinaryTreeNode<E>, BinaryTreeNode<E>> parents = new HashMap<BinaryTreeNode<E>, BinaryTreeNode<E>>();
		Stack<BinaryTreeNode<E>> stack = new Stack<BinaryTreeNode<E>>();
		parents.put(root, null);
		stack.push(root);
		while(!stack.isEmpty()) {
			BinaryTreeNode<E> node = stack.pop();
			if(node.right!=null) {
				parents.put(node.right, node);
				stack.push(node.right);
			}
			if(node.left!=null) {
				parents.put(node.left, node);
				stack.push(node.left);
			}
		}
		return parents;
	}
	
	private static <E> Map<BinaryTreeNode<E>, Integer> calculateDistance(BinaryTreeNode<E> node, Map<BinaryTreeNode<E>, BinaryTreeNode<E>> parents) {
		if(node == null) {
			return null;
		}

		Map<BinaryTreeNode<E>, Integer> distances = new LinkedHashMap<BinaryTreeNode<E>, Integer>();
		int height = 0;
		int currentLineNodeCount = 1;
		int nextLineNodeCount = 0;
		Queue<BinaryTreeNode<E>> queue = new LinkedList<BinaryTreeNode<E>>();
		queue.offer(node);
		
		while(!queue.isEmpty()) {
			BinaryTreeNode<E> tmpNode = queue.poll();
			distances.put(tmpNode, height);
			
			if(tmpNode.left!=null && parents.containsKey(tmpNode.left)) {
				queue.offer(tmpNode.left);
				nextLineNodeCount++;
			}
			if(tmpNode.right != null && parents.containsKey(tmpNode.right)) {
				queue.offer(tmpNode.right);
				nextLineNodeCount++;
			}
			if(parents.containsKey(tmpNode) && parents.containsKey(parents.get(tmpNode))) {
				BinaryTreeNode<E> parent = parents.get(tmpNode);
				queue.offer(parent);
				nextLineNodeCount++;
			}

			currentLineNodeCount--;
			parents.remove(tmpNode);
			if(currentLineNodeCount==0) {
				height++;
				currentLineNodeCount = nextLineNodeCount;
				nextLineNodeCount = 0;
			}
		}
		return distances;
	}
	
	public static <E> Map<BinaryTreeNode<E>, Integer> calculateDistance(BinaryTreeNode<E> root, BinaryTreeNode<E> node) {
		Map<BinaryTreeNode<E>, BinaryTreeNode<E>> parents = getParents(root);
		return calculateDistance(node, parents);
	}
}

public class DistanceFromGivenNode {
	private static <E> void printDistance(Map<BinaryTreeNode<E>, Integer> distances) {
		Iterator<Entry<BinaryTreeNode<E>, Integer>> it = distances.entrySet().iterator();
		while(it.hasNext()) {
			Entry<BinaryTreeNode<E>, Integer> entry = it.next();
			System.out.println("Node"+entry.getKey().value+": " + entry.getValue());
		}
	}

	
	public static <E> void calculateDistance(BinaryTreeNode<E> root, BinaryTreeNode<E> node) {
		Map<BinaryTreeNode<E>, Integer> result_solution1 = Solution1.calculateDistance(root, node);
		Map<BinaryTreeNode<E>, Integer> result_solution2 = Solution2.calculateDistance(root, node);
		
		printDistance(result_solution1);
		System.out.println("=================================================");
		printDistance(result_solution2);
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
		
//		root.left = new TreeNode(2);
//		root.right = child1;
//		root.right.left = child2;
//		root.right.right = new TreeNode(6);
//		root.right.right.left = child3;
		
		calculateDistance(root, child3);
	}
}
