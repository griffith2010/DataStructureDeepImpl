package wenyu.learning.Heap;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

public class BuildinPriorityQueue {
	public static void usage() {
		Comparator<Integer> OrderIsdn =  new Comparator<Integer>(){  
			public int compare(Integer o1, Integer o2) {
				if(o1>o2) return -1;
				else if(o1<o2) return 1;
				else return 0;
			}
		};  
		
		Queue<Integer> minHeap = new PriorityQueue<Integer>(100);
		Queue<Integer> maxHeap = new PriorityQueue<Integer>(100, OrderIsdn);
		for(int i=0;i<10;i++) {
			int value = new Random().nextInt();
			minHeap.add(value);
			maxHeap.add(value);
		}
		
		System.out.print("Min Heap: ");
		for(int i=0;i<10;i++) {
			System.out.print(minHeap.poll() + ",");
		}
		System.out.print("\nMax Heap: ");
		for(int i=0;i<10;i++) {
			System.out.print(maxHeap.poll() + ",");
		}
	}
	
	public static void main(String[] args) {
		usage();
	}
}
