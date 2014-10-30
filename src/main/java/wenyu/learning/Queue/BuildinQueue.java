package wenyu.learning.Queue;

import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Iterator;
import java.util.Queue;
import java.util.LinkedList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

class LinkedListUsage {
	public static void demo() {
		Queue<String> queue = new LinkedList<String>();

		if(queue.isEmpty()) {
			System.out.println("Queue is empty...");
		}
		
		queue.offer("First"); //Offer call add inside the LinkedList.
		queue.offer("Second");
		queue.add("Third");
		queue.offer("Forth");
		QueueDemoUtils.printQueue(queue);
		
		System.out.println("Queue.peek: " + queue.peek());
		QueueDemoUtils.printQueue(queue);
		
		System.out.println("Queue.poll: " + queue.poll());
		QueueDemoUtils.printQueue(queue);
		
		// This method differs from poll only in that 
		// it throws an exception if this queue is empty.
		System.out.println("Queue.remove: " + queue.remove()); 
		QueueDemoUtils.printQueue(queue);
		
		System.out.println("Queue.toString: " + queue.toString());
	}
}

class ArrayQueueUsage {
	public static void demo() {
		Deque<String> queue = new ArrayDeque<String>();
		queue.offer("First"); //Offer call add inside the LinkedList.
		queue.offer("Second");
		queue.add("Third");
		queue.offer("Forth");
		queue.offerFirst("Zero");
		queue.offerLast("Sixth");
		QueueDemoUtils.printQueue(queue);
		
		System.out.println("Queue.peek: " + queue.peek());
		QueueDemoUtils.printQueue(queue);
		
		System.out.println("Queue.poll: " + queue.poll());
		QueueDemoUtils.printQueue(queue);
		
		// This method differs from poll only in that 
		// it throws an exception if this queue is empty.
		System.out.println("Queue.remove: " + queue.remove()); 
		QueueDemoUtils.printQueue(queue);
		
		System.out.println("Queue.pollFirst: " + queue.pollFirst());
		QueueDemoUtils.printQueue(queue);
		
		System.out.println("Queue.pollLast: " + queue.pollLast());
		QueueDemoUtils.printQueue(queue);
		
		System.out.println("Queue.peekFirst: " + queue.peekFirst());
		System.out.println("Queue.peekLast: " + queue.peekLast());
		
		System.out.println(Arrays.toString(queue.toArray()));	
	}
}

class BuildinBlockingQueue {
	private static BlockingQueue<String> blockQueue;
	
	public static void synchronousQueueDemo() throws InterruptedException {
		/**
		 * SynchronousQueue is special kind of BlockingQueue in which each insert operation must wait 
		 * for a corresponding remove operation by another thread, and vice versa. When you call put() 
		 * method on SynchronousQueue it blocks until another thread is there to take that element out 
		 * of the Queue. Similarly, if a thread tries to remove an element and no element is currently 
		 * present, that thread is blocked until another thread puts an element into the queue.
		 */
		class MyThread extends Thread {
			BlockingQueue<String> blockQueue;
			boolean flag;
			public MyThread(BlockingQueue<String> blockQueue, boolean flag) {
				this.blockQueue = blockQueue;
				this.flag = flag;
			}
			private void produce() {
				System.out.println(Thread.currentThread().getName() + " putting value.");
				try {
					blockQueue.put("Value");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + " finish putting value");
			}
			private void consume() {
				System.out.println(Thread.currentThread().getName() + " taking value");
				try {
					blockQueue.take();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + " finish taking value");
			}
			public void run() {
				if(flag) {
					produce();
					consume();
				} else {
					consume();
					produce();
				}
			}
		}
		blockQueue = new SynchronousQueue<String>();
		MyThread thread1 = new MyThread(blockQueue, true);
		MyThread thread2 = new MyThread(blockQueue, false);
		thread1.start();
		thread2.start();
		thread1.join();
		thread2.join();
	}
	
	public static void arrayBlockingQueueDemo() {
		int initialCapacity = 2;
		blockQueue = new ArrayBlockingQueue<String>(initialCapacity);
	}
	
	public static void linkedBlockingQueueDemo() {
		blockQueue = new LinkedBlockingQueue<String>();
		// used in Producer And Consumer Sample.
	}
}

class DelayedQueueUsage {
	static class MyDelayed<T> implements java.util.concurrent.Delayed {
		public long delayTime;
		private long insertionTime;
		private T value;
		public MyDelayed(T value, long delayTime, TimeUnit unit) {
			this.delayTime = TimeUnit.MILLISECONDS.convert(delayTime, unit);
			this.value = value;
			this.insertionTime = System.currentTimeMillis();
		}
		public int compareTo(Delayed o) {
			@SuppressWarnings("unchecked")
			MyDelayed<T> comparator = (MyDelayed<T>) o;
			long sourceTime = delayTime; 
			long comparatorTime = comparator.delayTime; 
			return (sourceTime<=comparatorTime)?-1:1;
		}
		public long getDelay(TimeUnit unit) {
			return insertionTime+delayTime-System.currentTimeMillis(); 
		}
		public String toString() {
			return value.toString();
		}
		
	}
	
	public static void demo() throws InterruptedException {
		/**
		 * 1. DelayQueue is an unbounded queue. It extends Delayed interface.
		 * 2. Element from DelayQueue can only be taken when its delay has expired.
		 * 3. At the head of the queue , element with furthest expired delay time is found.
		 * 4. An element is expired when its getDelay() method returns a value less than or equal to zero.
		 * 5. Null is not permitted in DelayQueue.
		 * 6. In DelayQueue, only those object can be inserted which implements Delayed interface. 
		 */
		
		DelayQueue<MyDelayed<Integer>> delayedQueue = new DelayQueue<MyDelayed<Integer>>();
		delayedQueue.put(new DelayedQueueUsage.MyDelayed<Integer>(1, 9, TimeUnit.SECONDS));
		delayedQueue.put(new DelayedQueueUsage.MyDelayed<Integer>(3, 7, TimeUnit.SECONDS));
		delayedQueue.put(new DelayedQueueUsage.MyDelayed<Integer>(5, 5, TimeUnit.SECONDS));
		delayedQueue.put(new DelayedQueueUsage.MyDelayed<Integer>(7, 3, TimeUnit.SECONDS));
		delayedQueue.put(new DelayedQueueUsage.MyDelayed<Integer>(9, 1, TimeUnit.SECONDS));
		
		while(!delayedQueue.isEmpty()) {
			System.out.println("Current: " + delayedQueue.toString());
			System.out.println("Take: " + delayedQueue.take());
			Thread.sleep(1000);
		}
	}
}


public class BuildinQueue {
	public static void main(String[] args) throws Exception {
//		LinkedListUsage.demo();
//		ArrayQueueUsage.demo();
//		BuildinBlockingQueue.synchronousQueueDemo();
		DelayedQueueUsage.demo();
	}
}
