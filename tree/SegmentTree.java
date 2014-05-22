package tree;

import java.util.Scanner;

class segment {
	int b, e;
	int cover;
}

public class SegmentTree {// 线段树，用数组实现
	static int M = 5000000;
	segment seg[];

	int Nnode;// 节点数
	int LastNode;// 最后一个节点

	public SegmentTree() {
		seg = new segment[M];
		for (int i = 0; i < M; i++)
			seg[i] = new segment();
	}

	void build(int b, int e, int root) {// 构造线段树
		Nnode++;
		if (root > LastNode)
			LastNode = root;
		seg[root].b = b;
		seg[root].e = e;
		if (b == e)
			return;
		int mid = (b + e) >> 1;
		build(b, mid, root << 1);
		build(mid + 1, e, (root << 1) + 1);
	}

	void insert(int l, int r, int node) {
		if (l <= seg[node].b && seg[node].e >= r)
			seg[node].cover++;
		else {
			int mid = (seg[node].b + seg[node].e) >> 1;
			if (l <= mid)
				insert(l, mid, node << 1);
			if (r >= mid)
				insert(mid, r, node << 1 + 1);
		}
	}

	void delete(int l, int r, int node) {
		if (l <= seg[node].b && seg[node].e >= r)
			seg[node].cover--;
		else {
			int mid = (seg[node].b + seg[node].e) >> 1;
			if (l <= mid)
				delete(l, mid, node << 1);
			if (r >= mid)
				delete(mid, r, node << 1 + 1);
		}
	}

	int count(int l, int r, int node) {
		if (l <= seg[node].b && seg[node].e >= r) {
			if (seg[node].cover > 0)
				return seg[node].e - seg[node].b;
			else
				return 0;
		} else {
			int mid = (seg[node].b + seg[node].e) >> 1;
			int leftnum = 0, rightnum = 0;
			if (l <= mid)
				leftnum = count(l, mid, node << 1);
			if (r >= mid)
				rightnum = count(mid, r, node << 1 + 1);
			return leftnum + rightnum;
		}
	}

	public int getNnode() {
		return Nnode;
	}

	public int getLastNode() {
		return LastNode;
	}

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int N;
		while (true) {
			System.out.printf("请输入区间长度:\n");
			N = in.nextInt();
			if (N == 0)
				break;
			SegmentTree st = new SegmentTree();
			st.build(1, N, 1);
			System.out.printf("线段树构造完成, 共有%d节点,最后一个节点是： %d\n", st.getNnode(),
					st.getLastNode());
			// 注意：节点个数总是2N-1
		}
		in.close();
	}
}
