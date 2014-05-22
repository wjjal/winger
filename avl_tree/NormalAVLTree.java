package avl_tree;

//AVLTree without balance
public class NormalAVLTree<T extends Comparable> {
	private Node root;

	public NormalAVLTree() {
		root = null;
	}

	private class Node {
		Node parent;
		Node left;
		Node right;
		int height;
		Object data;

		public Node(Object data, Node left, Node right) {
			this.data = data;
			this.left = left;
			this.right = right;
			this.height = 0;
		}

		public String toString() {
			return "[data=" + data + "]";
		}

		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj.getClass() == Node.class) {
				Node target = (Node) obj;
				return data.equals(target.data) && left == target.left
						&& right == target.right;
			}
			return false;
		}
	}

	private int height(Node t) {
		return t == null ? -1 : t.height;
	}

	public void insert(T x) {
		root = insert(x, root);
	}

	public Node insert(T ele, Node t) {
		if (t == null)
			return new Node(ele, null, null);
		else {
			int cmp = ele.compareTo(t.data);
			if (cmp < 0) {
				t.left = insert(ele, t.left);
				t.left.parent = t;
				if (height(t.left) - height(t.right) == 2) {
					if (ele.compareTo(t.left.data) < 0) {// LL型
						t = rotateRight(t);
					} else {// LR型
						rotateLeft(t.left);
						t = rotateRight(t);
					}
				}
			} else if (cmp > 0) {
				t.right = insert(ele, t.right);
				t.right.parent = t;
				if (height(t.right) - height(t.left) == 2) {
					if (ele.compareTo(t.right.data) > 0) {// RR型
						t = rotateLeft(t);
					} else {// RL型
						rotateRight(t.right);
						t = rotateLeft(t);
					}
				}
			} else {
				System.out.print("The node has existed!");
				return t;
			}
			t.height = Math.max(height(t.left), height(t.right)) + 1;
			return t;
		}
	}

	public void delete(T x) {
		delete(x, root);
	}

	public Node delete(T ele, Node t) {
		if (t == null) {
			System.out.println("There's not such element");
			return null;
		} else {
			int cmp = ele.compareTo(t.data);
			if (cmp < 0) {
				Node del = delete(ele, t.left);
				if (del != null) {
					if (height(t.right) - height(t.left) == 2) {
						    t = rotateLeft(t);
					}
				}
			} else if (cmp > 0) {
				Node del = delete(ele, t.right);
				if (del != null) {
					if (height(t.left) - height(t.right) == 2) {
						t = rotateRight(t);
					}
				}
			} else {
				if (t.left != null && t.right != null) {
					Node s = successor(t);
					t.data = s.data;
					t = s;
				}
				Node replacement = (t.left != null ? t.left : t.right);
				if (replacement != null) {
					replacement.parent = t.parent;
					if (t.parent == null)
						root = replacement;
					else if (t == t.parent.left)
						t.parent.left = replacement;
					else
						t.parent.right = replacement;
					Node s = t.parent;
					t.left = t.right = t.parent = null;
					return s;
				} else if (t.parent == null) {
					root = null;
					return root;
				} else {
					if (t.parent != null) {
						if (t == t.parent.left)
							t.parent.left = null;
						else if (t == t.parent.right)
							t.parent.right = null;
						Node s = t.parent;
						t.parent = null;
						return s;
					}
				}
			}
			t.height = Math.max(height(t.left), height(t.right)) + 1;
			return t;
		}
	}

	public void makeEmpty() {
		root = null;
	}

	public boolean isEmpty() {
		return root == null;
	}

	public void printTree() {
		if (isEmpty())
			System.out.println("Empty tree");
		else
			printTree(root);
		System.out.println();
		printTree2(root);
	}

	public void printTree(Node t) {
		if (t != null) {
			printTree(t.left);
			System.out.print(t.data + " ");
			printTree(t.right);
		}
	}
	
	public void printTree2(Node t) {
		if (t != null) {
			System.out.print(t.data + " ");
			printTree2(t.left);
			printTree2(t.right);
		}
	}

	Node successor(Node t) {
		if (t == null)
			return null;
		else if (t.right != null) { // 往右，然后向左直到尽头
			Node p = t.right;
			while (p.left != null)
				p = p.left;
			return p;
		} else { // right为空，如果t是p的左子树，则p为t的直接后继
			Node p = t.parent;
			Node ch = t;
			while (p != null && ch == p.right) { // 如果t是p的右子树，则继续向上搜索其直接后继
				ch = p;
				p = p.parent;
			}
			return p;
		}
	}

	private Node rotateLeft(Node p) {
		if (p != null) {
			Node r = p.right;
			Node q = r.left;
			p.right = q;
			if (q != null)
				q.parent = p;
			r.parent = p.parent;
			if (p.parent == null)
				root = r;
			else if (p.parent.left == p)
				p.parent.left = r;
			else
				p.parent.right = r;
			r.left = p;
			p.parent = r;
			p.height = Math.max(height(p.left), height(p.right)) + 1;
			r.height = Math.max(p.height, height(r.right)) + 1;
			return r;
		}
		return null;
	}

	private Node rotateRight(Node p) {
		if (p != null) {
			Node l = p.left;
			Node q = l.right;
			p.left = q;
			if (q != null)
				q.parent = p;
			l.parent = p.parent;
			if (p.parent == null)
				root = l;
			else if (p.parent.left == p)
				p.parent.left = l;
			else
				p.parent.right = l;
			l.right = p;
			p.parent = l;
			p.height = Math.max(height(p.left), height(p.right)) + 1;
			l.height = Math.max(height(l.left), p.height) + 1;
			return l;
		}
		return null;
	}

	public static void main(String[] args) {
		NormalAVLTree<Integer> t = new NormalAVLTree<Integer>();
		final int NUMS = 10;
		final int GAP = 3;
		for (int i = GAP; i != 0; i = (i + GAP) % NUMS) {
			// System.out.print(i + " ");
			t.insert(i);
			t.printTree();
			System.out.println();
		}
		t.printTree();
		System.out.println();
		t.delete(3);
		t.printTree();
//		t.delete(5);
//		System.out.println();
//		t.printTree();
//		t.delete(4);
//		System.out.println();
//		t.printTree();
	}
}
