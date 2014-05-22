package algorithm;

//输入一个整数n，求从1到n这n个整数的十进制表示中1出现的次数。

public class NumberOf1_baseten {
	public static void main(String[] args) {
		int n = 12, result = 0;
		for (int i = 1; i <= n; i++) {
			result += numberOf1(i);
		}
		System.out.println(result);
	}

	static int numberOf1(int i) {
		int back = 0;
		while (i != 0) {
			if (i % 10 == 1)
				back++;
			i /= 10;
		}
		return back;
	}
}
