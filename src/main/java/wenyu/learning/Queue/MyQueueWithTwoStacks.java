package wenyu.learning.Queue;

import java.util.Stack;

public class MyQueueWithTwoStacks<T> {
	private Object lock = new Object();
	private int capacity = 10;
	
	public MyQueueWithTwoStacks(int capacity) {
		if(capacity < 0) {
			this.capacity = 0;
			return;
		}
		this.capacity = capacity;
	}
	
	private Stack<T> stack1 = new Stack<T>();
	private Stack<T> stack2 = new Stack<T>();
	
	public void offer(T item) {
		synchronized(lock) {
			if(item != null) {
				if(stack1.size()<capacity) {
					stack1.push(item);
				} else {
					System.out.println("Queue has reached maximum size...");
				}
			}
		}
	}
	
	public T poll() {
		synchronized(lock) {
			if(stack1.isEmpty()) {
				System.out.println("No items in queue...");
				return null;
			}
			
			T returnItem = null;
			while(!stack1.isEmpty()) {
				stack2.push(stack1.pop());
			}
			returnItem = stack2.pop();
			while(!stack2.isEmpty()) {
				stack1.push(stack2.pop());
			}

			return returnItem;
		}
	}
	
	public T peek() {
		synchronized(lock) {
			if(stack1.isEmpty()) {
				System.out.println("No items in queue...");
				return null;
			}
			
			T returnItem = null;
			while(!stack1.isEmpty()) {
				stack2.push(stack1.pop());
			}
			returnItem = stack2.peek();
			while(!stack2.isEmpty()) {
				stack1.push(stack2.pop());
			}
			
			return returnItem;
		}
	}
	
	public boolean isEmpty() {
		synchronized(lock) {
			return stack1.isEmpty();
		}
	}
	
	public int size() {
		synchronized(lock) {
			return stack1.size();
		}
	}
	
	public String toString() {
		if(stack1.isEmpty()) {
			return "[]";
		}
		
		String output = "[";
		while(!stack1.isEmpty()) {
			stack2.push(stack1.pop());
		}
		while(!stack2.isEmpty()) {
			T item = stack2.pop();
			output += item + ", ";
			stack1.push(item);
		}
		
		return output.substring(0, output.length()-2) + "]";
	}
	
	public static void main(String[] args) {
		int capacity = 10;
		MyQueueWithTwoStacks<String> queue = new MyQueueWithTwoStacks<String>(capacity);
		queue.offer("First");
		queue.offer("Second");
		queue.offer("Third");
		System.out.println("Queue.toString: " + queue.toString() + "/Size: " + queue.size());
		
		System.out.println("Queue.poll: " + queue.poll());
		System.out.println("Queue.toString: " + queue.toString() + "/Size: " + queue.size());
		
		System.out.println("Queue.poll: " + queue.poll());
		System.out.println("Queue.toString: " + queue.toString() + "/Size: " + queue.size());
		
		System.out.println("Queue.peek: " + queue.peek());
		System.out.println("Queue.toString: " + queue.toString() + "/Size: " + queue.size());
	}
}
