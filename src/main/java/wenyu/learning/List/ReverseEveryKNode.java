package wenyu.learning.List;

/*
 * Reverse every k node in the link
 * Logic: 
 * 	1. Scan the link from the head, and count k every time
 *  2. Passed in the current start node and end node
 *  	a) reverse current sub-link
 *      b) current end becomes the new start of sub-link
 *      c) current start becomes the new end of sub-link
 *  3. tmpPre's next point the new start if tmpPre is not null 
 *     or the new start will be the new head of link    
 *  4. new end's next will be the tmpNext   
 *  5. next start node to be reverse will be the tmpNext
 */
public class ReverseEveryKNode {
	
	private static <T> void reverseList(MyListNode<T> start, MyListNode<T> end) {
		if(start==end) {
			return;
		}
		
		MyListNode<T> preNode = start;
		MyListNode<T> currNode = start.next;
		
		while(currNode!=null) {
			MyListNode<T> nextNode = currNode.next;
			currNode.next = preNode;
			if(currNode == end) {
				return;
			} else {
				preNode = currNode;
				currNode = nextNode;
			}
		}
	}
	
	public static <T> MyListNode<T> reverseEveryKList(MyListNode<T> head, int k) {
		if(head == null ) {
			return null;
		}
		if(k <= 1) {
			return head;
		}
		
		int currCount = 0;
		
		MyListNode<T> tmpHead = new MyListNode<T>(null);
		MyListNode<T> last = tmpHead;
		MyListNode<T> start = head;
		MyListNode<T> end = head;
		while(true) {
			if(currCount==k-1 || end.next==null) {
				MyListNode<T> next = end.next;
				reverseList(start, end);
				last.next = end;
				last = start;
				start.next = null;
				start = next;
				end = next;
				currCount = 0;
				if(end == null) {
					return tmpHead.next;
				}
			} else {
				currCount++;
				end = end.next;
			}
		}
	}
	
	public static void main(String[] args) {
		MyListNode<Integer> head = DemoUtils.generateLinkedList(false, 1,2,3,4,5,6,7,8,9);
		
		DemoUtils.printList(reverseEveryKList(head, 3));
	}

}
