package wenyu.learning.Queue;

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

public class MyLinkedQueue<E> implements IMyQueue<E> {
	private final ListNode<E> internalHead = new ListNode<E>(null);
	private ListNode<E> tail = internalHead;
	private int count = 0;
	
	public synchronized boolean offer(E element) {
		if(element == null) {
			return false;
		}
		
		ListNode<E> node = new ListNode<E>(element);
		tail.next = node;
		node.pre = tail;
		tail = node;
		count++;
		return true;
	}

	public synchronized E poll() {
		ListNode<E> node = internalHead.next;
		if(node != null) {
			internalHead.next = node.next;
		}
		
		if(internalHead.next==null) {
			tail = internalHead;
		}
		
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
	
}
