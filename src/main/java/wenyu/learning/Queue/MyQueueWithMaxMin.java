package wenyu.learning.Queue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MyQueueWithMaxMin<T extends Comparable<T>> {
	private final int capacity;
	private final Object lock = new Object();
	
	private Queue<T> queue = new LinkedList<T>();
	private List<T> maxList = new ArrayList<T>();
	private List<T> minList = new ArrayList<T>();
	
	public MyQueueWithMaxMin(int capacity) {
		if(capacity < 0) {
			this.capacity = 0;
			return;
		}
		this.capacity = capacity;
	}
	
	public void offer(T item) {
		if(item == null) {
			return;
		}
		
		synchronized(lock) {
			if(queue.size()>=capacity) {
				System.out.println("Reach maximum capacity...");
				return;
			}
			
			queue.offer(item);
			
			if(maxList.isEmpty()) {
				// if max list is empty, append it
				maxList.add(item);
			} else {
				// if not! 
				// Remove all the items in the max list 
				// which are smaller than insert one from the tail
				// and then add the insert one to the tail.
				while(!maxList.isEmpty()) {
					T tmpItem = maxList.get(maxList.size()-1);
					if(item.compareTo(tmpItem) > 0) {
						maxList.remove(maxList.size()-1);
					} else {
						break;
					}
				}
				maxList.add(item);
			}
			
			// same logic as maximum
			if(minList.isEmpty()) {
				minList.add(item);
			} else {
				while(!minList.isEmpty()) {
					T tmpItem = minList.get(minList.size()-1);
					if(item.compareTo(tmpItem) < 0) {
						minList.remove(minList.size()-1);
					} else {
						break;
					}
				}
				minList.add(item);
			}
		}
	}
	
	public T poll() {
		synchronized(lock) {
			if(queue.isEmpty()) {
				return null;
			}
			
			T returnItem = queue.poll();
			
			// if the maximum one in the max list 
			// is the one try to delete. 
			// then delete the maximum one 
			// as well as the poll one in the queue
			T top = maxList.get(0);
			if(returnItem == top) {
				maxList.remove(0);
			}
			
			// same logic as max.
			top = minList.get(0);
			if(returnItem == top) {
				minList.remove(0);
			}
			
			return returnItem;
		}
	}
	
	public T peek() {
		synchronized(lock) {
			if(queue.isEmpty()) {
				return null;
			}
			
			return queue.peek();
		}
	}
	
	public boolean isEmpty() {
		synchronized(lock) {
			return queue.isEmpty();
		}
	}
	
	public int size() {
		synchronized(lock) {
			return queue.size();
		}
	}
	
	public T max() {
		synchronized(lock) {
			if(maxList.isEmpty()) {
				return null;
			}
			return maxList.get(0);
		}
	}
	
	public T min() {
		synchronized(lock) {
			if(minList.isEmpty()) {
				return null;
			}
			return minList.get(0);
		}
	}
	
	public static void main(String[] args) {
		MyQueueWithMaxMin<Integer> queue = new MyQueueWithMaxMin<Integer>(10);
		
		queue.offer(9);
		queue.offer(8);
		queue.offer(1);
		queue.offer(7);
		queue.offer(9);
		queue.offer(5);
		queue.offer(2);
		queue.offer(3);
		queue.offer(6);
		
		System.out.println("Queue.max: " + queue.max());
		System.out.println("Queue.min: " + queue.min());
		queue.poll();
		System.out.println("Queue.max: " + queue.max());
		System.out.println("Queue.min: " + queue.min());
		queue.poll();
		System.out.println("Queue.max: " + queue.max());
		System.out.println("Queue.min: " + queue.min());
		queue.poll();
		System.out.println("Queue.max: " + queue.max());
		System.out.println("Queue.min: " + queue.min());
		queue.poll();
		System.out.println("Queue.max: " + queue.max());
		System.out.println("Queue.min: " + queue.min());
		queue.poll();
		System.out.println("Queue.max: " + queue.max());
		System.out.println("Queue.min: " + queue.min());
		queue.poll();
		System.out.println("Queue.max: " + queue.max());
		System.out.println("Queue.min: " + queue.min());
		queue.poll();
		System.out.println("Queue.max: " + queue.max());
		System.out.println("Queue.min: " + queue.min());
		queue.poll();
		System.out.println("Queue.max: " + queue.max());
		System.out.println("Queue.min: " + queue.min());
	}

}
