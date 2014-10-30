package wenyu.learning.Stack;

import java.util.LinkedList;
import java.util.Queue;

public class MyStackWithTwoQueues<E> implements IMyStack<E> {
	private final int capacity;
	private final Object lock = new Object();
	
	private final Queue<E> queue1;
	private final Queue<E> queue2;
	private Queue<E> currentQueue;
	
	public MyStackWithTwoQueues() throws Exception{
		// Set default capacity to 20
		this(20);
	}
	
	public MyStackWithTwoQueues(int capacity) throws Exception {
		if(capacity < 0) {
			this.capacity = 0;
			throw new Exception("Capacity cannot be negative.");
		}
		this.capacity = capacity;
		
		queue1 = new LinkedList<E>();
		queue2 = new LinkedList<E>();
		currentQueue = queue1;
	}
	
	public boolean push(E item) {
		if(item == null) {
			return false;
		}
		
		synchronized(lock) {
			if(currentQueue.size()<capacity) {
				currentQueue.offer(item);
			} else {
				System.out.println("Reach maximum capacity...");
			}
			
			return true;
		}
	}
	
	public E pop() {
		synchronized(lock) {
			if(currentQueue.isEmpty()) {
				return null;
			}
			
			Queue<E> auxQueue = (currentQueue==queue1)?queue2:queue1;
			while(currentQueue.size() > 1) {
				auxQueue.offer(currentQueue.poll());
			}
			if(currentQueue.size() == 1) {
				E returnItem = currentQueue.poll();
				currentQueue = auxQueue;
				return returnItem;
			}
			return null;
		}
	}
	
	public boolean ifEmpty() {
		synchronized(lock) {
			return currentQueue.isEmpty();
		}
	}
	
	public int size() {
		synchronized(lock) {
			return currentQueue.size();
		}
	}
	
	public E peek() {
		synchronized(lock) {
			if(currentQueue.isEmpty()) {
				return null;
			}
			
			Queue<E> auxQueue = (currentQueue==queue1)?queue2:queue1;
			while(currentQueue.size() > 1) {
				auxQueue.offer(currentQueue.poll());
			}
			if(currentQueue.size() == 1) {
				E returnItem = currentQueue.poll();
				auxQueue.offer(returnItem);
				currentQueue = auxQueue;
				return returnItem;
			}
			return null;
		}
	}
	
	public boolean clear() {
		synchronized(lock) {
			queue1.clear();
			queue2.clear();
			currentQueue = queue1;
			return true;
		}
	}

	public boolean isEmpty() {
		synchronized(lock) {
			return currentQueue.isEmpty();
		}
	}
	
	public static void main(String[] args) throws Exception {
		MyStackWithTwoQueues<Integer> stack = new MyStackWithTwoQueues<Integer>(10);
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(5);
		stack.push(6);
		stack.push(7);
		stack.push(8);
		stack.push(9);
		
		System.out.println("Stack size: " + stack.size());
		System.out.println("Stack pop: " + stack.pop());
		System.out.println("Stack peek: " + stack.peek());
		System.out.println("Stack pop: " + stack.pop());
		System.out.println("Stack peek: " + stack.peek());
		System.out.println("Stack pop: " + stack.pop());
		System.out.println("Stack size: " + stack.size());
	}
}
