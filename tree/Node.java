package tree;

public class Node {
	String value;
	Node lchild;
	Node rchild;
	boolean flag;
	int maxLeft;
	int maxRight;

	public Node(String s) {
		// TODO Auto-generated constructor stub
		flag = false;
		value = s;
		maxLeft = 0;
		maxRight = 0;
	}

	public Node(String s, Node node1, Node node2) {
		// TODO Auto-generated constructor stub
		flag = false;
		value = s;
		lchild = node1;
		rchild = node2;
		maxLeft = 0;
		maxRight = 0;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Node getLchild() {
		return lchild;
	}

	public void setLchild(Node lchild) {
		this.lchild = lchild;
	}

	public Node getRchild() {
		return rchild;
	}

	public void setRchild(Node rchild) {
		this.rchild = rchild;
	}

	public void visit() {
		System.out.print(this.getValue());
	}
}
