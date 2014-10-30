package wenyu.learning.Heap;

// MyDHeap class
//
// CONSTRUCTION: with optional capacity (that defaults to 100)
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// Comparable deleteMin( )--> Return and remove smallest item
// Comparable findMin( )  --> Return smallest item
// boolean isEmpty( )     --> Return true if empty; else false
// boolean isFull( )      --> Return true if full; else false
// int findChild( )		  --> Find the child position to insert new node. 
//							  which is the only different from binaryHeap.

public class MyDHeap<T extends Comparable<T>> {
	public static final String MAX_HEAP = "MAXHEAP";
	public static final String MIN_HEAP = "MINHEAP";
	
	private final int d;
	private final int capacity;
	private final Object lock = new Object();
	private final T[] array;
	private int arrayCount = 0;
	private final String heapType;
	
	@SuppressWarnings("unchecked")
	public MyDHeap(int d, int capacity, String heapType) {
		if(d<=0) {
			this.d = 1;
		} else {
			this.d = d;
		}
		if(capacity < 0) {
			this.capacity = 0;
		} else {
			this.capacity = capacity;
		}
		this.heapType = heapType;
		array = (T[]) new Comparable[this.capacity];
	}
	
	private boolean compare(T item1, T item2) {
		if(MAX_HEAP.equals(heapType)) {
			return item1.compareTo(item2) > 0;
		} else if(MIN_HEAP.equals(heapType)) {
			return item1.compareTo(item2) < 0;
		}
		return false;
	}
	
	public void insert(T item) {
		if(isFull()) {
			System.out.println("Reach maximum capacity...");
			return;
		}
		
		synchronized(lock) {
			if(isEmpty()) {
				array[0] = item;
				arrayCount++;
				return;
			}
			
			// Percolate up
            int hole = size()+1;
            for(; hole>1&&compare(item, array[(hole-2+d)/d-1]); hole=(hole-2+d)/d)
                array[hole-1] = array[(hole-2+d)/d-1];
            array[hole-1] = item;
			arrayCount++;
		}
	}
	
	public T pop() {
		if(isEmpty()) {
			return null;
		}
		
		synchronized(lock) {
			T returnItem = array[0];
			array[0] = array[size()-1];
			array[size()-1] = null;
			arrayCount--;
			
			// Percolate Down
			int hole = 0+1;
			int child = 0;
			T current = array[hole-1];
			for(;(d*hole-d+2)<=size();hole=child) {
				child = d*hole-d+2;
				// Find the child position to insert new node.
				child = findChild(child);
				
				if(!compare(current, array[child-1])) {
					array[hole-1] = array[child-1];
				} else {
					break;
				}
			}
			array[hole-1] = current;
			return returnItem;
		}
		
	}
	
	public T peek() {
		if(isEmpty()) {
			return null;
		}
		
		synchronized(lock) {
			return array[0];
		}
		
	}
	
	public int size() {
		return arrayCount;
	}
	
	public boolean isEmpty() {
		synchronized(lock) {
			return arrayCount == 0;
		}
	}
	
	public boolean isFull() {
		synchronized(lock) {
			return arrayCount == capacity;
		}
	}
	
	private int findChild(int child) {
		int returnChild = child;
		for(int i=0;i<d&&returnChild!=size()&&(child+i)!=size();i++) {
			if(!compare(array[returnChild-1], array[child-1+i])) {
				returnChild = child+i;
			}
		}
			
		return returnChild;
	}
	
	public static void main(String[] args) {
		MyDHeap<Integer> minHeap = new MyDHeap<Integer>(8, 10, MyDHeap.MIN_HEAP);

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
		
		MyDHeap<Integer> maxHeap = new MyDHeap<Integer>(6, 10, MyDHeap.MAX_HEAP);
		
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
