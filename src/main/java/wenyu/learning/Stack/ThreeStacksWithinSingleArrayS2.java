package wenyu.learning.Stack;

public class ThreeStacksWithinSingleArrayS2<E> {

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
	public ThreeStacksWithinSingleArrayS2() {
		if(array==null) {
			array = (E[]) new Object[DEFAULT_SIZE];
		}
		tail1 = 0;
		tail2 = array.length-1;
		tail3 = array.length/3;
	}
	
	public void push(String stack, E element) {
		synchronized(lock) {
			if(STACK1.equals(stack)) {
				array[tail1++] = element;
				if(tail1 >= array.length/3-1) {
					resize();
				}
			} else if (STACK2.equals(stack)) {
				array[tail2--] = element;
				if(tail2 <= tail3+1) {
					resize();
				}
			} else if (STACK3.equals(stack)) {
				array[tail3++] = element;
				if(tail3 >= tail2-1) {
					resize();
				}
			}
		}
	}
	
	public synchronized E pop(String stack) {
		synchronized(lock) {
			if(!isEmpty(stack)) {
				if(STACK1.equals(stack)) {
					E ele = array[--tail1];
					array[tail1]=null;
					return ele;
				} else if (STACK2.equals(stack)) {
					E ele = array[++tail2];
					array[tail2]=null;
					return ele;
				} else if (STACK3.equals(stack)) {
					E ele = array[--tail3];
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
				return tail2==array.length-1;
			} else if (STACK3.equals(stack)) {
				return tail3==array.length/3;
			} else {
				return true;
			}
		}
	}
	
	public int size(String stack) {
		if(STACK1.equals(stack)) {
			return tail1;
		} else if (STACK2.equals(stack)) {
			return array.length-1-tail2;
		} else if (STACK3.equals(stack)) {
			return tail3-array.length/3;
		} else {
			return -1;
		}
	}
	
	@SuppressWarnings("unchecked")
	private synchronized void resize() {
		synchronized(lock) {
			if(tail1 >= array.length/3-1) {
				int oldSize = array.length;
	            int newSize = (int)Math.ceil(oldSize*DEFAULT_INCREMENT); 
	            E[] newArray = (E[]) new Object[newSize];
	            for(int i=0;i<tail1;i++) {
	            	newArray[i] = array[i];
	            }
	            
	            int i=0;
	            for(;i<array.length-tail2-1;i++) {
	            	newArray[newSize-1-i] = array[array.length-1-i];
	            }
	            tail2 = newSize-(oldSize-tail2);
	            
	            int base = newSize/3;
	            for(i=0;i<tail3-oldSize/3;i++) {
	            	newArray[base+i] = array[oldSize/3+i];
	            }
	            tail3 = base + i;
	            array = newArray;
			}
		}
	}
	
	public static void main(String[] args) {
		ThreeStacksWithinSingleArrayS2<Integer> stacks = new ThreeStacksWithinSingleArrayS2<Integer>();
		stacks.push(ThreeStacksWithinSingleArrayS2.STACK1, 3);
		stacks.push(ThreeStacksWithinSingleArrayS2.STACK1, 6);
		stacks.push(ThreeStacksWithinSingleArrayS2.STACK1, 8);
		stacks.push(ThreeStacksWithinSingleArrayS2.STACK1, 9);
		System.out.println("Stack1 size: " + stacks.size(STACK1));
		
		stacks.push(ThreeStacksWithinSingleArrayS2.STACK2, 3);
		stacks.push(ThreeStacksWithinSingleArrayS2.STACK2, 6);
		stacks.push(ThreeStacksWithinSingleArrayS2.STACK2, 8);
		stacks.push(ThreeStacksWithinSingleArrayS2.STACK2, 9);
		System.out.println("Stack2 size: " + stacks.size(STACK2));
		
		stacks.push(ThreeStacksWithinSingleArrayS2.STACK3, 3);
		stacks.push(ThreeStacksWithinSingleArrayS2.STACK3, 6);
		stacks.push(ThreeStacksWithinSingleArrayS2.STACK3, 8);
		stacks.push(ThreeStacksWithinSingleArrayS2.STACK3, 9);
		System.out.println("Stack3 size: " + stacks.size(STACK3));
		
		stacks.pop(ThreeStacksWithinSingleArrayS2.STACK1);
		stacks.pop(ThreeStacksWithinSingleArrayS2.STACK1);
		stacks.pop(ThreeStacksWithinSingleArrayS2.STACK1);
		System.out.println("Stack1 size: " + stacks.size(STACK1));
		
		stacks.pop(ThreeStacksWithinSingleArrayS2.STACK3);
		stacks.pop(ThreeStacksWithinSingleArrayS2.STACK3);
		stacks.pop(ThreeStacksWithinSingleArrayS2.STACK3);
		System.out.println("Stack3 size: " + stacks.size(STACK3));

		stacks.pop(ThreeStacksWithinSingleArrayS2.STACK2);
		stacks.pop(ThreeStacksWithinSingleArrayS2.STACK2);
		stacks.pop(ThreeStacksWithinSingleArrayS2.STACK2);
		System.out.println("Stack2 size: " + stacks.size(STACK2));
		
	}


}
