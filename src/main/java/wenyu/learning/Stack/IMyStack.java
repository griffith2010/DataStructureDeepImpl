package wenyu.learning.Stack;

public interface IMyStack<E> {
	public boolean push(E element);
	public E pop();
	public E peek();
	public boolean clear();
	public int size();
	public boolean isEmpty();
	public String toString();
}
