package binomial_heap;

public class BinomialHeap {
	Node head;

	BinomialHeap(){
		this.head = null;
	}
	
	public static BinomialHeap Make_Binomial_Heap() {
		BinomialHeap h = new BinomialHeap();
		h.head = null;
		return h;
	}

	public Node Binomial_Heap_Min() {
		Node x = this.head;
		Node y = null;
		int min = Integer.MAX_VALUE;
		while (x != null) {
			if (x.key < min) {
				min = x.key;
				y = x;
			}
			x = x.sibling;
		}
		return y;
	}

	static void Binomial_Link(Node x, Node y) {
		y.parent = x;
		y.sibling = x.child;
		x.child = y;
		x.degree++;
	}

	BinomialHeap Binomial_Heap_Merge(BinomialHeap x) {
		Node p = null;
		Node headx = this.head;
		Node heady = x.head;
		if (headx.degree < heady.degree) {
			p = headx;
			headx = headx.sibling;
		} else {
			p = heady;
			heady = heady.sibling;
		}
		this.head = p;
		while (headx != null && heady != null) {
			if (headx.degree <= heady.degree) {
				p.sibling = headx;
				headx = headx.sibling;
			} else {
				p.sibling = heady;
				heady = heady.sibling;
			}
			p = p.sibling;
		}
		if (headx != null)
			p.sibling = headx;
		if (heady != null)
			p.sibling = heady;
		return this;
	}

	public BinomialHeap Binomial_Heap_Union(BinomialHeap x) {
		Binomial_Heap_Merge(x);
		x = null;
		if (this.head == null)
			return this;
		Node prev = null;
		Node cur = this.head;
		Node next = cur.sibling;
		while (next != null) {
			if (cur.degree != next.degree
					|| (next.sibling != null && cur.degree == next.sibling.degree)) {
				prev = cur;
				cur = next;
			} else if (cur.key < next.key) {
				cur.sibling = next.sibling;
				Binomial_Link(cur, next);
			} else {
				if (prev == null)
					this.head = next;
				else
					prev.sibling = next;
				Binomial_Link(next, cur);
				cur = next;
			}
			next = cur.sibling;
		}
		return this;
	}

	public void Binomial_Heap_Insert(int ele) {
		Node x = new Node(ele);
		Binomial_Heap_Insert(x);
	}

	public void Binomial_Heap_Insert(Node x) {
		if (this.head == null)
			head = x;
		else {
			BinomialHeap temp = Make_Binomial_Heap();
			temp.head = x;
			Binomial_Heap_Union(temp);
		}
	}

	public Node Binomial_Heap_Extract_Min() {
		Node x = this.head;
		Node p = null, y = null, z = null;
		int min = Integer.MAX_VALUE;
		while (x != null) {
			if (x.key < min) {
				min = x.key;
				y = x;
				z = p;
			}
			p = x;
			x = x.sibling;
		}
		z.sibling = y.sibling;
		BinomialHeap temp = Make_Binomial_Heap();
		Node cur = y.child;
		if (cur == null)
			return y;
		Node prev = null;
		Node next = cur.sibling;
		while (next != null) {
			cur.sibling = prev;
			cur.parent = null;
			prev = cur;
			cur = next;
			next = cur.sibling;
		}
		cur.sibling = prev;
		cur.parent = null;
		temp.head = cur;
		Binomial_Heap_Union(temp);
		return y;
	}

	public void Binomial_Heap_Decrease_Key(Node x, int k) {
		if (k >= x.key) {
			System.out.println("New key is not smaller than current key!");
			return;
		}
		x.key = k;
		Node y = x.parent;
		while (y != null && y.key > x.key) {
			Exchange(x,y);
			x = y;
			y = x.parent;
		}
	}
	
	private void Exchange(Node x, Node y) {
		x.key ^= y.key;
		y.key ^= x.key;
		x.key ^= y.key;
	}

	public void Binomial_Heap_Delete(Node x) {
		Binomial_Heap_Decrease_Key(x, Integer.MIN_VALUE);
		Binomial_Heap_Extract_Min();
	}

	public void Binomial_Heap_Delete(int ele) {
		Node x = find(ele, this.head);
		if (x == null) {
			System.out.println("No such node!");
			return;
		}
		Binomial_Heap_Decrease_Key(x, Integer.MIN_VALUE);
		Binomial_Heap_Extract_Min();
	}

	public Node find(int key, Node node) {
		if (null == node) {
			return null;
		}
		if (key == node.key) {
			return node;
		}
		Node n1 = find(key, node.sibling);
		Node n2 = find(key, node.child);
		if (n1 != null || n2 != null) {
			if (n1 != null) {
				return n1;
			} else {
				return n2;
			}
		} else {
			return null;
		}
	}

	public void traversal(Node node) {
		if (null == node) {
			return;
		}
		System.out.print(node.key + ",");
		traversal(node.child);
		traversal(node.sibling);
		
	}

	public static void main(String[] args) {
		int[] E = { 0, 12, 90, 1, 85, 12, 3, 13, 49, 55, 10, 3, 31, 97, 19, 93,
				41, 55, 56, 82, 2, };
		BinomialHeap heap = Make_Binomial_Heap();

		// 建立二项堆
		for (int i : E) {
			heap.Binomial_Heap_Insert(i);
			heap.traversal(heap.head);
			System.out.println();
		}
		// 遍历
		heap.traversal(heap.head);

		// 查找
		Node n = heap.find(87, heap.head);
		System.out.println();
		if (null == n) {
			System.out.print("结点不存在\n");
		} else
			System.out.print("要找的结点值为" + n.key + "\n");

		// 输出最小值
		System.out.println("最小值为：" + heap.Binomial_Heap_Min().key);

		// 删除结点
		heap.Binomial_Heap_Delete(19);

		heap.traversal(heap.head);
	}
}
