package wenyu.learning.Heap;

public interface IMyHeap<E extends Comparable<E>> {
	public void insert(E item);
	public E pop();
	public E peek();
	public int size();
	public boolean isEmpty();
}
