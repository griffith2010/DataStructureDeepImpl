package wenyu.learning.Stack;

import java.util.Iterator;
import java.util.Stack;

public class BuildinStack {
	public static <E> void printStack(Stack<E> stack) {
		Iterator<E> it = stack.iterator();
		StringBuilder printOut = new StringBuilder();
		printOut.append("Stack: [");
		while(it.hasNext()) {
			printOut.append(it.next().toString()+", ");
		}
		System.out.println(printOut.substring(0, printOut.length()-2) + "]");
	}
	
	public static void stackDemo() {
		Stack<String> stack = new Stack<String>();

		if(stack.isEmpty()) {
			System.out.println("Stack is empty...");
		}
		
		stack.push("First");
		stack.add("Second");
		stack.push("Third");
		stack.add("Forth");
		printStack(stack);
		
		System.out.println("Stack.peek: " + stack.peek());
		printStack(stack);
		
		System.out.println("Stack.pop: " + stack.pop());
		printStack(stack);
		
		System.out.println("Stack.toString: " + stack.toString());
	}
	
	public static void demoEntry() {
		stackDemo();
	}
	
	public static void main(String[] args) {
		demoEntry();

	}
}
