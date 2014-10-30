package wenyu.learning.Tree.TrieTree;

import java.util.Stack;

public class TrieTree<T> {
	private final T NULL = null;
	private TrieNode<T> root;
	private int entryNum;
	private final Object lock = new Object();
	
	public TrieTree() {
		root = new TrieNode<T>(NULL);
		entryNum = 0;
	}
	
	public boolean insert(T[] values) {
		synchronized(lock) {
			if(values==null || values.length==0) {
				System.out.println("Insert item is empty...");
				return false;
			}
			
			TrieNode<T> currNode = root;
			for(int i=0;i<values.length;i++) {
				T current = values[i];
				if(current == null) {
					System.out.println("Insert item contains null value...");
					return false;
				}
				
				currNode = currNode.insertChild(current);
				if(i == values.length-1) {
					if (!currNode.isEndMarker()) {
						currNode.setEndMarker(true);
						entryNum++;
					} else {
						System.out.println("Insert item already exists...");
						return false;
					}
				}
			}
			return true;
		}
	}
	
	public boolean find(T[] values) {
		synchronized(lock) {
			if(values==null || values.length==0) {
				System.out.println("Insert item is empty...");
				return false;
			}
			
			TrieNode<T> currNode = root;
			for(int i=0;i<values.length;i++) {
				T current = values[i];
				if(current == null) {
					System.out.println("Insert item contains null value...");
					return false;
				}
				
				currNode = currNode.getChild(current);
				if(currNode == null) {
					System.out.println("Cannot find this item in the Trie Tree...");
					return false;
				} else if(i == values.length-1) {
					if (!currNode.isEndMarker()) {
						return false;
					} else {
						return true;
					}
				}
			}
			return false;
		}
	}
	
	public boolean delete(T[] values) {
		synchronized(lock) {
			if(values==null || values.length==0) {
				System.out.println("Insert item is empty...");
				return false;
			}
			
			Stack<TrieNode<T>> stack = new Stack<TrieNode<T>>();
			TrieNode<T> currNode = root;
			stack.push(currNode);
			for(int i=0;i<values.length;i++) {
				T current = values[i];
				if(current == null) {
					System.out.println("Deletion item contains null value...");
					return false;
				}
				
				currNode = currNode.getChild(current);
				if(currNode == null) {
					System.out.println("Cannot find this item in the Trie Tree...");
					return false;
				}  else if(i == values.length-1) {
					if (!currNode.isEndMarker()) {
						System.out.println("Cannot find this item in the Trie Tree...");
						return false;
					} else {
						currNode.setEndMarker(false);
					}
				}
				stack.add(currNode);
			}
			
			while(!stack.isEmpty()) {
				if(stack.peek() == root) {
					return true;
				}
				
				TrieNode<T> currDelNode = stack.pop();
				if(currDelNode.getChildrenSize() == 0) {
					TrieNode<T> parent = stack.peek();
					parent.removeChild(currDelNode.getValue());
				} else {
					return true;
				}
			}
			
			return false;
		}
	}
}
