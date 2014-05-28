package algorithm;

//给定m*n的矩阵，每个位置是一个整数，从左上角开始，每次只能朝右和下走，走到右下角，然后每次只能过朝左和上走，走回左上角，
//除了起点和终点外，每个格子只能走一次，求总和最大？

public class Maxgift {
	public static void main(String[] args) {

	}

	public int maxGift(int[][] matrix) {
		int m = matrix.length;
		if (m == 0)
			return 0;
		int n = matrix[0].length;
		int dp[][][] = new int[m + n - 1][m][m];
		dp[0][0][0] = matrix[0][0];
		for (int k = 1; k < m + n - 1; k++) {
			for (int i = 0; i < m && i <= k; i++) {
				for (int j = 0; j < n && j <= k; j++) {
					if (k != m + n - 2 && i == j)
						continue;
					if (i == 0)
						dp[k][i][j] = Math.max(dp[k - 1][i][j - 1],
								dp[k - 1][i][j])
								+ matrix[i][k - i]
								+ matrix[j][k - j];
					if (j == 0)
						dp[k][i][j] = Math.max(dp[k - 1][i - 1][j],
								dp[k - 1][i][j])
								+ matrix[i][k - i]
								+ matrix[j][k - j];
					else
						dp[k][i][j] = Math.max(Math.max(dp[k - 1][i - 1][j],
								dp[k - 1][i][j - 1]), Math.max(
								dp[k - 1][i - 1][j - 1], dp[k - 1][i][j]))
								+ matrix[i][k - i] + matrix[j][k - j];
				}
			}
		}
		return dp[m + n - 2][m - 1][m - 1];
	}
}
