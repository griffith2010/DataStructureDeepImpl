package wenyu.learning.Stack;

class ListNode<E> {
	E element;
	ListNode<E> next;
	ListNode<E> pre;
	public ListNode(E element) {
		this.element = element;
	}
	public String toString() {
		return element.toString();
	}
}

public class MyLinkedStack<E> implements IMyStack<E> {
	private final ListNode<E> internalHead = new ListNode<E>(null);
	private int count = 0;
	
	public synchronized boolean push(E element) {
		if(element == null) {
			return false;
		}
		
		ListNode<E> node = new ListNode<E>(element);
		node.next = internalHead.next;
		node.pre = internalHead;
		internalHead.next = node;
		count++;
		return true;
	}

	public synchronized E pop() {
		if(isEmpty()) {
			return null;
		}
		
		ListNode<E> node = internalHead.next;
		internalHead.next = node.next;		
		count--;
		return node.element;
	}

	public synchronized E peek() {
		if(isEmpty()) {
			return null;
		} else {
			return internalHead.next.element;
		}
	}

	public boolean clear() {
		internalHead.next = null;
		count = 0;
		return true;
	}

	public int size() {
		return count;
	}

	public boolean isEmpty() {
		return count==0;
	}
	
	public String toString() {
		StringBuilder printOut = new StringBuilder("Stack: [");
		ListNode<E> node = internalHead.next;
		while(node != null) {
			printOut.append(node + ", ");
			node = node.next;
		}
		
		String returnStr = "";
		if(printOut.length()>8) {
			returnStr = printOut.substring(0, printOut.length()-2) + "]";
		} else {
			returnStr = printOut.toString() + "]";
		}
		
		return returnStr;
	}
}
