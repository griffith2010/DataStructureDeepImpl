package wenyu.learning.List;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

/*
 * A link list contains following elements
	struct node{
	    int data;
	    node* next;
	    node* random;
	}
	Given head of such a linked list write a function who copies such a linked list and returns the head of the new list.
 */
public class CloneComplexList {

	public static <T> MyListNode<T> clone_solution1(MyListNode<T> head) {
		/*
		 * Solution1: O(n^2)
		 * Brute Force
		 * 1. clone every node in the original list O(n)
		 *    and link all cloned nodes with next pointers.
		 * 2. set m_pSibling links on the cloned list. O(n^2)
		 * 		Let’s suppose m_pSibling of node N points to node S in the original list, and the cloned 
		 * 		node of N is N'. If the distance between the head node and node S in the original list is S, the distance
		 * 		between the head node and node S', referenced by m_pSibling of N' in the cloned list, should also be s
		 */
		MyListNode<T> newHead = null;
		MyListNode<T> pre = null;
		MyListNode<T> node = head;
		while(node!=null) {
			if(newHead == null) {
				newHead = new MyListNode<T>(node.getValue());
				pre = newHead;
			} else {
				MyListNode<T> newNode = new MyListNode<T>(node.getValue());
				pre.next = newNode;
				pre = newNode;
			}
			node = node.next;
		}
		
		node = head;
		MyListNode<T> newNode = newHead;
		while(node!=null) {
			MyListNode<T> tmpNode = head;
			MyListNode<T> tmpNewNode = newHead;
			while(tmpNode!=null && tmpNode != node.random) {
				tmpNode = tmpNode.next;
				tmpNewNode = tmpNewNode.next;
			}
			newNode.random = tmpNewNode;
			
			node = node.next;
			newNode = newNode.next;
		}
		
		return newHead;
	}
	
	public static <T> MyListNode<T> clone_solution2(MyListNode<T> head) {
		/*
		 * Solution2: O(n) and Space O(n)
		 * HashMap
		 * The second solution sacrifices space efficiency 
		 * for time efficiency. It needs a hash table with 
		 * O(n) size if there are n nodes in a list, and
		 * it reduces the time complexity to O(n) from O(n2)
		 * 
		 * 	1. it creates a node N' and clones data from every node N in 
		 *     the original list and links all cloned nodes together with 
		 *     m_pNext pointers.
		 *  2. all node pairs <N, N'> are saved in a hash table where N is 
		 *  a key and N' is a value. The second step is also to set random 
		 *  links on the cloned list. Suppose random of node N points to node 
		 *  S in the original list and their corresponding nodes are N' and S' 
		 *  in the cloned list  
		 */
		HashMap<MyListNode<T>, MyListNode<T>> map = new HashMap<MyListNode<T>, MyListNode<T>>();
		MyListNode<T> newHead = null;
		MyListNode<T> pre = null;
		MyListNode<T> node = head;
		while(node!=null) {
			if(newHead == null) {
				newHead = new MyListNode<T>(node.getValue());
				pre = newHead;
				map.put(head, newHead);
			} else {
				MyListNode<T> newNode = new MyListNode<T>(node.getValue());
				pre.next = newNode;
				pre = newNode;
				map.put(node, newNode);
			}
			node = node.next;
		}
		
		Iterator<Entry<MyListNode<T>,MyListNode<T>>> it = map.entrySet().iterator();
		while(it.hasNext()) {
			Entry<MyListNode<T>,MyListNode<T>> entry = it.next();
			MyListNode<T> oldNode = entry.getKey();
			MyListNode<T> newNode = entry.getValue();
			newNode.random = map.get(oldNode.random);
		}
		
		return newHead;
	}
	
	public static <T> MyListNode<T> clone_solution3(MyListNode<T> head) {
		/*
		 * Solution3: O(n)
		 * 1. creates new nodes and clones the data of the original nodes
		 * 2. the cloned node N' is linked next to the original node N
		 * 3. set random point to the previous node's random's next point
		 * 	  (node.next.random = node.random.next)
		 * 4. remove the old node which is in the odd position
		 * 5. return newHead
		 */
		if(head == null) {
			return null;
		}
		
		MyListNode<T> node = head;
		while(node != null) {
			MyListNode<T> newNode = new MyListNode<T>();
			newNode.setValue(node.getValue());
			newNode.next = node.next;
			node.next = newNode;
			node = node.next.next;
		}
		
		node = head;
		while(node != null) {
			MyListNode<T> newNode = node.next;
			if(node.random != null) {
				newNode.random = node.random.next;
			}
			node = newNode.next;
		}
		
		MyListNode<T> tmpHead = new MyListNode<T>();
		MyListNode<T> preNode = tmpHead;
		node = head;
		while(node != null) {
			MyListNode<T> newNode = node.next;
			preNode.next = newNode;
			preNode = newNode;
			node.next = newNode.next;
			node = node.next;
		}
		
		return tmpHead.next;
	}
	
	public static void main(String[] args) {
		MyListNode<Integer> head = new MyListNode<Integer>(1);
		MyListNode<Integer> node2 = new MyListNode<Integer>(8);
		MyListNode<Integer> node3 = new MyListNode<Integer>(3);
		MyListNode<Integer> node5 = new MyListNode<Integer>(6);
		MyListNode<Integer> node6 = new MyListNode<Integer>(9);
		head.next = node2;
		node2.next = node3;
		node3.next = node5;
		node5.next = node6;
		head.random = node2;
		node2.random = node6;
		node5.random = node2;
		
		MyListNode<Integer> newHead = clone_solution1(head);
		System.out.println("=======================================");
		newHead = clone_solution2(head);
		System.out.println("=======================================");
		newHead = clone_solution3(head);
		System.out.println("=======================================");
	}

}
