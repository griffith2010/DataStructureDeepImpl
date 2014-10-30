package wenyu.learning.Tree.BalanceTree;

import java.util.Random;

/*
 * A splay tree is a self-adjusting binary search tree with the 
 * additional property that recently accessed elements are quick 
 * to access again. 
 * 
 * All normal operations on a binary search tree are combined 
 * with one basic operation, called splaying. Splaying the tree 
 * for a certain element rearranges the tree so that the element is 
 * placed at the root of the tree. One way to do this is to first 
 * perform a standard binary tree search for the element in question, 
 * and then use tree rotations in a specific fashion to bring the 
 * element to the top. 
 */

public class SplayTreeUtils<E extends Comparable<E>> {
	private final E biggest;	// Biggest value of E
	private final E smallest;   // Smallest value of E
	public SplayTreeUtils(E biggest, E smallest) {
		this.biggest = biggest;
		this.smallest = smallest;
	}
	
	/**
     * Insert into the tree.
     * @param root
     * @param value the item to insert.
     */
    public BalancedTreeNode<E> insert(BalancedTreeNode<E> root, E value) {
        BalancedTreeNode<E> newNode = new BalancedTreeNode<E>(value);

        if(root == null) {
            root = newNode;
        } else {
        	 // Do splay node(x) in tree(root), to see if the node(x) exists!! 
            root = splay(root, value);
            
            // If node(x) doesn't exists
            if(value.compareTo(root.value) < 0) {
                newNode.left = root.left;
                newNode.right = root;
                root.left = null;
                root = newNode;
            } else if(value.compareTo(root.value) > 0) {
                newNode.right = root.right;
                newNode.left = root;
                root.right = null;
                root = newNode;
            }
        }

        return root;
    }
    
    /**
     * Remove from the tree.
     * @param root
     * @param value the item to delete.
     */
    public BalancedTreeNode<E> remove(BalancedTreeNode<E> root, E value) {
        BalancedTreeNode<E> newTree;
        
        root = splay(root, value); // If x is found, it will be at the root
        if(root.value != value) {
        	// Item not found; do nothing
            return null;   
        } else if(root.left == null) {
            newTree = root.right;
        } else {
            // Find the maximum in the left subtree. And this node must not have right node. 
            // Splay it to the root; and then attach right child
            newTree = root.left;
            newTree = splay(newTree, value);
            newTree.right = root.right;
        }
        root = newTree;
        return root;
    }
    
    /**
     * Find an item in the tree.
     * @param value the item to search for.
     * @return the matching item or null if not found.
     */
    public BalancedTreeNode<E> find(BalancedTreeNode<E> root, E value) {
        root = splay(root, value);
        if(root.value != value) {
            // Such node doesn't exists...
        	return null;
        }
        return root;
    }
    
    /**
     * Find the biggest item in the tree.
     * @return the matching item or null if not found.
     */
    public BalancedTreeNode<E> findMax(BalancedTreeNode<E> root) {
    	E value = biggest;
        root = splay(root, value);
        return root;
    }
    
    /**
     * Find the smallest item in the tree.
     * @return the matching item or null if not found.
     */
    public BalancedTreeNode<E> findMin(BalancedTreeNode<E> root) {
    	E value = smallest;
        root = splay(root, value);
        return root;
    }
    
    
    private BalancedTreeNode<E> splay(BalancedTreeNode<E> node, E value) {
    	if(node == null) {
    		return null;
    	}
    	
    	BalancedTreeNode<E> tmpHeader = new BalancedTreeNode<E>(null);
    	BalancedTreeNode<E> leftTreeMax = tmpHeader;
    	BalancedTreeNode<E> rightTreeMin = tmpHeader;
    	tmpHeader.value = biggest;

        while(true) {
            if(value.compareTo(node.value) < 0) {
            	BalancedTreeNode<E> parent = node.left;
            	if(parent == null) { 
            		break; 
            	} else {
            		if(value.compareTo(parent.value) < 0) {
            			if(parent.left == null) {
                			// Zig
            				node.left = null;
            				rightTreeMin.left = node;
            				rightTreeMin = node;
            				node = parent;
            			} else {
            				// Zig-Zig
            				BalancedTreeNode<E> tmp = parent.left;
            				node = rightRotate(node);
            				rightTreeMin.left = node;
            				rightTreeMin = node;
            				node = tmp;
            			}
            		} else { 
            			//ie. value >= parent.value  
                        //zig or zig-zag(simplified to zig) 
            			node.left = null;
            			rightTreeMin.left = node;
        				rightTreeMin = node;
            			node = parent;
            		}
            	}
            } else if(value.compareTo(node.value) > 0) {
            	BalancedTreeNode<E> parent = node.right;
            	if(parent == null) {
            		break;
            	} else {
            		if(value.compareTo(parent.value) > 0) {
            			if(parent.right == null) {
            				//Zag
            				node.right = null;
            				leftTreeMax.right = node;
            				leftTreeMax = node;
            				node = parent;
            			} else {
                			// Zag-Zag
                			BalancedTreeNode<E> tmp = parent.right;
                			node = leftRotate(node);
            				leftTreeMax.right = node;
            				leftTreeMax = node;
                			node = tmp;
                		}
            		} else {
            			//ie. value <= parent.value
            			//Zag or Zag-Zig
            			node.right = null;
        				leftTreeMax.right = node;
        				leftTreeMax = node;
            			node = parent;
            		}
            	}
            } else {
                break;
            }
        }
        
        leftTreeMax.right = node.left;
        rightTreeMin.left = node.right;
        node.left = tmpHeader.right;
        node.right = tmpHeader.left;
        return node;
    }
    
    /**
     * Rotate binary tree node with right child.
     */
    private BalancedTreeNode<E> leftRotate(BalancedTreeNode<E> k1)
    {
        BalancedTreeNode<E> k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        return k2;
    }

    /**
     * Rotate binary tree node with left child.
     */
    private BalancedTreeNode<E> rightRotate(BalancedTreeNode<E> k2)
    {
        BalancedTreeNode<E> k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        return k1;
    }
    
    
    public static void main(String[] args) {
    	int treeNodeCount = 10;
    	SplayTreeUtils<Integer> splayUtils = new SplayTreeUtils<Integer>(Integer.MAX_VALUE, Integer.MIN_VALUE);
    	Integer[] values = new Integer[treeNodeCount];
		Random random = new Random();
		for(int i=0;i<treeNodeCount;i++) {
			values[i] = random.nextInt(100);
		}
		
		
		
		BalancedTreeNode<Integer> root = null;
		for(int i=0;i<treeNodeCount;i++) {
			root = splayUtils.insert(root, values[i]);
		}
		TraversalBFS.traversalWithLine(root);
		
		System.out.println("=================================================");
		int findIdx = random.nextInt(treeNodeCount);
		System.out.println("Try to find"
				+ "" + values[findIdx] + "\n");
		root = splayUtils.find(root, values[findIdx]);
		TraversalBFS.traversalWithLine(root);
    }
}
