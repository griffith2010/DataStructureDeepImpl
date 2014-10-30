package wenyu.learning.Tree.kAryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 * Find all the cousin of given node in a k-ary tree
 */

public class FindCousin {

	public static <E> ArrayList<KAryTreeNode<E>> findAllCousins(KAryTreeNode<E> root, KAryTreeNode<E> node) {
		if(root == null) {
			return null;
		}
		
		ArrayList<KAryTreeNode<E>> cousins = new ArrayList<KAryTreeNode<E>>();
		ArrayList<KAryTreeNode<E>> nextLineCousins = new ArrayList<KAryTreeNode<E>>();
		Queue<KAryTreeNode<E>> queue = new LinkedList<KAryTreeNode<E>>();
		int currentLineNodeCount = 1;
		int nextLineNodeCount = 0;
		
		queue.offer(root);
		cousins.add(root);
		while(!queue.isEmpty()) {
			KAryTreeNode<E> tmpNode = queue.poll();
			if(tmpNode == node) {
				System.out.print("Cousins are [");
				for(KAryTreeNode<E> tmp:cousins) {
					System.out.print(tmp.value + " ");
				}
				System.out.println("]");
				return cousins;
			}
			
			if(tmpNode.children != null) {
				for(int i=0;i<tmpNode.children.length;i++) {
					if(tmpNode.children[i] != null) {
						queue.offer(tmpNode.children[i]);
						nextLineCousins.add(tmpNode.children[i]);
						nextLineNodeCount++;
					}
				}
			}
			
			currentLineNodeCount--;
			if(currentLineNodeCount==0) {
				currentLineNodeCount = nextLineNodeCount;
				nextLineNodeCount = 0;
				cousins = nextLineCousins;
				nextLineCousins = new ArrayList<KAryTreeNode<E>>();
			}
		}
		
		return null;
	}
	
	public static <E> ArrayList<KAryTreeNode<E>> findAllCousins(KAryTreeNode<E> root, E node) {
		if(root == null) {
			return null;
		}
		
		ArrayList<KAryTreeNode<E>> cousins = new ArrayList<KAryTreeNode<E>>();
		ArrayList<KAryTreeNode<E>> nextLineCousins = new ArrayList<KAryTreeNode<E>>();
		Queue<KAryTreeNode<E>> queue = new LinkedList<KAryTreeNode<E>>();
		int currentLineNodeCount = 1;
		int nextLineNodeCount = 0;
		
		queue.offer(root);
		cousins.add(root);
		while(!queue.isEmpty()) {
			KAryTreeNode<E> tmpNode = queue.poll();
			if(tmpNode.value.equals(node)) {
				System.out.print("Cousins are [");
				for(KAryTreeNode<E> tmp:cousins) {
					System.out.print(tmp.value + " ");
				}
				System.out.println("]");
				return cousins;
			}
			
			if(tmpNode.children != null) {
				for(int i=0;i<tmpNode.children.length;i++) {
					if(tmpNode.children[i] != null) {
						queue.offer(tmpNode.children[i]);
						nextLineCousins.add(tmpNode.children[i]);
						nextLineNodeCount++;
					}
				}
			}
			
			currentLineNodeCount--;
			if(currentLineNodeCount==0) {
				currentLineNodeCount = nextLineNodeCount;
				nextLineNodeCount = 0;
				cousins = nextLineCousins;
				nextLineCousins = new ArrayList<KAryTreeNode<E>>();
			}
		}
		
		return null;
	}
	
	public static void main(String[] args) {
		KAryTreeNode<Integer> root = KAryTreeUtils.genRandomIntegerKAryTree(8, 20, false);
		TraversalBFS.BFSWithLine(root);
		
		while(true) {
			Scanner scanner = new Scanner(System.in);
			String input = scanner.nextLine();
			int value = Integer.parseInt(input);
			findAllCousins(root, value);
		}
		
	}
}
