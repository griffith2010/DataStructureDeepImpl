package wenyu.learning.List;

/*
 * Find the last Kth node in the given link
 * Logic:
 * 	two points start different from the k steps.
 * 	!!! Be careful about the edge cases
 */
public class TailKthNode {

	public static <T> MyListNode<T> tailKthNode(MyListNode<T> head, int k) {
		if(head==null || k<=0) {
			return null;
		}
		
		int lastCount = k;
		MyListNode<T> point1 = head;
		MyListNode<T> point2 = head;
		
		while(--k>0 && point2!=null) {
			point2 = point2.next;
		}
		if(point2 == null) {
			System.out.println("No so many nodes in the list...");
			return null;
		}
		
		while(point2.next!=null) {
			point1 = point1.next;
			point2 = point2.next;
		}
		
		System.out.println("Last " + lastCount + "th node is " + point1.getValue());
		return point1;
	}
	
	public static void main(String[] args) {
		MyListNode<Integer> head = DemoUtils.generateLinkedList(false, 1,2,3,4,5,6,7,8,9);
		tailKthNode(head, 3);
	}
}
