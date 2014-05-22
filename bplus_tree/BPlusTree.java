package bplus_tree;

//与B树不同的时，B+树的关键字都存储在叶子节点，分支节点均为索引，在实现上大致与B树类似，在几个细节稍有不同。
//
//（1） 数据结构中增加prev，next指针，用于将叶子节点串成有序双向链表。
//（2） 在节点分裂的时候，如果分裂的节点为叶子，则需要把中间节点保留在左(或右)边的分支上，并且需要更新prev和next。
//（3） 在节点合的时候，如果合并的节点为叶子，不需要把根节点下降为中间节点，并且需要更新prev和next。
//（4） 在向邻接节点借节点时，借来的关键字并不是父节点的关键字，而是邻接点的关键字，并根据实际情况更新父节点的索引。

public class BPlusTree {
	private Node root;
	private final int t;

	public BPlusTree(int t) {
		this.root = null;
		this.t = t;
	}

	private class Node {
		int n;
		int key[];
		boolean leaf;
		Node child[];
		Node prev;
		Node next;

		Node() {
			n = 0;
			key = new int[2 * t - 1];
			leaf = true;
			child = new Node[2 * t];
		}
	}

	Node btree_create() {
		root = new Node();
		root.prev = root;
		root.next = root;
		return root;
	}

	// 当y满时，将其进行分裂，y = x->child[pos]
	void btree_split_child(Node x, int pos, Node y) {
		Node z = new Node();
		// 新节点的leaf与child相同，key的个数为t-1
		z.leaf = y.leaf;
		z.n = t - 1;
		// 将child后半部分的key拷贝给新节点
		for (int i = 0; i < t - 1; i++)
			z.key[i] = y.key[i + t];
		// 如果child不是叶子，还需要把指针拷过去，指针比节点多1
		if (!y.leaf) {
			for (int i = 0; i < t; i++)
				z.child[i] = y.child[i + t];
		}
		y.n = t - 1;
		// if leaf, keep the middle ele, put it in the left
		if (y.leaf)
			y.n++;
		// y的中间节点需要插入x的pos处，更新x的key和child
		for (int i = x.n; i > pos; i--) {
			x.child[i + 1] = x.child[i];
		}
		x.child[pos + 1] = z;
		for (int i = x.n - 1; i >= pos; i--)
			x.key[i + 1] = x.key[i];
		x.key[pos] = y.key[t - 1];
		x.n++;

		// update link
		if (y.leaf) {
			z.next = y.next;
			if (y.next != null)
				y.next.prev = z;
			y.next = z;
			z.prev = y;
		}
	}

	// 执行该操作时，x->n < 2t-1
	void btree_insert_nonfull(Node x, int k) {
		if (x.leaf) { // 如果在叶子中找到
			int pos = x.n;
			while (pos > 0 && k < x.key[pos - 1]) {
				x.key[pos] = x.key[pos - 1];
				pos--;
			}
			x.key[pos] = k;
			x.n++;
		} else { // 沿着查找路径下降
			int pos = x.n;
			while (pos > 0 && k < x.key[pos - 1])
				pos--;
			if (x.child[pos].n == 2 * t - 1) {
				// 如果路径上有满节点则分裂
				btree_split_child(x, pos, x.child[pos]);
				if (k > x.key[pos])
					pos++;
			}
			btree_insert_nonfull(x.child[pos], k);
		}
	}

	Node btree_insert(Node root, int k) {
		if (root == null) {
			root = new Node();
			return root;
		}
		// 对根节点的特殊处理，如果根是满的，唯一使得树增高的情形
		// 先申请一个新的
		if (root.n == 2 * t - 1) {
			Node p = new Node();
			p.leaf = false;
			p.child[0] = root;
			btree_split_child(p, 0, root);
			root = p;
			btree_insert_nonfull(p, k);
			return root;
		} else {
			btree_insert_nonfull(root, k);
			return root;
		}
	}

	// 将y,z合并到y节点
	void btree_merge_child(Node x, int pos, Node y, Node z) {
		if (y.leaf) {
			y.n = 2 * t - 2;
			for (int i = t; i <= y.n; i++) {
				y.key[i - 1] = z.key[i - t];
			}
		} else {
			y.n = 2 * t - 1;
			y.key[t - 1] = x.key[pos];
			for (int i = t; i < y.n; i++)
				y.key[i] = z.key[i - t];
			for (int i = t; i <= y.n; i++)
				y.child[i] = z.child[i - t];
		}
		for (int i = pos + 1; i < x.n; i++) {
			x.key[i - 1] = x.key[i];
			x.child[i] = x.child[i + 1];
		}
		x.n--;
		z.n = 0;
		if (y.leaf) {
			y.next = z.next.next;
			if (z.next != null)
				z.next.prev = y;
		}
	}

	// 删除入口
	Node btree_delete(Node root, int k) {
		// 特殊处理，当根只有两个子女，且两个子女的关键字个数都为M-1时，合并根与两个子女
		// 这是唯一能降低树高的情形
		if (root.n == 1) {
			Node x = root.child[0];
			Node y = root.child[1];
			if (x != null && y != null && x.n == t - 1 && y.n == t - 1) {
				btree_merge_child(root, 0, x, y);
				root = x;
				btree_delete_nonone(root, k);
				return root;
			} else {
				btree_delete_nonone(root, k);
				return root;
			}
		} else {
			btree_delete_nonone(root, k);
			return root;
		}
	}

	// root至少有个t个关键字，保证不会回溯
	void btree_delete_nonone(Node x, int k) {
		if (x.leaf) {
			int i = 0;
			while (i < x.n && k > x.key[i])
				i++;
			if (k == x.key[i]) {
				for (; i < x.n - 1; i++)
					x.key[i] = x.key[i + 1];
				x.n--;
			}
		} else {
			int i = 0;
			Node y = null, lb = null, rb = null;
			while (i < x.n && k > x.key[i])
				i++;
			y = x.child[i];
			if (i > 0)
				lb = x.child[i - 1];
			if (i < x.n)
				rb = x.child[i + 1];
			if (y.n == t - 1) {
				if (i > 0 && lb.n > t - 1)
					btree_shift_to_right_child(x, i - 1, y, lb);
				else if (i < x.n && rb.n > t - 1)
					btree_shift_to_left_child(x, i, y, rb);
				else if (i > 0) {
					btree_merge_child(x, i - 1, lb, y);
					y = lb;
				} else
					btree_merge_child(x, i, y, rb);
				btree_delete_nonone(y, k);
			} else
				btree_delete_nonone(y, k);
		}
	}

	// y向lb借节点，即向左兄弟借节点
	void btree_shift_to_right_child(Node x, int pos, Node y, Node lb) {
		y.n++;
		for (int i = y.n - 1; i > 0; i--)
			y.key[i] = y.key[i - 1];
		if (!y.leaf) {
			y.key[0] = x.key[pos];
			x.key[pos] = lb.key[lb.n - 1];
		} else {
			y.key[0] = lb.key[lb.n - 1];
			x.key[pos] = lb.key[lb.n - 2];
		}
		if (!y.leaf) {
			for (int i = y.n; i > 0; i--)
				y.child[i] = y.child[i - 1];
			y.child[0] = lb.child[lb.n];
		}
		lb.n--;
	}

	// y向rb借节点,即向右兄弟借节点
	void btree_shift_to_left_child(Node x, int pos, Node y, Node rb) {
		y.n++;
		if (!y.leaf) {
			y.key[y.n - 1] = x.key[pos];
			x.key[pos] = rb.key[0];
		} else {
			y.key[y.n - 1] = rb.key[0];
			x.key[pos] = rb.key[0];
		}
		for (int i = 1; i < rb.n; i++)
			rb.key[i - 1] = rb.key[i];
		if (!y.leaf) {
			y.child[y.n] = rb.child[0];
			for (int i = 1; i <= rb.n; i++)
				rb.child[i - 1] = rb.child[i];
		}
		rb.n--;
	}

	boolean btree_search_key(Node x, int k) {
		while (!x.leaf) {
			int i = 0;
			while (i < x.n && k > x.key[i])
				i++;
			x = x.child[i];
		}
		if (x != null) {
			for (int i : x.key) {
				if (i == k)
					return true;
			}
		}
		return false;
	}

	void btree_inorder_print(Node root) {
		if (root != null) {
			btree_inorder_print(root.child[0]);
			for (int i = 0; i < root.n; i++) {
				System.out.print(root.key[i] + " ");
				btree_inorder_print(root.child[i + 1]);
			}
		}
	}

	void btree_linear_print() {
		if (root != null) {
			Node leftmost = root;
			while (!leftmost.leaf) {
				leftmost = leftmost.child[0];
			}
			Node iter = leftmost;
			do {
				for (int i = 0; i < iter.n; i++) {
					System.out.print(iter.key[i] + " ");
				}
				iter = iter.next;
			} while (iter != leftmost);
			System.out.println();
		}
	}

	void btree_level_display(Node root) {
		// just for simplicty, can't exceed 200 nodes in the tree
		Node[] queue = new Node[200];
		int front = 0;
		int rear = 0;

		queue[rear++] = root;

		while (front < rear) {
			Node node = queue[front++];

			System.out.print("[");
			for (int i = 0; i < node.n; i++) {
				System.out.print(node.key[i] + " ");
			}
			System.out.print("]");

			for (int i = 0; i <= node.n; i++) {
				if (null != node.child[i]) {
					queue[rear++] = node.child[i];
				}
			}
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int arr[] = { 18, 31, 12, 10, 15, 48, 45, 47, 50, 52, 23, 30, 20 };
		BPlusTree tree = new BPlusTree(2);
		Node root = tree.btree_create();
		for (int i : arr) {
			root = tree.btree_insert(root, i);
			tree.btree_level_display(root);
			tree.btree_linear_print();
		}
		System.out.println();
		 //int todel[] = { 15, 18, 23, 30, 31, 52, 99, 50, 48, 47, 45, 20, 12, 10 };
		int todel[] = { 45, 30, 12, 10 };
		for (int i : todel) {
			System.out.println("after delete:" + i);
			if (tree.btree_search_key(root, i)) {
				root = tree.btree_delete(root, i);
				tree.btree_level_display(root);
			} else {
				System.out.println("No such node!");
			}
		}
	}

}
