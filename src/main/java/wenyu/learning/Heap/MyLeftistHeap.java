package wenyu.learning.Heap;

/**
 * Null Path Length, npl(X): Any node X to be the length of the shortest path
 * from X to a node without two children the npl of a node with zero or one
 * child is 0, while npl(null) = −1
 * 
 * Important: The leftist heap property is that for every node X in the heap,
 * the null path length of the left child is at least as large as that of the
 * right child.
 */

// MyLeftistHeap class
//
// CONSTRUCTION: with a negative infinity sentinel
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x ) --> Insert x
// Comparable deleteMin( )--> Return and remove smallest item
// Comparable findMin( ) --> Return smallest item
// boolean isEmpty( ) --> Return true if empty; else false
// boolean isFull( ) --> Return false in this implementation
// void makeEmpty( ) --> Remove all items
// void merge( rhs ) --> Absorb rhs into this heap

class LeftHeapNode<E extends Comparable<E>> {
	public E element;
	public LeftHeapNode<E> left;
	public LeftHeapNode<E> right;
	public int npl; //null path length 的缩写，指的是从该结点到达一个没有两个孩子的结点的最短距离（一个孩子的结点或者叶子）

	// Constructors
	public LeftHeapNode(E element) {
		this(element, null, null);
	}

	LeftHeapNode(E element, LeftHeapNode<E> lt, LeftHeapNode<E> rt) {
		this.element = element;
		this.left = lt;
		this.right = rt;
		this.npl = 0;
	}
}

public class MyLeftistHeap<E extends Comparable<E>> implements IMyHeap<E> {
	public static final String MAX_LEFTIST_HEAP = "MAXHEAP";
	public static final String MIN_LEFTIST_HEAP = "MINHEAP";
	
	private final int capacity;
	private int nodeCount = 0;
	private final Object lock = new Object();
	private final String heapType;
	private LeftHeapNode<E> root;
	
	public MyLeftistHeap(int capacity, String heapType) {
		if(capacity < 0) {
			this.capacity = 0;
		} else {
			this.capacity = capacity;
		}
		this.heapType = heapType;
		root = null;
	}
	
	private boolean compare(E item1, E item2) {
		if(MAX_LEFTIST_HEAP.equals(heapType)) {
			return item1.compareTo(item2) > 0;
		} else if(MIN_LEFTIST_HEAP.equals(heapType)) {
			return item1.compareTo(item2) < 0;
		}
		return false;
	}
	
	public void insert(E item) {
		if(item == null) { 
			return;
		}
		
		if(isFull()) {
			System.out.println("Reach maximum capacity...");
			return;
		}
		
		synchronized(lock) {
			root = merge(root, new LeftHeapNode<E>(item));
			nodeCount++;
		}
	}

	public E pop() {
		if(isEmpty()) {
			return null;
		}
		
		E returnItem = root.element;
		root = merge(root.left, root.right);
		
		return returnItem;
	}

	public E peek() {
		if(isEmpty()) {
			return null;
		}
		return root.element;
	}

	public boolean isEmpty() {
		return root == null;
	}

	public boolean isFull() {
		return nodeCount == capacity;
	}

	public int size() {
		return nodeCount;
	}

	/**
	 * Internal static method to merge two roots. Deals with deviant cases and
	 * calls recursive merge1.
	 */
	private LeftHeapNode<E> merge(LeftHeapNode<E> head1, LeftHeapNode<E> head2) {
		if (head1 == null) {
			return head2;
		}
		if (head2 == null) {
			return head1;
		}

		if (compare(head1.element, head2.element)) {
			return merge1(head1, head2);
		} else {
			return merge1(head2, head1);
		}
	}

	/**
	 * Internal static method to merge two roots. Assumes trees are not empty,
	 * and h1's root contains smallest item.
	 */
	private LeftHeapNode<E> merge1(LeftHeapNode<E> head1, LeftHeapNode<E> head2) {
		if (head1.left == null) { 
			// Single node
			// Other fields in head1 already accurate
			head1.left = head2;
		} else {
			head1.right = merge(head1.right, head2);
			if (head1.left.npl < head1.right.npl) {
				LeftHeapNode<E> tmp = head1.left;
				head1.left = head1.right;
				head1.right = tmp;
			}
			head1.npl = head1.right.npl + 1;
		}
		return head1;
	}

	public static void main(String[] args) {
		MyLeftistHeap<Integer> minHeap = new MyLeftistHeap<Integer>(10, MyLeftistHeap.MIN_LEFTIST_HEAP);

		minHeap.insert(6);
		minHeap.insert(1);
		minHeap.insert(9);
		minHeap.insert(2);
		minHeap.insert(7);
		minHeap.insert(5);
		minHeap.insert(3);
		minHeap.insert(8);
		
		System.out.println("MinHeap.pop: " + minHeap.pop());
		System.out.println("MinHeap.pop: " + minHeap.pop());
		System.out.println("MinHeap.pop: " + minHeap.pop());
		System.out.println("MinHeap.pop: " + minHeap.pop());
		System.out.println("MinHeap.pop: " + minHeap.pop());
		System.out.println("MinHeap.pop: " + minHeap.pop());
		System.out.println("MinHeap.pop: " + minHeap.pop());
		System.out.println("MinHeap.pop: " + minHeap.pop());
		
		MyLeftistHeap<Integer> maxHeap = new MyLeftistHeap<Integer>(10, MyLeftistHeap.MAX_LEFTIST_HEAP);
		
		maxHeap.insert(6);
		maxHeap.insert(1);
		maxHeap.insert(9);
		maxHeap.insert(2);
		maxHeap.insert(7);
		maxHeap.insert(5);
		maxHeap.insert(3);
		maxHeap.insert(8);
		
		System.out.println("maxHeap.pop: " + maxHeap.pop());
		System.out.println("maxHeap.pop: " + maxHeap.pop());
		System.out.println("maxHeap.pop: " + maxHeap.pop());
		System.out.println("maxHeap.pop: " + maxHeap.pop());
		System.out.println("maxHeap.pop: " + maxHeap.pop());
		System.out.println("maxHeap.pop: " + maxHeap.pop());
		System.out.println("maxHeap.pop: " + maxHeap.pop());
		System.out.println("maxHeap.pop: " + maxHeap.pop());
	}
}
