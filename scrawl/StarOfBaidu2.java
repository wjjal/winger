package scrawl;

//描述：度度熊拥有一个自己的Baidu空间，度度熊时不时会给空间朋友赠送礼物，以增加度度熊与朋友之间的友谊值。度度熊在偶然的机会下得到了两种超级礼物，于是决定给每位朋友赠送一件超级礼物。不同类型的朋友在收到不同的礼物所能达到的开心值是不一样的。开心值衡量标准是这样的：每种超级礼物都拥有两个属性(A, B)，每个朋友也有两种属性(X, Y)，如果该朋友收到这个超级礼物，则这个朋友得到的开心值为A*X + B*Y。
//由于拥有超级礼物的个数限制，度度熊很好奇如何分配这些超级礼物，才能使好友的开心值总和最大呢？

//输入
//第一行n表示度度熊的好友个数。
//接下来n行每行两个整数表示度度熊好朋友的两种属性值Xi, Yi。
//接下来2行，每行三个整数ki, Ai, Bi，表示度度熊拥有第i种超级礼物的个数以及两个属性值。
//1<=n<=1000, 0<=Xi,Yi, Ai, Bi 1+k2>=n

//输出
//输出一行一个值表示好友开心值总和的最大值

//样例输入
//4
//3 6
//7 4
//1 5
//2 4
//3 3 4
//3 4 3

//样例输出
//118

public class StarOfBaidu2 {
	public static void main(String[] args) {
		int n = 4;
		int[][] friend = { { 0, 0 }, { 3, 6 }, { 7, 4 }, { 1, 5 }, { 2, 4 } };
		int[][] gift = { { 3, 3, 4 }, { 3, 4, 3 } };
		System.out.println(findMaxHappy(n, friend, gift));
	}

	static int findMaxHappy(int n, int[][] friend, int[][] gift) {
		int max = 0;
		int g1_num = gift[0][0];
		int g2_num = gift[1][0];
		int dp[][] = new int[n + 1][g1_num+1];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= g1_num && j <= i; j++) {
				//总礼物数少于人数
				if (i - j > g2_num)
					continue;
				//拿第一个礼物
				int c1 = friend[i][0] * gift[0][1] + friend[i][1] * gift[0][2];
				//拿第二个礼物
				int c2 = friend[i][0] * gift[1][1] + friend[i][1] * gift[1][2];
				if (j > 0)
					dp[i][j] = dp[i - 1][j - 1] + c1;
				dp[i][j] = Math.max(dp[i][j], dp[i - 1][j] + c2);
			}
		}
		for(int i =0;i<=g1_num;i++){
			max = Math.max(max, dp[n][i]);
		}
		return max;
	}
}
