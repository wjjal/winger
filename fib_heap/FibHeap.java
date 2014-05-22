package fib_heap;

public class FibHeap {
	Node head;
	Node min;
	int n;
	int maxdegree;

	FibHeap() {
		this.head = null;
		this.min = null;
		this.n = 0;
		this.maxdegree = 0;
	}

	public static FibHeap Make_Fib_Heap() {
		FibHeap h = new FibHeap();
		return h;
	}

	public void Fib_Heap_Insert(int ele) {
		Node x = new Node(ele);
		Fib_heap_Insert(x);
	}

	public void Fib_heap_Insert(Node x) {
		x.left = x;
		x.right = x;
		x.mark = false;
		if (this.head == null) {
			this.head = x;
		} else {
			Insert_Into_List(this.head, x);
		}
		if (this.min == null || min.key > x.key)
			this.min = x;
		this.n++;
		this.maxdegree = Math.max(this.maxdegree, x.degree);
	}

	public void Fib_Heap_Union(FibHeap x) {
		if (x == null)
			return;
		if (this.head == null) {
			this.head = x.head;
			this.min = x.min;
			this.n = x.n;
			return;
		}
		Node temp = x.head;
		for (int i = 1; i <= x.n; i++) {
			Insert_Into_List(this.head, temp);
			temp = temp.right;
		}
		if (x.min.key < this.min.key)
			this.min = x.min;
		this.n += x.n;
		this.maxdegree = Math.max(this.maxdegree, x.maxdegree);
	}

	public Node Fib_Heap_Extract_Min() {
		Node p = this.min;
		this.n--;
		if (p != null) {
			Node c = p.child;
			if (c != null) {
				Node temp = c;
				Node flag = c;
				do {
					c = temp;
					temp = temp.right;
					Insert_Into_List(p, c);
					c.parent = null;
					this.n++;
				} while (temp != flag);
			}
			Remove_From_List(p);
			if (p.right == p)
				this.min = null;
			else {
				this.min = p.left;
				this.Consolidate();
			}
		}
		return p;
	}

	private void Consolidate() {
		Node A[] = new Node[this.maxdegree + n];
		Node x = this.head;
		for (int i = 1; i <= this.n; i++) {
			int d = x.degree;
			while (A[d] != null) {
				Node y = A[d];
				if (x.key > y.key)
					Exchange(x, y);
				// this.FibHeapPrint();
				Fib_Heap_Link(x, y);
				A[d] = null;
				d++;
			}
			this.head = x.right;
			x.left = x.right = x;
			A[d] = x;
			x = this.head;
		}
		this.head = null;
		this.min = null;
		this.n = 0;
		this.maxdegree = 0;
		Node prev = null;
		for (Node p : A) {
			if (p != null) {
				if (this.head == null)
					head = p;
				else
					Insert_Into_List(prev, p);
				if (this.min == null || p.key < this.min.key)
					this.min = p;
				prev = p;
				this.n++;
			}
		}
		this.maxdegree = prev.degree;
	}

	private void Fib_Heap_Link(Node x, Node y) {
		Remove_From_List(y);
		Node c = x.child;
		if (c == null) {
			x.child = y;
			y.left = y;
			y.right = y;
		} else
			Insert_Into_List(c, y);
		y.parent = x;
		x.degree++;
		y.mark = false;
	}

	public void Fib_Heap_Decrease_Key(Node x, int k) {
		if (k >= x.key) {
			System.out.println("New key is not smaller than current key!");
			return;
		}
		x.key = k;
		Node p = x.parent;
		if (p != null && x.key < p.key) {
			Cut(x, p);
			Cascading_Cut(p);
		}
		if (x.key < this.min.key)
			this.min = x;
	}

	private void Cut(Node child, Node parent) {
		Remove_From_List(child);
		parent.degree--;
		Insert_Into_List(this.head, child);
		this.n++;
		child.mark = false;
	}

	private void Cascading_Cut(Node child) {
		Node parent = child.parent;
		if (parent != null) {
			if (!child.mark)
				child.mark = true;
			else {
				Cut(child, parent);
				Cascading_Cut(parent);
			}
		}
	}

	public void Fib_Heap_Delete(Node x) {
		Fib_Heap_Decrease_Key(x, Integer.MIN_VALUE);
		Fib_Heap_Extract_Min();
	}

	public void Fib_Heap_Delete(int ele) {
		Node x = FibHeapSearch(ele);
		if (x == null) {
			System.out.println("No key" + ele + "!");
			return;
		}
		Fib_Heap_Delete(x);
	}

	private void Remove_From_List(Node p) {
		if (p == null)
			return;
		if (head == p)
			head = p.right;
		if (p.right == p) {
			if (p.parent != null)
				p.parent.child = null;
			p.parent = null;
			return;
		}
		if (p.parent != null && p.parent.child == p)
			p.parent.child = p.right;
		if (p.left != null)
			p.left.right = p.right;
		if (p.right != null)
			p.right.left = p.left;
		p.parent = null;
	}

	// insert node y into right of Node x
	private void Insert_Into_List(Node x, Node y) {
		y.right = x.right;
		x.right.left = y;
		y.left = x;
		x.right = y;
		y.parent = x.parent;
	}

	private void Exchange(Node x, Node y) {
		x.key ^= y.key;
		y.key ^= x.key;
		x.key ^= y.key;
	}

	// 堆内搜索关键字
	public Node FibHeapSearch(int k) {
		return FibNodeSearch(this.head, k);
	}

	// 被FibHeapSearch调用
	private Node FibNodeSearch(Node x, int k) {
		Node w = x, result = null;
		if (x != null) {
			do {
				if (w.key == k) {
					result = w;
					break;
				} else if ((result = FibNodeSearch(w.child, k)) != null) {
					break;
				}
				w = w.right;
			} while (w != x);
		}
		return result;
	}

	// 输出打印堆
	public void FibHeapPrint() {
		System.out.println("The keyNum = " + this.n);
		FibNodePrint(this.head);
		System.out.println();
	};

	// 被FibHeapPrint调用
	private void FibNodePrint(Node x) {
		Node p = null;
		if (x == null) {
			return;
		}
		p = x;
		do {
			System.out.print(" (");
			System.out.print(p.key);
			if (p.child != null) {
				FibNodePrint(p.child);
			}
			System.out.print(") ");
			p = p.right;
		} while (p != x);
	}

	public static void main(String[] args) {
		int keys[] = { 11, 10, 9, 7, 6, 5, 4, 3, 2, 1 };
		FibHeap h = Make_Fib_Heap();
		for (int i : keys) {
			h.Fib_Heap_Insert(i);
		}
		h.FibHeapPrint();

		Node x = h.Fib_Heap_Extract_Min();
		System.out.println("抽取最小值" + x.key + "之后");
		h.FibHeapPrint();

		// x = h.FibHeapSearch(11);
		// if (x != null) {
		// System.out.print("查找" + x.key + "成功,");
		// h.Fib_Heap_Decrease_Key(x, 8);
		// System.out.println("减小到" + x.key + "后：");
		// h.FibHeapPrint();
		// }

		x = h.FibHeapSearch(7);
		if (x != null) {
			System.out.println("删除" + x.key + "成功:");
			h.Fib_Heap_Delete(x);
			h.FibHeapPrint();
		}
		x = h.FibHeapSearch(4);
		Node y = x.parent;
		if (x != null) {
			System.out.println("删除" + x.key + "成功:");
			h.Fib_Heap_Delete(x);
			h.FibHeapPrint();
		}
		x = h.FibHeapSearch(3);
		System.out.println(y.mark);
		if (x != null) {
			System.out.println("删除" + x.key + "成功:");
			h.Fib_Heap_Delete(x);
			h.FibHeapPrint();
		}
	}
}
