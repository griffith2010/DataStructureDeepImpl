package wenyu.learning.Stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
 * Imagine a (literal) stack of plates. If the stack gets too high, 
 * it might topple. Therefore, in real life, we would likely start 
 * a new stack when the previous stack exceeds some threshold. 
 * Implement a data structure SetOfStacks that mimics this. 
 * SetOfStacks should be composed of several stack, and should create 
 * a new stack once the previous one exceeds capacity. SetOfStacks.push() 
 * and SetOfStacks.pop() should behave identically to a single stack 
 * (that is, pop() should return the same values as it would if there 
 * were just a single stack).
 * 
 * FOLLOW UP
 * Implement a function popAt(int index) which performs a pop 
 * operation on a specific sub-stack.
 */

public class SetOfStacks<E> implements IMyStack<E> {
	private final int capacity;
	private final Object lock = new Object();
	private final List<Stack<E>> stackList;
	
	public SetOfStacks(int eachCapacity) {
		this.capacity = eachCapacity;
		stackList = new ArrayList<Stack<E>>();
	}
	
	public boolean push(E item) {
		if(item == null) {
			return false;
		}
		
		synchronized(lock) {
			if(stackList.size()==0 || stackList.get(stackList.size()-1).size()==capacity) {
				Stack<E> stack = new Stack<E>();
				stackList.add(stack);
			}
			Stack<E> currentStack = stackList.get(stackList.size()-1);
			currentStack.add(item);
			
			return true;
		}
		
		
	}
	
	public E pop() {
		if(stackList.size()==0) {
			System.out.println("No items in stack...");
			return null;
		}
		
		synchronized(lock) {
			Stack<E> currentStack = stackList.get(stackList.size()-1);
			E item = currentStack.pop();
			if(currentStack.size()==0) {
				stackList.remove(stackList.size()-1);
			}
			return item;
		}
	}
	
	public int size() {
		synchronized(lock) {
			int size = 0;
			for(Stack<E> stack:stackList) {
				size += stack.size();
			}
			return size;
		}
	}
	
	public boolean isEmpty() {
		synchronized(lock) {
			return stackList.size()==0;
		}
	}
	
	public E popAt(int index) {
		if(index>=stackList.size()) {
			System.out.println("No such index in stack list.");
			return null;
		}
		
		synchronized(lock) {
			Stack<E> stack = stackList.get(index);
			E item = stack.pop();
			
			Stack<E> tmpStack = new Stack<E>();
			for(int i=stackList.size()-1;i>index;i--) {
				Stack<E> tmp = stackList.get(i);
				while(!tmp.isEmpty()) {
					tmpStack.push(tmp.pop());
				}
				stackList.remove(i);
			}
			while(!tmpStack.isEmpty()) {
				push(tmpStack.pop());
			}
			return item;
		}
	}
	
	public E peek() {
		if(stackList.size()==0) {
			System.out.println("No items in stack...");
			return null;
		}
		
		synchronized(lock) {
			Stack<E> currentStack = stackList.get(stackList.size()-1);
			E item = currentStack.peek();
			return item;
		}
	}

	public boolean clear() {
		synchronized(lock) {
			stackList.clear();
			return true;
		}
	}
	
	public static void main(String[] args) {
		SetOfStacks<Integer> stack = new SetOfStacks<Integer>(2);
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(5);
		stack.push(6);
		stack.push(7);
		stack.push(8);
		stack.push(9);
		System.out.println("Stack size: " + stack.size());
		System.out.println("Stack.pop:" + stack.pop());
		System.out.println("Stack.pop:" + stack.pop());
		System.out.println("Stack.pop:" + stack.pop());
		System.out.println("Stack size: " + stack.size());
		

		stack.push(7);
		stack.push(8);
		stack.push(9);
		System.out.println("Stack size: " + stack.size());
		System.out.println("Stack.pop:" + stack.popAt(1));
		System.out.println("Stack.pop:" + stack.popAt(2));
	}

}
