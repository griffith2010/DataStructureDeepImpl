package wenyu.learning.List;

import java.util.Random;

/*
 * Find the entry of loop
 * Logic:
 * 	1. First to make sure link has loop and return one node in loop
 * 		a) two points, one move forward by one step every time 
 * 		   and another point move by two steps every time.
 * 		b) when the two nodes meet each other before ending which means has loop.
 * 		c) Or, no loop. 
 *  2. From the node found above, scan the loop and find the length of loop.
 *  3. Two point start different from the length of loop. 
 *     When they meet each other, current node is the entry.
 */

public class FindLoopEntry {

	public static <T> MyListNode<T> ifHasLoop(MyListNode<T> head) {
		if(head == null) {
			return null;
		}
		
		MyListNode<T> first = head;
		MyListNode<T> next = head;
		
		while(next!=null && (first==head || first!=next)) {
			first = first.next;
			if(next.next != null) {
				next = next.next.next;
			} else {
				return null;
			}
		}
		
		if(next == null) {
			return null;
		} else {
			return next;
		}
	}
	
	public static <T> MyListNode<T> findLoopEntry(MyListNode<T> head) {
		//First step
		MyListNode<T> nodeInLoop = ifHasLoop(head);
		if(nodeInLoop != null) {
			System.out.println("Have loop...");
		} else {
			System.out.println("No loop...");
			return null;
		}
		
		//Second step
		int loopCount = 0;
		MyListNode<T> tmpNode = nodeInLoop;
		while(loopCount==0 || tmpNode!=nodeInLoop) {
			loopCount++;
			tmpNode = tmpNode.next;
		}
		System.out.println("Loop count: " + loopCount);
		
		//Third step
		MyListNode<T> node1 = head;
		MyListNode<T> node2 = head;
		while(loopCount>0) {
			node2 = node2.next;
			loopCount--;
		}
		
		while(node1 != node2) {
			node1 = node1.next;
			node2 = node2.next;
		}
		
		return node1;
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
		endNode.next = node;
		
		MyListNode<Integer> entry = findLoopEntry(head);
		if(entry != null) {
			System.out.println("Entry is " + entry.getValue());
		}
	}

}
