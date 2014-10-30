package wenyu.learning.Heap;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyIndexMaxHeap<Key extends Comparable<Key>> implements Iterable<Integer> {
    private int N;           // number of elements on PQ
    private int[] pq;        // binary heap using 1-based indexing
    private int[] qp;        // inverse of pq - qp[pq[i]] = pq[qp[i]] = i
    private Key[] keys;      // keys[i] = priority of i

    @SuppressWarnings("unchecked")
	public MyIndexMaxHeap(int NMAX) {
        keys = (Key[]) new Comparable[NMAX + 1];    // make this of length NMAX??
        pq   = new int[NMAX + 1];
        qp   = new int[NMAX + 1];                   // make this of length NMAX??
        for (int i = 0; i <= NMAX; i++) qp[i] = -1;
    }

    public boolean isEmpty() { return N == 0; }

    public boolean contains(int i) {
        return qp[i] != -1;
    }

    public int size() {
        return N;
    }

    public void insert(int i, Key key) {
        if (contains(i)) throw new IllegalArgumentException("index is already in the priority queue");
        N++;
        qp[i] = N;
        pq[N] = i;
        keys[i] = key;
        swim(N);
    }

    public int maxIndex() { 
        if (N == 0) throw new NoSuchElementException("Priority queue underflow");
        return pq[1];
    }

    public Key maxKey() { 
        if (N == 0) throw new NoSuchElementException("Priority queue underflow");
        return keys[pq[1]];
    }

    public int delMax() { 
        if (N == 0) throw new NoSuchElementException("Priority queue underflow");
        int min = pq[1];        
        exch(1, N--); 
        sink(1);
        qp[min] = -1;            // delete
        keys[pq[N+1]] = null;    // to help with garbage collection
        pq[N+1] = -1;            // not needed
        return min; 
    }

    public Key keyOf(int i) {
        if (!contains(i)) throw new NoSuchElementException("index is not in the priority queue");
        else return keys[i];
    }
    
    public void changeKey(int i, Key key) {
        if (!contains(i)) throw new NoSuchElementException("index is not in the priority queue");
        keys[i] = key;
        swim(qp[i]);
        sink(qp[i]);
    }

    public void increaseKey(int i, Key key) {
        if (!contains(i)) throw new NoSuchElementException("index is not in the priority queue");
        if (keys[i].compareTo(key) >= 0) throw new IllegalArgumentException("Calling increaseKey() with given argument would not strictly increase the key");


        keys[i] = key;
        swim(qp[i]);
    }

    public void decreaseKey(int i, Key key) {
        if (!contains(i)) throw new NoSuchElementException("index is not in the priority queue");
        if (keys[i].compareTo(key) <= 0) throw new IllegalArgumentException("Calling decreaseKey() with given argument would not strictly decrease the key");

        keys[i] = key;
        sink(qp[i]);
    }

    public void delete(int i) {
        if (!contains(i)) throw new NoSuchElementException("index is not in the priority queue");
        int index = qp[i];
        exch(index, N--);
        swim(index);
        sink(index);
        keys[i] = null;
        qp[i] = -1;
    }

    private boolean less(int i, int j) {
        return keys[pq[i]].compareTo(keys[pq[j]]) < 0;
    }

    private void exch(int i, int j) {
        int swap = pq[i]; pq[i] = pq[j]; pq[j] = swap;
        qp[pq[i]] = i; qp[pq[j]] = j;
    }

    private void swim(int k)  {
        while (k > 1 && less(k/2, k)) {
            exch(k, k/2);
            k = k/2;
        }
    }

    private void sink(int k) {
        while (2*k <= N) {
            int j = 2*k;
            if (j < N && less(j, j+1)) j++;
            if (!less(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    public Iterator<Integer> iterator() { 
    	return new HeapIterator(); 
    }

    private class HeapIterator implements Iterator<Integer> {
        // create a new pq
        private MyIndexMaxHeap<Key> copy;

        // add all elements to copy of heap
        // takes linear time since already in heap order so no keys move
        public HeapIterator() {
            copy = new MyIndexMaxHeap<Key>(pq.length - 1);
            for (int i = 1; i <= N; i++)
                copy.insert(pq[i], keys[pq[i]]);
        }

        public boolean hasNext()  { return !copy.isEmpty();                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Integer next() {
            if (!hasNext()) throw new NoSuchElementException();
            return copy.delMax();
        }
    }
    
    public static void main(String[] args) {
    	MyIndexMaxHeap<Integer> maxHeap = new MyIndexMaxHeap<Integer>(100);
    	
    	maxHeap.insert(1, 6);
		maxHeap.insert(2, 1);
		maxHeap.insert(3, 9);
		maxHeap.insert(5, 2);
		maxHeap.insert(6, 7);
		maxHeap.insert(7, 5);
		maxHeap.insert(8, 3);
		maxHeap.insert(9, 8);
		
		System.out.println("MinHeap.minIndex: " + maxHeap.maxIndex());
		System.out.println("MinHeap.minKey: " + maxHeap.maxKey());
		System.out.println("MinHeap.delMin: " + maxHeap.delMax());
		maxHeap.changeKey(5, -1);
		System.out.println("MinHeap.minIndex: " + maxHeap.maxIndex());
		System.out.println("MinHeap.minKey: " + maxHeap.maxKey());
		maxHeap.delete(5);
		System.out.println("MinHeap.minIndex: " + maxHeap.maxIndex());
		System.out.println("MinHeap.minKey: " + maxHeap.maxKey());
    }
}
