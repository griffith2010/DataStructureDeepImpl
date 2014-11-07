package wenyu.learning.Tree.BinaryTree;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

public class NextNodeInBSTUtils {
	
	public static <E extends Comparable<E>> BinaryTreeNode<E> nextPreOrderNode(BinaryTreeNode<E> root, BinaryTreeNode<E> currNode) {
		if(root==null || currNode==null) {
			return null;
		}
		
		if(currNode.left != null) {
			return currNode.left;
		} else if (currNode.right != null) {
			return currNode.right;
		} else {
			Stack<Boolean> ifRight = new Stack<Boolean>();
			Stack<BinaryTreeNode<E>> preNodes = new Stack<BinaryTreeNode<E>>();
			BinaryTreeNode<E> tmpNode = root;
			while(tmpNode != null) {
				if(tmpNode.value.compareTo(currNode.value) == 0) {
					while(!ifRight.isEmpty() && !preNodes.isEmpty()) {
						BinaryTreeNode<E> parent = preNodes.pop();
						if(!ifRight.pop() && parent.right != null) {
							return parent.right;
						}
					}
					return null;
				}
				
				preNodes.push(tmpNode);
				if(currNode.value.compareTo(tmpNode.value)>0) {
					tmpNode = tmpNode.right;
					ifRight.push(true);
				} else {
					tmpNode = tmpNode.left;
					ifRight.push(false);
				}
			}
			return null;
		}
	}
	
	public static <E extends Comparable<E>> BinaryTreeNode<E> nextInOrderNode(BinaryTreeNode<E> root, BinaryTreeNode<E> currNode) {
		// Three cases:
		// 1. Has right children
		// 2. Has no right children, and is the left child of its parent
		// 3. Has no right children, and is the right child of its parent
		
		if(root==null || currNode==null) {
			return null;
		}
		
		if(currNode.right != null) {
			// Case 1:
			BinaryTreeNode<E> tmpNode = currNode.right;
			while(tmpNode.left != null) {
				tmpNode = tmpNode.left;
			}
			return tmpNode;
		} else {
			Stack<Boolean> ifRight = new Stack<Boolean>();
			Stack<BinaryTreeNode<E>> preNodes = new Stack<BinaryTreeNode<E>>();
			BinaryTreeNode<E> tmpNode = root;
			while(tmpNode != null) {
				if(tmpNode.left.value.compareTo(currNode.value) == 0) {
					// Case 2:
					return tmpNode;
				} else if(tmpNode.right.value.compareTo(currNode.value) == 0) {
					// Case 3:
					while(!preNodes.isEmpty() && !ifRight.isEmpty() && ifRight.pop()) {
						tmpNode = preNodes.pop();
					}
					if(!preNodes.isEmpty()) {
						return preNodes.pop();
					} else {
						return null;
					}
				}
				
				preNodes.push(tmpNode);
				if(currNode.value.compareTo(tmpNode.value)>0) {
					tmpNode = tmpNode.right;
					ifRight.push(true);
				} else {
					tmpNode = tmpNode.left;
					ifRight.push(false);
				}
			}
			return null;
		}
	}
	
	public static <E extends Comparable<E>> BinaryTreeNode<E> nextPostOrderNode(BinaryTreeNode<E> root, BinaryTreeNode<E> currNode) {
		Stack<Boolean> ifRight = new Stack<Boolean>();
		Stack<BinaryTreeNode<E>> preNodes = new Stack<BinaryTreeNode<E>>();
		BinaryTreeNode<E> tmpNode = root;
		while(tmpNode != null) {
			if(tmpNode.value.compareTo(currNode.value) == 0) {
				while(!ifRight.isEmpty() && !preNodes.isEmpty()) {
					BinaryTreeNode<E> parent = preNodes.pop();
					if(ifRight.pop() || parent.right==null) {
						return parent;
					} else {
						BinaryTreeNode<E> tmp = parent.right;
						while(tmp.left != null) {
							tmp = tmp.left;
						}
						return tmp;
					}
				}
				return null;
			}
			
			preNodes.push(tmpNode);
			if(currNode.value.compareTo(tmpNode.value)>0) {
				tmpNode = tmpNode.right;
				ifRight.push(true);
			} else {
				tmpNode = tmpNode.left;
				ifRight.push(false);
			}
		}
		
		
		return currNode;
	}
	
	public static void main(String[] args) {
		int treeNodeCount = 10;
		Integer[] values = new Integer[treeNodeCount];
		Random random = new Random();
		for(int i=0;i<treeNodeCount;i++) {
			values[i] = random.nextInt(100);
		}
		Arrays.sort(values);
		
		
		BinaryTreeNode<Integer> root = BSTUtils.genFromSortedArr(values, 0, values.length-1);
		TraversalBFS.traversalWithLine(root, null);
		
		Scanner scanner = new Scanner(System.in);
		PrintStream out = System.out;
		while (true) 
		{
			out.print("Node Integer Value>");			
			String commandLine = scanner.nextLine();
			Integer value = Integer.parseInt(commandLine);
			BinaryTreeNode<Integer> node = BSTUtils.findNode(root, value);
			if(node == null) {
				System.out.println("No such node in the tree.");
			}
			
			BinaryTreeNode<Integer> preOrderNode = nextPreOrderNode(root, node);
			if(preOrderNode != null) {
				System.out.println("Pre Order Next node value is: " + preOrderNode.value);
			} else {
				System.out.println("No pre order next node");
			}
			
			BinaryTreeNode<Integer> inOrderNode = nextInOrderNode(root, node);
			if(inOrderNode != null) {
				System.out.println("In Order Next node value is: " + inOrderNode.value);
			} else {
				System.out.println("No in order next node");
			}
			
			BinaryTreeNode<Integer> postOrderNode = nextPostOrderNode(root, node);
			if(postOrderNode != null) {
				System.out.println("Post Order Next Node value is: " + postOrderNode.value);
			} else {
				System.out.println("No post order next node");
			}
		}
		
	}

}
