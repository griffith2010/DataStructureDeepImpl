package wenyu.learning.Queue;

import java.util.Random;

public class MyQueueUsage {
	private static void usage(IMyQueue<Integer> queue) {
		Random random = new Random();
		System.out.println("Queue Empty: " + queue.isEmpty());
		for(int i=0;i<3;i++) {
			queue.offer(random.nextInt());
		}
		System.out.println("Current size: " + queue.size());
		System.out.println("Queue Empty: " + queue.isEmpty());
		
		System.out.println("Poll first element: " + queue.poll());
		System.out.println("Current size: " + queue.size());
		System.out.println("Poll first element: " + queue.poll());
		System.out.println("Current size: " + queue.size());
		System.out.println("Poll first element: " + queue.poll());
		System.out.println("Current size: " + queue.size());
		
		System.out.println("Peek first element: " + queue.peek());
		System.out.println("Current size: " + queue.size());
		
		for(int i=0;i<3;i++) {
			queue.offer(random.nextInt());
		}
		System.out.println("Current size: " + queue.size());
		
		System.out.println("Clear queue: " + queue.clear());
		System.out.println("Queue Empty: " + queue.isEmpty());
	}
	
	public static void arrayQueue() {
		IMyQueue<Integer> arrQueue = new MyArrayQueue<Integer>();
		usage(arrQueue);
	}
	
	public static void linkedQueue() {
		IMyQueue<Integer> linkedQueue = new MyLinkedQueue<Integer>();
		usage(linkedQueue);
	}
	
	public static void circularQueue() {
		IMyQueue<Integer> circularQueue = new MyCircularQueue<Integer>();
		usage(circularQueue);
	}
	
	public static void blockingQueue() {
		final int maxSize = 10;
		final IMyQueue<Integer> blockingQueue = new MyBlockingQueue<Integer>(new MyLinkedQueue<Integer>(), maxSize);
		Thread producer = new Thread() {
			public void run() {
				Random random = new Random();
				for(int i=0; i<maxSize*3; i++) {
					int ele = random.nextInt();
					System.out.println("Produce: " + ele);
					blockingQueue.offer(ele);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		
		Thread consumer = new Thread() {
			public void run() {
				for(int i=0; i<maxSize*3; i++) {
					int ele = blockingQueue.poll();
					System.out.println("Consume: " + ele);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		
		producer.start();
		consumer.start();
	}
	
	public static void main(String[] args) {
//		arrayQueue();
//		linkedQueue();
//		circularQueue();
		blockingQueue();
	}
	
}
