package wenyu.learning.Heap;

import java.lang.reflect.Array;

/**
 * 
 * Details: http://en.wikipedia.org/wiki/Binomial_heap
 * Binomial queues differ from all the priority queue implementations that we have seen in
 * that a binomial queue is not a heap-ordered tree but rather a collection of heap-ordered
 * trees, known as a forest. Each of the heap-ordered trees is of a constrained form known
 * as a binomial tree
 * 
 * 
 * A binomial tree is defined recursively:
 * 1. A binomial tree of order 0 is a single node
 * 2. A binomial tree of order k has a root node whose 
 *    children are roots of binomial trees of orders k−1, 
 *    k−2, ..., 2, 1, 0 (in this order).
 *    
 *    0     1          2                       3
 *    O     O	       O               --------O
 *    		|         / \             /       / \
 *    		|        /   \           O       O   O
 *    		O       O     O         / \      |
 *    				|			   /   \     |
 *    				|			  O		O    O
 *    				O			  |
 *    							  |
 *    							  O
 */

//MyBinomialQueue class
//
// CONSTRUCTION: with a negative infinity sentinel
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// Comparable deleteMin( )--> Return and remove smallest item
// Comparable findMin( )  --> Return smallest item
// boolean isEmpty( )     --> Return true if empty; else false
// boolean isFull( )      --> Return true if full; else false
// void merge( rhs )      --> Absord rhs into this heap

class BinomialNode<E extends Comparable<E>> {
	E element;
    BinomialNode<E> leftistChild;
    BinomialNode<E> nextBrother;
	
	public BinomialNode(E element ) {
        this(element, null, null);
    }

    private BinomialNode(E element, BinomialNode<E> lt, BinomialNode<E> nt) {
        this.element = element;
        leftistChild = lt;
        nextBrother = nt;
    }
}

public class MyBinomialHeap<E extends Comparable<E>> implements IMyHeap<E> {
	public static final String MAX_BINOMIAL_HEAP = "MAXHEAP";
	public static final String MIN_BINOMIAL_HEAP = "MINHEAP";
	
	private final int capacity;
	private int nodeCount = 0;
	private final Object lock = new Object();
	private final String heapType;
	
	private final BinomialNode<E>[] binomialNodeList;
	
	@SuppressWarnings("unchecked")
	public MyBinomialHeap(int capacity, String heapType) {
		if(capacity < 0) {
			this.capacity = 0;
		} else {
			this.capacity = capacity;
		}
		this.heapType = heapType;
		
		int listLen = 1;
		while(capacity > 1) {
			listLen++;
			capacity = capacity>>1;
		}
		binomialNodeList = (BinomialNode<E>[]) Array.newInstance(BinomialNode.class, listLen);
	}
	
	private boolean compare(E item1, E item2) {
		if(MAX_BINOMIAL_HEAP.equals(heapType)) {
			return item1.compareTo(item2) > 0;
		} else if(MIN_BINOMIAL_HEAP.equals(heapType)) {
			return item1.compareTo(item2) < 0;
		}
		return false;
	}
	
	private void merge(MyBinomialHeap<E> heap){
		if (heap==null || this==heap)
			return;
		if (nodeCount + heap.nodeCount > capacity) {
			System.out.println("Reach maximum capacity...");
			return;
		}
		
		nodeCount += heap.nodeCount;

		BinomialNode<E> carry = null;
		for (int i=0,j=1; j<=nodeCount; i++,j*=2) {
			BinomialNode<E> t1 = binomialNodeList[i];
			BinomialNode<E> t2 = heap.binomialNodeList[i];

			int whichCase = t1 == null ? 0 : 1;
			whichCase += t2 == null ? 0 : 2;
			whichCase += carry == null ? 0 : 4;

			switch (whichCase) {
				case 0: /* No trees */
				case 1: /* Only this */
					break;
				case 2: /* Only heap */
					binomialNodeList[i] = t2;
					heap.binomialNodeList[i] = null;
					break;
				case 4: /* Only carry */
					binomialNodeList[i] = carry;
					carry = null;
					break;
				case 3: /* this and heap */
					if(compare(t1.element, t2.element)) {
						carry = combineTrees(t1, t2);
					} else {
						carry = combineTrees(t2, t1);
					}
					binomialNodeList[i] = heap.binomialNodeList[i] = null;
					break;
				case 5: /* this and carry */
					if(compare(t1.element, carry.element)) {
						carry = combineTrees(t1, carry);
					} else {
						carry = combineTrees(carry, t1);
					}
					binomialNodeList[i] = null;
					break;
				case 6: /* heap and carry */
					if(compare(t2.element, carry.element)) {
						carry = combineTrees(t2, carry);
					} else {
						carry = combineTrees(carry, t2);
					}
					heap.binomialNodeList[i] = null;
					break;
				case 7: /* All three */
					binomialNodeList[i] = carry;
					if(compare(t1.element, t2.element)) {
						carry = combineTrees(t1, t2);
					} else {
						carry = combineTrees(t2, t1);
					}
					heap.binomialNodeList[i] = null;
					break;
			}
		}

		for (int k = 0; k < heap.binomialNodeList.length; k++)
			heap.binomialNodeList[k] = null;
		heap.nodeCount = 0;
	}
	
	/**
     * Return the result of merging equal-sized t1 and t2.
     */
    private BinomialNode<E> combineTrees(BinomialNode<E> t1, BinomialNode<E> t2) {
        t2.nextBrother = t1.leftistChild;
        t1.leftistChild = t2;
        return t1;
    }
	
    /**
     * Find index of tree containing the smallest item in the priority queue.
     * The priority queue must not be empty.
     * @return the index of tree containing the smallest item.
     */
	private int findPopIndex() {
		int minIndex = -1;
		for(int i=0; i<binomialNodeList.length; i++) {
			if(binomialNodeList[i] == null) {
				continue;
			}
			if(minIndex<0) {
				minIndex = i;
			}
			if(compare(binomialNodeList[i].element, binomialNodeList[minIndex].element)) {
				minIndex = i;
			}
		}
		
		return minIndex;
	}
    
	public void insert(E item) {
		if(item == null) { 
			return;
		}
		
		synchronized(lock) {
			MyBinomialHeap<E> oneItem = new MyBinomialHeap<E>(capacity, heapType);
            oneItem.nodeCount = 1;
            oneItem.binomialNodeList[0] = new BinomialNode<E>(item);
            merge(oneItem);
		}
	}

	public E pop() {
		if(isEmpty()) {
			return null;
		}
		synchronized(lock) {
			if(isEmpty()) {
				return null;
			}

            int minIndex = findPopIndex();
            E minItem = binomialNodeList[minIndex].element;

            BinomialNode<E> deletedTree = binomialNodeList[minIndex].leftistChild;

            MyBinomialHeap<E> deletedQueue = new MyBinomialHeap<E>(capacity, heapType);
            deletedQueue.nodeCount = (1<<minIndex)-1;
			for (int j=minIndex-1; j>=0; j--) {
				deletedQueue.binomialNodeList[j] = deletedTree;
				deletedTree = deletedTree.nextBrother;
				deletedQueue.binomialNodeList[j].nextBrother = null;
			}

			binomialNodeList[minIndex] = null;
			nodeCount -= deletedQueue.nodeCount + 1;

			merge(deletedQueue);
			return minItem;
		}
	}

	public E peek() {
		if(isEmpty()) {
			return null;
		}
		synchronized(lock) {
			return binomialNodeList[findPopIndex()].element;
		}
	}

	public boolean isEmpty() {
		return nodeCount == 0;
	}

	public boolean isFull() {
		return nodeCount == capacity;
	}

	public int size() {
		return nodeCount;
	}
	
	public static void main(String[] args) {
		MyBinomialHeap<Integer> minHeap = new MyBinomialHeap<Integer>(70, MyBinomialHeap.MIN_BINOMIAL_HEAP);

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
		
		MyBinomialHeap<Integer> maxHeap = new MyBinomialHeap<Integer>(70, MyBinomialHeap.MAX_BINOMIAL_HEAP);
		
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
