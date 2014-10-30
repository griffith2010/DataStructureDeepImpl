package wenyu.learning.Queue;


public class MyBlockingQueue<E> implements IMyQueue<E> {
	private static int DEFAULT_SIZE = 10;
	private final Object mutex = new Object();
	public final IMyQueue<E> queue;
	private final int maxSize;

	public MyBlockingQueue(IMyQueue<E> queue, int maxSize) {
		if (queue == null) {
			throw new IllegalArgumentException("queue can't be null");
		} else if (maxSize < 0) {
			throw new IllegalArgumentException("maxSize can't be < 0");
		}

		this.queue = queue;
		this.maxSize = maxSize;
	}

	public MyBlockingQueue(IMyQueue<E> queue) {
		this(queue, DEFAULT_SIZE);
	}

	public boolean offer(E element) {
		if(element == null) {
			return false;
		}
		
		synchronized (mutex) {
			while (size() == maxSize) {
				waitForNotification();
			}
			queue.offer(element);
			mutex.notifyAll();
			return true;
		}
	}

	private synchronized void waitForNotification() {
		try {
			mutex.wait();
		} catch (InterruptedException e) {
			// Ignored
		}
	}

	public E poll() {
		synchronized (mutex) {
			while (queue.isEmpty()) {
				waitForNotification();
			}

			E value = queue.poll();
			mutex.notifyAll();
			return value;
		}
	}

	public boolean clear() {
		synchronized (mutex) {
			queue.clear();
			mutex.notifyAll();
			return true;
		}
	}

	public int size() {
		synchronized (mutex) {
			return queue.size();
		}
	}

	public boolean isEmpty() {
		synchronized (mutex) {
			return queue.isEmpty();
		}
	}

	public E peek() {
		return queue.peek();
	}
}