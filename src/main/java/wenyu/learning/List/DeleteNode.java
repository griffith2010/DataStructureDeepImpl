package wenyu.learning.List;

import java.util.Random;

public class DeleteNode {

	private static <T> MyListNode<T> scanDelete(MyListNode<T> head, MyListNode<T> del) {
		if(head == null) {
			return null;
		}
		
		MyListNode<T> tmpHead = new MyListNode<T>(null);
		tmpHead.next = head;
		
		MyListNode<T> node = tmpHead.next;
		MyListNode<T> pre = tmpHead;
		while(node != null) {
			if(node == del) {
				pre.next = node.next;
				node = pre.next;
			} else {
				pre = node;
				node = node.next;
			}
		}
		
		return tmpHead.next;
	}
	
	private static <T> MyListNode<T> fastDelete(MyListNode<T> head, MyListNode<T> del) {
		if(head == null) {
			return null;
		}
		
		if(del.next == null) {
			return scanDelete(head, del);
		} else {
			del.setValue(del.next.getValue());
			del.next = del.next.next;
			return head;
		}
	}
	
	public static <T> MyListNode<T> deleteNode(MyListNode<T> head, MyListNode<T> node) {
		if(head == null) {
			return null;
		}
		
		if(node == null) {
			return head;
		}
		
		//O(n) way
		//MyListNode<T> tmpHead = scanDelete(head, node);
		
		//O(1) way
		MyListNode<T> tmpHead = fastDelete(head, node);
		
		return tmpHead;
	}
	
	public static void main(String[] args) {
		Integer[] items = new Integer[] {1,3,5,7,9};
		MyListNode<Integer> head = DemoUtils.generateLinkedList(false, items);
		
		int nodeIndex = new Random().nextInt(items.length);
		MyListNode<Integer> node = head;
		while(nodeIndex-- > 0) {
			node = node.next;
		}
		
		System.out.println("Try to delete " + node.getValue());
		DemoUtils.printList(deleteNode(head, node));
	}

}
