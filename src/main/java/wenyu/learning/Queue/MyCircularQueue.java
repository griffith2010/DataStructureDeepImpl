package wenyu.learning.Queue;

public class MyCircularQueue<E> implements IMyQueue<E> {
	private final float DEFAULT_INCREMENT = 2.0f;
	private final int capacity = 3;
	private int currCount = 0;
    private E[] array;
    private int head = 0;
    private int tail = 0;
    private Object lock = new Object();
     
    @SuppressWarnings("unchecked")
	public MyCircularQueue(){
        array = (E[]) new Object[capacity];
    }
 
    public int size() {
    	synchronized(lock) {
	        if(tail > head)
	            return tail - head;
	        return array.length - head + tail;
    	}
    }
 
    public boolean isEmpty() {
    	synchronized(lock) {
    		return (tail == head) ? true : false;
    	}
    }
 
    private boolean isFull() {
        int diff = tail - head;
        if((diff==0&&currCount!=0)||diff==array.length)
            return true;
        return false;
    }
 
    public boolean offer(E element) {
    	if(element == null) {
    		return false;
    	}
    	
    	synchronized(lock) {
    		array[tail] = element;
            tail = (tail + 1) % array.length;
            currCount++;
	        if(isFull()){
	            resize();
	        }
	        return true;
    	}
    }
 
    public E poll() {
    	synchronized(lock) {
	        E item = null;
	        if(isEmpty()){
	        }else{
	            item = array[head];
	            array[head] = null;
	            currCount--;
	            head = (head + 1) % array.length;
	        }
	       return item;
    	}
    }
    
	public E peek() {
		return array[head];
	}
    
    @SuppressWarnings("unchecked")
    private void resize() {
    	if (isFull()) { 
			int oldSize = array.length;
            int newSize = (int)Math.ceil(array.length*DEFAULT_INCREMENT);
			E[] newArray = (E[]) new Object[newSize];

            System.arraycopy(array, head, newArray, 0, array.length-head);
            System.arraycopy(array, 0, newArray, array.length-head, head);
            array = newArray;
            tail = oldSize;
            head = 0;  
        }
    }

	public boolean clear() {
		synchronized(lock) {
			for(int i=0; i<array.length; i++) {
				array[i] = null;
			}
			currCount = head = tail = 0;
			return true;
		}
	}
}
