package wenyu.learning.Maths;

import java.util.Stack;

/*
 * In the classic problem of the Towers of Hanoi, you have 3 rods and N 
 * disks of different sizes which can slide onto any tower. The puzzle 
 * starts with disks sorted in ascending order of size from top to bottom 
 * (e.g., each disk sits on top of an even larger one). You have the 
 * following constraints:
 * (A) Only one disk can be moved at a time.
 * (B) A disk is slid off the top of one rod onto the next rod.
 * (C) A disk can only be placed on top of a larger disk.
 * Write a program to move the disks from the first rod to the last using Stacks.
 * 
 * Logic:
 * 	1. First move n-1 disks from current tower to buffer tower (dest as buffer)
 *  2. Second move the only left disk in current tower to dest tower
 *  3. Third to move n-1 disks from buffer tower to dest tower (this as buffer)
 *  Step is 2^n-1
 */

public class HanoiProblem {
	public static class Tower {
		private final Stack<Integer> stack;
		private final int towerIndex;
		
		public Tower(int index) {
			this.towerIndex = index;
			stack = new Stack<Integer>();
		}
		
		public void add(int disk) {
			if(!stack.isEmpty() && disk>stack.peek()) {
				System.out.println("Error placing disk...");
				System.exit(1);
			}
			stack.push(disk);
		}
		
		public int moveDisks(int diskCount, Tower dest, Tower buffer) {
			if(diskCount>0) {
				// First move n-1 disks from current tower to buffer tower (dest as buffer)
				int step = moveDisks(diskCount-1, buffer, dest);
				// Second move the only left disk in current tower to dest tower
				int top = stack.pop();
				dest.add(top);
				System.out.println("Move disk " + top + " from Tower" + towerIndex + " to Tower" + dest.towerIndex);
				step++;
				// Third to move n-1 disks from buffer tower to dest tower (this as buffer)
				step += buffer.moveDisks(diskCount-1, dest, this);
				// Return step count
				return step;
			} else {
				return 0;
			}
		}
	}
	
	public static void main(String[] args) {
		int towerCount = 3;
		int diskCount = 5;
		Tower[] towers = new Tower[towerCount];
		for(int i=0;i<towerCount;i++) {
			towers[i] = new Tower(i);
		}

		// Initially add disk to first tower.
		for(int i=diskCount;i>0;i--) {
			towers[0].add(i);
		}
		
		// Target: move all the disks in tower[0] to tower[2]
		int step = towers[0].moveDisks(diskCount, towers[2], towers[1]);
		System.out.println("Total step is " + step);
	}
}
;