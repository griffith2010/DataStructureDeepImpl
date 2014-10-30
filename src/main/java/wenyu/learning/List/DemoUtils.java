package wenyu.learning.List;

public class DemoUtils {
	public static <T> void printList(MyListNode<T> list) {
		if(list == null) {
			System.out.println("Link: []");
			return;
		}
		
		StringBuilder output = new StringBuilder("Link: [");
		while(list!=null) {
			output.append(list.getValue());
			output.append(", ");
			list = list.next;
		}
		if(output.length()>7) {
			System.out.println(output.substring(0, output.length()-2) + "]");
		} else {
			System.out.println(output.toString() + "]");
		}
	}
	
	public static <T> MyListNode<T> generateLinkedList(boolean ifDoubleLink, T ...items) {
		if(items.length <= 0) {
			return null;
		}
		
		MyListNode<T> head = new MyListNode<T>(items[0]);
		MyListNode<T> pre = head;
		for(int i=1;i<items.length;i++) {
			MyListNode<T> node = new MyListNode<T>(items[i]);
			pre.next = node;
			if(ifDoubleLink) {
				node.previous = pre;
			}
			pre = node;
		}
		return head;
	}
}
