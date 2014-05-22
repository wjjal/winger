package scrawl;

//给定函数d(n)=n+n的各位之和，n为正整数，如d(78)=78+7+8=93。
//这样这个函数可以看成一个生成器，如93可以看成由78生成。
//定义数A：数A找不到一个数B可以由d(B)=A，即A不能由其他数生成。
//现在要写程序，找出1至10000里的所有符合数A定义的数。

public class Func2 {
	public static void main(String[] args) {
		boolean array[] = new boolean[10001];
		func(array);
		for (int i = 1; i <= 10000; i++) {
			if (!array[i])
				System.out.println(i + " ");
		}
	}

	static void func(boolean[] a) {
		for (int i = 1; i <= 10000; i++) {
			int temp = i;
			int sum = i;
			while (true) {
				sum += temp % 10;
				temp /= 10;
				if (temp == 0)
					break;
			}
			if (sum < 10000)
				a[sum] = true;
		}
	}
}
