package wenyu.learning.List;

public class ReconnectLink {
	public static void reconnect_problem1() {
		/*
		 * Given a linked list like a1-a2-a3-a4-b1-b2-b3-b4. 
		 * Convert it into a1-b1-a2-b2-a3-b3-a4-b4.
		 */
		MyListNode<String> header = new MyListNode<String>("a1");
		header.next = new MyListNode<String>("a2");
		header.next.next = new MyListNode<String>("a3");
		header.next.next.next = new MyListNode<String>("a4");
		MyListNode<String> splitNode = header.next.next.next.next = new MyListNode<String>("b1");
		header.next.next.next.next.next = new MyListNode<String>("b2");
		header.next.next.next.next.next.next = new MyListNode<String>("b3");
		header.next.next.next.next.next.next.next = new MyListNode<String>("b4");
//		header.next.next.next.next.next.next.next.next = new MyListNode<String>("b5");
//		header.next.next.next.next.next.next.next.next.next = new MyListNode<String>("b6");
		
		MyListNode<String> mid = header;
		MyListNode<String> node = header.next;
		while(node!=null && node.next!=null) {
			mid = mid.next;
			node = node.next.next;
		}
		
		MyListNode<String> node1 = header;
		MyListNode<String> node2 = mid.next;
		mid.next = null;
		
		while(node2!=null) {
			MyListNode<String> next1 = node1.next;
			MyListNode<String> next2 = node2.next;
			
			node2.next = node1.next;
			node1.next = node2;
			
			if(next1==null) {
				break;
			}
			
			node1 = next1;
			node2 = next2;
		}
		
		DemoUtils.printList(header);
	}
	
	
	public static void reconnect_problem2() {
		/*
		 * Given a linked list like a1-b1-a2-b2-a3-b3-a4-b4. 
		 * Convert it into a1-a2-a3-a4-b1-b2-b3-b4.
		 */
		MyListNode<String> header = new MyListNode<String>("a1");
		header.next = new MyListNode<String>("b1");
		header.next.next = new MyListNode<String>("a2");
		header.next.next.next = new MyListNode<String>("b2");
		header.next.next.next.next = new MyListNode<String>("a3");
		header.next.next.next.next.next = new MyListNode<String>("b3");
		header.next.next.next.next.next.next = new MyListNode<String>("a4");
		header.next.next.next.next.next.next.next = new MyListNode<String>("b4");
		header.next.next.next.next.next.next.next.next = new MyListNode<String>("a5");
		header.next.next.next.next.next.next.next.next.next = new MyListNode<String>("b5");
		
		if(header==null || header.next==null) {
			return;
		}
		
		MyListNode<String> head1 = header;
		MyListNode<String> head2 = header.next;
		MyListNode<String> pre1 = head1;
		MyListNode<String> pre2 = head2;
		MyListNode<String> node = head2.next;
		head1.next = null;
		head2.next = null;
		
		while(node != null) {
			pre1.next = node;
			pre2.next = node.next;
			
			pre1 = node;
			pre2 = node.next;
			pre1.next = null;
			
			if(pre2==null || pre2.next==null) {
				pre1.next = head2;
				break;
			} else {
				node = pre2.next;
				pre2.next = null;
			}
		}
		
		DemoUtils.printList(header);
	}
	public static void main(String[] args) {
		reconnect_problem1();
		System.out.println();
		reconnect_problem2();
	}

}
