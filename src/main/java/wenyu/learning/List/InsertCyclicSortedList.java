package wenyu.learning.List;

/*
 * Problem 1: Insert a new node into a cyclic sorted link. Head is given.
 * Logic:
 * 		1) Linked List is empty
 * 		2) New node is to be inserted just before the head node
 * 		3) New node is to be inserted somewhere after the head
 * 
 * Problem 2: Insert a new node into a cyclic sorted link. Any node in link is given.
 */

public class InsertCyclicSortedList {

	public static <E extends Comparable<E>> MyListNode<E> problem1(MyListNode<E> head, E newEle) {
		MyListNode<E> newNode = new MyListNode<E>(newEle);
		
		// Case 1: Link is empty
		if(head == null) {
			newNode.next = newNode;
			return newNode;
		}
		
		// Case 2: New node is before head
		MyListNode<E> currNode = head;
		MyListNode<E> nextNode = head.next;
		while(true) {
			if(newEle.compareTo(currNode.getValue()) == 0) {
				// new value equal to current value
				newNode.next = nextNode;
				currNode.next = newNode;
				break;
			} else if(newEle.compareTo(currNode.getValue())>0 && newEle.compareTo(nextNode.getValue())<0) {
				// new value is between current value and next value
				newNode.next = nextNode;
				currNode.next = newNode;
				break;
			} else {
				if(newEle.compareTo(currNode.getValue())>0 && newEle.compareTo(nextNode.getValue())>0 && nextNode==head) {
					// new value is bigger than last value
					newNode.next = nextNode;
					currNode.next = newNode;
					break;
				}
				
				if(newEle.compareTo(currNode.getValue())<0 && newEle.compareTo(nextNode.getValue())<0 && nextNode==head) {
					// new value is smaller than head value
					newNode.next = nextNode;
					currNode.next = newNode;
					break;
				}
			}
			
			currNode = nextNode;
			nextNode = currNode.next;
		}
		return head;
	}
	
	public static <E extends Comparable<E>> MyListNode<E> problem2(MyListNode<E> node, E newEle) {
		MyListNode<E> newNode = new MyListNode<E>(newEle);
		
		// Case 1: Link is empty
		if(node == null) {
			newNode.next = newNode;
			return newNode;
		}
		
		// Case 2: New node is before head
		MyListNode<E> currNode = node;
		MyListNode<E> nextNode = node.next;
		while(true) {
			if(newEle.compareTo(currNode.getValue()) == 0) {
				// new value equal to current value
				newNode.next = nextNode;
				currNode.next = newNode;
				break;
			} else if(newEle.compareTo(currNode.getValue())>0 && newEle.compareTo(nextNode.getValue())<0) {
				// new value is between current value and next value
				newNode.next = nextNode;
				currNode.next = newNode;
				break;
			} else {
				if(newEle.compareTo(currNode.getValue())>0 && newEle.compareTo(nextNode.getValue())>0 && nextNode.getValue().compareTo(currNode.getValue())<0) {
					// new value is bigger than last value
					newNode.next = nextNode;
					currNode.next = newNode;
					break;
				}
				
				if(newEle.compareTo(currNode.getValue())<0 && newEle.compareTo(nextNode.getValue())<0 && nextNode.getValue().compareTo(currNode.getValue())<0) {
					// new value is smaller than head value
					newNode.next = nextNode;
					currNode.next = newNode;
					break;
				}
				
				// The difference between problem 1
				if(currNode == nextNode) {
					// if has only one node in link
					currNode.next = newNode;
					newNode.next = currNode;
					break;
				}
			}
			
			currNode = nextNode;
			nextNode = currNode.next;
		}
		return node;
	}
	
	private static <E> void prepareLink(MyListNode<E> head) {
		if(head == null) {
			return;
		}
		MyListNode<E> node = head;
		while(node!=null) {
			if(node.next == null) {
				node.next = head;
				break;
			}
			node = node.next;
		}
		DemoUtils.printCyclicList(head);
	}
	
	public static void main(String[] args) {
		MyListNode<Integer> head = DemoUtils.generateLinkedList(false, 1,2,3,5,6,7,8,9);
		prepareLink(head);
		head = problem2(head.next.next, new Integer(4));
		DemoUtils.printCyclicList(head);
	}

}
