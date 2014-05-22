//17、输入一个正数 n，输出所有和为 n 连续正数序列。 
//例如输入 15，由于 1+2+3+4+5=4+5+6=7+8=15，所以输出 3 个连续序列 1-5、4-6 和 7-8。 
//方法一：
//可以发现任意自然数序列其实是公差为1的等差数列，假设从i开始的连续k个数的和为n，
//即[i , i+k-1]，则n=k*(2*i+k-1)/2，所以转化为一元二次方程为：k*k+(2*i-1)*k-2*n=0，
//解得k=(1-2*i+sqrt((2*i-1)*(2*i-1)+8*n))/2
//要满足k为整数，根号里面的肯定必须是平方数，且分子为偶数，否则k就是小数。

//方法二：
//我们知道：
//1+2 = 3；
//4+5 = 9；
//2+3+4 = 9。
//等式的左边都是两个以上连续的自然数相加，那么是不是所有的整数都可以写成这样的形式呢？
//稍微考虑一下，我们发现，4和8等数不能写成这样的形式。
//问题1：写一个程序，对于一个64位的正整数，输出它所有可能的连续自然数（两个以上）之和的算式。
//问题2：大家在测试上面的程序的过程中，肯定会注意到有一些数字不能表达为一系列连续的自然数之和，例如32好像就找不到。
//那么，这样的数字有什么规律呢？能否证明你的结论？
//问题3：在64位正整数范围内，子序列数目最多的数是哪一个？这个问题要用程序蛮力搜索，恐怕要运行很长时间，能够用数学知识推导出来？
//问题1解答：对于任意的正整数n >= 3（1和2均不能写成连续的自然数序列之和）。
//假设n能够写成自然数序列[seqStart, seqEnd]之和，
//则有(seqEnd + seqStart)*(seqEnd - seqStart + 1) = 2*n。
//考虑左式是两个整数之积，想到对右边的2*n进行因数分解，不妨假定2*n = minFactor * maxFactor，则有
//seqEnd + seqStart = maxFactor           (1)
//seqEnd - seqStart = minFactor-1         (2)
//解方程组(1)(2)得：
//seqStart = (maxFactor - minFactor + 1) / 2
//seqEnd = (maxFactor + minFactor - 1) / 2
//因为maxFactor - minFactor与maxFactor + minFactor有相同的奇偶性，
//因此只需要判断maxFactor + minFactor的奇偶性即可，
//如果maxFactor + minFactor为奇数，那么seqStart和seqEnd不是分数，是整数，即这个序列存在。

package array;

public class plusSequence {
	public static void main(String[] args) {
		plusSequence1(15);
		plusSequence2(15);
	}

	// 输入一个正数 n，输出所有和为n 连续正数序列。
	static void plusSequence1(int n) {
		int i, j, k, m;
		double num;
		for (i = 1; i <= n / 2; ++i) {
			m = (2 * i - 1) * (2 * i - 1) + 8 * n;
			num = Math.sqrt(m * 1.0);
			if (num != (int) num)
				continue;
			k = 1 - 2 * i + (int) num;
			if (0 == (k & 1) && k > 0) {
				for (j = 0; j < k / 2; ++j)
					System.out.print(i + j);
				System.out.println();
			}
		}
	}

	static void plusSequence2(int n) {
		int i, minFactor, maxFactor, start, end;
		double sqrtn = Math.sqrt(2.0 * n);
		for (i = 2; i <= sqrtn; ++i) {
			if (2 * n % i == 0) {
				minFactor = i;
				maxFactor = 2 * n / i;
				if (((minFactor + maxFactor) & 1) == 1) // 奇数
				{
					start = (maxFactor - minFactor + 1) >> 1;
					end = (maxFactor + minFactor - 1) >> 1;
					System.out.println(start + "," + end);
				}
			}
		}
	}
}
