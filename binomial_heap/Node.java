package binomial_heap;

public class Node {
	public Node(int ele) {
		parent = null;
		child = null;
		sibling = null;
		key = ele;
		degree = 0;
	}

	Node parent;
	Node child;
	Node sibling;
	int key;
	int degree;
}
