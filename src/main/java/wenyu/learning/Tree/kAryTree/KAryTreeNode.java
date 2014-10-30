package wenyu.learning.Tree.kAryTree;

import java.lang.reflect.Array;

public class KAryTreeNode<E> {
	public final E value;
	public final KAryTreeNode<E>[] children;
	public KAryTreeNode<E> nextBrother;
	
	@SuppressWarnings("unchecked")
	public KAryTreeNode(E value, int k) {
		this.value = value;
		children = (KAryTreeNode<E>[]) Array.newInstance(KAryTreeNode.class, k);
	}
	public void clearChildren() {
		for(int i=0; i<children.length; i++) {
			children[i] = null;
		}
	}
	public int currChildrenCount() {
		int count = 0;
		for(KAryTreeNode<E> child : children) {
			if(child != null) {
				count++;
			}
		}
		return count;
	}
}
