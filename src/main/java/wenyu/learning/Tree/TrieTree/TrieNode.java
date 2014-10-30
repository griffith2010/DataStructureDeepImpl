package wenyu.learning.Tree.TrieTree;

import java.util.HashMap;
import java.util.Map;

public class TrieNode<T> {
	private T value;
	private Map<T, TrieNode<T>> children;
	private boolean endMarker;
	
	public TrieNode(T value) {
		this.value = value;
		children = new HashMap<T, TrieNode<T>>();
		endMarker = false;
	}
	
	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public TrieNode<T> insertChild(T key) {
		if(children == null) {
			return null;
		}
		
		if(children.containsKey(key)) {
			return children.get(key);
		} else {
			TrieNode<T> newChild = new TrieNode<T>(key);
			children.put(key, newChild);
			return newChild;
		}
	}

	public TrieNode<T> getChild(T key) {
		if(children == null) {
			return null;
		}
		
		if(children.containsKey(key)) {
			return children.get(key);
		} else {
			return null;
		}
	}
	
	public boolean removeChild(T key) {
		if(children == null) {
			return false;
		}
		
		if(children.containsKey(key)) {
			children.remove(key);
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isEndMarker() {
		return endMarker;
	}

	public void setEndMarker(boolean endMarker) {
		this.endMarker = endMarker;
	}

	public int getChildrenSize() {
		if(children == null) {
			return 0;
		}
		
		return children.size();
	}
}
