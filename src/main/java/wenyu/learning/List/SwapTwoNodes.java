package wenyu.learning.List;

/*
 * Swap two given nodes in the given link
 * Logic:
 * 	Solution1: Simplest way is just swap the value of two nodes
 *  Solution2: If solution1 is not allowed.
 *  	1. Need to find the previous node of node1 and node2.
 *  	2. Then there will be three cases:
 *  		a) node1 is the next node of node2
 *  		b) node2 is the next node of node1
 *  		c) node1 and node2 are not adjacent
 */

public class SwapTwoNodes {

	public static <T> MyListNode<T> singleLinkSwap(MyListNode<T> head, MyListNode<T> node1, MyListNode<T> node2) {
		// First most easy way is just swap these two nodes' value
		// T tmp = node1.getValue();
		// node2.setValue(node2.getValue());
		// node1.setValue(tmp);
		
		//Second way, find two nodes' previous and swap them
		if(head==null || node1==null || node2==null || node1==node2) {
			return head;
		}
		
		MyListNode<T> tmpHead = new MyListNode<T>(null);
		tmpHead.next = head;
		MyListNode<T> preNode1 = null;
		MyListNode<T> preNode2 = null;
		MyListNode<T> node = head;
		
		while(node != null) {
			if(node.next == node1) {
				preNode1 = node;
			}
			if(node.next == node2) {
				preNode2 = node;
			}
			node = node.next;
		}
		
		if(preNode1==null || preNode2==null) {
			return null;
		}
		
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
		
		return tmpHead.next;
	}
	
	public static <T> MyListNode<T> doubleLinkSwap(MyListNode<T> root, MyListNode<T> node1, MyListNode<T> node2) {
		// First most easy way is just swap these two nodes' value
//		T tmp = node1.getValue();
//		node2.setValue(node2.getValue());
//		node1.setValue(tmp);
		
		//Second way, find two nodes' previous and swap them
		if(root==null || node1==null || node2==null || node1==node2) {
			return root;
		}
		
		MyListNode<T> tmpRoot = new MyListNode<T>(null);
		tmpRoot.next = root;
		root.previous = tmpRoot;
		MyListNode<T> preNode1 = node1.previous;
		MyListNode<T> preNode2 = node2.previous;
		
		if(node1.next!=node2 && node2.next!=node1) {
			// Make sure these two nodes are not adjanace
			MyListNode<T> tmp = node2.next;
			preNode1.next = node2;
			node2.previous = preNode1;
			node2.next = node1.next;
			node1.next.previous = node2;
			preNode2.next = node1;
			node1.previous = preNode2;
			node1.next = tmp;
			if(tmp != null) {
				tmp.previous = node1;
			}
		} else if(node1.next == node2){
			MyListNode<T> tmp = node2.next;
			preNode1.next = node2;
			node2.previous = preNode1;
			node2.next = node1;
			node1.previous = node2;
			node1.next = tmp;
			if(tmp != null) {
				tmp.previous = node1;
			}
		} else {
			MyListNode<T> tmp = node1.next;
			preNode2.next = node1;
			node1.previous = preNode2;
			node1.next = node2;
			node2.next = node1;
			node2.next = tmp;
			if(tmp != null) {
				tmp.previous = node2;
			}

		}
		
		tmpRoot.next.previous = null;
		return tmpRoot.next;
	}
	
	private static <T> void print(MyListNode<T> node) {
		while(node != null) {
			System.out.print(node.getValue().toString() + " --> ");
			node = node.next;
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		MyListNode<Integer> root = new MyListNode<Integer>(1);
		MyListNode<Integer> node = new MyListNode<Integer>(2);
		MyListNode<Integer> node1 = new MyListNode<Integer>(3);
		MyListNode<Integer> node2 = new MyListNode<Integer>(6);
		MyListNode<Integer> anothernode = new MyListNode<Integer>(9);

		// Single LinkDemo
		//=================================================
//		root.next = node;
//		node.next = node1;
//		node1.next = anothernode;
//		anothernode.next = node2;
//		print(singleLinkSwap(root, node1, node2));
		
		//=================================================
//		root.next = node;
//		node.next = node1;
//		node1.next = node2;
//		node2.next = anothernode;
//		print(singleLinkSwap(root, node1, node2));
		
		//=================================================
//		root.next = node;
//		node.next = node1;
//		node1.next = node2;
//		node2.next = anothernode;
//		print(singleLinkSwap(root, root, node2));
		

		// Double Link
		//=================================================
		root.next = node;
		node.previous = root;
		node.next = node1;
		node1.previous = node;
		node1.next = anothernode;
		anothernode.previous = node1;
		anothernode.next = node2;
		node2.previous = anothernode;
		print(doubleLinkSwap(root, node1, node2));
		
	}

}
