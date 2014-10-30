package wenyu.learning.Tree.TrieTree;

public abstract class TrieTreeAPI<T> {
	private TrieTree<T> trie;
 
	public TrieTreeAPI() {
		trie = new TrieTree<T>();
	}
 
	public boolean insert(Object s) {
		return trie.insert(toArray(s));
	}
 
	public boolean search(Object s) {
		return trie.find(toArray(s));
	}
	
	public boolean delete(Object s) {
		return trie.delete(toArray(s));
	}
 
	public abstract T[] toArray(Object s);
 
	public String toString() {
		return trie.toString();
	}
 
}