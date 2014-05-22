package tree;

public class PointTree {
	final static int N = 64;// 表示可用空间为[0，N），其中N必须是2的幂数
	int a[] = new int[2 * N];
	int size = 0;

	// 插入一个数
	public void add(int n) {
		int i = N + n;
		this.size++;
		for (++a[i]; i > 1; i >>= 1) {
			if ((~i & 1) != 0)
				a[i >> 1]++;
		}
	}

	// 统计小于n的数的个数
	public int cntLs(int n) {
		int i = N + n;
		int count = 0;
		for (; i > 1; i >>= 1) {
			if ((i & 1) != 0)
				count += a[i >> 1];
		}
		return count;
	}

	// 统计大于n的数的个数
	public int cntGt(int n) {
		return size - a[N + n] - cntLs(n);
	}

	// 删除一个数
	public void del(int n) {
		int i = n + N;
		if (a[i] == 0)
			return;
		this.size--;
		for (--a[i]; n > 1; n >>= 1) {
			if ((~n & 1) != 0)
				a[n >> 1]--;
		}
	}

	public int nthLittleNum(int n) {
		int i = 1;
		while (i < N) {
			if (n < a[i])
				i *= 2;
			else {
				n -= a[i];
				i = i * 2 + 1;
			}
		}
		return i - N;
	}

	public static void main(String args[]) {
		PointTree t = new PointTree();
		for (int i = 0; i < 10; i++) {
			int n = (int) (Math.random() * 64);
			System.out.print(n + " ");
			t.add(n);
		}
		System.out.println();
		System.out.println(t.cntLs(18));
		System.out.println(t.nthLittleNum(5));
	}
}
