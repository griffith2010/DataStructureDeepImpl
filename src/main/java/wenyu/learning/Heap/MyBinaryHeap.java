package wenyu.learning.Heap;
// MyBinaryHeap class

public class MyBinaryHeap<E extends Comparable<E>> implements IMyHeap<E> {
	public static final String MAX_HEAP = "MAXHEAP";
	public static final String MIN_HEAP = "MINHEAP";
	
	private final int capacity;
	private final Object lock = new Object();
	private final E[] array;
	private int arrayCount = 0;
	private final String heapType;
	
	@SuppressWarnings("unchecked")
	public MyBinaryHeap(int capacity, String heapType) {
		if(capacity < 0) {
			this.capacity = 0;
		} else {
			this.capacity = capacity;
		}
		this.heapType = heapType;
		array = (E[]) new Comparable[this.capacity];
	}
	
	private boolean compare(E item1, E item2) {
		if(MAX_HEAP.equals(heapType)) {
			return item1.compareTo(item2) > 0;
		} else if(MIN_HEAP.equals(heapType)) {
			return item1.compareTo(item2) < 0;
		}
		return false;
	}
	
	public void insert(E item) {
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
            for(; hole>1&&compare(item, array[hole/2-1]); hole=hole/2)
                array[hole-1] = array[hole/2-1];
            array[hole-1] = item;
			arrayCount++;
		}
	}
	
	public E pop() {
		if(isEmpty()) {
			return null;
		}
		
		synchronized(lock) {
			E returnItem = array[0];
			array[0] = array[size()-1];
			array[size()-1] = null;
			arrayCount--;
			
			// Percolate Down
			int hole = 0+1;
			int child = 0;
			E current = array[hole-1];
			for(;hole*2<=size();hole=child) {
				child = hole*2;
				if(child!=size()&&!compare(array[child-1], array[child])) {
					child++;
				}
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
	
	public E peek() {
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
	
	public static void main(String[] args) {
		MyBinaryHeap<Integer> minHeap = new MyBinaryHeap<Integer>(10, MyBinaryHeap.MIN_HEAP);

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
		
		MyBinaryHeap<Integer> maxHeap = new MyBinaryHeap<Integer>(10, MyBinaryHeap.MAX_HEAP);
		
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
