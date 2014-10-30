package wenyu.learning.Stack;

import java.util.Arrays;

public class ThreeStacksWithinSingleArrayS1<E> {

	private static final String STACK1 = "stack1";
	private static final String STACK2 = "stack2";
	private static final String STACK3 = "stack3";
	
	private final int DEFAULT_SIZE = 10;
	private final float DEFAULT_INCREMENT = 2.0f;
	private E[] array;
	private int tail1;
	private int tail2;
	private int tail3;
	private static Object lock = new Object();
	
	@SuppressWarnings("unchecked")
	public ThreeStacksWithinSingleArrayS1() {
		if(array==null) {
			array = (E[]) new Object[DEFAULT_SIZE];
		}
		tail1 = 0;
		tail2 = 1;
		tail3 = 2;
	}
	
	public void push(String stack, E element) {
		synchronized(lock) {
			if(STACK1.equals(stack)) {
				array[tail1] = element;
				tail1 += 3;
				if(tail1 >= array.length) {
					resize();
				}
			} else if (STACK2.equals(stack)) {
				array[tail2] = element;
				tail2 += 3;
				if(tail2 >= array.length) {
					resize();
				}
			} else if (STACK3.equals(stack)) {
				array[tail3] = element;
				tail3 += 3;
				if(tail3 >= array.length) {
					resize();
				}
			}
		}
	}
	
	public synchronized E pop(String stack) {
		synchronized(lock) {
			if(!isEmpty(stack)) {
				if(STACK1.equals(stack)) {
					tail1 -= 3;
					E ele = array[tail1];
					array[tail1]=null;
					return ele;
				} else if (STACK2.equals(stack)) {
					tail2 -= 3;
					E ele = array[tail2];
					array[tail2]=null;
					return ele;
				} else if (STACK3.equals(stack)) {
					tail3 -= 3;
					E ele = array[tail3];
					array[tail3]=null;
					return ele;
				} else {
					return null;
				}
			} else {
				return null;
			}
		}
	}
	
	public boolean isEmpty(String stack) {
		synchronized(lock) {
			if(STACK1.equals(stack)) {
				return tail1==0;
			} else if (STACK2.equals(stack)) {
				return tail2==1;
			} else if (STACK3.equals(stack)) {
				return tail3==2;
			} else {
				return true;
			}
		}
	}
	
	public int size(String stack) {
		if(STACK1.equals(stack)) {
			return tail1/3;
		} else if (STACK2.equals(stack)) {
			return (tail2-1)/3;
		} else if (STACK3.equals(stack)) {
			return (tail3-2)/3;
		} else {
			return -1;
		}
	}
	
	@SuppressWarnings("unchecked")
	private synchronized void resize() {
		synchronized(lock) {
			if(tail1>=array.length || tail2>=array.length || tail3>=array.length) {
				int oldSize = array.length;
	            int newSize = (int)Math.ceil(oldSize*DEFAULT_INCREMENT);    
	            array = Arrays.copyOf(array, newSize);
			}
		}
	}
	
	public static void main(String[] args) {
		ThreeStacksWithinSingleArrayS1<Integer> stacks = new ThreeStacksWithinSingleArrayS1<Integer>();
		stacks.push(ThreeStacksWithinSingleArrayS1.STACK1, 3);
		stacks.push(ThreeStacksWithinSingleArrayS1.STACK1, 6);
		stacks.push(ThreeStacksWithinSingleArrayS1.STACK1, 8);
		stacks.push(ThreeStacksWithinSingleArrayS1.STACK1, 9);
		System.out.println("Stack1 size: " + stacks.size(STACK1));
		
		stacks.push(ThreeStacksWithinSingleArrayS1.STACK2, 3);
		stacks.push(ThreeStacksWithinSingleArrayS1.STACK2, 6);
		stacks.push(ThreeStacksWithinSingleArrayS1.STACK2, 8);
		stacks.push(ThreeStacksWithinSingleArrayS1.STACK2, 9);
		System.out.println("Stack2 size: " + stacks.size(STACK2));
		
		stacks.push(ThreeStacksWithinSingleArrayS1.STACK3, 3);
		stacks.push(ThreeStacksWithinSingleArrayS1.STACK3, 6);
		stacks.push(ThreeStacksWithinSingleArrayS1.STACK3, 8);
		stacks.push(ThreeStacksWithinSingleArrayS1.STACK3, 9);
		System.out.println("Stack3 size: " + stacks.size(STACK3));
		
		stacks.pop(ThreeStacksWithinSingleArrayS1.STACK1);
		stacks.pop(ThreeStacksWithinSingleArrayS1.STACK1);
		stacks.pop(ThreeStacksWithinSingleArrayS1.STACK1);
		System.out.println("Stack1 size: " + stacks.size(STACK1));
		
		stacks.pop(ThreeStacksWithinSingleArrayS1.STACK3);
		stacks.pop(ThreeStacksWithinSingleArrayS1.STACK3);
		stacks.pop(ThreeStacksWithinSingleArrayS1.STACK3);
		System.out.println("Stack3 size: " + stacks.size(STACK3));

		stacks.pop(ThreeStacksWithinSingleArrayS1.STACK2);
		stacks.pop(ThreeStacksWithinSingleArrayS1.STACK2);
		stacks.pop(ThreeStacksWithinSingleArrayS1.STACK2);
		System.out.println("Stack2 size: " + stacks.size(STACK2));
		
	}
}
