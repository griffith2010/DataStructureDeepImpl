package wenyu.learning.Stack;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class StackSortingUtils {

	public static <E extends Comparable<E>> void sortWithStack(Stack<E> stack) {
		if(stack.isEmpty()) {
			return;
		}
		
		Stack<E> auxStack = new Stack<E>();
		while(!stack.isEmpty()) {
			E item = stack.pop();
			
			while(!auxStack.isEmpty() && auxStack.peek().compareTo(item)>0) {
				E tmpItem = auxStack.pop();
				stack.push(tmpItem);
			}
			auxStack.push(item);
		}

		Queue<E> tmpQueue = new LinkedList<E>();
		while(!auxStack.isEmpty()) {
			tmpQueue.offer(auxStack.pop());
		}
		while(!tmpQueue.isEmpty()) {
			stack.push(tmpQueue.poll());
		}
	}
	
	public static <E extends Comparable<E>> void sortWithQueue(Stack<E> stack) {
		if(stack==null || stack.size()==0) {
			return;
		}
		
		Queue<E> auxQueue = new LinkedList<E>();
		while(!stack.isEmpty()) {
			E item = stack.pop();
			
			int auxQSize = auxQueue.size();
			while(auxQSize>0 && auxQueue.peek().compareTo(item)<=0) {
				auxQueue.offer(auxQueue.poll());
				auxQSize--;
			}
			auxQueue.offer(item);
			while(auxQSize>0) {
				auxQueue.offer(auxQueue.poll());
				auxQSize--;
			}
		}
		
		while(!auxQueue.isEmpty()) {
			stack.push(auxQueue.poll());
		}
	}
	
	private static void print(Stack<Integer> stack) {
		Iterator<Integer> it = stack.iterator();
		while(it.hasNext()) {
			System.out.print(it.next() + " ");
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<Integer>();
		stack.add(8);
		stack.add(3);
		stack.add(2);
		stack.add(3);
		stack.add(5);
		stack.add(6);
		sortWithQueue(stack);
		print(stack);
		
		
		stack = new Stack<Integer>();
		stack.add(8);
		stack.add(3);
		stack.add(2);
		stack.add(3);
		stack.add(5);
		stack.add(6);
		sortWithStack(stack);
		print(stack);
	}

}
