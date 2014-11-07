package wenyu.learning.Others;

/*
 * There are three threads in a process. 
 * The first thread prints 1 1 1 …, 
 * the second one prints 2 2 2…, 
 * and the third one prints 3 3 3 … endlessly. 
 * 
 * How do you schedule these three threads in order to print 1 2 3 1 2 3 …?
 */
public class Print123123 {
	public static Thread thread1 = new Thread() {
		public void run() {
			synchronized(this) {
				while(true) {
					try {
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.print("1 ");
				}
			}
		}
	};
	public static Thread thread2 = new Thread() {
		public void run() {
			synchronized(this) {
				while(true) {
					try {
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.print("2 ");
				}
			}
		}
	};
	public static Thread thread3 = new Thread() {
		public void run() {
			synchronized(this) {
				while(true) {
					try {
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.print("3 ");
				}
			}
		}
	};
	
	public static void mainThread() {
		int runCount = 10;
		Thread[] threads = new Thread[3];
		threads[0] = thread1;
		thread1.setDaemon(true);
		threads[1] = thread2;
		thread2.setDaemon(true);
		threads[2] = thread3;
		thread3.setDaemon(true);
		thread1.start();
		thread2.start();
		thread3.start();
		for(int i=0; i<runCount; i++) {
			synchronized(threads[i%3]) {
				threads[i%3].notify();
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		mainThread();
	}
}
