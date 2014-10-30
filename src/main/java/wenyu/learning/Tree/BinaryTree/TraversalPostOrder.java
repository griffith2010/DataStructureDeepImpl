package wenyu.learning.Tree.BinaryTree;

import java.util.Stack;

public class TraversalPostOrder {
	public static <E> void traversalWithRecursion(BinaryTreeNode<E> node) {
		if(node == null) {
			return;
		}
		
		if(node.left!=null) {
			traversalWithRecursion(node.left);
		}
		if(node.right!=null) {
			traversalWithRecursion(node.right);
		}
		System.out.print(node.value + " ");
	}
	
	public static <E> void traversalWithoutRecursion(BinaryTreeNode<E> root) {
		if(root == null) {
			return;
		}
		
		Stack<BinaryTreeNode<E>> stack = new Stack<BinaryTreeNode<E>>();
		stack.push(root);
		BinaryTreeNode<E> lastVisited = null;
		while(!stack.isEmpty()) {
			BinaryTreeNode<E> node = stack.peek();
			while(node!=null) {
				stack.push(node.left);
				node = node.left;
			}
			
			stack.pop(); //Pop out null peek value
			if(!stack.isEmpty()) {
				node = stack.peek();
				if(node.right==null || node.right==lastVisited) {
					node = stack.pop();
					System.out.print(node.value + " ");
					if(lastVisited!=null && node.right == lastVisited) {
						lastVisited = node;
						stack.push(null);
						continue;
					}
					lastVisited = node;
				}
				stack.push(node.right);
			}
		}
	}
	
	public static <E> void MorrisPostOrder(BinaryTreeNode<E> root) {
		/*
		 * Time: O(n), Space: O(1)
		 * 1. 后续遍历稍显复杂，需要建立一个临时节点dump，令其左孩子是root。
		 *    并且还需要一个子过程，就是倒序输出某两个节点之间路径上的各个节点。
		 *    当前节点设置为临时节点dump。
		 * 2. 如果当前节点的左孩子为空，则将其右孩子作为当前节点。
		 * 3. 如果当前节点的左孩子不为空，在当前节点的左子树中找到当前节点在中序遍历下的前驱节点。
		 *    a) 如果前驱节点的右孩子为空，将它的右孩子设置为当前节点。当前节点更新为当前节点的左孩子。
		 *    b) 如果前驱节点的右孩子为当前节点，将它的右孩子重新设为空。
		 *    	 倒序输出从当前节点的左孩子到该前驱节点这条路径上的所有节点。当前节点更新为当前节点的右孩子。
		 * 4. 重复以上2、3直到当前节点为空。
		 * 
		 * Reference:
		 * http://www.cnblogs.com/AnnieKim/archive/2013/06/15/MorrisTraversal.html
		 */
		if(root == null) {
			return;
		}
		
		BinaryTreeNode<E> dump = new BinaryTreeNode<E>();
		dump.left = root; // Step 1;
		
		BinaryTreeNode<E> currNode = dump;
		while(currNode!=null) {
			
			if(currNode.left == null) {
				currNode = currNode.right;
			} else {
				BinaryTreeNode<E> pre = currNode.left;
				while(pre.right!=null && pre.right!=currNode) {
					pre = pre.right;
				}
				
				if(pre.right == null) {  // 2.a
					pre.right = currNode;
					currNode = currNode.left;
				} else if(pre.right == currNode) {				// 2.b
					// Print from pre to currNode.left
					
					BinaryTreeNode<E> tmpTo = pre;
					while(currNode.left!=tmpTo) {
						BinaryTreeNode<E> tmp = currNode.left;
						while(tmp.right != tmpTo) {
							tmp = tmp.right;
						}
						System.out.print(tmpTo.value + " ");
						tmpTo = tmp;
					}
					System.out.print(currNode.left.value + " ");
					
					pre.right = null;
					currNode = currNode.right;
				}
			}
		}
	}
	
	private void demoEntry() {
		BinaryTreeNode<Integer> root = BSTUtils.genIntegerBST(30);
		TraversalBFS.traversalWithLine(root);
		
		System.out.println("========================");
		traversalWithRecursion(root);
		System.out.println();
		System.out.println("========================");
		traversalWithoutRecursion(root);
		System.out.println();
		System.out.println("========================");
		MorrisPostOrder(root);
	}
	
	public static void main(String[] args) {
		new TraversalPostOrder().demoEntry();
	}

}
