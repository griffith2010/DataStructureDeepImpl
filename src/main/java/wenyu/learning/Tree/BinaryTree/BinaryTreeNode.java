package wenyu.learning.Tree.BinaryTree;

public class BinaryTreeNode<E> {
	public E value;
	public BinaryTreeNode<E> left;
	public BinaryTreeNode<E> right;
	
	public BinaryTreeNode() {}
	public BinaryTreeNode(E value) {
		this.value = value;
	}
	
	public String toString() {
		return value.toString();
	}
}
