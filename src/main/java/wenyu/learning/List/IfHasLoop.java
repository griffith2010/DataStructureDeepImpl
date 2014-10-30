package wenyu.learning.List;

import java.util.Random;

public class IfHasLoop {

	public static <T> boolean ifHasLoop(MyListNode<T> head) {
		//One pointer advances once at each step, 
		// and the other advances twice at each step. 
		// If the faster pointer meets the slower one again, 
		// there is a loop in the list. 
		// Otherwise, there is no loop if the faster one reaches the end of list.
		if(head == null) {
			return false;
		}

		MyListNode<T> first = head;
		MyListNode<T> next = head;
		
		while(next!=null && (first==head || first!=next)) {
			first = first.next;
			if(next.next != null) {
				next = next.next.next;
			} else {
				return false;
			}
		}
		
		if(next == null) {
			return false;
		} else {
			return true;
		}
	}
	
	public static void main(String[] args) {
		MyListNode<Integer> head = new MyListNode<Integer>(1);
		MyListNode<Integer> node = head;
		node.next = new MyListNode<Integer>(2);
		node = node.next;
		node.next = new MyListNode<Integer>(3);
		node = node.next;
		node.next = new MyListNode<Integer>(5);
		node = node.next;
		node.next = new MyListNode<Integer>(6);
		node = node.next;
		node.next = new MyListNode<Integer>(7);
		node = node.next;
		node.next = new MyListNode<Integer>(8);
		node = node.next;
		node.next = new MyListNode<Integer>(9);
		MyListNode<Integer> endNode = node.next;
		
		int loopEntry = new Random().nextInt(8);
		node = head;
		while(loopEntry>0) {
			node = node.next;
			loopEntry--;
		}
		//endNode.next = node;
		
		if(ifHasLoop(head)) {
			System.out.println("Have loop...");
		} else {
			System.out.println("No loop...");
		}
	}

}
