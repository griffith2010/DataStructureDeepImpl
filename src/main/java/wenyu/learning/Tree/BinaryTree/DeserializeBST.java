package wenyu.learning.Tree.BinaryTree;

public class DeserializeBST {
	private static final Object NULL = null;
	
	public static <E> BinaryTreeNode<E> deserialPreOrder(E[] elements, int[] idx) {
		/*
		 * Sample input "027  8  35  6  ";
		 */
		if(idx[0]>=elements.length || elements[idx[0]]==NULL) {
			return null;
		}
		
		BinaryTreeNode<E> node = new BinaryTreeNode<E>(elements[idx[0]]);
		idx[0]++;
		node.left = deserialPreOrder(elements, idx);
		idx[0]++;
		node.right = deserialPreOrder(elements, idx);
		return node;
	}
	
	public static <E> BinaryTreeNode<E> deserialPostOrder(E[] elements, int[] idx) {
		/*
		 * Sample input "  7  82  5  630"
		 */
		if(idx[0]<0 || elements[idx[0]]==NULL) {
			return null;
		}
		
		BinaryTreeNode<E> node = new BinaryTreeNode<E>(elements[idx[0]]);
		idx[0]--;
		node.right = deserialPostOrder(elements, idx);
		idx[0]--;
		node.left = deserialPostOrder(elements, idx);
		return node;
	}
	
	public static <E> BinaryTreeNode<E> deserialPreAndInOrder(E[] preorder, int[] preidx, E[] inorder, int instart, int inend) throws Exception {
		if(preorder.length != inorder.length) {
			throw new Exception("Invalid input");
		}
		
		if(preidx[0]>=preorder.length) {
			return null;
		}
		
		// Initial current node
		E currValue = preorder[preidx[0]];
		BinaryTreeNode<E> node = new BinaryTreeNode<E>(currValue);
		node.left = null;
		node.right = null;
		
		// find current node in inorder sequence
		int i=0;
		for(;i<=inend;i++) {
			if(inorder[instart+i].equals(preorder[preidx[0]])) {
				break;
			}
		}
		if(i>inend) {
			throw new Exception("Invalid input");
		}
		
		// form left tree
		if(i>0) {
			preidx[0]++;
			node.left = deserialPreAndInOrder(preorder,preidx,inorder,instart,instart+i-1);
		}
		
		// form right tree
		if(instart+i < inend) {
			preidx[0]++;
			node.right = deserialPreAndInOrder(preorder,preidx,inorder,instart+i+1,inend);
		}
	
		return node;
	}
	
	public static <E> BinaryTreeNode<E> deserialPostAndInOrder(E[] postorder, int[] postidx, E[] inorder, int instart, int inend) throws Exception {
		if(postorder.length != inorder.length) {
			throw new Exception("Invalid input");
		}
		
		if(postidx[0]<0) {
			return null;
		}

		// Initial current node
		E currValue = postorder[postidx[0]];
		BinaryTreeNode<E> node = new BinaryTreeNode<E>(currValue);
		node.left = null;
		node.right = null;

		// find current node in inorder sequence
		int i=0;
		for(;i<=inend;i++) {
			if(inorder[instart+i].equals(postorder[postidx[0]])) {
				break;
			}
		}
		if(i>inend) {
			throw new Exception("Invalid input");
		}
		
		// form right tree
		if(instart+i < inend) {
			postidx[0]--;
			node.right = deserialPostAndInOrder(postorder,postidx,inorder,instart+i+1,inend);
		}
		
		// form left tree
		if(i>0) {
			postidx[0]--;
			node.left = deserialPostAndInOrder(postorder,postidx,inorder,instart,instart+i-1);
		}
		
		return node;
	}
}
