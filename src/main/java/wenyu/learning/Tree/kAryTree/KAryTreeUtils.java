package wenyu.learning.Tree.kAryTree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class KAryTreeUtils {
	public static KAryTreeNode<Integer> genRandomIntegerKAryTree(int k, int treeNodeCount, boolean full) {
		Random random = new Random();
		KAryTreeNode<Integer> root = new KAryTreeNode<Integer>(random.nextInt(1000), k);
		int currNodeCount = 0;
		
		Queue<KAryTreeNode<Integer>> queue = new LinkedList<KAryTreeNode<Integer>>();
		queue.offer(root);
		while(!queue.isEmpty()) {
			KAryTreeNode<Integer> node = queue.poll();
			int currNodeChildrenCount = full ? k:random.nextInt(k+1);
			for(int i=0; i<currNodeChildrenCount&&currNodeCount<treeNodeCount; i++) {
				KAryTreeNode<Integer> child = new KAryTreeNode<Integer>(random.nextInt(1000), k);
				node.children[i] = child;
				queue.offer(child);
				currNodeCount++;
			}
			
			if(queue.isEmpty() && currNodeCount<treeNodeCount) {
				currNodeChildrenCount = random.nextInt(k+1);
				while(currNodeChildrenCount == 0) {
					currNodeChildrenCount = random.nextInt(k+1);
				}
				
				for(int i=0; i<k&&currNodeCount<treeNodeCount; i++) {
					KAryTreeNode<Integer> child = new KAryTreeNode<Integer>(random.nextInt(1000), k);
					node.children[i] = child;
					queue.offer(child);
					currNodeCount++;
				}
			}
		}
		
		return root;
	}
}
