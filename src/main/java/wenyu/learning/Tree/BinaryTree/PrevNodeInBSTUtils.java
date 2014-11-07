package wenyu.learning.Tree.BinaryTree;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

public class PrevNodeInBSTUtils {
	
	public static <E extends Comparable<E>> BinaryTreeNode<E> PrevPreOrderNode(BinaryTreeNode<E> root, BinaryTreeNode<E> currNode) {
		if(root==null || currNode==null) {
			return null;
		}
		
		BinaryTreeNode<E> node = root;
		Stack<Boolean> ifRight = new Stack<Boolean>();
		Stack<BinaryTreeNode<E>> preNodes = new Stack<BinaryTreeNode<E>>();
		while(node != null) {
			if(node.value.compareTo(currNode.value) == 0) {
				if(preNodes.isEmpty()) {
					return null;
				}
				
				while(!ifRight.isEmpty() && !preNodes.isEmpty()) {
					boolean ifright = ifRight.pop();
					BinaryTreeNode<E> tmpNode = preNodes.pop();
					if(!ifright || tmpNode.left==null) {
						return tmpNode;
					} else {
						BinaryTreeNode<E> tmp = tmpNode.left;
						while(tmp.right != null) {
							tmp = tmp.right;
						}
						return tmp;
					}
				}
			}
			
			preNodes.push(node);
			if(currNode.value.compareTo(node.value) > 0) {
				node = node.right;
				ifRight.push(true);
			} else {
				node = node.left;
				ifRight.push(false);
			}
		}
		
		return null;
	}
	
	public static <E extends Comparable<E>> BinaryTreeNode<E> prevInOrderNode(BinaryTreeNode<E> root, BinaryTreeNode<E> currNode) {
		if(root==null || currNode==null) {
			return null;
		}
		
		if(currNode.left != null) {
			BinaryTreeNode<E> tmpNode = currNode.left;
			while(tmpNode.right != null) {
				tmpNode = tmpNode.right;
			}
			return tmpNode;
		} else {
			Stack<Boolean> ifRight = new Stack<Boolean>();
			Stack<BinaryTreeNode<E>> preNodes = new Stack<BinaryTreeNode<E>>();
			BinaryTreeNode<E> tmpNode = root;
			while(tmpNode != null) {
				if(tmpNode.right.value.compareTo(currNode.value) == 0) {
					return tmpNode;
				} else if(tmpNode.left.value.compareTo(currNode.value) == 0) {
					while(!preNodes.isEmpty() && !ifRight.isEmpty() && !ifRight.pop()) {
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
	
	public static <E extends Comparable<E>> BinaryTreeNode<E> prevPostOrderNode(BinaryTreeNode<E> root, BinaryTreeNode<E> currNode) {
		if(root==null || currNode==null) {
			return null;
		}
		
		if(currNode.right != null) {
			BinaryTreeNode<E> tmpNode = currNode.left;
			while(tmpNode.right != null) {
				tmpNode = tmpNode.right;
			}
			return tmpNode;
		} else if(currNode.left != null) {
			return currNode.left;
		} else {
			Stack<Boolean> ifRight = new Stack<Boolean>();
			Stack<BinaryTreeNode<E>> preNodes = new Stack<BinaryTreeNode<E>>();
			BinaryTreeNode<E> tmpNode = root;
			while(tmpNode != null) {
				if(tmpNode.value.compareTo(currNode.value) == 0) {
					while(!preNodes.isEmpty() && !ifRight.isEmpty() && (!ifRight.pop() || preNodes.peek().left==null)) {
						tmpNode = preNodes.pop();
					}
					if(!preNodes.isEmpty()) {
						return preNodes.pop().left;
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
		}
		return null;
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
			
			BinaryTreeNode<Integer> preOrderNode = PrevPreOrderNode(root, node);
			if(preOrderNode != null) {
				System.out.println("Pre Order Previous node value is: " + preOrderNode.value);
			} else {
				System.out.println("No pre order previous node");
			}
			
			BinaryTreeNode<Integer> inOrderNode = prevInOrderNode(root, node);
			if(inOrderNode != null) {
				System.out.println("In Order Previous node value is: " + inOrderNode.value);
			} else {
				System.out.println("No in order previous node");
			}
			
			BinaryTreeNode<Integer> postOrderNode = prevPostOrderNode(root, node);
			if(postOrderNode != null) {
				System.out.println("Post Order Previous Node value is: " + postOrderNode.value);
			} else {
				System.out.println("No post order previous node");
			}
		}
		
	}

}
