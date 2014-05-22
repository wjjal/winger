package tree;

//模仿TreeMap中红黑树实现方式实现，注释版请见package R_B_Tree
public class RedBlackTree<T extends Comparable> {
	// 定义红黑树的颜色
	private static final boolean RED = false;
	private static final boolean BLACK = true;
	private Node root;

	static class Node {
		Object data;
		Node parent;
		Node left;
		Node right;
		// 节点的默认颜色是黑色
		boolean color = BLACK;

		public Node(Object data, Node parent, Node left, Node right) {
			this.data = data;
			this.parent = parent;
			this.left = left;
			this.right = right;
		}

		public String toString() {
			return "[data=" + data + ", color=" + color + "]";
		}

		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj.getClass() == Node.class) {
				Node target = (Node) obj;
				return data.equals(target.data) && color == target.color
						&& left == target.left && right == target.right
						&& parent == target.parent;
			}
			return false;
		}
	}

	// 获取指定节点的颜色
	private boolean colorOf(Node p) {
		return (p == null ? BLACK : p.color);
	}

	// 获取指定节点的父节点
	private Node parentOf(Node p) {
		return (p == null ? null : p.parent);
	}

	// 为指定节点设置颜色
	private void setColor(Node p, boolean c) {
		if (p != null) {
			p.color = c;
		}
	}

	// 获取指定节点的左子节点
	private Node leftOf(Node p) {
		return (p == null) ? null : p.left;
	}

	// 获取指定节点的右子节点
	private Node rightOf(Node p) {
		return (p == null) ? null : p.right;
	}

	// 两个构造器用于创建排序二叉树
	public RedBlackTree() {
		root = null;
	}

	public RedBlackTree(T o) {
		root = new Node(o, null, null, null);
	}

	public void add(T ele) {
		if (root == null)
			root = new Node(ele, null, null, null);
		else {
			Node current = root;
			//Node parent = null;
			int cmp = 0;
			//parent = current;
			while (current != null) {
				cmp = ele.compareTo(current.data);
				if (cmp > 0)
					current = current.right;
				else if (cmp < 0)
					current = current.left;
				else
					return;
			}
			Node newNode = new Node(ele, current, null, null);
			if (cmp > 0)
				current.right = newNode;
			else
				current.left = newNode;
			fixAfterInsertion(newNode);
		}
	}

	private void fixAfterInsertion(Node x) {
		x.color = RED;
		while (x != null && x.parent != null && x.parent.color == RED) {
			if (parentOf(x) == leftOf(parentOf(parentOf(x)))) {
				Node y = rightOf(parentOf(parentOf(x)));
				if (y.color == RED) {
					setColor(parentOf(x), BLACK);
					setColor(y, BLACK);
					setColor(parentOf(parentOf(x)), RED);
					x = parentOf(parentOf(x));
				} else {
					if (x == rightOf(parentOf(x))) {
						x = parentOf(x);
						rotateLeft(x);
					}
					setColor(parentOf(x), BLACK);
					setColor(parentOf(parentOf(x)), RED);
					rotateRight(parentOf(parentOf(x)));
				}
			} else {
				Node y = leftOf(parentOf(parentOf(x)));
				if (y.color == RED) {
					setColor(parentOf(x), BLACK);
					setColor(y, BLACK);
					setColor(parentOf(parentOf(x)), RED);
					x = parentOf(parentOf(x));
				} else {
					if (x == leftOf(parentOf(x))) {
						x = parentOf(x);
						rotateRight(x);
					}
					setColor(parentOf(x), BLACK);
					setColor(parentOf(parentOf(x)), RED);
					rotateLeft(parentOf(parentOf(x)));
				}
			}
		}
		setColor(root, BLACK);
	}

	public void remove(T ele) {
		Node p = getNode(ele);
		if (p == null) {
			System.out.println("There's no such element");
			return;
		}
		if (p.left != null && p.right != null) {
			Node s = successor(p);
			// Node s = p.left;
			// while (s.right != null) {
			// s = s.right;
			// }
			p.data = s.data;
			p = s;
		}
		Node replacement = (p.left != null ? p.left : p.right);
		if (replacement != null) {
			replacement.parent = p.parent;
			if (p.parent == null)
				root = replacement;
			else if (p.parent.left == p)
				p.parent.left = replacement;
			else
				p.parent.right = replacement;
			p.left = p.right = p.parent = null;

			if (p.color == BLACK)
				fixAfterDeletion(replacement);
		} else if (p.parent == null) {
			root = null;
		} else {
			if (p.color == BLACK)
				fixAfterDeletion(p);
			if (p.parent != null) {
				if (p.parent.left == p)
					p.parent.left = null;
				else
					p.parent.right = null;
				p.parent = null;
			}
		}
	}

	private void fixAfterDeletion(Node x) {
		while (x != root && x.color == BLACK) {
			if (x == leftOf(parentOf(x))) {
				Node sib = rightOf(parentOf(x));
				if (sib.color == RED) {
					setColor(sib, BLACK);
					setColor(parentOf(x), RED);
					rotateLeft(parentOf(x));
					sib = rightOf(parentOf(x));
				}
				if (colorOf(leftOf(sib)) == BLACK
						&& colorOf(rightOf(sib)) == BLACK) {
					setColor(sib, RED);
					x = parentOf(x);
				} else {
					if (colorOf(rightOf(sib)) == BLACK) {
						setColor(leftOf(sib), BLACK);
						setColor(sib, RED);
						rotateRight(sib);
						sib = rightOf(parentOf(x));
					}
					setColor(sib, colorOf(parentOf(x)));
					setColor(parentOf(x), BLACK);
					setColor(rightOf(sib), BLACK);
					rotateLeft(parentOf(parentOf(x)));
					x = root;
				}
			} else {
				Node sib = leftOf(parentOf(x));
				if (sib.color == RED) {
					setColor(sib, BLACK);
					setColor(parentOf(x), RED);
					rotateRight(parentOf(x));
					sib = leftOf(parentOf(x));
				}
				if (colorOf(leftOf(sib)) == BLACK
						&& colorOf(rightOf(sib)) == BLACK) {
					setColor(sib, RED);
					x = parentOf(x);
				} else {
					if (colorOf(leftOf(sib)) == BLACK) {
						setColor(rightOf(sib), BLACK);
						setColor(sib, RED);
						rotateLeft(sib);
						sib = leftOf(parentOf(x));
					}
					setColor(sib, colorOf(parentOf(x)));
					setColor(parentOf(x), BLACK);
					setColor(leftOf(sib), BLACK);
					rotateRight(parentOf(x));
					x = root;
				}
			}
		}
	}

	public Node getNode(T ele) {
		Node p = root;
		while (p != null) {
			int cmp = ele.compareTo(p.data);
			if (cmp < 0)
				p = p.left;
			else if (cmp > 0)
				p = p.right;
			else
				return p;
		}
		return null;
	}

	// Returns the successor of Node t
	public Node successor(Node t) {
		if (t == null)
			return null;
		else if (t.right != null) {
			Node p = t.right;
			while (p.left != null)
				p = p.left;
			return p;
		} else {
			Node p = t.parent;
			Node ch = t;
			while (p != null && ch == p.right) {
				ch = p;
				p = p.parent;
			}
			return p;
		}
	}

	// Returns the presuccessor of Node t
	public Node predecessor(Node t) {
		if (t == null)
			return null;
		else if (t.left != null) {
			Node p = t.left;
			while (p.right != null)
				p = p.right;
			return p;
		} else {
			Node p = t.parent;
			Node ch = t;
			while (p != null && ch == p.left) {
				ch = p;
				p = p.parent;
			}
			return p;
		}
	}

	private void rotateLeft(Node p) {
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
		}
	}

	private void rotateRight(Node p) {
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
		}
	}
}
