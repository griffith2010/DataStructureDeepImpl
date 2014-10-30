package wenyu.learning.Tree.BalanceTree;

public class BalancedTreeNode<E> {
	public E value;
	public BalancedTreeNode<E> left;
	public BalancedTreeNode<E> right;
	public int height;
	
	public BalancedTreeNode(E value) {
		this.value = value;
	}
}
