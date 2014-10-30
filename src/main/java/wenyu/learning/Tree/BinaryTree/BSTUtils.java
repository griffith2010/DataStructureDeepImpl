package wenyu.learning.Tree.BinaryTree;

//******************PUBLIC OPERATIONS*********************
//TreeNode insertNodeRec(TreeNode node, int value)       	--> Insert node recursely
//TreeNode insertNode(TreeNode root, int value)      	 	--> Insert node without recurse
//TreeNode deleteNodeRec(TreeNode node, int value)   		--> Delete node recursely
//TreeNode deleteNode(TreeNode root, int value)  			--> Delete node without recurse
//TreeNode genBinarySearchTree()  							--> Generate binary search tree
//TreeNode formBinarySearchTree(int[] values, int[] idx)    --> 
//int findMin(TreeNode root)							 	--> Find the min value in a binary tree
//int findMax(TreeNode root)								--> Find the max value in a binary tree
//int find(int value)										--> Find value in a binary tree
//int findBigger(int value)									--> Find bigger or equal value in a binary tree
//int findSmaller(int value)								--> Find smaller or equal value in a binary tree


import java.util.Arrays;
import java.util.Random;

public class BSTUtils {
	
	/**
	 * Find the min value in a binary tree
	 */
	public static <E extends Comparable<E>> E findMin(BinaryTreeNode<E> root) {
        if(root == null) {
            return null;
        }
        
        BinaryTreeNode<E> node = root;
        while(node.left != null) {
            node = node.left;
        }
        return node.value;
    }
	
	/**
	 * Find the max value in a binary tree
	 */
	public static <E extends Comparable<E>> E findMax(BinaryTreeNode<E> root) {
		if(root == null) {
            return null;
		}

        BinaryTreeNode<E> node = root;
        while(node.right != null) {
            node = node.right;
        }
        return node.value;
	}
	
	
	/**
	 * Insert nodes recursely
	 */
	public static <E extends Comparable<E>> BinaryTreeNode<E> insertNodeRec(BinaryTreeNode<E> node, E value) {
		if(node == null) {
			node = new BinaryTreeNode<E>(value);
			return node;
		}
		
		if(value.compareTo(node.value)<0) {
			node.left = insertNodeRec(node.left, value);
		} else if (value.compareTo(node.value)>0) {
			node.right = insertNodeRec(node.right, value);
		} else {
			System.out.println(value + " is already exists...");
		}
		
		return node;
	}
	
	/**
	 * Insert nodes without recurse
	 */
	public static <E extends Comparable<E>> BinaryTreeNode<E> insertNode(BinaryTreeNode<E> root, E value) {
		if(root==null) {
			root = new BinaryTreeNode<E>(value);
			return root;
		}
		
		BinaryTreeNode<E> node = root;
		while(node != null) {
			if(value.compareTo(node.value)>0) {
				if(node.right == null) {
					BinaryTreeNode<E> newNode = new BinaryTreeNode<E>(value);
					node.right = newNode;
					break;
				} else {
					node = node.right;
				}
			} else if(value.compareTo(node.value)<0) {
				if(node.left == null) {
					BinaryTreeNode<E> newNode = new BinaryTreeNode<E>(value);
					node.left = newNode;
					break;
				} else {
					node = node.left;
				}
			} else {
				System.out.println(value + " is already exists...");
				break;
			}
		}
		
		return root;
	}
	
	/**
	 * Delete node recursely
	 */
	public static <E extends Comparable<E>> BinaryTreeNode<E> deleteNodeRec(BinaryTreeNode<E> node, E value) {
		if(node == null) {
			return node;
		}
		
		if(value.compareTo(node.value)<=0) {
			node.left = deleteNodeRec(node.left, value);
		} else if(value.compareTo(node.value)>0) {
			node.right = deleteNodeRec(node.right, value);
		} else if(node.left!=null && node.right!=null) {
			BinaryTreeNode<E> minNode = node.right;
			BinaryTreeNode<E> parentNode = node;
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
		return node;
	}
	
	/**
	 * Delete node without recurse
	 */
	public static <E extends Comparable<E>> BinaryTreeNode<E> deleteNode(BinaryTreeNode<E> root, E value) {
		if(root == null) {
			return null;
		}
		
		BinaryTreeNode<E> node = root;
		BinaryTreeNode<E> parent = root;
		BinaryTreeNode<E> auxRoot = null;
		if(root.value.compareTo(value) == 0) {
			// If trying to delete root.
			// First add a new aux-root and then follow the same logic of deleting other node.
			parent = new BinaryTreeNode<E>();
			auxRoot = parent;
			parent.left = root;
			node = root;
		}
		
		boolean ifLeft = true;
		while(node!=null) {
			if(node.value.compareTo(value)<0) {
				parent = node;
				node = node.right;
				ifLeft = false;
			} else if(node.value.compareTo(value)>0) {
				parent = node;
				node = node.left;
				ifLeft = true;
			} else {
				if(node.left==null && node.right==null) {
					// All of its nodes are null
					if(ifLeft) {
						parent.left = null;
					} else {
						parent.right = null;
					}
				} else if(node.left!=null && node.right==null) {
					// Only one of its node (left) is not null
					node.value = node.left.value;
					node.right = node.left.right;
					node.left = node.left.left;
				} else if(node.left==null && node.right!=null) {
					// Only one of its node (right) is not null
					node.value = node.right.value;
					node.left = node.right.left;
					node.right = node.right.right;
				} else if(node.left!=null && node.right!=null) {
					// Both of its nodes are not null
					BinaryTreeNode<E> minNode = node.right;
					parent = node;
					if(minNode.left == null) {
						parent.value = minNode.value;
						parent.right = minNode.right;
					} else {
						while(minNode.left!=null) {
							parent = minNode;
							minNode = minNode.left;
						}
						node.value = minNode.value;
						parent.left = minNode.right;
					}
				}
				break;
			}
		}
		
		if(auxRoot!=null) {
			return auxRoot.left;
		}
		return root;
	}
	
	public static <E extends Comparable<E>> E findValue(BinaryTreeNode<E> root, E value) {
		BinaryTreeNode<E> node = root;
		while(node!=null) {
			if(node.value == value) {
				System.out.println("Find " + value);
				return value;
			} else if(node.value.compareTo(value)>0) {
				node = node.left;
			} else {
				node = node.right;
			}
		}
		
		System.out.println("Cannot Find " + value);
		return null;
	}
	
	public static <E extends Comparable<E>> BinaryTreeNode<E> findNode(BinaryTreeNode<E> root, E value) {
		BinaryTreeNode<E> node = root;
		while(node!=null) {
			if(node.value == value) {
				System.out.println("Find " + value);
				return node;
			} else if(node.value.compareTo(value)>0) {
				node = node.left;
			} else {
				node = node.right;
			}
		}
		
		System.out.println("Cannot Find " + value);
		return null;
	}
	
	public static <E extends Comparable<E>> E findBigger(BinaryTreeNode<E> root, E value) {
		BinaryTreeNode<E> node = root;
		BinaryTreeNode<E> bigger = null;
		while (node != null) {
			if (node.value.compareTo(value) == 0) {
				System.out.println("Find Equal " + value);
				return value;
			} else if (node.value.compareTo(value) > 0) {
				bigger = node;
				node = node.left;
			} else {
				node = node.right;
			}
		}
		if(bigger == null) {
			System.out.println("Cannot Find " + value);
			return null;
		} else {
			System.out.println("Find Bigger " + bigger.value);
			return bigger.value;
		}
	}
	
	public static <E extends Comparable<E>> E findSmaller(BinaryTreeNode<E> root, E value) {
		BinaryTreeNode<E> node = root;
		BinaryTreeNode<E> smaller = null;
		while (node != null) {
			if (node.value == value) {
				System.out.println("Find Equal " + value);
				return value;
			} else if (node.value.compareTo(value) < 0) {
				node = node.left;
			} else {
				smaller = node;
				node = node.right;
			}
		}
		if(smaller == null) {
			System.out.println("Cannot Find " + value);
			return null;
		} else {
			System.out.println("Find Smaller " + smaller.value);
			return smaller.value;
		}
	}
	
	/**
	 * Generate very simple integer binary search tree.
	 */
	public static BinaryTreeNode<Integer> genIntegerBST(int nodeCount) {
		Random random = new Random();
		BinaryTreeNode<Integer> root = null;
		for(int i=0;i<nodeCount;i++) {
			int value = random.nextInt(1000);
			root = insertNodeRec(root, value);
		}
		return root;
	}
	
	/**
	 * Generate binary search tree with lowest level from a sorted array.
	 */
	public static <E> BinaryTreeNode<E> genFromSortedArr(E[] arr, int start, int end) {
		if(start<=end) {
			int mid = start + (end-start)/2;
			BinaryTreeNode<E> node = new BinaryTreeNode<E>(arr[mid]);
			node.left = genFromSortedArr(arr, start, mid-1);
			node.right = genFromSortedArr(arr, mid+1, end);
			return node;
		} else {
			return null;
		}
	}
	
	/**
	 * Generate most-balanced binary search tree.
	 */
	public static BinaryTreeNode<Integer> genMostBalancedIntegerBST(int nodeCount) {
		Integer[] arr = new Integer[nodeCount];
		Random random = new Random();
		for(int i=0;i<nodeCount;i++) {
			int value = random.nextInt(100);
			arr[i] = value;
		}
		Arrays.sort(arr);
		
		return genFromSortedArr(arr, 0, nodeCount-1);
	}
}
