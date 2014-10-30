package wenyu.learning.List;

public class MyListNode<T> {
	private T value = null;
	public MyListNode<T> previous = null;
	public MyListNode<T> next = null;
	public MyListNode<T> random = null;
	
	public MyListNode() {}
	
	public MyListNode(T value) {
		this.value = value;
	}
	
	public T getValue() {
		return value;
	}
	
	public void setValue(T value) {
		this.value = value;
	}
	
	public String toString() {
		return value.toString();
	}
}
