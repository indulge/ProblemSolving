package sg.ds.tree.binary;

public class Node<T> {
	T data;
	Node<T> left;
	Node<T> right;
	public Node() {
		
	}
	public Node(T data) {
		this.data = data;
		this.left = null;
		this.right = null;
	}
}
