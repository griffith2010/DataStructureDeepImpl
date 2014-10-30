package wenyu.learning.Queue;

import wenyu.learning.Stack.IMyStack;
import wenyu.learning.Stack.MyArrayStack;

public class QueueSortingUtils {

	public static <E extends Comparable<E>> void sortWithStack(IMyQueue<E> queue) {
		if(queue.isEmpty()) {
			return;
		}
		
		IMyStack<E> auxStack = new MyArrayStack<E>();
		while(!queue.isEmpty()) {
			E element = queue.poll();
			while(!auxStack.isEmpty() && auxStack.peek().compareTo(element)<0) {
				queue.offer(auxStack.pop());
			}
			auxStack.push(element);
		}
		
		// Put items back to queue
		while(!auxStack.isEmpty()) {
			queue.offer(auxStack.pop());
		}
	}
	
	public static <E extends Comparable<E>> void sortWithQueue(IMyQueue<E> queue) {
		if(queue.isEmpty()) {
			return;
		}
		
		IMyQueue<E> auxQue = new MyLinkedQueue<E>();
		while(!queue.isEmpty()) {
			// First get current element from queue
			E element = queue.poll();
			// Then, get current smallest item (CSI)
			// if auxQueue is empty, CSI is current item from queue;
			// if auxQueue is not empty and auxQueue's peek is smaller than current item from queue, CSI is this item;
			// if auxQueue is not empty and auxQueue's peek is bigger than current item from queue, CSI is current item from queue;
			E currMin = (auxQue.peek()!=null && auxQue.peek().compareTo(element)<=0)?auxQue.peek():element;
			
			// put items smaller than item from queue to the end of auxQueue
			int size = auxQue.size();
			while(!auxQue.isEmpty() && auxQue.peek().compareTo(element)<=0 && size>0) {
				auxQue.offer(auxQue.poll());
				size--;
			}
			
			// put current item from queue to the end of auxQueue
			auxQue.offer(element);
			
			// continue to put frontend items which is bigger than current item from queue after current item in auxQueue
			while(auxQue.peek().compareTo(currMin)!=0) {
				auxQue.offer(auxQue.poll());
			}
		}
		
		// Put items back to queue
		while(!auxQue.isEmpty()) {
			queue.offer(auxQue.poll());
		}
	}
}
