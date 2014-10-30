package wenyu.learning.Stack;

import java.util.Arrays;

public class MyArrayStack<E> implements IMyStack<E> {
	private final int DEFAULT_SIZE = 3;
	private final float DEFAULT_INCREMENT = 1.5f;
	private E[] array;
	private int tail;
	
	@SuppressWarnings("unchecked")
	public MyArrayStack() {
		array = (E[]) new Object[DEFAULT_SIZE];
		tail = 0;
	}
	
	@SuppressWarnings("unchecked")
	public MyArrayStack(int size) {
		array = (E[]) new Object[size];
		tail = 0;
	}
	
	public synchronized boolean push(E element) {
		if(element == null) {
			return false;
		}
		
		array[tail++] = element;
		if(tail>=array.length) {
			resize();
		}
		return true;
	}
	
	public synchronized E pop() {
		if(isEmpty()) {
			return null;
		}
		
		E element = array[--tail];
		array[tail]=null;
		return element;
	}

	public synchronized E peek() {
		if(isEmpty()) {
			return null;
		}
		return array[tail-1];
	}
	
	public synchronized boolean isEmpty() {
		return tail == 0;
	}
	
	public int size() {
		return tail;
	}
	
	public boolean clear() {
		for(int i=0; i<tail; i++) {
			array[i] = null;
		}
		tail = 0;
		return true;
	}
	
	private void resize() {
		if (tail >= array.length) { 
			int oldSize = array.length;
            int newSize = (int)Math.ceil(array.length*DEFAULT_INCREMENT);    
            array = Arrays.copyOf(array, newSize);  
            tail = oldSize;
        }
	}
	
	public String toString() {
		StringBuilder printOut = new StringBuilder("Stack: [");
		for(int i=0; i<tail; i++) {
			printOut.append(array[i] + ", ");
		}
		
		String returnStr = "";
		if(printOut.length()>8) {
			returnStr = printOut.substring(0, printOut.length()-2) + "]";
		} else {
			returnStr = printOut.toString() + "]";
		}
		
		return returnStr;
	}
	
	public static void main(String[] args) {
		MyArrayStack<Integer> stack = new MyArrayStack<Integer>();
		for(int i=0;i<3;i++) {
			stack.push(i);
		}
		for(int i=0;i<10;i++) {
			Integer ele = stack.pop();
			System.out.println(ele);
			stack.push(i);
			stack.push(i+1);
			stack.push(i+3);
		}
	}
}
