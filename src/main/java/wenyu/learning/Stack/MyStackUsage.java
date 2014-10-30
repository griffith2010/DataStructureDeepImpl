package wenyu.learning.Stack;

import java.util.Random;

public class MyStackUsage {
	private static void usage(IMyStack<Integer> stack) {
		Random random = new Random();
		System.out.println("Stack Empty: " + stack.isEmpty());
		for(int i=0;i<9;i++) {
			stack.push(random.nextInt());
		}
		System.out.println(stack);
		System.out.println("Current size: " + stack.size());
		System.out.println("Stack Empty: " + stack.isEmpty());
		
		System.out.println("Poll first element: " + stack.pop());
		System.out.println(stack);
		System.out.println("Current size: " + stack.size());
		System.out.println("Poll first element: " + stack.pop());
		System.out.println(stack);
		System.out.println("Current size: " + stack.size());
		System.out.println("Poll first element: " + stack.pop());
		System.out.println(stack);
		System.out.println("Current size: " + stack.size());
		
		System.out.println("Peek first element: " + stack.peek());
		System.out.println(stack);
		System.out.println("Current size: " + stack.size());
		
		for(int i=0;i<3;i++) {
			stack.push(random.nextInt());
		}
		System.out.println(stack);
		System.out.println("Current size: " + stack.size());
		
		System.out.println("Clear stack: " + stack.clear());
		System.out.println("Stack Empty: " + stack.isEmpty());
	}
	
	public static void arrayStack() {
		IMyStack<Integer> arrStack = new MyArrayStack<Integer>();
		usage(arrStack);
	}
	
	public static void linkedStack() {
		IMyStack<Integer> linkedStack = new MyLinkedStack<Integer>();
		usage(linkedStack);
	}

	public static void twoQueuesStack() {
//		IMyStack<Integer> arrStack = new MyStackWithTwoQueues<Integer>();
//		usage(arrStack);
	}
//	
//	public static void maxminStack() {
//		MyStackWithMaxMin<Integer> stack = new MyStackWithMaxMin<Integer>(new MyStackWithTwoStacks<Integer>());
//		stack.offer(9);
//		stack.offer(8);
//		stack.offer(1);
//		stack.offer(7);
//		stack.offer(9);
//		stack.offer(5);
//		stack.offer(2);
//		stack.offer(3);
//		stack.offer(6);
//		System.out.println(stack);
//		System.out.println("Stack.max: " + stack.max());
//		System.out.println("Stack.min: " + stack.min());
//		stack.poll();
//		System.out.println(stack);
//		System.out.println("Stack.max: " + stack.max());
//		System.out.println("Stack.min: " + stack.min());
//		stack.poll();
//		System.out.println(stack);
//		System.out.println("Stack.max: " + stack.max());
//		System.out.println("Stack.min: " + stack.min());
//		stack.poll();
//		System.out.println(stack);
//		System.out.println("Stack.max: " + stack.max());
//		System.out.println("Stack.min: " + stack.min());
//		stack.poll();
//		System.out.println(stack);
//		System.out.println("Stack.max: " + stack.max());
//		System.out.println("Stack.min: " + stack.min());
//		stack.poll();
//		System.out.println(stack);
//		System.out.println("Stack.max: " + stack.max());
//		System.out.println("Stack.min: " + stack.min());
//		stack.poll();
//		System.out.println(stack);
//		System.out.println("Stack.max: " + stack.max());
//		System.out.println("Stack.min: " + stack.min());
//		stack.poll();
//		System.out.println(stack);
//		System.out.println("Stack.max: " + stack.max());
//		System.out.println("Stack.min: " + stack.min());
//		stack.poll();
//		System.out.println(stack);
//		System.out.println("Stack.max: " + stack.max());
//		System.out.println("Stack.min: " + stack.min());
//	}
//	
//	public static void sortStack() {
//		IMyStack<Integer> stack = new MyArrayStack<Integer>();
//		
//		// Test sort with stack
//		Random random = new Random();
//		for(int i=0;i<18;i++) {
//			stack.offer(random.nextInt(100));
//		}
//		StackSortingUtils.sortWithStack(stack);
//		System.out.println(stack);
//		stack.clear();
//		
//		// Test sort with stack
//		for(int i=0;i<18;i++) {
//			stack.offer(random.nextInt(100));
//		}
//		StackSortingUtils.sortWithStack(stack);
//		System.out.println(stack);
//		stack.clear();
//	}
	
	public static void main(String[] args) {
//		arrayStack();
//		linkedStack();
		twoQueuesStack();
//		maxminStack();
//		sortStack();
	}
	
}
