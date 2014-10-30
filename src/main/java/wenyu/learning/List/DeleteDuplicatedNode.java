package wenyu.learning.List;

import java.util.HashSet;
import java.util.Set;

public class DeleteDuplicatedNode {

	public static <T> MyListNode<T> deleteDuplicate(MyListNode<T> head) {
		if(head == null) {
			return null;
		}
		
		Set<T> set = new HashSet<T>();
		set.add(head.getValue());
		
		MyListNode<T> node = head.next;
		MyListNode<T> pre = head;
		while(node != null) {
			if(set.contains(node.getValue())) {
				pre.next = node.next;
				node = pre.next;
			} else {
				set.add(node.getValue());
				pre = node;
				node = node.next;
			}
		}
		
		return head;
	}
	
	public static void main(String[] args) {
		MyListNode<Integer> head = DemoUtils.generateLinkedList(false, 1,1,3,5,7,6,2,8,3,5,7,9);
		DemoUtils.printList(deleteDuplicate(head));
	}

}
