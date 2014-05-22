package pairingheap;

public class PairingHeap {
	Node root;

	PairingHeap() {
		root = null;
	}

	public static PairingHeap Make_Pairing_Heap() {
		PairingHeap h = new PairingHeap();
		return h;
	}

	public void Pairing_Heap_Union(PairingHeap x) {
		if (x.root == null) {
			return;
		}
		if (this.root == null) {
			this.root = x.root;
			return;
		}
		if (this.root.key <= x.root.key) {
			x.root.sibling = this.root.child;
			if (x.root.sibling != null) {
				x.root.sibling.prev = x.root;
			}
			x.root.prev = this.root;
			this.root.child = x.root;
		} else {
			this.root.prev = x.root;
			this.root.sibling = x.root.child;
			if (this.root.sibling != null) {
				this.root.sibling.prev = this.root;
			}
			x.root.child = this.root;
			this.root = x.root;
		}
	}

	public void Pairing_Heap_Union(Node q) {
		if (q == null) {
			return;
		}
		if (this.root == null) {
			this.root = q;
			return;
		}
		Node p = this.root;
		if (p.key <= q.key) {
			q.prev = p;
			p.sibling = q.sibling;
			if (p.sibling != null)
				p.sibling.prev = p;
			q.sibling = p.child;
			if (q.sibling != null)
				q.sibling.prev = q;
			p.child = q;
		} else {
			q.prev = p.prev;
			p.prev = q;
			p.sibling = q.child;
			if (p.sibling != null)
				p.sibling.prev = p;
			q.child = p;
			this.root = q;
		}
	}

	public void Pairing_Heap_Insert(int ele) {
		Node x = new Node(ele);
		Pairing_Heap_Insert(x);
	}

	public void Pairing_Heap_Insert(Node x) {
		if (x == null)
			return;
		Pairing_Heap_Union(x);
	}

	public void Pairing_Heap_Delete(Node x) {
		Pairing_Heap_Decrease_Key(x, Integer.MIN_VALUE);
		Pairing_Heap_Extract_Min();
	}

	public void Pairing_Heap_Decrease_Key(Node x, int k) {
		if (k >= x.key) {
			System.out.println("New key is not smaller than current key!");
			return;
		}
		x.key = k;
		if (x == this.root)
			return;
		if (x.sibling != null)
			x.sibling.prev = x.prev;
		if (x.prev.child == x)
			x.prev.child = x.sibling;
		else
			x.prev.sibling = x.sibling;
		x.sibling = null;
		Pairing_Heap_Union(x);
	}

	public Node Pairing_Heap_Extract_Min() {
		Node p = this.root;
		this.root = null;
		if (p.child != null) {
			Combine_Siblings(p.child);
		}
		return p;
	}

	private void Combine_Siblings(Node p) {
		if (p.sibling == null) {
			this.root = p;
			return;
		}
		Node q = null;
		while (p != null) {
			q = p.sibling;
			p.prev = null;
			p.sibling = null;
			Pairing_Heap_Union(p);
			p = q;
		}
	}

	public Node PairingHeapsearch(int k) {
		return PairingHeapsearch(this.root, k);
	}

	public Node PairingHeapsearch(Node x, int k) {
		if (x == null)
			return null;
		if (x.key == k)
			return x;
		Node m = PairingHeapsearch(x.sibling, k);
		Node n = PairingHeapsearch(x.child, k);
		return m != null ? m : n;
	}

	public void PairingHeapPrint() {
		PairingNodePrint(this.root);
		System.out.println();
	}

	public void PairingNodePrint(Node x) {
		if (x == null)
			return;
		System.out.print("(" + x.key + " ");
		PairingNodePrint(x.child);
		System.out.print(") ");
		PairingNodePrint(x.sibling);
	}

	public static void main(String args[]) {
		int keys[] = { 50, 90, 85, 12, 13, 49, 55, 3, 10, 31, 1, 97, 19 };
		PairingHeap h = Make_Pairing_Heap();
		for (int i : keys)
			h.Pairing_Heap_Insert(i);
		System.out.println("After insert all the element:");
		h.PairingHeapPrint();
		Node x = h.Pairing_Heap_Extract_Min();
		System.out.println("The min value is " + x.key);
		System.out.println("After extract the minimum node:");
		h.PairingHeapPrint();
		x = h.PairingHeapsearch(12);
		System.out.println("After " + x.key + " has been decreased to 0:");
		h.Pairing_Heap_Decrease_Key(x, 0);
		h.PairingHeapPrint();
		x = h.PairingHeapsearch(97);
		h.Pairing_Heap_Delete(x);
		System.out.println("After delete 97:");
		h.PairingHeapPrint();
		x = h.PairingHeapsearch(50);
		h.Pairing_Heap_Delete(x);
		System.out.println("After delete 50:");
		h.PairingHeapPrint();
	}
}
