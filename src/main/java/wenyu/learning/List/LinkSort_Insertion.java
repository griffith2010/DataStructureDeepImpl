package wenyu.learning.List;

/*
 * Sort the given link
 * Logic:
 * 		As I know, all the sort algorithm which is n^2 is always can be used to sort list.
		Only need to change the swap function.
		For example: insertion sorting:
 */
public class LinkSort_Insertion {
	public static <T extends Comparable<T>> MyListNode<T> insertionSorting(MyListNode<T> head) {
		if(head == null) {
			return null;
		}
		
		MyListNode<T> tmpHead = new MyListNode<T>();
		tmpHead.next = head;
		MyListNode<T> currNode = head.next;
		head.next = null;
		
		while(currNode != null) {
			MyListNode<T> next = currNode.next;
			MyListNode<T> node = tmpHead.next;
			MyListNode<T> pre = tmpHead;
			while(node!=null && node!=currNode) {
				if(currNode.getValue().compareTo(node.getValue()) < 0) {
					currNode.next = node;
					pre.next = currNode;
					break;
				}
				pre = node;
				node = node.next;
			}
			if(node == null) {
				currNode.next = pre.next;
				pre.next = currNode;
			}
			currNode = next;
		}
		
		return tmpHead.next;
	}
	
	public static void main(String[] args) {
		MyListNode<Integer> head = DemoUtils.generateLinkedList(false, 9,4,6,7,1,2,3,1,6,2,9,8);
		DemoUtils.printList(insertionSorting(head));
	}

}
