package scrawl;

//实现一个函数，对一个正整数n，算得到1需要的最少操作次数。
//操作规则为：如果n为偶数，将其除以2；如果n为奇数，可以加1或减1；一直处理下去。

public class Func1 {
	public static void main(String[] args) {
		System.out.println(func(7));
	}

	static int func(int n) {
		if (n == 1)
			return 0;
		if (n % 2 == 0)
			return 1 + func(n >> 1);
		int x = func(n - 1) + 1;
		int y = func(n + 1) + 1;
		if (x > y)
			return y;
		else
			return x;
	}
}
