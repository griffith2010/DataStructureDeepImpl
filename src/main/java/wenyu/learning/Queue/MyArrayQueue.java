package wenyu.learning.Queue;

import java.util.Arrays;

public class MyArrayQueue<E> implements IMyQueue<E> {
	private static final int DEFAULT_SIZE = 3;
	private static final float DEFAULT_INCREMENT = 1.5f;
	private E[] array;
	private int head;
	private int tail;

	public MyArrayQueue() {
		this(DEFAULT_SIZE);
	}
	
	@SuppressWarnings("unchecked")
	public MyArrayQueue(int size) {
		array = (E[]) new Object[size];
		tail = 0;
		head = 0;
	}
	
	public synchronized boolean offer(E element) {
		if(element == null) {
			return false;
		}
		
		array[tail++] = element;
		if(tail>=array.length) {
			resize();	
		}
		return true;
	}

	public synchronized E poll() {
		E element = array[head];
		if(element == null) {
			// no element
		} else {
			array[head++]=null;
			array = Arrays.copyOfRange(array, head--, array.length);
			tail--;
		}
		return element;
	}

	public E peek() {
		return array[head];
	}

	public boolean clear() {
		for(int i=0; i<array.length; i++) {
			array[i] = null;
		}
		head = tail = 0;
		return true;
	}

	public int size() {
		return tail-head;
	}

	public synchronized boolean isEmpty() {
		if(head==tail) {
			return true;
		} else {
			return false;
		}
	}
	
	private void resize() {
		if (tail >= array.length) { 
			int oldSize = array.length;
            int newSize = (int)Math.ceil(array.length*DEFAULT_INCREMENT);    
            array = Arrays.copyOf(array, newSize);  
            tail = oldSize;  
            head = 0;  
        }
	}
}
