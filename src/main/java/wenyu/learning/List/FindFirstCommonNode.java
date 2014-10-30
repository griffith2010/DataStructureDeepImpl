package wenyu.learning.List;

/*
 * Find the first common node in these two nodes
 * Logic:
 * 	1. Find the length of link1;
 *  2. Find the length of link2;
 *  3. Find the different of these length above
 *  4. Two points start to step diff from the different of lengths
 *  5. When two points are equal. find this first common node
 */

public class FindFirstCommonNode {

	public static <T> MyListNode<T> findFistCommonNode(MyListNode<T> head1, MyListNode<T> head2) {
		if(head1 == null || head2 == null) {
			return null;
		}
		
		DemoUtils.printList(head1);
		DemoUtils.printList(head2);
		
		int list1Count = 0;
		int list2Count = 0;
		MyListNode<T> node1 = head1;
		MyListNode<T> node2 = head2;
		
		// Calculate the node number of list1
		while(node1!=null) {
			list1Count++;
			node1 = node1.next;
		}
		node1 = head1;
		
		// Calculate the node number of list2
		while(node2!=null) {
			list2Count++;
			node2 = node2.next;
		}
		node2 = head2;
		
		
		// Get node number different.
		int diff = list1Count - list2Count;
		// Two points start diff from the diff of length
		while(diff > 0) {
			node1 = node1.next;
			diff--;
		}
		while(diff < 0) {
			node2 = node2.next;
			diff++;
		}
		
		// Start to scan at the same tail count;
		while(node1!=null && node2!=null) {
			if(node1 == node2) {
				return node1;
			} else {
				node1 = node1.next;
				node2 = node2.next;
			}
		}
		
		return null;
	}
	
	public static void main(String[] args) {
		MyListNode<Integer> node1 = new MyListNode<Integer>(1);
		MyListNode<Integer> head1 = node1;
		node1.next = new MyListNode<Integer>(2);
		node1 = node1.next;
		node1.next = new MyListNode<Integer>(4);
		node1 = node1.next;
		node1.next = new MyListNode<Integer>(5);
		node1 = node1.next;
		
		MyListNode<Integer> node2 = new MyListNode<Integer>(10);
		MyListNode<Integer> head2 = node2;
		node2.next = new MyListNode<Integer>(8);
		node2 = node2.next;
		node2.next = new MyListNode<Integer>(7);
		node2 = node2.next;
		
		MyListNode<Integer> nodeCommon = new MyListNode<Integer>(3);
		MyListNode<Integer> commonHead = nodeCommon;
		nodeCommon.next = new MyListNode<Integer>(6);
		nodeCommon = nodeCommon.next;
		nodeCommon.next = new MyListNode<Integer>(9);
		nodeCommon = nodeCommon.next;
		nodeCommon.next = new MyListNode<Integer>(12);
		nodeCommon = nodeCommon.next;
		
		node1.next = commonHead;
		node2.next = commonHead;
		
		MyListNode<Integer> node = findFistCommonNode(head1, head2);
		if(node != null) {
			System.out.println("Common node is " + node.getValue());
		} else {
			System.out.println("No common node!");
		}
	}

}
