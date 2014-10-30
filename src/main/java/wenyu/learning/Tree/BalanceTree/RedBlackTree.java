package wenyu.learning.Tree.BalanceTree;

/*
 *  A red-black tree is a binary search tree with one extra bit of storage per node: its color, 
 *  which can be either RED or BLACK. Red-Black trees ensure that no such path is more than twice 
 *  as long as any other, so that the tree is approximately balanced.
	a)	Every node is either red or black.
	b)	The root is black.
	c)	Every leaf (NIL) is black.
	d)	If a node is red, then both its children are black.
 * @author Wenyu Chang
 */

class RedBlackNode {
	// Constructors
	RedBlackNode(Comparable theElement) {
		this(theElement, null, null);
	}

	RedBlackNode(Comparable theElement, RedBlackNode lt, RedBlackNode rt) {
		element = theElement;
		left = lt;
		right = rt;
		color = RedBlackTree.BLACK;
	}

	// Friendly data; accessible by other package routines
	Comparable element; // The data in the node
	RedBlackNode left; // Left child
	RedBlackNode right; // Right child
	int color; // Color
}

public class RedBlackTree {
	/**
	 * Construct the tree.
	 * 
	 * @param negInf
	 *            a value less than or equal to all others.
	 */
	public RedBlackTree(Comparable negInf) {
		header = new RedBlackNode(negInf);
		header.left = header.right = nullNode;
	}

	/**
	 * Insert into the tree. Does nothing if item already present.
	 * 
	 * @param item
	 *            the item to insert.
	 */
	public void insert(Comparable item) {
		current = parent = grand = header;
		nullNode.element = item;

		while (current.element.compareTo(item) != 0) {
			great = grand;
			grand = parent;
			parent = current;
			current = item.compareTo(current.element) < 0 ? current.left
					: current.right;

			// Check if two red children; fix if so
			if (current.left.color == RED && current.right.color == RED)
				handleReorient(item);
		}

		// Insertion fails if already present
		if (current != nullNode)
			return;
		current = new RedBlackNode(item, nullNode, nullNode);

		// Attach to parent
		if (item.compareTo(parent.element) < 0)
			parent.left = current;
		else
			parent.right = current;
		handleReorient(item);
	}

	/**
	 * Remove from the tree. Not implemented in this version.
	 * 
	 * @param x
	 *            the item to remove.
	 */
	public void remove(Comparable x) {
		System.out.println("Remove is not implemented");
	}

	/**
	 * Find the smallest item the tree.
	 * 
	 * @return the smallest item or null if empty.
	 */
	public Comparable findMin() {
		if (isEmpty())
			return null;

		RedBlackNode itr = header.right;

		while (itr.left != nullNode)
			itr = itr.left;

		return itr.element;
	}

	/**
	 * Find the largest item in the tree.
	 * 
	 * @return the largest item or null if empty.
	 */
	public Comparable findMax() {
		if (isEmpty())
			return null;

		RedBlackNode itr = header.right;

		while (itr.right != nullNode)
			itr = itr.right;

		return itr.element;
	}

	/**
	 * Find an item in the tree.
	 * 
	 * @param x
	 *            the item to search for.
	 * @return the matching item or null if not found.
	 */
	public Comparable find(Comparable x) {
		nullNode.element = x;
		current = header.right;

		for (;;) {
			if (x.compareTo(current.element) < 0)
				current = current.left;
			else if (x.compareTo(current.element) > 0)
				current = current.right;
			else if (current != nullNode)
				return current.element;
			else
				return null;
		}
	}

	/**
	 * Make the tree logically empty.
	 */
	public void makeEmpty() {
		header.right = nullNode;
	}

	/**
	 * Test if the tree is logically empty.
	 * 
	 * @return true if empty, false otherwise.
	 */
	public boolean isEmpty() {
		return header.right == nullNode;
	}

	/**
	 * Print the tree contents in sorted order.
	 */
	public void printTree() {
		if (isEmpty())
			System.out.println("Empty tree");
		else
			printTree(header.right);
	}

	/**
	 * Internal method to print a subtree in sorted order.
	 * 
	 * @param t
	 *            the node that roots the tree.
	 */
	private void printTree(RedBlackNode t) {
		if (t != nullNode) {
			printTree(t.left);
			System.out.println(t.element);
			printTree(t.right);
		}
	}

	/**
	 * Internal routine that is called during an insertion if a node has two red
	 * children. Performs flip and rotations.
	 * 
	 * @param item
	 *            the item being inserted.
	 */
	private void handleReorient(Comparable item) {
		// Do the color flip
		current.color = RED;
		current.left.color = BLACK;
		current.right.color = BLACK;

		if (parent.color == RED) // Have to rotate
		{
			grand.color = RED;
			if ((item.compareTo(grand.element) < 0) != (item.compareTo(parent.element) < 0))
				parent = rotate(item, grand); // Start dbl rotate
			current = rotate(item, great);
			current.color = BLACK;
		}
		header.right.color = BLACK; // Make root black
	}

	/**
	 * Internal routine that performs a single or double rotation. Because the
	 * result is attached to the parent, there are four cases. Called by
	 * handleReorient.
	 * 
	 * @param item
	 *            the item in handleReorient.
	 * @param parent
	 *            the parent of the root of the rotated subtree.
	 * @return the root of the rotated subtree.
	 */
	private RedBlackNode rotate(Comparable item, RedBlackNode parent) {
		if (item.compareTo(parent.element) < 0)
			return parent.left = item.compareTo(parent.left.element) < 0 ? rotateWithLeftChild(parent.left)
					: // LL
					rotateWithRightChild(parent.left); // LR
		else
			return parent.right = item.compareTo(parent.right.element) < 0 ? rotateWithLeftChild(parent.right)
					: // RL
					rotateWithRightChild(parent.right); // RR
	}

	/**
	 * Rotate binary tree node with left child.
	 */
	static RedBlackNode rotateWithLeftChild(RedBlackNode k2) {
		RedBlackNode k1 = k2.left;
		k2.left = k1.right;
		k1.right = k2;
		return k1;
	}

	/**
	 * Rotate binary tree node with right child.
	 */
	static RedBlackNode rotateWithRightChild(RedBlackNode k1) {
		RedBlackNode k2 = k1.right;
		k1.right = k2.left;
		k2.left = k1;
		return k2;
	}

	private RedBlackNode header;
	private static RedBlackNode nullNode;
	static // Static initializer for nullNode
	{
		nullNode = new RedBlackNode(null);
		nullNode.left = nullNode.right = nullNode;
	}

	static final int BLACK = 1; // Black must be 1
	static final int RED = 0;

	// Used in insert routine and its helpers
	private static RedBlackNode current;
	private static RedBlackNode parent;
	private static RedBlackNode grand;
	private static RedBlackNode great;
}
