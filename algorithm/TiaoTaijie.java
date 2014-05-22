package algorithm;

//一个台阶总共有n级，如果一次可以跳1级，也可以跳2级,求总共有多少总跳法

public class TiaoTaijie {
	public static void main(String[] args) {
		int n = 10;
		System.out.println(jump1(n));
		System.out.println(jump2(n));
	}

	// 递归
	static int jump1(int n) {
		assert (n > 0);
		if (n == 1 || n == 2)
			return n;
		return jump1(n - 1) + jump1(n - 2);
	}

	// 迭代
	static int jump2(int n) {
		assert (n > 0);
		if (n == 1 || n == 2)
			return n;
		int result = 0, a = 1, b = 2;
		for (int i = 3; i <= n; i++) {
			result = a + b;
			a = b;
			b = result;
		}
		return result;
	}
}
