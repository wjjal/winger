package algorithm;

//输入a1,a2,...,an,b1,b2,...,bn, 
//在O(n)的时间,O(1)的空间将这个序列顺序改为a1,b1,a2,b2,a3,b3,...,an,bn，
//且不需要移动，通过交换完成，只需一个交换空间。

public class SloveArraySwap {
	public static void main(String[] args) {
		int[] a = { 1, 3, 5, 7, 9, 2, 4, 6, 8, 10 };
		new SloveArraySwap().new Solution().arraySwap(a, 0, a.length - 1);
		for (int i = 0; i < a.length; i++){
			if (i == 0)
				System.out.print(a[i]);
			else
				System.out.print("," + a[i]);
		}
	}

	class Solution {
		public void arraySwap(int[] a, int start, int end) {
			if (end - start <= 1)
				return;
			int ls = start;
			int le = (start + end) >> 1;
			int rs = le + 1;
			int re = end;
			for (int i = (ls + le) / 2 + 1, j = rs; i <= le; i++, j++)
				swap(a, i, j);
			// 奇数个
			if (le != ls && (le - ls) % 2 == 0) {
				le++;
				rs--;
			}
			arraySwap(a, ls, le);
			arraySwap(a, rs, re);
		}

		private  void swap(int[] array, int i, int j) {
			int temp = array[i];
			array[i] = array[j];
			array[j] = temp;
		}
	}
}
