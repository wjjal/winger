package scrawl;
//描述：一年一度的百度之星又开始了，这次参赛人数创下了吉尼斯世界纪录，
//于是百度之星决定奖励一部分人：所有资格赛提交ID以x结尾的参赛选手将得到精美礼品一份。
//小小度同学非常想得到这份礼品，于是他就连续狂交了很多次，提交ID从a连续到b，
//他想问问你他能得到多少份礼品，你能帮帮他吗？

//输入
//第一行一个正整数T表示数据组数；
//接下去T行，每行三个正整数x，a，b (0 <=x <= 1018， 1 <= a，b <= 1018，a <= b)

//输出
//T行，每行为对应的数据情况下，小小度得到的礼品数

//样例输入
//1
//88888 88888 88888

//样例输出
//1

public class StarOfBaidu1 {
	public static void main(String[] args) {
		long a = 99999999;
		long b = 22222222;
		long x = 123;
		long digital = getDigitals(x);
		long left = findNumberOfGifts(b - 1, x, digital);
		long right = findNumberOfGifts(a, x, digital);
		System.out.println(right - left);
	}

	// 查找1~k区间以x为结尾的数字的个数
	static long findNumberOfGifts(long k, long x, long digital) {
		long m = k / digital;
		long n = k % digital;
		if (n >= x)
			return m + 1;
		else
			return m;
	}

	static long getDigitals(long x) {
		long digital = 1;
		while (x >= 10) {
			digital *= 10;
			x /= 10;
		}
		return digital;
	}
}
