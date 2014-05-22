//11、整数的拆分问题
//如，对于正整数n=6，可以拆分为：
//6
//5+1
//4+2, 4+1+1
//3+3, 3+2+1, 3+1+1+1
//2+2+2, 2+2+1+1, 2+1+1+1+1
//1+1+1+1+1+1+1
//现在的问题是，对于给定的正整数n，程序输出该整数的拆分种类数(HDOJ  1028)。
//DP思路：
//n = n1 + n2 + n3 + n4 + .... + nk
//状态表示：将n划分为k个数相加的组合方案数记为 q(n,k)。（相当于将n个苹果放入k个盘子）
//状态转移：
//(1)若k>n，则盘子数大于苹果数，至少有k-n个空盘子，可以将其拿掉，对组合方案数无影响。
//q(n,k) = q(n,n)
//(2)若k<=n，则盘子数小于等于苹果数，则分为两种情况
//1.至少有一个盘子空着：q(n,k) = q(n,k-1)
//2.所有盘子都不空：q(n,k) = q(n-k,k)
//q(n,k) = q(n,k-1) + q(n-k,k)

package array;

public class IntegerSplit {
	static int dp[][] = new int[121][121];

	public static void main(String[] args) {
		int n = 5;
		solution1();
		System.out.println(dp[n][n]);
		System.out.println(solution2(n, n));
	}

	// DP
	static void solution1() {
		int n, i, j;
		for (i = 1; i < 121; ++i) {
			for (j = 1; j < 121; ++j) {
				if (i == 1 || j == 1)
					dp[i][j] = 1;
				else if (i > j)
					dp[i][j] = dp[i][j - 1] + dp[i - j][j];
				else if (i == j)
					dp[i][j] = dp[i][j - 1] + 1;
				else
					dp[i][j] = dp[i][i];
			}
		}
	}

	// 递归
	static int solution2(int n, int m) {
		if (n < 1 || m < 1)
			return 0;
		if (n == 1 || m == 1)
			return 1;
		if (n < m)
			return solution2(n, n);
		if (n == m)
			return solution2(n, m - 1) + 1;
		return solution2(n, m - 1) + solution2(n - m, m);
	}
}
