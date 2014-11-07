package wenyu.learning.Tree.BinaryTree;

public class ConvertBSTtoLink {
	
	public static <E> BinaryTreeNode<E> transfer_inorder(BinaryTreeNode<E> root) {
		BinaryTreeNode<E> newRoot = new BinaryTreeNode<E>();
		newRoot.right = root;
		
		BinaryTreeNode<E> node = newRoot.right;
		BinaryTreeNode<E> preNode = newRoot;
		while(node!=null) {
			if(node.left != null) {
				BinaryTreeNode<E> k1 = node.left;
				node.left = k1.right;
		        k1.right = node;
		        node = k1;
				preNode.right = node;
			} else {
				preNode = node;
				node = node.right;
			}
		}
		
		// For Double Link
		// Double link cannot be print, since it has loop
//		node = newRoot.right;
//		preNode = newRoot;
//		while(node!=null) {
//			node.left = preNode;
//			preNode = node;
//			node = node.right;
//		}
		
		return newRoot.right;
	}
	
	
	public static <E> BinaryTreeNode<E> transfer_preorder(BinaryTreeNode<E> root) {
		BinaryTreeNode<E> newRoot = new BinaryTreeNode<E>();
		newRoot.right = root;
		BinaryTreeNode<E> node = newRoot.right;
		BinaryTreeNode<E> preNode = newRoot;
		
		while(node != null) {
			if(node.left != null) {
				BinaryTreeNode<E> tmp = node.left;
				while(tmp.left!=null || tmp.right!=null) {
					if(tmp.right!=null) {
						tmp = tmp.right;
						continue;
					}
					
					if(tmp.left!=null) {
						tmp = tmp.left;
					}
				}
				tmp.right = node.right;
				node.right = node.left;
				node.left = null;
			}
			
			preNode = node;
			node = node.right;
		}

		// For Double Link
		// Double link cannot be print, since it has loop
//		node = newRoot.right;
//		preNode = newRoot;
//		while(node!=null) {
//			node.left = preNode;
//			preNode = node;
//			node = node.right;
//		}
		
		return newRoot.right;
	}
	
	public static void main(String[] args) {
		BinaryTreeNode<Integer> root = BSTUtils.genIntegerBST(10);
		TraversalBFS.traversalWithLine(root, null);
		System.out.println("===========================");
		root = transfer_inorder(root);
		TraversalBFS.traversalWithLine(root, null);

		System.out.println("==================================================================");

		root = BSTUtils.genIntegerBST(10);
		TraversalBFS.traversalWithLine(root, null);
		System.out.println("===========================");
		root = transfer_preorder(root);
		TraversalBFS.traversalWithLine(root, null);
	}
}
