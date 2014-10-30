package wenyu.learning.List;

/*
 * Given two sorted links' head, implement a function to merge them
 * Logic:
 * 	1. Solution1: recursion
 *  2. Solution2: Non-recursion, same as merge two sorted arrays
 */
public class MergeTwoSortedLinks {

	public static <T extends Comparable<T>> MyListNode<T> mergeListsWithRecursion(MyListNode<T> node1, MyListNode<T> node2) {
		if(node1==null && node2==null) {
			return null;
		}
		
		if(node1==null && node2!=null) {
			return node2;
		} else if(node1!=null && node2==null) {
			return node1;
		} else if(node1.getValue().compareTo(node2.getValue()) <= 0) {
			node1.next = mergeListsWithRecursion(node1.next, node2);
			return node1;
		} else {
			node2.next = mergeListsWithRecursion(node1, node2.next);
			return node2;
		}
	}
	
	public static <T extends Comparable<T>> MyListNode<T> mergeLists(MyListNode<T> head1, MyListNode<T> head2) {
		if(head1==null && head2==null) {
			return null;
		}

		MyListNode<T> node1 = head1;
		MyListNode<T> node2 = head2;
		MyListNode<T> tmpHead = new MyListNode<T>(null);
		MyListNode<T> preNode = tmpHead;
		
		while(node1!=null && node2!=null) {
			if(node1.getValue().compareTo(node2.getValue()) <= 0) {
				preNode.next = node1;
				preNode = node1;
				node1 = node1.next;
			} else if(node1.getValue().compareTo(node2.getValue()) > 0) {
				preNode.next = node2;
				preNode = node2;
				node2 = node2.next;
			}
		}
		
		while(node1 != null) {
			preNode.next = node1;
			preNode = node1;
			node1 = node1.next;
		}
		
		while(node2 != null) {
			preNode.next = node2;
			preNode = node2;
			node2 = node2.next;
		}
		
		return tmpHead.next;
	}
	
	public static void main(String[] args) {
		MyListNode<Integer> head1 = DemoUtils.generateLinkedList(false, 1,3,5,7,9);
		MyListNode<Integer> head2 = DemoUtils.generateLinkedList(false, 1,2,4,6,8,9);
		DemoUtils.printList(mergeLists(head1, head2));
		

		head1 = DemoUtils.generateLinkedList(false, 1,3,5,7,9);
		head2 = DemoUtils.generateLinkedList(false, 1,2,4,6,8,9);
		DemoUtils.printList(mergeListsWithRecursion(head1, head2));
	}

}
