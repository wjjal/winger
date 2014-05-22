package scrawl;

//8*8的棋盘上面放着64个不同价值的礼物，
//每个小的棋盘上面放置一个礼物（礼物的价值大于0），
//一个人初始位置在棋盘的左上角，每次他只能向下或向右移动一步，
//并拿走对应棋盘上的礼物，结束位置在棋盘的右下角，
//请设计一个算法使其能够获得最大价值的礼物。

public class Func8 {
	public static void main(String[] args) {
		int value[][] = new int[8][8];
		findmaxValue(value);
	}

	private static int findmaxValue(int[][] value) {
		int dp[][] = new int[8][8];
		dp[0][0] = value[0][0];
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				if(i==0&&j==0)
					break;
				if(i==0)
					dp[i][j] = dp[i][j-1]+value[i][j];
				if(j==0)
					dp[i][j] = dp[i-1][j]+value[i][j];
				dp[i][j]=Math.max(dp[i-1][j], dp[i][j-1])+value[i][j];
			}
		}
		return dp[7][7];
	}
}
