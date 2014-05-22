package pairingheap;

public class Node {
	int key;
	Node child;
	//prev is the parent for first child; is the left sibling for other children
	//对于第一个孩子，prev属性表示该孩子的父结点；对于其他结点，prev属性表示该结点的左兄弟。
	Node prev;
	Node sibling;

	Node(int ele) {
		this.key = ele;
		this.child = null;
		this.prev = null;
		this.sibling = null;
	}
}
