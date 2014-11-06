package wenyu.learning.List;

import java.util.Stack;

/*
 * Given a singly linked list of characters, write a function that returns true if the given list is palindrome, else false.
 * Solution 1: Using stack O(n)/O(n)
 *			   1) Traverse the given list from head to tail and push every visited node to stack.
 *			   2) Traverse the list again. For every visited node, pop a node from stack and compare data of 
 *				  popped node with currently visited node.
 *			   3) If all nodes matched, then return true, else false.
 * Solution 2: By reversing the list O(n)/O(1)
 * 			   1) Get the middle of the linked list.
 * 			   2) Reverse the second half of the linked list.
 * 			   3) Check if the first half and second half are identical.
 * 			   4) Construct the original linked list by reversing the second half again and attaching it back to the first half.
 * Solution 3: Using Recursion
 * 			   Use two pointers left and right. Move right and left using recursion and check for following in each recursive call.
 * 			   1) Sub-list is palindrome.
 * 			   2) Value at current left and right are matching.
 */
public class palindromeCheck {

	public static <E> boolean solution1(MyListNode<E> head) {
		if(head == null) {
			return true;
		}
		
		Stack<MyListNode<E>> stack = new Stack<MyListNode<E>>();
		MyListNode<E> node = head;
		while(node!=null) {
			stack.add(node);
			node = node.next;
		}
		
		node = head;
		while(node!=null && !stack.isEmpty()) {
			MyListNode<E> popNode = stack.pop();
			if(!node.getValue().equals(popNode.getValue())) {
				return false;
			}
			node = node.next;
		}
		
		return true;
	}
	
	public static <E> boolean solution2(MyListNode<E> head) {
		if(head==null || head.next==null) {
			return true;
		}
		
		// Step 1: find middle node
		MyListNode<E> first = head;
		MyListNode<E> next = head;
		while(next != null) {
			if(next.next==null) {
				break;
			} else if(next.next.next==null) {
				next = next.next;
				break;
			}
			first = first.next;
			next = next.next.next;
		}
		
		// Reverse nodes after middle
		first.next = ReverseNode.reverseList(first.next);
		
		next = first.next;
		first = head;
		while(next!=null && first!=null) {
			if(!first.getValue().equals(next.getValue())) {
				return false;
			}
			first = first.next;
			next = next.next;
		}
		
		return true;
	}

	public static <E> boolean solution3(MyListNode<E> head) {
		MyListNode<E> tmp = solution3_core(head, head);
		if(tmp == null) {
			return false;
		} else {
			return true;
		}
	}
	private static <E> MyListNode<E> solution3_core(MyListNode<E> node1, MyListNode<E> node2) {
		if(node1 == null) {
			return node2;
		}
		MyListNode<E> node = solution3_core(node1.next, node2);
		if(node==null) {
			return null;
		} else if(!node.getValue().equals(node1.getValue())) {
			return null;
		} else {
			return node.next==null ? node : node.next;
		}
	}
	
	public static void main(String[] args) {
		MyListNode<Integer> head = DemoUtils.generateLinkedList(false, 1,2,3,5,6,5,3,2,1);
		System.out.println(solution1(head));
		
		head = DemoUtils.generateLinkedList(false, 1,2,3,5,6,5,3,2,1);
		System.out.println(solution2(head));
		
		head = DemoUtils.generateLinkedList(false, 1,2,3,5,6,5,3,2,1);
		System.out.println(solution3(head));
	}

}
