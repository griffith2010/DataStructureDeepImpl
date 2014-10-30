package wenyu.learning.Tree.BinaryTree;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

/**
 *  Given an input like: 
	[2, 4] 
	[1, 2] 
	[3, 6] 
	[1, 3] 
	[2, 5] 
	Use it to reconstruct this binary tree:
					 1
	              2      3
	           4   5  6
	Note that: 
	a) The first number in each line is a parent. 
	b) Second number is a child. 
	c) The left child always shows up in the input before the right child. 
	Return root.
 */
public class FormBinaryTreeByPairedInput {
	public static <E> BinaryTreeNode<E> formTree(E[][] pairs) throws Exception {
		HashMap<E, BinaryTreeNode<E>> map = new HashMap<E, BinaryTreeNode<E>>();
		HashMap<E, Boolean> hasParentMap = new HashMap<E, Boolean>();
		
		for(int i=0;i<pairs.length;i++) {
			E parent = pairs[i][0];
			E child = pairs[i][1];
			
			if(!map.containsKey(parent)) {
				BinaryTreeNode<E> node = new BinaryTreeNode<E>(parent);
				map.put(parent, node);
				hasParentMap.put(parent, false);
			}
			
			if(!map.containsKey(child)) {
				BinaryTreeNode<E> node = new BinaryTreeNode<E>(child);
				map.put(child, node);
				hasParentMap.put(child, true);
			}
			
			BinaryTreeNode<E> parentNode = map.get(parent);
			BinaryTreeNode<E> childNode = map.get(child);
			if(parentNode.left == null) {
				parentNode.left = childNode;
			} else if(parentNode.right == null){
				parentNode.right = childNode;
			} else {
				throw new Exception("Node" + parent + " already has two children.");
			}
		}
		
		Set<Entry<E, Boolean>> set = hasParentMap.entrySet();
		for(Entry<E, Boolean> entry:set) {
			Boolean hasParent = entry.getValue();
			if(!hasParent) {
				return map.get(entry.getKey());
			}
			
		}
		return null;
	}
	
	public static void main(String[] args) throws Exception {
		Integer[][] nodes = { { 2, 4 }, { 1, 2 }, { 3, 6 }, { 1, 3 }, { 2, 5 } };
		
		BinaryTreeNode<Integer> root = formTree(nodes);
		System.out.println("Root is: " + root.value + "\n");
		System.out.println("Tree is: ");
		TraversalBFS.traversalWithLine(root);
	}

}
