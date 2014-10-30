package wenyu.learning.List;

/*
 * Reverse the given link
 * Logic:
 * 	1. Every swap step need three nodes prepared.
 * 	   a) previous node
 *     b) current node
 *     c) next node
 *  2. Return new head
 */
public class ReverseNode {

	public static <T> MyListNode<T> reverseListRec(MyListNode<T> node, MyListNode<T> head) {
		if(node==null) {
			return null;
		}
		
		MyListNode<T> pre = reverseListRec(node.next, head);
		node.next = null;
		if(pre != null) {
			pre.next = node;
		} else {
			head.next = node;
		}
		return node;
	}
	
	public static <T> MyListNode<T> reverseList(MyListNode<T> head) {
		if(head==null) {
			return null;
		}
		
		MyListNode<T> preNode = head;
		MyListNode<T> currNode = head.next;
		head.next = null;
		
		while(currNode != null) {
			MyListNode<T> nextNode = currNode.next;
			currNode.next = preNode;
			preNode = currNode;
			currNode = nextNode;
		}
		
		return preNode;
	}
	
	public static void main(String[] args) {
		MyListNode<Integer> head = DemoUtils.generateLinkedList(false, 1,2,3,4,5,6,7,8,9);
		DemoUtils.printList(reverseList(head));
		
		head = DemoUtils.generateLinkedList(false, 1,2,3,4,5,6,7,8,9);
		MyListNode<Integer> tmpHead = new MyListNode<Integer>(null);
		reverseListRec(head, tmpHead);
		DemoUtils.printList(tmpHead.next);
		
	}

}
