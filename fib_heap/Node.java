package fib_heap;

public class Node {
	public Node(int ele) {
		parent = null;
		child = null;
		left = null;
		right = null;
		key = ele;
		degree = 0;
		mark = false;
	}

	Node parent;
	Node child;
	Node left;
	Node right;
	int key;
	int degree;
	boolean mark;
}
