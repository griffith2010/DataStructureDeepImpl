package wenyu.learning.Stack;

import java.util.Stack;

public class MyStackWithMaxMin<E extends Comparable<E>> implements IMyStack<E> {
	private final int capacity;
	private final Object lock = new Object();
	
	private final Stack<E> stack;
	private final Stack<E> minStack;
	private final Stack<E> maxStack;
	
	public MyStackWithMaxMin() throws Exception{
		// Set default capacity to 20
		this(20);
	}
	
	public MyStackWithMaxMin(int capacity) throws Exception {
		if(capacity < 0) {
			this.capacity = 0;
			throw new Exception("Capacity cannot be negative.");
		}
		this.capacity = capacity;
		
		stack = new Stack<E>();
		minStack = new Stack<E>();
		maxStack = new Stack<E>();
	}
	
	public E pop() {
		synchronized(lock) {
			if(stack.isEmpty()) {
				return null;
			}
			
			E currItem = stack.pop();
			E currMinItem = minStack.peek();
			E currMaxItem = maxStack.peek();
			if(currItem == currMinItem) {
				minStack.pop();
			}
			if(currItem == currMaxItem) {
				maxStack.pop();
			}
			
			return currItem;
		}
	}
	
	public boolean push(E item) {
		if(item == null) {
			return false;
		}
		
		synchronized(lock) {
			if(stack.size()>=capacity) {
				System.out.println("Reach maximum capacity...");
				return false;
			}
			
			// Insert item into stack
			stack.push(item);
			
			// Prepare max stack
			if(maxStack.isEmpty()) {
				maxStack.push(item);
			} else {
				E currMaxItem = maxStack.peek();
				if(item.compareTo(currMaxItem) >= 0) {
					maxStack.push(item);
				}
			}
			
			if(minStack.isEmpty()) {
				minStack.push(item);
			} else {
				E min = minStack.peek();
				if(item.compareTo(min) <= 0) {
					minStack.push(item);
				}
			}
			
			return true;
		}
	}
	
	public E peek() {
		synchronized(lock) {
			if(stack.isEmpty()) {
				return null;
			}
			return stack.peek();
		}
	}
	
	public boolean isEmpty() {
		synchronized(lock) {
			return stack.isEmpty();
		}
	}
	
	public int size() {
		synchronized(lock) {
			return stack.size();
		}
	}
	
	public E max() {
		synchronized(lock) {
			if(maxStack.isEmpty()) {
				return null;
			}
			return maxStack.peek();
		}
	}
	
	public E min() {
		synchronized(lock) {
			if(minStack.isEmpty()) {
				return null;
			}
			return minStack.peek();
		}
	}

	public boolean clear() {
		stack.clear();
		minStack.clear();
		maxStack.clear();
		return false;
	}
	 
	public static void main(String[] args) throws Exception {
		MyStackWithMaxMin<Integer> stack = new MyStackWithMaxMin<Integer>(100);
		stack.push(3);
		stack.push(5);
		stack.push(6);
		stack.push(7);
		stack.push(8);
		stack.push(9);
		stack.push(1);
		stack.push(2);
		
		
		System.out.println("stack.max: " + stack.max());
		System.out.println("stack.min: " + stack.min());
		System.out.println("stack.pop: " + stack.pop());
		System.out.println("stack.max: " + stack.max());
		System.out.println("stack.min: " + stack.min());
		System.out.println("stack.pop: " + stack.pop());
		System.out.println("stack.max: " + stack.max());
		System.out.println("stack.min: " + stack.min());
		System.out.println("stack.pop: " + stack.pop());
		System.out.println("stack.max: " + stack.max());
		System.out.println("stack.min: " + stack.min());
		System.out.println("stack.pop: " + stack.pop());
		System.out.println("stack.max: " + stack.max());
		System.out.println("stack.min: " + stack.min());
		System.out.println("stack.pop: " + stack.pop());
		System.out.println("stack.max: " + stack.max());
		System.out.println("stack.min: " + stack.min());
	}
}
