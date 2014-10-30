package wenyu.learning.Tree.BalanceTree;

import wenyu.learning.Tree.BinaryTree.BinaryTreeNode;

/**
 * @author Wenyu
 * Treap (tree + heap) class
 * 		1. For node V is the left child of U, then key[V]<key[U];
 * 		2. For node V is the right child of U, then key[V]>key[U];
 * 		3. for node V is the child of U, priority[V]<priority[U];
 * 		4. The excepted height of Treap should be O(logn) 
 */

class TreapNode<E extends Comparable<E>> extends BinaryTreeNode<E> {
	/*
	 * extends BinaryTreeNode
	 * properties:
	 * 1. public E value;
	 * 2. public BinaryTreeNode<E> left;
	 * 3. public BinaryTreeNode<E> right;
	 */
	
	// Add one more property
	int priority;
	public TreapNode(E element) {
		super(element);
	}
}

public class MyTreap<E extends Comparable<E>> {
	private TreapNode<E> root;
	public MyTreap() {
		root = null;
	}
	public TreapNode<E> getRoot() {
		return root;
	}

	/**
	 * Insert into tree.
	 * @param x the item to remove.
	 */
	public void insert(E x) {
		root = insert(x, root);
	}

	/**
	 * Remove from the tree. Does nothing if x is not found.
	 * @param x the item to remove.
	 */
	public void remove(E x) {
		root = remove(x, root);
	}

	/**
	 * Find the smallest item in the tree.
	 * @return the smallest item, or null if empty.
	 */
	public E findMin() {
		if (isEmpty())
			return null;

		TreapNode<E> ptr = root;
		while (ptr.left != null) {
			ptr = (TreapNode<E>) ptr.left;
		}

		return ptr.value;
	}

	/**
	 * Find the largest item in the tree.
	 * @return the largest item, or null if empty.
	 */
	public E findMax() {
		if (isEmpty())
			return null;

		TreapNode<E> ptr = root;
		while (ptr.right != null)
			ptr = (TreapNode<E>) ptr.right;

		return ptr.value;
	}

	/**
	 * Find an item in the tree.
	 * @param x the item to search for.
	 * @return the matching item, or null if not found.
	 */
	public E find(E x) {
		TreapNode<E> current = root;

		while(current != null) {
			if (x.compareTo(current.value) < 0) {
				current = (TreapNode<E>) current.left;
			} else if (x.compareTo(current.value) > 0) {
				current = (TreapNode<E>) current.right;
			} else {
				return current.value;
			}
		}
		
		return null;
	}

	/**
	 * Make the tree logically empty.
	 */
	public void makeEmpty() {
		root = null;
	}

	/**
	 * Test if the tree is logically empty.
	 * @return true if empty, false otherwise.
	 */
	public boolean isEmpty() {
		return root == null;
	}

	/**
	 * Internal method to insert into a subtree.
	 * @param x the item to insert.
	 * @param t the node that roots the tree.
	 * @return the new root.
	 */
	private TreapNode<E> insert(E x, TreapNode<E> t) {
		if (t == null) {
			t = new TreapNode<E>(x);
		} else if (x.compareTo(t.value) < 0) {
			t.left = (TreapNode<E>) insert(x, (TreapNode<E>) t.left);
			if (((TreapNode<E>)t.left).priority < t.priority) {
				t = rotateWithLeftChild(t);
			}
		} else if (x.compareTo(t.value) > 0) {
			t.right = (TreapNode<E>) insert(x, (TreapNode<E>) t.right);
			if (((TreapNode<E>)t.left).priority < t.priority)
				t = rotateWithRightChild(t);
		}
		// Otherwise, it's a duplicate; do nothing

		return t;
	}

	/**
	 * Internal method to remove from a subtree.
	 * @param x the item to remove.
	 * @param t the node that roots the tree.
	 * @return the new root.
	 */
	private TreapNode<E> remove(E x, TreapNode<E> t) {
		if (t != null) {
			if (x.compareTo(t.value) < 0) {
				t.left = (TreapNode<E>) remove(x, (TreapNode<E>) t.left);
			} else if (x.compareTo(t.value) > 0) {
				t.right = (TreapNode<E>) remove(x, (TreapNode<E>) t.right);
			} else {
				// Match found
				if (((TreapNode<E>)t.left).priority < ((TreapNode<E>)t.right).priority) {
					t = rotateWithLeftChild(t);
				} else {
					t = rotateWithRightChild(t);
				}
				
				if (t != null) { // Continue on down
					t = remove(x, t);
				}
			}
		}
		return t;
	}

	/**
	 * Rotate binary tree node with left child.
	 */
	private TreapNode<E> rotateWithLeftChild(TreapNode<E> k2) {
		TreapNode<E> k1 = (TreapNode<E>) k2.left;
		k2.left = k1.right;
		k1.right = k2;
		return k1;
	}

	/**
	 * Rotate binary tree node with right child.
	 */
	private TreapNode<E> rotateWithRightChild(TreapNode<E> k1) {
		TreapNode<E> k2 = (TreapNode<E>) k1.right;
		k1.right = k2.left;
		k2.left = k1;
		return k2;
	}
}
