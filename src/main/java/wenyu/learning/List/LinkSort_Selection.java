package wenyu.learning.List;

public class LinkSort_Selection {
	private static <T extends Comparable<T>> void swap(MyListNode<T> node1, MyListNode<T> preNode1, MyListNode<T> node2, MyListNode<T> preNode2) {
		if(node1 == preNode2) {
			preNode1.next = node2;
			node1.next = node2.next;
			node2.next = node1;
		} else if(node2 == preNode1) {
			preNode2.next = node1;
			node2.next = node1.next;
			node1.next = node2;
		} else {
			preNode1.next = node2;
			preNode2.next = node1;
			
			MyListNode<T> tmp = node1.next;
			node1.next = node2.next;
			node2.next = tmp;
		}
	}
	
	public static <T extends Comparable<T>> MyListNode<T> selectionSort(MyListNode<T> head) {
		if(head == null) {
			return null;
		}
		
		MyListNode<T> tmpHead = new MyListNode<T>();
		tmpHead.next = head;
		MyListNode<T> currNode = head;
		MyListNode<T> preNode = tmpHead;
		while(currNode != null) {
			MyListNode<T> minPreNode = preNode;
			MyListNode<T> minNode = currNode;
			MyListNode<T> tmpNode = currNode.next;
			MyListNode<T> tmpPreNode = currNode;
			while(tmpNode != null) {
				if(tmpNode.getValue().compareTo(minNode.getValue()) < 0) {
					minNode = tmpNode;
					minPreNode = tmpPreNode;
				}
				tmpPreNode = tmpNode;
				tmpNode = tmpNode.next;
			}
			
			// Swap two nodes
			if(currNode != minNode) {
				swap(currNode, preNode, minNode, minPreNode);				
				preNode = minNode;
				currNode = minNode.next;
			} else {
				preNode = currNode;
				currNode = currNode.next;
			}
		}
		
		return tmpHead.next;
 	}
	
	public static void main(String[] args) {
		MyListNode<Integer> head = DemoUtils.generateLinkedList(false, 9,4,6,7,1,2,3,1,6,2,9,8);
		DemoUtils.printList(selectionSort(head));
	}

}
