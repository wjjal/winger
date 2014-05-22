package tree;

public class MorrisEndOrderTraverse {
	public static void reverse(Node from, Node to) // reverse the tree nodes
													// 'from' -> 'to'.
	{
		if (from == to)
			return;
		Node x = from;
		Node y = from.rchild;
		Node z;
		while (true) {
			z = y.rchild;
			y.rchild = x;
			x = y;
			y = z;
			if (x == to)
				break;
		}
	}

	public static void printReverse(Node from, Node to) // print the reversed
														// tree nodes
	// 'from' -> 'to'.
	{
		reverse(from, to);
		Node p = to;
		while (true) {
			p.visit();
			if (p == from)
				break;
			p = p.rchild;
		}

		reverse(to, from);
	}

	public static void morrisEndOrderTraverse(Node root) {
		Node dump = new Node("");
		dump.lchild = root;
		Node cur = dump, prev = null;
		while (cur != null) {
			if (cur.lchild == null) {
				cur = cur.rchild;
			} else {
				prev = cur.lchild;
				while (prev.rchild != null && prev.rchild != cur)
					prev = prev.rchild;

				if (prev.rchild == null) {
					prev.rchild = cur;
					cur = cur.lchild;
				} else {
					printReverse(cur.lchild, prev); // call print
					prev.rchild = null;
					cur = cur.rchild;
				}
			}
		}
	}
}
