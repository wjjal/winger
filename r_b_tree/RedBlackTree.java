package r_b_tree;

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
		if (root == null) {
			root = new Node(ele, null, null, null);
		} else {
			Node current = root;
			Node parent = null;
			int cmp = 0;
			// 搜索合适的叶子节点，以该叶子节点为父节点添加新节点
			while (current != null) {
				parent = current;
				cmp = ele.compareTo(current.data);
				// 如果新节点的值大于当前节点的值,以右子节点作为当前节点
				if (cmp > 0)
					current = current.right;
				// 如果新节点的值小于当前节点的值,以左子节点作为当前节点
				else if (cmp < 0)
					current = current.left;
				else
					return;
			}
			// 创建新节点
			Node newNode = new Node(ele, parent, null, null);
			// 如果新节点的值大于父节点的值,新节点作为父节点的右子节点
			if (cmp > 0)
				parent.right = newNode;
			// 如果新节点的值小于父节点的值,新节点作为父节点的左子节点
			else
				parent.left = newNode;
			// 维护红黑树
			fixAfterInsertion(newNode);
		}
	}

	public void fixAfterInsertion(Node x) {
		x.color = RED;
		// 直到x是根节点，或者x的父节点不是红色
		while (x != null && x != root && x.parent.color == RED) {
			// 如果x的父节点是其父节点的左子节点
			if (parentOf(x) == leftOf(parentOf(parentOf(x)))) {
				// 获取x的父节点的兄弟节点
				Node y = rightOf(parentOf(parentOf(x)));
				// 如果x的父节点的兄弟节点是红色
				if (colorOf(y) == RED) {
					// 将x的父节点设为黑色
					setColor(parentOf(x), BLACK);
					// 将x的父节点的兄弟节点设为黑色
					setColor(y, BLACK);
					// 将x的父节点的父节点设为红色
					setColor(parentOf(parentOf(x)), RED);
					x = parentOf(parentOf(x));
				} else // 如果x的父节点的兄弟节点是黑色
				{ // 如果x是其父节点的右子节点
					if (x == rightOf(parentOf(x))) {
						// 将x的父节点设为x
						x = parentOf(x);
						rotateLeft(x);
					}
					// 把x的父节点设为黑色
					setColor(parentOf(x), BLACK);
					// 把x的父节点的父节点设为红色
					setColor(parentOf(parentOf(x)), RED);
					rotateRight(parentOf(parentOf(x)));
				}
			}
			// 如果x的父节点是其父节点的右子节点
			else { // 获取x的父节点的兄弟节点
				Node y = leftOf(parentOf(parentOf(x)));
				if (colorOf(y) == RED) {
					// 将x的父节点设为黑色
					setColor(parentOf(x), BLACK);
					// 将x的父节点的兄弟节点设为黑色
					setColor(y, BLACK);
					// 将x的父节点的父节点设为红色
					setColor(parentOf(parentOf(x)), RED);
					x = parentOf(parentOf(x));
				} else // 如果x的父节点的兄弟节点是黑色
				{ // 如果x是其父节点的右子节点
					if (x == leftOf(parentOf(x))) {
						// 将x的父节点设为x
						x = parentOf(x);
						rotateRight(x);
					}
					// 把x的父节点设为黑色
					setColor(parentOf(x), BLACK);
					// 把x的父节点的父节点设为红色
					setColor(parentOf(parentOf(x)), RED);
					rotateLeft(parentOf(parentOf(x)));
				}
			}
		}
		// 将根节点设为黑色
		root.color = BLACK;
	}

	// 删除节点
	public void remove(T ele) {
		// 获取要删除的节点
		Node target = getNode(ele);
		// 如果被删除节点的左子树、右子树都不为空
		if (target.left != null && target.right != null) {
			// 找到target节点中序遍历的前一个节点
			// s用于保存target节点的左子树中值最大的节点
			Node s = target.left;
			// 搜索target节点的左子树中值最大的节点
			while (s.right != null) {
				s = s.right;
			}
			// 用s节点来代替p节点
			target.data = s.data;
			target = s;
		}
		// 开始修复它的替换节点，如果该替换节点的左孩子不为null
		Node replacement = (target.left != null ? target.left : target.right);
		if (replacement != null) {
			// 让replacement的parent指向target的parent
			replacement.parent = target.parent;
			// 如果target的parent为null，表明target本身是根节点
			if (target.parent == null) {
				root = replacement;
			}
			// 如果target是其父节点的左子节点
			else if (target == target.parent.left) {
				// 让target的父节点left指向replacement
				target.parent.left = replacement;
			}
			// 如果target是其父节点的右子节点
			else {
				// 让target的父节点right指向replacement
				target.parent.right = replacement;
			}
			// 彻底删除target节点
			target.left = target.right = target.parent = null;
			// 修复红黑树
			if (target.color == BLACK) {
				fixAfterDeletion(replacement);
			}
		}
		// target本身是根节点
		else if (target.parent == null) {
			root = null;
		} else {
			// target没有子节点，把它当成虚的替换节点。
			// 修复红黑树
			if (target.color == BLACK) {
				fixAfterDeletion(target);
			}
			if (target.parent != null) {
				// 如果target是其父节点的左子节点
				if (target == target.parent.left) {
					// 将target的父节点left设为null
					target.parent.left = null;
				}
				// 如果target是其父节点的右子节点
				else if (target == target.parent.right) {
					// 将target的父节点right设为null
					target.parent.right = null;
				}
				// 将target的parent设置null
				target.parent = null;
			}
		}
	}
  
	// 删除节点后修复红黑树
	private void fixAfterDeletion(Node x) {
		// 直到x是根节点，或x的颜色是红色
		while (x != root && colorOf(x) == BLACK) {
			// 如果x是其父节点的左子节点
			if (x == leftOf(parentOf(x))) {
				// 获取x节点的兄弟节点
				Node sib = rightOf(parentOf(x));
				// 如果sib节点是红色
				if (colorOf(sib) == RED) {
					// 将sib节点设为黑色
					setColor(sib, BLACK);
					// 将x的父节点设为红色
					setColor(parentOf(x), RED);
					rotateLeft(parentOf(x));
					// 再次将sib设为x的父节点的右子节点
					sib = rightOf(parentOf(x));
				}
				// 如果sib的两个子节点都是黑色
				if (colorOf(leftOf(sib)) == BLACK
						&& colorOf(rightOf(sib)) == BLACK) {
					// 将sib设为红色
					setColor(sib, RED);
					// 让x等于x的父节点
					x = parentOf(x);
				} else {
					// 如果sib的只有右子节点是黑色
					if (colorOf(rightOf(sib)) == BLACK) {
						// 将sib的左子节点也设为黑色
						setColor(leftOf(sib), BLACK);
						// 将sib设为红色
						setColor(sib, RED);
						rotateRight(sib);
						sib = rightOf(parentOf(x));
					}
					// 设置sib的颜色与x的父节点的颜色相同
					setColor(sib, colorOf(parentOf(x)));
					// 将x的父节点设为黑色
					setColor(parentOf(x), BLACK);
					// 将sib的右子节点设为黑色
					setColor(rightOf(sib), BLACK);
					rotateLeft(parentOf(x));
					x = root;
				}
			}
			// 如果x是其父节点的右子节点
			else {
				// 获取x节点的兄弟节点
				Node sib = leftOf(parentOf(x));
				// 如果sib的颜色是红色
				if (colorOf(sib) == RED) {
					// 将sib的颜色设为黑色
					setColor(sib, BLACK);
					// 将sib的父节点设为红色
					setColor(parentOf(x), RED);
					rotateRight(parentOf(x));
					sib = leftOf(parentOf(x));
				}
				// 如果sib的两个子节点都是黑色
				if (colorOf(rightOf(sib)) == BLACK
						&& colorOf(leftOf(sib)) == BLACK) {
					// 将sib设为红色
					setColor(sib, RED);
					// 让x等于x的父节点
					x = parentOf(x);
				} else {
					// 如果sib只有左子节点是黑色
					if (colorOf(leftOf(sib)) == BLACK) {
						// 将sib的右子节点也设为黑色
						setColor(rightOf(sib), BLACK);
						// 将sib设为红色
						setColor(sib, RED);
						rotateLeft(sib);
						sib = leftOf(parentOf(x));
					}
					// 将sib的颜色设为与x的父节点颜色相同
					setColor(sib, colorOf(parentOf(x)));
					// 将x的父节点设为黑色
					setColor(parentOf(x), BLACK);
					// 将sib的左子节点设为黑色
					setColor(leftOf(sib), BLACK);
					rotateRight(parentOf(x));
					x = root;
				}
			}
		}
		setColor(x, BLACK);
	}
	
	// 根据给定的值搜索节点
	public Node getNode(T ele) {
		// 从根节点开始搜索
		Node p = root;
		while (p != null) {
			int cmp = ele.compareTo(p.data);
			// 如果搜索的值小于当前p节点的值,向左子树搜索
			if (cmp < 0)
				p = p.left;
			// 如果搜索的值大于当前p节点的值,右子树搜索
			else if (cmp > 0)
				p = p.right;
			else
				return p;
		}
		return null;
	}

	 /** 
     * 执行如下转换 
     *  p        r 
     *     r   p    
     *  q        q 
     */  
	private void rotateLeft(Node p) {
		if (p != null) {
			// 取得p的右子节点
			Node r = p.right;
			Node q = r.left;
			// 将r的左子节点链到p的右节点链上
			p.right = q;
			// 让r的左子节点的parent指向p节点
			if (q != null)
				q.parent = p;
			r.parent = p.parent;
			// 如果p已经是根节点
			if (p.parent == null)
				root = r;
			// 如果p是其父节点的左子节点
			else if (p.parent.left == p)
				// 将r设为p的父节点的左子节点
				p.parent.left = r;
			else
				// 将r设为p的父节点的右子节点
				p.parent.right = r;

			r.left = p;
			p.parent = r;
		}
	}
	
	 /** 
     * 执行如下转换 
     *     p       l 
     *  l              p 
     *     q       q 
     */  
    private void rotateRight(Node p)   
    {  
        if (p != null)  
        {  
        	//取得p的左子节点
            Node l = p.left;
            Node q = l.right;
            //将l的右子节点链到p的左节点链上
            p.left =q;
            //让l的右子节点的parent指向p节点 
            if(q!=null)
            	q.parent = p;
            l.parent = p.parent;
            //如果p已经是根节点  
            if(p.parent == null) 
				root = l;
			else if (p.parent.left == p) //如果p是其父节点的左子节点,将l设为p的父节点的右子节点
				p.parent.left = l;
			else //如果p是其父节点的右子节点,将l设为p的父节点的左子节点
				p.parent.right = l;
			l.right = p;
			p.parent = l;
        }  
    }  
}