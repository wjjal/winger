package algorithm;

//输入a1,a2,...,an,b1,b2,...,bn, 
//在O(n)的时间,O(1)的空间将这个序列顺序改为a1,b1,a2,b2,a3,b3,...,an,bn，
//且不需要移动，通过交换完成，只需一个交换空间。

public class SloveArraySwap {
	public static void main(String[] args) {
		int[] array = { 1,3,5,7,9,2,4,6,8,10 };
		arraySwap(array, 0, array.length - 1);
		for (int i : array) {
			System.out.print(i + " ");
		}
	}

	private static void arraySwap(int[] array, int start, int end) {
		if (end - start <=1)
			return;
		int ls = start;
		int le = (start + end) >> 1;
		int rs = le + 1;
		int re = end;
		for (int i = (ls + le) / 2 + 1, j = rs; i <= le; i++, j++)
			swap(array, i, j);
		// 奇数个
		if (le != ls && (le - ls) % 2 == 0) {
			le++;
			rs--;
		}
		arraySwap(array, ls, le);
		arraySwap(array, rs, re);
	}

	private static void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
}
