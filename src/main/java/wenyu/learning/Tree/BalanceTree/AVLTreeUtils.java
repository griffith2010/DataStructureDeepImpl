package wenyu.learning.Tree.BalanceTree;

import java.util.Random;

/*
 * In an AVL tree, the heights of the two child subtrees of any node differ 
 * by at most one; if at any time they differ by more than one, rebalancing 
 * is done to restore this property. Lookup, insertion, and deletion all take 
 * O(log n) time in both the average and worst cases.
 * 
 * For lookup-intensive applications, AVL trees are faster than red-black 
 * trees because they are more rigidly balanced.
 */

public class AVLTreeUtils<E extends Comparable<E>> {
	
	private int height(BalancedTreeNode<E> node)
    {
        return (node==null)? -1:node.height;
    }
	
	private BalancedTreeNode<E> rightRotate(BalancedTreeNode<E> k2)
    { // LL
        BalancedTreeNode<E> k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = (height(k2.left)>height(k2.right))?height(k2.left)+1:height(k2.right)+1;
        k1.height= (height(k1.left)>height(k1.right))?height(k1.left)+1:height(k1.right)+1;
        return k1;
    }

    private BalancedTreeNode<E> leftRotate(BalancedTreeNode<E> k1)
    { // RR
        BalancedTreeNode<E> k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        k1.height = (height(k1.left)>height(k1.right))?height(k1.left)+1:height(k1.right)+1;
        k2.height = (height(k2.left)>height(k2.right))?height(k2.left)+1:height(k2.right)+1;
        return k2;
    }
	
    private BalancedTreeNode<E> leftRightRotate(BalancedTreeNode<E> k3)
    { // LR
        k3.left = leftRotate(k3.left);
        return rightRotate(k3);
    }

    private BalancedTreeNode<E> rightLeftRotate(BalancedTreeNode<E> k1)
    { //RL
        k1.right = rightRotate(k1.right);
        return leftRotate(k1);
    }

    
	/**
	 * 
	 * One way to insert node in AVL tree
	 */
	public BalancedTreeNode<E> insert(BalancedTreeNode<E> node, E value) {
		if(node == null) {
            return new BalancedTreeNode<E>(value);
		}
		
		if(value.compareTo(node.value) < 0) {
            node.left = insert(node.left, value);
            if(height(node.left)-height(node.right) > 1) {
                if(value.compareTo(node.left.value) < 0) {
                    node = rightRotate(node); //LL
                } else {
                    node = leftRightRotate(node); //LR
                }
            }
        } else if(value.compareTo(node.value) > 0) {
            node.right = insert(node.right, value);
            if(height(node.right)-height(node.left) > 1) {
                if(value.compareTo(node.right.value) > 0) {
                    node = leftRotate(node); //RR
                } else {
                    node = rightLeftRotate(node); //RL
                }
            }
        } else {
			System.out.println(value + " is already exists...");
        }
        
		node.height = (height(node.left)>height(node.right)) ? height(node.left)+1 : height(node.right)+1;
        return node;
	}
	
    public BalancedTreeNode<E> insertNodeRec(BalancedTreeNode<E> node, E value) {
		if(node == null) {
			return new BalancedTreeNode<E>(value);
		}
		
		if(value.compareTo(node.value) < 0) {
			node.left = insertNodeRec(node.left, value);
		} else if (value.compareTo(node.value) > 0) {
			node.right = insertNodeRec(node.right, value);
		} else {
			System.out.println(value + " is already exists...");
		}
		
		return balance(node);
	}
	
	public BalancedTreeNode<E> deleteNodeRec(BalancedTreeNode<E> node, E value) {
		if(node == null) {
			return node;
		}
		
		if(value.compareTo(node.value) < 0) {
			node.left = deleteNodeRec(node.left, value);
		} else if(value.compareTo(node.value) > 0) {
			node.right = deleteNodeRec(node.right, value);
		} else if(node.left!=null && node.right!=null) {
			BalancedTreeNode<E> minNode = node.right;
			BalancedTreeNode<E> parentNode = node;
			if(minNode.left == null) {
				parentNode.value = minNode.value;
				parentNode.right = minNode.right;
			} else {
				while(minNode.left!=null) {
					parentNode = minNode;
					minNode = minNode.left;
				}
				node.value = minNode.value;
				parentNode.left = deleteNodeRec(minNode, minNode.value);
			}
			
		} else {
			node=(node.left!=null)?node.left:node.right;
		}
		return balance(node);
	}
    
	private BalancedTreeNode<E> balance(BalancedTreeNode<E> node) {
		if(node == null) {
			return node;
		}
		
		if(height(node.left)-height(node.right) == 2) {
			if(height(node.left.left) >= height(node.left.right)) {
				node = rightRotate(node); //LL
			} else {
				node = leftRightRotate(node); //LR
			}
		} else if(height(node.right)-height(node.left) == 2) {
			if(height(node.right.right) >= height(node.right.left)) {
                node = leftRotate(node); //RR
            } else {
                node = rightLeftRotate(node); //RL
            }
		}
		node.height = (height(node.left)>height(node.right))?height(node.left)+1:height(node.right)+1;
		return node;
	}
	
	public static void main(String[] args) {
		int treeNodeCount = 10;
		AVLTreeUtils<Integer> AVLUtils = new AVLTreeUtils<Integer>();
		
		Integer[] values = new Integer[treeNodeCount];
		Random random = new Random();
		for(int i=0;i<treeNodeCount;i++) {
			values[i] = random.nextInt(100);
		}
		
		BalancedTreeNode<Integer> root = null;
		for(int i=0;i<treeNodeCount;i++) {
			root = AVLUtils.insertNodeRec(root, values[i]);
		}
		TraversalBFS.traversalWithLine(root);
		
//		System.out.println("==========================");
//		BalancedTreeNode<Integer> root = null;
//		for(int i=0;i<treeNodeCount;i++) {
//			root = AVLUtils.insert(root, values[i]);
//		}
//		TraversalBFS.traversalWithLine(root);
		
//		System.out.println("==========================");
//		int delNodeValue = values[random.nextInt(treeNodeCount)];
//		System.out.println(delNodeValue + "\n***");
//		root = AVLUtils.deleteNodeRec(root, delNodeValue);
//		TraversalBFS.traversalWithLine(root);
	}
}
