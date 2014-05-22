package b_tree;

//假设B树的度为t（t>=2），则B树满足如下要求：（参考算法导论）
//（1）  每个非根节点至少包含t-1个关键字，t个指向子节点的指针；至多包含2t-1个关键字，2t个指向子女的指针（叶子节点的子女为空）。
//（2）  节点的所有key按非降序存放，假设节点的关键字分别为K[1], K[2] … K[n], 
//指向子女的指针分别为P[1], P[2]…P[n+1]，其中n为节点关键字的个数。则有：
//P[1] <= K[1] <= P[2] <= K[2] …..<= K[n] <= P[n+1]   // 这里P[n]也指其指向的关键字
//（3）  若根节点非空，则根节点至少包含两个子女；
//（4）  所有的叶子节点都在同一层。

//在删除B树节点时，为了避免回溯，当遇到需要合并的节点时就立即执行合并，B树的删除算法如下：从root向叶子节点按照search规律遍历：
//（1）  如果target在叶节点x中，则直接从x中删除target，情况（2）和（3）会保证当再叶子节点找到target时，肯定能借节点或合并成功而不会引起父节点的关键字个数少于t-1。
//（2）  如果target在分支节点x中：
//（a）  如果x的左分支节点y至少包含t个关键字，则找出y的最右的关键字prev，并替换target，并在y中递归删除prev。
//（b）  如果x的右分支节点z至少包含t个关键字，则找出z的最左的关键字next，并替换target，并在z中递归删除next。
//（c）  否则，如果y和z都只有t-1个关键字，则将targe与z合并到y中，使得y有2t-1个关键字，再从y中递归删除target。
//（3）  如果关键字不在分支节点x中，则必然在x的某个分支节点p[i]中，如果p[i]节点只有t-1个关键字。
//（a）  如果p[i-1]拥有至少t个关键字，则将x的某个关键字降至p[i]中，将p[i-1]的最大节点上升至x中。
//（b）  如果p[i+1]拥有至少t个关键字，则将x个某个关键字降至p[i]中，将p[i+1]的最小关键字上升至x个。
//（c）  如果p[i-1]与p[i+1]都拥有t-1个关键字，则将p[i]与其中一个兄弟合并，将x的一个关键字降至合并的节点中，成为中间关键字。

public class BTree {
	private Node root;
	private final int t;

	public BTree(int t) {
		this.root = null;
		this.t = t;
	}

	private class Node {
		int n;
		int key[];
		boolean leaf;
		Node child[];

		Node() {
			n = 0;
			key = new int[2 * t - 1];
			leaf = true;
			child = new Node[2 * t];
		}
	}

	Node btree_create() {
		root = new Node();
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
		// y的中间节点需要插入x的pos处，更新x的key和child
		for (int i = x.n; i > pos; i--) {
			x.child[i + 1] = x.child[i];
		}
		x.child[pos + 1] = z;
		for (int i = x.n - 1; i >= pos; i--)
			x.key[i + 1] = x.key[i];
		x.key[pos] = y.key[t - 1];
		x.n++;
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
			btree_insert_nonfull(root, k);
			return root;
		} else {
			btree_insert_nonfull(root, k);
			return root;
		}
	}

	// 将y，x->k[pos], z合并到y节点，并释放z节点，y,z各有t-1个节点
	void btree_merge_child(Node x, int pos, Node y, Node z) {
		y.n = 2 * t - 1;
		y.key[t - 1] = x.key[pos];
		for (int i = 0; i < t - 1; i++)
			y.key[i + t] = z.key[i];
		if (!y.leaf) {
			for (int i = 0; i < t; i++)
				y.child[i + t] = z.child[i];
		}
		for (int i = pos + 1; i < x.n; i++) {
			x.key[i - 1] = x.key[i];
			x.child[i] = x.child[i + 1];
		}
		x.n--;
		z.n = 0;
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
			while (i < x.n && k > x.key[i])
				i++;
			if (i < x.n && k == x.key[i]) { // 如果在分支节点找到k
				Node y = x.child[i];
				Node z = x.child[i + 1];
				// 如果左分支关键字多于t-1，则找到左分支的最右节点prev，替换k,并在左分支中递归删除prev,情况2(a)
				if (y.n > t - 1) {
					int pre = btree_search_predecessor(y);
					x.key[i] = pre;
					btree_delete_nonone(y, pre);
				}
				// 如果右分支关键字多于t-1，则找到右分支的最左节点next，替换k,并在右分支中递归删除next,情况2(b)
				else if (z.n > t - 1) {
					int next = btree_search_successor(z);
					x.key[i] = next;
					btree_delete_nonone(z, next);
				}
				// 两个分支节点数都为t-1，则合并至y，并在y中递归删除k,情况2(c)
				else {
					btree_merge_child(x, i, y, z);
					btree_delete_nonone(y, k);
				}
			}
			// 在分支没有找到，肯定在分支的子节点中
			else {
				Node y = x.child[i];
				Node lb = null, rb = null;
				if (i < x.n)
					rb = x.child[i + 1];
				if (i > 0)
					lb = x.child[i - 1];
				if (y.n == t - 1) {
					// 左邻接节点关键字个数大于t-1,3(a)
					if (i > 0 && lb.n > t - 1)
						btree_shift_to_right_child(x, i - 1, y, lb);
					// 右邻接节点关键字个数大于t-1,3(b)
					else if (i < x.n && rb.n > t - 1)
						btree_shift_to_left_child(x, i, y, rb);
					// 3(c)
					else if (i > 0) {
						btree_merge_child(x, i - 1, lb, y);
						btree_delete_nonone(lb, k);
					}
					// 3(c)
					else {
						btree_merge_child(x, i, y, rb);
						btree_delete_nonone(y, k);
					}
				} else
					btree_delete_nonone(y, k);
			}
		}
	}

	// 寻找rightmost，以x为根的最大关键字
	int btree_search_predecessor(Node x) {
		while (!x.leaf)
			x = x.child[x.n];
		return x.key[x.n - 1];
	}

	// 寻找leftmost，以x为根的最小关键字
	int btree_search_successor(Node x) {
		while (!x.leaf)
			x = x.child[0];
		return x.key[0];
	}

	// y向lb借节点，将x.key[pos]下降至y，将lb的最大关键字上升至x的pos处,即向左兄弟借节点
	void btree_shift_to_right_child(Node x, int pos, Node y, Node lb) {
		y.n++;
		for (int i = y.n - 1; i > 0; i--)
			y.key[i] = y.key[i - 1];
		y.key[0] = x.key[pos];
		x.key[pos] = lb.key[lb.n - 1];
		if (!y.leaf) {
			for (int i = y.n; i > 0; i--)
				y.child[i] = y.child[i - 1];
			y.child[0] = lb.child[lb.n];
		}
		lb.n--;
	}

	// y向rb借节点，将x.k[pos]下降至y，将rb的最小关键字上升至x的pos处，即向右兄弟借节点
	void btree_shift_to_left_child(Node x, int pos, Node y, Node rb) {
		y.n++;
		y.key[y.n - 1] = x.key[pos];
		x.key[pos] = rb.key[0];
		for (int i = 1; i < rb.n; i++)
			rb.key[i - 1] = rb.key[i];
		if (!y.leaf) {
			y.child[y.n] = rb.child[0];
			for (int i = 1; i <= rb.n; i++)
				rb.child[i - 1] = rb.child[i];
		}
		rb.n--;
	}

	boolean btree_search_key(Node root, int k) {
		while (root != null) {
			int i = 0;
			while (i < root.n && k > root.key[i])
				i++;
			if (i < root.n && k == root.key[i])
				return true;
			root = root.child[i];
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
		BTree tree = new BTree(2);
		Node root = tree.btree_create();
		for (int i : arr) {
			root = tree.btree_insert(root, i);
			tree.btree_level_display(root);
		}
		tree.btree_inorder_print(root);
		System.out.println();
		int todel[] = { 15, 18, 23, 30, 31, 52, 99, 50, 48, 47, 45, 20, 12, 10 };
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
