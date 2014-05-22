package algorithm;

//全排列

public class CalcAllPermutation {
	private static int total;

	public static void main(String[] args) {
		char[] perm1 = { 'a', 'b', 'c', 'd' };
		callAllPermutation1(perm1);
		System.out.println("total:" + total);
		total = 0;
		char[] perm2 = { 'a', 'b', 'c', 'd' };
		//callAllPermutation2(perm2, 0, perm2.length - 1);
		//System.out.println("total:" + total);
	}

	//STL模板算法，非递归
	static void callAllPermutation1(char[] perm) {
		int len = perm.length;
		if (perm.length < 0)
			return;
		for (char c : perm)
			System.out.print(c);
		System.out.println();
		total++;
		while (true) {
			int i = 0, j = 0;
			for (i = len - 2; i >= 0; i--)
				if (perm[i] < perm[i + 1])
					break;
			if (i < 0)
				break;
			for (j = len - 1; j >= 0; j--)
				if (perm[j] > perm[i])
					break;
			swap(perm, i, j);
			reverse(perm, i + 1);
			for (char c : perm)
				System.out.print(c);
			System.out.println();
			total++;
			int a = 0;
		}
	}
	
	// 递归
	static void callAllPermutation2(char[] perm, int i, int n) {
		if (i == n) {
			for (char c : perm) {
				System.out.print(c);
			}
			total++;
			System.out.println();
		} else {
			for (int j = i; j <= n; j++) {
				swap(perm, i, j);
				callAllPermutation2(perm, i + 1, n);
				swap(perm, i, j);
			}
		}

	}

	private static void reverse(char[] data, int k) {
		char temp;
		int len = data.length, i = 0;
		while (k + i < len - 1 - i) {
			swap(data, k + i, len - 1 - i);
			i++;
		}
	}

	private static void swap(char[] data, int i, int j) {
		char temp = data[i];
		data[i] = data[j];
		data[j] = temp;
	}
}
