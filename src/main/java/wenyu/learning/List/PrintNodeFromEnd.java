package wenyu.learning.List;

import java.util.Stack;

/*
 * Print the link from the end.
 * Logic:
 * 	1. Using recursion, go into the next recursion and then print current node
 *  2. Non-recursion, using stack to store nodes
 */
public class PrintNodeFromEnd {

	public static void printWithStack(MyListNode<?> head) {
		// if not allowed, using stack to print it.
		Stack<MyListNode<?>> stack = new Stack<MyListNode<?>>();
		MyListNode<?> node = head;
		while(node != null) {
			stack.push(node);
			node = node.next;
		}

		System.out.print("Reversed Link: [");
		while(!stack.isEmpty()) {
			node = stack.pop();
			System.out.print(node.getValue() + " ");
		}
		System.out.print("]");
	}
	
	public static void printWithRec(MyListNode<?> node, int pos) {
		if(node == null) {
			System.out.print("Reversed Link: [");
			return;
		}
		
		printWithRec(node.next, pos+1);
		if(pos == 0) {
			System.out.print(node.getValue() + "]");
		} else {
			System.out.print(node.getValue() + " ");
		}
	}
	
	public static void printFromEnd(MyListNode<?> head, boolean allowStructureChange) {
		if(head == null) {
			return;
		}
		
		if(allowStructureChange) {
			head = ReverseNode.reverseList(head);
			DemoUtils.printList(head);
		} else {
			//printWithStack(head);
			
			printWithRec(head, 0);
		}
		
	}
	
	public static void main(String[] args) {
		MyListNode<Integer> head = DemoUtils.generateLinkedList(false, 1,2,3,4,5,6,7,8,9);
		
		printFromEnd(head, false);
	}
}
